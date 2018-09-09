package com.quebecambiental.controledefrota.negocio;

public class Motorista {
    String nome;
    int codigo;
    String numeroCelular;
    String rfId;

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public String getRfId() {
        return rfId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
