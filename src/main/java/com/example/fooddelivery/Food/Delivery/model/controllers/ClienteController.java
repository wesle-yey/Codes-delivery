package com.example.fooddelivery.Food.Delivery.model.controllers;

import com.example.fooddelivery.Food.Delivery.model.classes.Cliente;
import com.example.fooddelivery.Food.Delivery.model.repositories.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return ResponseEntity.of(cliente);
    }

    @PostMapping
    public ResponseEntity<?> addCliente(
            @RequestBody(required = false) Cliente clienteBody,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        Cliente cliente = clienteBody;
        if (cliente == null || cliente.getName() == null) {
            if (name == null || email == null) {
                return ResponseEntity.badRequest().body("Nome e e-mail são obrigatórios.");
            }
            cliente = new Cliente(name, email);
        }

        if (cliente.getEmail() != null && clienteRepository.existsByEmail(cliente.getEmail())) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado.");
        }

        Cliente novoCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
}
