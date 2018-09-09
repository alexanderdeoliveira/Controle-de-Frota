package com.quebecambiental.controledefrota.negocio;

public class GrupoFrota {
    String nomeGrupoFrota;
    int codGrupo;

    public void setCodGrupo(int codGrupo) {
        this.codGrupo = codGrupo;
    }

    public int getCodGrupo() {
        return codGrupo;
    }

    public void setNomeGrupoFrota(String nomeGrupoFrota) {
        this.nomeGrupoFrota = nomeGrupoFrota;
    }

    public String getNomeGrupoFrota() {
        return nomeGrupoFrota;
    }
}
