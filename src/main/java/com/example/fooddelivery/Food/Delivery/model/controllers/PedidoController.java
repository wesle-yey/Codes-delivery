package com.example.fooddelivery.Food.Delivery.model.controllers;

import com.example.fooddelivery.Food.Delivery.model.classes.Pedido;
import com.example.fooddelivery.Food.Delivery.model.repositories.PedidoRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> novoPedido(@RequestParam Long idRestaurante, @RequestParam Long idCliente) {
        Pedido meuPedido= new Pedido(idRestaurante, idCliente);
        Pedido novoPedido = pedidoRepository.save(meuPedido);
        return ResponseEntity.ok(novoPedido);
    }

    @PatchMapping("/aceitar/{id}")
    public HttpStatusCode aceitarPedido(@PathVariable Long id, @RequestParam Long idRestaurante) {
        try {
            Optional<Pedido> meuPedido= pedidoRepository.findById(id);
            if (meuPedido.isPresent()) {
                meuPedido.get().aceitarPedido(idRestaurante);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return HttpStatusCode.valueOf(200);
    }

    @PatchMapping("/recusar/{id}")
    public HttpStatusCode recusarPedido(@PathVariable Long id, @RequestParam Long idRestaurante) {
        try {
            Optional<Pedido> meuPedido= pedidoRepository.findById(id);
            if (meuPedido.isPresent()) {
                meuPedido.get().recusarPedido(idRestaurante);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return HttpStatusCode.valueOf(200);
    }

    @PatchMapping("/entregar/{id}")
    public HttpStatusCode entregarPedido(@PathVariable Long id, @RequestParam Long idRestaurante) {
        try {
            Optional<Pedido> meuPedido= pedidoRepository.findById(id);
            if (meuPedido.isPresent()) {
                meuPedido.get().entregarPedido(idRestaurante);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()).getStatusCode();
        }
        return HttpStatusCode.valueOf(200);
    }

    @GetMapping
    public Optional<List<Pedido>>getPedidos() {
        return Optional.of(pedidoRepository.findAll());
    }

    @GetMapping
    public Optional<Pedido> getPedido(@RequestParam Long id) {
        try {
            return pedidoRepository.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    }

