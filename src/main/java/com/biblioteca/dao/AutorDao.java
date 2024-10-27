package com.biblioteca.dao;

import com.biblioteca.model.AutorModel;
import com.biblioteca.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class AutorDao implements BaseCRUDDao {
    @Override
    public boolean inserir(Model model) {
        AutorModel autorModel = (AutorModel) model;
        String sql = "INSERT INTO autor (nome) VALUES (?)";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, autorModel.getNome());
                statement.executeUpdate();

                statement.close();
                conexao.close();

                return true;
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return false;
    }

    @Override
    public boolean deletarPorId(int id) {
        String sql = "DELETE FROM autor WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();

                statement.close();
                conexao.close();

                return true;
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return false;
    }

    @Override
    public boolean atualizarPorId(int id, Model novoModel) {
        AutorModel autorModel = (AutorModel) novoModel;
        String sql = "UPDATE autor SET nome = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, autorModel.getNome());
                statement.setInt(2, id);
                statement.executeUpdate();

                statement.close();
                conexao.close();

                return true;
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return false;
    }

    @Override
    public List<AutorModel> consultarTodos() {
        LinkedList<AutorModel> autores = new LinkedList<>();
        String sql = "SELECT * FROM autor";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    autores.add(new AutorModel(resultado.getInt("id"), resultado.getString("nome")));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return autores;
    }

    @Override
    public Model consultarPorId(int id) {
        AutorModel autor = new AutorModel();
        String sql = "SELECT * FROM autor WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    autor = new AutorModel(resultado.getInt("id"),
                            resultado.getString("nome"));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return autor;
    }
}
