package com.biblioteca.model;

public class AutorModel implements Model {
    private int id;
    private String nome;

    public AutorModel() {}

    public AutorModel(String nome) {
        this.nome = nome;
    }

    public AutorModel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
