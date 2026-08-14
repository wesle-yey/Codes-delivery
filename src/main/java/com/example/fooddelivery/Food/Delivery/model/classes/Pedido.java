package com.example.fooddelivery.Food.Delivery.model.classes;

import com.example.fooddelivery.Food.Delivery.model.enums.PedidoNotaEnum;
import com.example.fooddelivery.Food.Delivery.model.enums.PedidoStatusEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
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
    private PedidoStatusEnum pedidoStatus= PedidoStatusEnum.CRIADO;
    private PedidoNotaEnum notaPedido;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Pedido(Long idRestaurante, Long idCliente) {
        this.idRestaurante = idRestaurante;
        this.idCliente = idCliente;
    }

    public Pedido() {

    }

    public void aceitarPedido(Long idRestaurante) throws Exception {
        if (getPedidoStatus() != PedidoStatusEnum.CRIADO) {
            throw new Exception("Apenas pedidos com status CRIADO podem ser aceitos ou recusados!");
        } else if (this.idRestaurante!= idRestaurante) {
            throw new Exception("Você só pode aceitar os pedidos do seu restaurante");

        } else {
        setPedidoStatus(PedidoStatusEnum.ACEITO);
        }
    }

    public void recusarPedido(Long idRestaurante) throws Exception {
        if (getPedidoStatus() != PedidoStatusEnum.CRIADO) {
            throw new Exception("Apenas pedidos com status CRIADO podem ser aceitos ou recusados!");
        } else if (this.idRestaurante!= idRestaurante) {
            throw new Exception("Você só pode recusar os pedidos do seu restaurante");
        } else {
            setPedidoStatus(PedidoStatusEnum.RECUSADO);
        }
    }

    public void entregarPedido(Long idRestaurante) throws Exception {
        if (getPedidoStatus() == PedidoStatusEnum.RECUSADO) {
            throw new Exception("Pedidos recusados não podem ser entregues.");
        } else if (this.idRestaurante != idRestaurante) {
            throw new Exception("Você só pode entregar os pedidos do seu restaurante.");
        }
        else if (getPedidoStatus() == PedidoStatusEnum.ACEITO) {
            setPedidoStatus(PedidoStatusEnum.ENTREGUE);
        } else {
            throw new Exception("Apenas pedidos com status ACEITO podem ser entregues");
        }
    }

    public void avaliarPedido(PedidoNotaEnum pedidoNota, Long idCliente) throws Exception {
        if (getPedidoStatus() != PedidoStatusEnum.ENTREGUE) {
            throw new Exception("Apenas pedidos entregues podem ser avaliados.");
        } else if (this.idCliente != idCliente) {
            throw new Exception("Você só pode avaliar os pedidos que você fez.");
        } else {
            setNotaPedido(pedidoNota);
        }
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
