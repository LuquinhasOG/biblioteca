package com.biblioteca.dao;

import com.biblioteca.model.EditoraModel;
import com.biblioteca.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class EditoraDao implements BaseCRUDDao {
    @Override
    public boolean inserir(Model model) {
        EditoraModel clienteModel = (EditoraModel) model;
        String sql = "INSERT INTO editora (nome) VALUES (?)";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, clienteModel.getNome());
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
        String sql = "DELETE FROM editora WHERE id = ?";

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
        EditoraModel enderecoModel = (EditoraModel) novoModel;
        String sql = "UPDATE editora SET nome = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, enderecoModel.getNome());
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
    public List<EditoraModel> consultarTodos() {
        LinkedList<EditoraModel> editoras = new LinkedList<>();
        String sql = "SELECT * FROM editora";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    editoras.add(new EditoraModel(resultado.getInt("id"), resultado.getString("nome")));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return editoras;
    }

    @Override
    public Model consultarPorId(int id) {
        EditoraModel editora = new EditoraModel();
        String sql = "SELECT * FROM editora WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    editora = new EditoraModel(resultado.getInt("id"),
                            resultado.getString("nome"));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return editora;
    }
}
