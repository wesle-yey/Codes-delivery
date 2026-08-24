package com.example.fooddelivery.Food.Delivery.model.repositories;

import com.example.fooddelivery.Food.Delivery.model.classes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);
}
