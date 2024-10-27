package com.biblioteca;

import com.biblioteca.dao.EnderecoDao;
import com.biblioteca.model.EnderecoModel;
import com.biblioteca.model.Model;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            EnderecoDao enderecoDao = new EnderecoDao();
            EnderecoModel endereco = new EnderecoModel("Rua Segunda", "Bairro Progresso", 75, "casa");

//            enderecoDao.inserir(endereco);
//            enderecoDao.deletarPorId();
//            enderecoDao.atualizarPorId(, endereco);
//            List<EnderecoModel> resultados = enderecoDao.consultarTodos();
//            for (EnderecoModel e : resultados) {
//                System.out.println("id: " + e.getId() + ", rua: " + e.getRua() + ", bairro: " + e.getBairro() + ", número: " + e.getNumero() + ", complemento: " + e.getComplemento());
//            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}