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

    public void aceitarPedido() {
        setPedidoStatus(PedidoStatusEnum.ACEITO);
    }

    public void recusarPedido() {
        setPedidoStatus(PedidoStatusEnum.RECUSADO);
    }

    public void entregarPedido() throws Exception {
        if (getPedidoStatus() == PedidoStatusEnum.RECUSADO) {
            throw new Exception("Pedidos recusados não podem ser entregues.");
        } else {
            setPedidoStatus(PedidoStatusEnum.ENTREGUE);
        }
    }

    public void avaliarPedido(PedidoNotaEnum pedidoNota) throws Exception {
        if (getPedidoStatus() != PedidoStatusEnum.ENTREGUE) {
            throw new Exception("Apenas pedidos entregues podem ser avaliados.");
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
