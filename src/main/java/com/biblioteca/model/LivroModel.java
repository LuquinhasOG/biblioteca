package com.biblioteca.model;

public class LivroModel implements Model {
    private int id;
    private String nome;
    private String isbn;
    private double precoAluguel;
    private String sinopse;
    private int idEditora;
    private int idAutor;
    private int quantidadeEstoque;
    private int quantidadeDisponivel;

    public LivroModel() {}

    public LivroModel(String nome, String isbn, double precoAluguel, String sinopse, int idEditora, int idAutor, int quantidadeEstoque, int quantidadeDisponivel) {
        this.nome = nome;
        this.isbn = isbn;
        this.precoAluguel = precoAluguel;
        this.sinopse = sinopse;
        this.idEditora = idEditora;
        this.idAutor = idAutor;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public LivroModel(int id, String nome, String isbn, double precoAluguel, String sinopse, int idEditora, int idAutor, int quantidadeEstoque, int quantidadeDisponivel) {
        this.id = id;
        this.nome = nome;
        this.isbn = isbn;
        this.precoAluguel = precoAluguel;
        this.sinopse = sinopse;
        this.idEditora = idEditora;
        this.idAutor = idAutor;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeDisponivel = quantidadeDisponivel;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrecoAluguel() {
        return precoAluguel;
    }

    public void setPrecoAluguel(double precoAluguel) {
        this.precoAluguel = precoAluguel;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(int idEditora) {
        this.idEditora = idEditora;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
}
