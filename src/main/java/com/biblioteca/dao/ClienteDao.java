package com.biblioteca.dao;

import com.biblioteca.model.ClienteModel;
import com.biblioteca.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ClienteDao implements BaseCRUDDao {
    @Override
    public boolean inserir(Model model) {
        ClienteModel clienteModel = (ClienteModel) model;
        String sql = "INSERT INTO cliente (nome, cpf, data_nascimento, numero_telefone, rua, bairro, numero, complemento, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, clienteModel.getNome());
                statement.setString(2, clienteModel.getCpf());
                statement.setDate(3, clienteModel.getDataNascimento());
                statement.setString(4, clienteModel.getNumeroTelefone());
                statement.setString(5, clienteModel.getRua());
                statement.setString(6, clienteModel.getBairro());
                statement.setInt(7, clienteModel.getNumero());
                statement.setString(8, clienteModel.getComplemento());
                statement.setBoolean(9, clienteModel.isAtivo());
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
        String sql = "DELETE FROM cliente WHERE id = ?";

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
        ClienteModel enderecoModel = (ClienteModel) novoModel;
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, data_nascimento = ?, rua = ?, bairro = ?, numero = ?, complemento = ?, ativo = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, enderecoModel.getNome());
                statement.setString(2, enderecoModel.getCpf());
                statement.setDate(3, enderecoModel.getDataNascimento());
                statement.setString(4, enderecoModel.getRua());
                statement.setString(5, enderecoModel.getBairro());
                statement.setInt(6, enderecoModel.getNumero());
                statement.setString(7, enderecoModel.getComplemento());
                statement.setBoolean(8, enderecoModel.isAtivo());
                statement.setInt(9, id);
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
    public List<ClienteModel> consultarTodos() {
        LinkedList<ClienteModel> clientes = new LinkedList<>();
        String sql = "SELECT * FROM cliente";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    clientes.add(new ClienteModel(resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getDate("data_nascimento"),
                            resultado.getString("numero_telefone"),
                            resultado.getString("rua"),
                            resultado.getString("bairro"),
                            resultado.getInt("numero"),
                            resultado.getString("complemento"),
                            resultado.getBoolean("ativo")));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return clientes;
    }

    @Override
    public Model consultarPorId(int id) {
        ClienteModel cliente = new ClienteModel();
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    cliente = new ClienteModel(resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getDate("data_nascimento"),
                            resultado.getString("numero_telefone"),
                            resultado.getString("rua"),
                            resultado.getString("bairro"),
                            resultado.getInt("numero"),
                            resultado.getString("complemento"),
                            resultado.getBoolean("ativo"));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return cliente;
    }
}
