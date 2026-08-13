package com.example.fooddelivery.Food.Delivery.model.Class;

import com.example.fooddelivery.Food.Delivery.model.Enum.PedidoNotaEnum;
import com.example.fooddelivery.Food.Delivery.model.Enum.PedidoStatusEnum;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idRestaurante;
    private Long idCliente;
    @Enumerated(EnumType.STRING)
    private PedidoStatusEnum pedidoStatus;
    private PedidoNotaEnum notaPedido;
    @CreatedDate
    private LocalDateTime createdAt;

    public Pedido(Long id, Long idRestaurante, Long idCliente) {
        this.id = id;
        this.idRestaurante = idRestaurante;
        this.idCliente = idCliente;
    }

    public Pedido() {

    }

    public PedidoStatusEnum getPedidoStatus() {
        return pedidoStatus;
    }

    public void setPedidoStatus(PedidoStatusEnum pedidoStatus) {
        this.pedidoStatus = pedidoStatus;
    }

    public PedidoNotaEnum getNotaPedido() {
        return notaPedido;
    }

    public void setNotaPedido(PedidoNotaEnum notaPedido) {
        this.notaPedido = notaPedido;
    }

    public Long getId() {
        return id;
    }

}
