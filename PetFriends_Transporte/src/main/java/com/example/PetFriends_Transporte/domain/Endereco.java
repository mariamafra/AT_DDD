package com.example.PetFriends_Transporte.domain;

import lombok.Data;

@Data
public class Endereco {
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String rua, String numero, String cidade, String estado, String cep) {
        if (rua == null || rua.length() > 255) {
            throw new IllegalArgumentException("Rua não pode ser nula ou exceder 255 caracteres.");
        }
        if (numero == null || cidade == null || estado == null ) {
            throw new IllegalArgumentException("Número/Cidade/Estado não pode ser nulo.");
        }
        if (cep == null || !cep.matches("\\d{6}")) {
            throw new IllegalArgumentException("CEP deve ter exatamente 6 números.");
        }

        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    /*EQUALSSSSSSSSSSSSSSS*/

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
