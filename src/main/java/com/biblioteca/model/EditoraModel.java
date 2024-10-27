package com.biblioteca.model;

public class EditoraModel implements Model {
    private int id;
    private String nome;

    public EditoraModel() {}

    public EditoraModel(String nome) {
        this.nome = nome;
    }

    public EditoraModel(int id) {
        this.id = id;
    }

    public EditoraModel(int id, String nome) {
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
