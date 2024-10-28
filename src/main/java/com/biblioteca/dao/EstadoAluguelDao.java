package com.biblioteca.dao;

import com.biblioteca.model.EstadoAluguelModel;
import com.biblioteca.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class EstadoAluguelDao implements BaseCRUDDao {
    @Override
    public boolean inserir(Model model) {
        EstadoAluguelModel estadoAluguelModel = (EstadoAluguelModel) model;
        String sql = "INSERT INTO estado_aluguel (descricao) VALUES (?)";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, estadoAluguelModel.getDescricao());
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
        String sql = "DELETE FROM estado_aluguel WHERE id = ?";

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
        EstadoAluguelModel estadoAluguelModel = (EstadoAluguelModel) novoModel;
        String sql = "UPDATE estado_aluguel SET descricao = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, estadoAluguelModel.getDescricao());
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
    public List<EstadoAluguelModel> consultarTodos() {
        LinkedList<EstadoAluguelModel> estadoAlugueis = new LinkedList<>();
        String sql = "SELECT * FROM estado_aluguel";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    estadoAlugueis.add(new EstadoAluguelModel(resultado.getInt("id"), resultado.getString("descricao")));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return estadoAlugueis;
    }

    @Override
    public Model consultarPorId(int id) {
        EstadoAluguelModel estadoAluguel = new EstadoAluguelModel();
        String sql = "SELECT * FROM estado_aluguel WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    estadoAluguel = new EstadoAluguelModel(resultado.getInt("id"),
                            resultado.getString("descricao"));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return estadoAluguel;
    }
}
