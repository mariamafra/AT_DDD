package com.example.PetFriends_Almoxarifado.events;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
public class EstadoOSMudou implements Serializable {

    private Long idPedido;
    private String estado;
    private Date momento;

    public EstadoOSMudou() {
    }

    public EstadoOSMudou(Long idPedido, String estado) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.momento = new Date();
    }

    public EstadoOSMudou(Long idPedido, String estado, Date momento) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.momento = momento;
    }

    @Override
    public String toString() {
        return "EstadoOSMudou{" + "idPedido=" + idPedido + ", estado=" + estado + ", momento=" + momento + '}';
    }
}
