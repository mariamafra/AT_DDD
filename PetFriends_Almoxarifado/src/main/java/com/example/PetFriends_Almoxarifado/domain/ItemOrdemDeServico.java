package com.example.PetFriends_Almoxarifado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrdemDeServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private Long productId;
    private Integer quantity;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ordem_de_servico_id", referencedColumnName = "ID")
    private OrdemDeServico ordemDeServicoId;

    @Override
    public String toString() {
        return "ItemOrdemServico{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", ordemId=" + ordemDeServicoId +
                '}';
    }
}
