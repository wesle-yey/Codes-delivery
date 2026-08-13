package com.example.fooddelivery.Food.Delivery.model.Controller;

import com.example.fooddelivery.Food.Delivery.model.Class.Restaurante;
import com.example.fooddelivery.Food.Delivery.model.Repository.RestauranteRepository;
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/restaurantes")
public class RestauranteController {
    private final RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestaurante(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        return ResponseEntity.of(restaurante);
    }

    @PostMapping
    public ResponseEntity<Restaurante> addRestaurante(
            @RequestBody(required = false) Restaurante restauranteBody,
            @RequestParam(required = false) String name) {
        Restaurante restaurante = restauranteBody;
        if (restaurante == null || restaurante.getName() == null) {
            if (name == null) {
                return ResponseEntity.badRequest().build();
            }
            restaurante = new Restaurante(name);
        }
        Restaurante novoRestaurante = restauranteRepository.save(restaurante);
        return ResponseEntity.ok(novoRestaurante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id) {
        if (restauranteRepository.existsById(id)) {
            restauranteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Restaurante> getAllRestaurantes() {
        return restauranteRepository.findAll();
    }
}
