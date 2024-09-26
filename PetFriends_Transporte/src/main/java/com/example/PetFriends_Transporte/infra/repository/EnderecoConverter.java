package com.example.PetFriends_Transporte.infra.repository;

import com.example.PetFriends_Transporte.domain.Endereco;
import jakarta.persistence.AttributeConverter;

public class EnderecoConverter implements AttributeConverter<Endereco, String> {
    @Override
    public String convertToDatabaseColumn(Endereco e) {
        if (e == null)
            return null;
        return String.join(",", e.getRua(), e.getNumero(), e.getCidade(), e.getEstado(), e.getCep());
    }

    @Override
    public Endereco convertToEntityAttribute(String e) {
        if (e == null || e.isEmpty()) {
            return null;
        }
        String[] data = e.split(",");
        return new Endereco(data[0], data[1], data[2], data[3], data[4]);
    }
}
