package com.example.fooddelivery.Food.Delivery.model.Repository;

import com.example.fooddelivery.Food.Delivery.model.Class.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}