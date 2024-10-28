package com.biblioteca.model;

public class EstadoAluguelModel implements Model {
    private int id;
    private String descricao;

    public EstadoAluguelModel() {}

    public EstadoAluguelModel(String descricao) {
        this.descricao = descricao;
    }

    public EstadoAluguelModel(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
