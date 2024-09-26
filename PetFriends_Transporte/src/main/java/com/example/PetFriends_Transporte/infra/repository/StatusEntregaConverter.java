package com.example.PetFriends_Transporte.infra.repository;

import com.example.PetFriends_Transporte.domain.StatusEntrega;
import jakarta.persistence.AttributeConverter;

public class StatusEntregaConverter implements AttributeConverter<StatusEntrega, String> {
    @Override
    public String convertToDatabaseColumn(StatusEntrega statusEntrega) {
        if (statusEntrega == null)
            return null;
        return statusEntrega.toString();
    }

    @Override
    public StatusEntrega convertToEntityAttribute(String statusEntrega) {
        return StatusEntrega.valueOf(statusEntrega);
    }
}
