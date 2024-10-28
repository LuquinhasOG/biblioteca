package com.biblioteca.model;

public class MultaModel implements Model {
    private int id;
    private int idAluguel;
    private double valor;
    private boolean pago;

    public MultaModel() {}

    public MultaModel(int idAluguel, double valor, boolean pago) {
        this.idAluguel = idAluguel;
        this.valor = valor;
        this.pago = pago;
    }

    public MultaModel(int id, int idAluguel, double valor, boolean pago) {
        this.id = id;
        this.idAluguel = idAluguel;
        this.valor = valor;
        this.pago = pago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
