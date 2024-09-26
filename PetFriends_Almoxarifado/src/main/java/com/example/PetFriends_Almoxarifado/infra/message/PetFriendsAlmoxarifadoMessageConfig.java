package com.example.PetFriends_Almoxarifado.infra.message;

import com.example.PetFriends_Almoxarifado.events.EstadoOSMudou;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class PetFriendsAlmoxarifadoMessageConfig {
    @Bean
    public JacksonPubSubMessageConverter estadoMudouConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(EstadoOSMudou.class, new EstadoOrdemDeServicoMudouSerializer());
        simpleModule.addDeserializer(EstadoOSMudou.class, new EstadoOrdemDeServicoMudouDeserializer());
        objectMapper.registerModule(simpleModule);
        return new JacksonPubSubMessageConverter(objectMapper);
    }

    @Bean
    public MessageChannel inputMessageChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter inboundChannelAdapter(
            @Qualifier("inputMessageChannel") MessageChannel messageChannel, PubSubTemplate pubSubTemplate) {

        pubSubTemplate.setMessageConverter(estadoMudouConverter());
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "teste-al-sub");
        adapter.setOutputChannel(messageChannel);
        adapter.setAckMode(AckMode.MANUAL);
        adapter.setPayloadType(EstadoOSMudou.class);
        return adapter;
    }
}
