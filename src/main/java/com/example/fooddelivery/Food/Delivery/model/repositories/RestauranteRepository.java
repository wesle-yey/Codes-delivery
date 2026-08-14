package com.example.fooddelivery.Food.Delivery.model.repositories;

import com.example.fooddelivery.Food.Delivery.model.classes.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}