package com.example.PetFriends_Almoxarifado.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class Prioridade {
    private final String nivel;

    public Prioridade(String nivel) {
        if (nivel == null || nivel.trim().isEmpty()) {
            throw new IllegalArgumentException("A prioridade não pode ser nula ou vazia.");
        }
        if (!nivel.matches("Alta|Média|Baixa")) {
            throw new IllegalArgumentException("Prioridade inválida.");
        }
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        final Prioridade outra = (Prioridade) o;
        return Objects.equals(this.nivel, outra.nivel);
    }

    @Override
    public String toString() {
        return nivel;
    }
}
