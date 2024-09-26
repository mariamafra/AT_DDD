package com.example.PetFriends_Transporte.domain;

import com.example.PetFriends_Transporte.infra.repository.EnderecoConverter;
import com.example.PetFriends_Transporte.infra.repository.StatusEntregaConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
public class Entrega implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private Long pedidoId;
    @Convert(converter = StatusEntregaConverter.class)
    private StatusEntrega status;
    @Convert(converter = EnderecoConverter.class)
    private Endereco endereco;

    public Entrega() {
        this.status = StatusEntrega.EM_ANDAMENTO;
    }

    public Entrega(Long pedidoId, Endereco endereco) {
        this.pedidoId = pedidoId;
        this.endereco = endereco;
        this.status = StatusEntrega.EM_ANDAMENTO;
    }


    public void finalizar() {
        if (this.status == StatusEntrega.EM_ANDAMENTO) {
            this.status = StatusEntrega.FINALIZADA;
        } else {
            throw new IllegalStateException("Entrega precisa esta como 'EM_ANDAMENTO' para ser finalizada");
        }
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "id=" + id + ", pedidoId=" + pedidoId + ", PRIORIDADE=" + endereco + ", status=" + status +
                '}';
    }
}
