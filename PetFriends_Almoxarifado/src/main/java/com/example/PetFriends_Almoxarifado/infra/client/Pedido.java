package com.example.PetFriends_Almoxarifado.infra.client;

import com.example.PetFriends_Almoxarifado.domain.ItemOrdemDeServico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Long ID;
    private List<ItemOrdemDeServico> itemList;
}
