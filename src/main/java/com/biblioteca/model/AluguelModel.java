package com.biblioteca.model;

import java.sql.Date;

public class AluguelModel implements Model {
    private int id;
    private int idCliente;
    private int idLivro;
    private int idEstadoAluguel;
    private Date dataAluguel;
    private Date dataDevolucao;
    private int renovacoes;

    public AluguelModel() {}

    public AluguelModel(int idCliente, int idLivro, int idEstadoAluguel, Date dataAluguel, Date dataDevolucao, int renovacoes) {
        this.idCliente = idCliente;
        this.idLivro = idLivro;
        this.idEstadoAluguel = idEstadoAluguel;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
        this.renovacoes = renovacoes;
    }

    public AluguelModel(int id, int idCliente, int idLivro, int idEstadoAluguel, Date dataAluguel, Date dataDevolucao, int renovacoes) {
        this.id = id;
        this.idCliente = idCliente;
        this.idLivro = idLivro;
        this.idEstadoAluguel = idEstadoAluguel;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
        this.renovacoes = renovacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdEstadoAluguel() {
        return idEstadoAluguel;
    }

    public void setIdEstadoAluguel(int idEstadoAluguel) {
        this.idEstadoAluguel = idEstadoAluguel;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getRenovacoes() {
        return renovacoes;
    }

    public void setRenovacoes(int renovacoes) {
        this.renovacoes = renovacoes;
    }
}
