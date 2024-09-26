package com.example.PetFriends_Almoxarifado.infra.service;

import com.example.PetFriends_Almoxarifado.domain.ItemOrdemDeServico;
import com.example.PetFriends_Almoxarifado.domain.OrdemDeServico;
import com.example.PetFriends_Almoxarifado.events.EstadoOSMudou;
import com.example.PetFriends_Almoxarifado.infra.client.Pedido;
import com.example.PetFriends_Almoxarifado.infra.repository.OrdemDeServicoRepository;
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
public class OrdemDeServicoService {

    private static final Logger LOG = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private PubSubTemplate pubSubTemplate;
    @Autowired
    JacksonPubSubMessageConverter converter;
    @Autowired
    private OrdemDeServicoRepository repository;
    @Autowired
    private PedidoService pedidoService;

    public OrdemDeServico obterPorId(long id) {
        return repository.getReferenceById(id);
    }

    public OrdemDeServico finalizar(long id) {
        OrdemDeServico os = this.obterPorId(id);
        os.finalizar();
        os = repository.save(os);

        this.enviar(new EstadoOSMudou(os.getPedidoId(), "EM_TRANSITO"));

        return os;
    }

    public OrdemDeServico save(OrdemDeServico ordemDeServico) {
        try {
            return repository.save(ordemDeServico);
        } catch (Exception e) {
            LOG.error("**** ERRO **** " + e.getMessage());
        }
        return null;
    }

    private void criarOS(Pedido pedido) {
        OrdemDeServico os = new OrdemDeServico(pedido.getID());
        this.save(os);
        for(ItemOrdemDeServico item : pedido.getItemList()) {
            try {
                os.adicionarItem(item.getProductId(), item.getQuantity());
            } catch (Exception e) {
                LOG.error("**** ERRO **** " + e.getMessage());
            }
        }
        this.save(os);
    }

    private void enviar(EstadoOSMudou estado) {
        pubSubTemplate.setMessageConverter(converter);
        pubSubTemplate.publish("teste-dr4", estado);
        LOG.info("***** Mensagem Publicada ---> " + estado);
    }

    public void processarEvento(EstadoOSMudou evento) {
        if(Objects.equals(evento.getEstado(), "FECHADO")) {
            Pedido pedido = pedidoService.getPedidoById(evento.getIdPedido());
            try {
                this.criarOS(pedido);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.enviar(new EstadoOSMudou(evento.getIdPedido(), "EM_PREPARACAO"));
        }
    }

    @ServiceActivator(inputChannel = "inputMessageChannel")
    private void receber(EstadoOSMudou payload,
                         @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) ConvertedBasicAcknowledgeablePubsubMessage<EstadoOSMudou> message) {

        LOG.info("***** Mensagem Recebida ---> " + payload);
        message.ack();
        this.processarEvento(payload);
    }
}
