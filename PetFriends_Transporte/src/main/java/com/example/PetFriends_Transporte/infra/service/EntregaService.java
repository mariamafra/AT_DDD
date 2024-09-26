package com.example.PetFriends_Transporte.infra.service;

import com.example.PetFriends_Transporte.domain.Endereco;
import com.example.PetFriends_Transporte.domain.Entrega;
import com.example.PetFriends_Transporte.events.EstadoEntregaMudou;
import com.example.PetFriends_Transporte.infra.client.Pedido;
import com.example.PetFriends_Transporte.infra.client.PedidoClient;
import com.example.PetFriends_Transporte.infra.repository.EntregaRepository;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.cloud.spring.pubsub.support.converter.ConvertedBasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EntregaService {

    private static final Logger LOG = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private PubSubTemplate pubSubTemplate;
    @Autowired
    JacksonPubSubMessageConverter converter;
    @Autowired
    private EntregaRepository repository;
    @Autowired
    private PedidoService pedidoService;

    public Entrega obterPorId(long id) {
        return repository.getReferenceById(id);
    }

    public Entrega finalizar(long id) {
        Entrega entrega = this.obterPorId(id);
        entrega.finalizar();
        entrega = repository.save(entrega);

        this.enviar(new EstadoEntregaMudou(entrega.getPedidoId(), "ENTREGUE"));

        return entrega;
    }

    private void criarRemessa(Pedido pedido) {
        try {
            Entrega entrega = new Entrega(pedido.getID(), new Endereco("Rua Martinaria", "20", "Niteroi", "Rio de Janeiro", "252525"));
            repository.save(entrega);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void enviar(EstadoEntregaMudou estado) {
        pubSubTemplate.setMessageConverter(converter);
        pubSubTemplate.publish("teste-dr4", estado);
        LOG.info("***** Mensagem Publicada ---> " + estado);
    }

    public void processarEvento(EstadoEntregaMudou evento) {
        if(Objects.equals(evento.getEstado(), "EM_TRANSITO")) {
            Pedido pedido = pedidoService.getPedidoById(evento.getIdPedido());
            try {
                this.criarRemessa(pedido);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.enviar(new EstadoEntregaMudou(evento.getIdPedido(), "SAIU_PARA_ENTREGA"));
        }
    }

    @ServiceActivator(inputChannel = "inputMessageChannel")
    private void receber(EstadoEntregaMudou payload,
                         @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) ConvertedBasicAcknowledgeablePubsubMessage<EstadoEntregaMudou> message) {

        LOG.info("***** Mensagem Recebida ---> " + payload);
        message.ack();
        this.processarEvento(payload);
    }
}
