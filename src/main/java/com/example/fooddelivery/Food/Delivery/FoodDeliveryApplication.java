package com.example.fooddelivery.Food.Delivery;

import com.example.fooddelivery.Food.Delivery.model.classes.Pedido;
import com.example.fooddelivery.Food.Delivery.model.enums.PedidoNotaEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApplication.class, args);
		Pedido pedido = new Pedido(1L,1L);
		pedido.setNotaPedido(PedidoNotaEnum.UM);
		System.out.println(pedido.getNotaPedido());
	}

}
