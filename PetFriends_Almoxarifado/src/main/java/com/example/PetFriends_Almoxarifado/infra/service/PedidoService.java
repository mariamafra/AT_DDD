package com.example.PetFriends_Almoxarifado.infra.service;

import com.example.PetFriends_Almoxarifado.infra.client.Pedido;
import com.example.PetFriends_Almoxarifado.infra.client.PedidoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
//@RequiredArgsConstructor
public class PedidoService {
    private static final Logger LOG = LoggerFactory.getLogger(PedidoService.class);
    @Autowired
    private PedidoClient pedidoClient;

    public Pedido getPedidoById(Long id) {
        try {
            return pedidoClient.getPedidoById(id);
        } catch (Exception e) {
            LOG.error("**** ERRO **** " + e.getMessage());
        }
        return null;
    }
}
