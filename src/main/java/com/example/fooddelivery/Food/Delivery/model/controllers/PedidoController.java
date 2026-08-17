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
    public ResponseEntity<?> aceitarPedido(@PathVariable Long id, @RequestParam Long idRestaurante) {
        try {
            Optional<Pedido> meuPedido= pedidoRepository.findById(id);
            if (meuPedido.isPresent()) {
                meuPedido.get().aceitarPedido(idRestaurante);
                pedidoRepository.save(meuPedido.get());
                return ResponseEntity.ok(meuPedido.get());
            } else {
                return ResponseEntity.status(404).body("Pedido não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());}
    }

    @PatchMapping("/recusar/{id}")
    public ResponseEntity<?> recusarPedido(@PathVariable Long id, @RequestParam Long idRestaurante) {
        try {
            Optional<Pedido> meuPedido = pedidoRepository.findById(id);
            if (meuPedido.isPresent()) {
                meuPedido.get().recusarPedido(idRestaurante);
                pedidoRepository.save(meuPedido.get());
                return ResponseEntity.ok(meuPedido.get());
            } else {
                return ResponseEntity.status(404).body("Pedido não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/entregar/{id}")
    public ResponseEntity<?> entregarPedido(@PathVariable Long id, @RequestParam Long idRestaurante) {
        try {
            Optional<Pedido> meuPedido= pedidoRepository.findById(id);
            if (meuPedido.isPresent()) {
                meuPedido.get().entregarPedido(idRestaurante);
                pedidoRepository.save(meuPedido.get());
                return ResponseEntity.ok(meuPedido.get());
            } else {
                return ResponseEntity.status(404).body("Pedido não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/avaliar/{id}")
    public ResponseEntity<?> avaliarPedido(@PathVariable Long id, @RequestParam Long idCliente, @RequestParam Integer notaPedido ) {
        try {
            Optional<Pedido> pedido= pedidoRepository.findById(id);
            if (pedido.isPresent()) {
                pedido.get().avaliarPedido(notaPedido, idCliente);
                pedidoRepository.save(pedido.get());
                return ResponseEntity.ok(pedido.get());
            } else {
                return ResponseEntity.status(404).body("Pedido não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public Optional<List<Pedido>>getPedidos() {
        return Optional.of(pedidoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPedido(@PathVariable Long id) {
        try {
            Optional<Pedido> meuPedido= pedidoRepository.findById(id);
            if (meuPedido.isPresent()) {
                return ResponseEntity.ok(meuPedido.get());
            } else {
                return ResponseEntity.status(404).body("Pedido não encontrado");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    }

