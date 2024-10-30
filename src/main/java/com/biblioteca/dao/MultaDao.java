package com.biblioteca.dao;

import com.biblioteca.model.Model;
import com.biblioteca.model.MultaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MultaDao implements BaseCRUDDao {
    @Override
    public boolean inserir(Model model) {
        MultaModel multaModel = (MultaModel) model;
        String sql = "INSERT INTO multas (id_aluguel, valor, pago) VALUES (?, ?, ?)";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, multaModel.getIdAluguel());
                statement.setDouble(2, multaModel.getValor());
                statement.setBoolean(3, multaModel.isPago());
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
        String sql = "DELETE FROM multas WHERE id = ?";

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
        MultaModel multaModel = (MultaModel) novoModel;
        String sql = "UPDATE multas SET id_aluguel = ?, valor = ?, pago = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, multaModel.getIdAluguel());
                statement.setDouble(2, multaModel.getValor());
                statement.setBoolean(3, multaModel.isPago());
                statement.setInt(4, id);
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
    public List<MultaModel> consultarTodos() {
        LinkedList<MultaModel> multa = new LinkedList<>();
        String sql = "SELECT * FROM multas";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    multa.add(new MultaModel(resultado.getInt("id"),
                            resultado.getInt("id_aluguel"),
                            resultado.getDouble("valor"),
                            resultado.getBoolean("pago")));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return multa;
    }

    @Override
    public Model consultarPorId(int id) {
        MultaModel multa = new MultaModel();
        String sql = "SELECT * FROM multas WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    multa = new MultaModel(resultado.getInt("id"),
                            resultado.getInt("id_aluguel"),
                            resultado.getDouble("valor"),
                            resultado.getBoolean("pago"));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return multa;
    }
}
