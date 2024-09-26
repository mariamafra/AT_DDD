package com.example.PetFriends_Almoxarifado.infra.message;

import com.example.PetFriends_Almoxarifado.events.EstadoOSMudou;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class EstadoOrdemDeServicoMudouSerializer extends StdSerializer<EstadoOSMudou> {
    public EstadoOrdemDeServicoMudouSerializer() { super(EstadoOSMudou.class);}

    @Override
    public void serialize(EstadoOSMudou evento, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("idPedido", evento.getIdPedido());
        jgen.writeStringField("estado", evento.getEstado());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        String data = sdf.format(evento.getMomento());
        jgen.writeStringField("momento", data);
    }
}
