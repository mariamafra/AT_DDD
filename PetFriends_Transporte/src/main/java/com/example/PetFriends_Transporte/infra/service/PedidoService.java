package com.example.PetFriends_Transporte.infra.service;

import com.example.PetFriends_Transporte.infra.client.Pedido;
import com.example.PetFriends_Transporte.infra.client.PedidoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
