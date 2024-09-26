package com.example.PetFriends_Almoxarifado.domain;

import com.example.PetFriends_Almoxarifado.infra.repository.PrioridadeConverter;
import com.example.PetFriends_Almoxarifado.infra.repository.StatusOrdemDeServicoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class OrdemDeServico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Convert(converter = StatusOrdemDeServicoConverter.class)
    private StatusOrdemDeServico status;
    @Convert(converter = PrioridadeConverter.class)
    private Prioridade prioridade;
    private Long pedidoId;
    private LocalDateTime dataCriacao;
    @OneToMany(mappedBy = "ordemDeServicoId", cascade = CascadeType.ALL)
    private List<ItemOrdemDeServico> itens;

    public OrdemDeServico() {
        this.status = StatusOrdemDeServico.CRIADA;
        this.dataCriacao = LocalDateTime.now();
        this.prioridade = new Prioridade("Alta");
    }

    public OrdemDeServico(Long pedidoId) {
        this.pedidoId = pedidoId;
        this.status = StatusOrdemDeServico.CRIADA;
        this.dataCriacao = LocalDateTime.now();
        this.prioridade = new Prioridade("Alta");
    }

    public void adicionarItem(Long productId, int quantidade) {
        ItemOrdemDeServico itemOrdem = new ItemOrdemDeServico();
        itemOrdem.setOrdemDeServicoId(this);
        itemOrdem.setProductId(productId);
        itemOrdem.setQuantity(quantidade);
        if (this.itens == null) {
            this.itens = new ArrayList<>();
        }
        this.itens.add(itemOrdem);
    }


    public void finalizar() {
        if (this.status == StatusOrdemDeServico.CRIADA) {
            this.status = StatusOrdemDeServico.FINALIZADA;
        } else {
            throw new IllegalStateException("OS precisa esta como 'CRIADA' para ser finalizada");
        }
    }

    @Override
    public String toString() {
        return "OrdemDeServico{" +
                "id=" + id +
                ", pedidoId=" + pedidoId +
                ", PRIORIDADE=" + prioridade +
                ", status=" + status +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
