package com.biblioteca.dao;

import com.biblioteca.model.Model;

import java.util.List;

public interface BaseCRUDDao {
    boolean inserir(Model model);
    boolean deletarPorId(int id);
    boolean atualizarPorId(int id, Model novoModel);
    List<? extends Model> consultarTodos();
    Model consultarPorId(int id);
}
