package com.example.PetFriends_Transporte.events;

import lombok.Data;

import java.util.Date;

@Data
public class EstadoEntregaMudou {

    private Long idPedido;
    private String estado;
    private Date momento;

    public EstadoEntregaMudou() {
    }

    public EstadoEntregaMudou(Long idPedido, String estado) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.momento = new Date();
    }

    public EstadoEntregaMudou(Long idPedido, String estado, Date momento) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.momento = momento;
    }

    @Override
    public String toString() {
        return "EstadoEntregaMudou{" + "idPedido=" + idPedido + ", estado=" + estado + ", momento=" + momento + '}';
    }
}
