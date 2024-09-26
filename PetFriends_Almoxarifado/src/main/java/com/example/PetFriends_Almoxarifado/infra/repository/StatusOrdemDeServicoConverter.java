package com.example.PetFriends_Almoxarifado.infra.repository;

import com.example.PetFriends_Almoxarifado.domain.StatusOrdemDeServico;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//@Converter(autoApply = true)
public class StatusOrdemDeServicoConverter implements AttributeConverter<StatusOrdemDeServico, String> {
    @Override
    public String convertToDatabaseColumn(StatusOrdemDeServico osStatus) {
        if (osStatus == null)
            return null;
        return osStatus.toString();
    }

    @Override
    public StatusOrdemDeServico convertToEntityAttribute(String osStatus) {
        return StatusOrdemDeServico.valueOf(osStatus);
    }
}
