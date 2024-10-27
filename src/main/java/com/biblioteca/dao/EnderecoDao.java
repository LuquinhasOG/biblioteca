package com.biblioteca.dao;

import com.biblioteca.model.EnderecoModel;
import com.biblioteca.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class EnderecoDao implements BaseCRUDDao {
    @Override
    public boolean inserir(Model model) {
        EnderecoModel enderecoModel = (EnderecoModel) model;
        String sql = "INSERT INTO endereco (rua, bairro, numero, complemento) VALUES (?, ?, ?, ?)";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, enderecoModel.getRua());
                statement.setString(2, enderecoModel.getBairro());
                statement.setInt(3, enderecoModel.getNumero());
                statement.setString(4, enderecoModel.getComplemento());
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
        String sql = "DELETE FROM endereco WHERE id = ?";

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
        EnderecoModel enderecoModel = (EnderecoModel) novoModel;
        String sql = "UPDATE endereco SET rua = ?, bairro = ?, numero = ?, complemento = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, enderecoModel.getRua());
                statement.setString(2, enderecoModel.getBairro());
                statement.setInt(3, enderecoModel.getNumero());
                statement.setString(4, enderecoModel.getComplemento());
                statement.setInt(5, id);
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
    public List<EnderecoModel> consultarTodos() {
        LinkedList<EnderecoModel> enderecos = new LinkedList<>();
        String sql = "SELECT * FROM endereco";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    enderecos.add(new EnderecoModel(resultado.getInt("id"),
                            resultado.getString("rua"),
                            resultado.getString("bairro"),
                            resultado.getInt("numero"),
                            resultado.getString("complemento")));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return enderecos;
    }
}
