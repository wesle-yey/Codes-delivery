package com.example.fooddelivery.Food.Delivery.model.Repository;

import com.example.fooddelivery.Food.Delivery.model.Class.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
