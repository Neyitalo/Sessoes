package com.example.graficos.Persistencia;

import java.io.Serializable;

public class Sessoes implements Serializable {
    private float idSessao;
    private float porcentagem;

    public Sessoes() {
    }

    public float getIdSessao() {
        return (float) idSessao;
    }

    public void setIdSessao() {
        this.idSessao = idSessao;
    }

    public float getPorcentagem() {
        return (float) porcentagem;
    }

    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

}
