package com.quebecambiental.controledefrota.negocio;

public class Veiculo {
    String placa;
    int codGrupo;
    int codVeiculo;

    public void setCodGrupo(int codGrupo) {
        this.codGrupo = codGrupo;
    }

    public void setCodVeiculo(int codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    public int getCodVeiculo() {
        return codVeiculo;
    }

    public int getCodGrupo() {
        return codGrupo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
