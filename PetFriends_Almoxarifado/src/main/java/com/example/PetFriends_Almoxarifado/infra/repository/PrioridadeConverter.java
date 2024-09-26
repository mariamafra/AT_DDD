package com.example.PetFriends_Almoxarifado.infra.repository;

import com.example.PetFriends_Almoxarifado.domain.Prioridade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//@Converter(autoApply = true)
public class PrioridadeConverter implements AttributeConverter<Prioridade, String> {
    @Override
    public String convertToDatabaseColumn(Prioridade p) {
        if (p == null)
            return null;
        return p.toString();
    }

    @Override
    public Prioridade convertToEntityAttribute(String p) {
        return new Prioridade(p);
    }
}
