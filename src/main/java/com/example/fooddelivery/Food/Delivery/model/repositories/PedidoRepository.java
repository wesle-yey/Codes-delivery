package com.example.fooddelivery.Food.Delivery.model.repositories;


import com.example.fooddelivery.Food.Delivery.model.classes.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}