package com.biblioteca;

import com.biblioteca.dao.LivroDao;
import com.biblioteca.model.LivroModel;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            LivroDao ld = new LivroDao();
            LivroModel l1 = new LivroModel("estrutura de dados", "1234567891234", 20d, "Livro sobre estrutura de dados", 1, 1, 5, 5);
            LivroModel l2 = new LivroModel("POO", "3216549876548", 40d, "Livro sobre POO", 1, 2, 7, 5);
            LivroModel l3 = new LivroModel("Inteligência Artificial", "6549873216545", 35d, "livro sobre inteligência artificial", 2, 3, 3, 0);

            ld.inserir(l1);
            ld.inserir(l2);
            ld.inserir(l3);

            List<LivroModel> livros = ld.consultarTodos();
            for (LivroModel l : livros) {
                System.out.println("nome: " + l.getNome() + ", isbn: " + l.getIsbn() + ", disponível: " + l.getQuantidadeDisponivel());
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}