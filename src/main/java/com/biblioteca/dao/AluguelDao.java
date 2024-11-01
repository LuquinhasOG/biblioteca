package com.biblioteca.dao;

import com.biblioteca.model.AluguelModel;
import com.biblioteca.model.ClienteModel;
import com.biblioteca.model.LivroModel;
import com.biblioteca.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class AluguelDao implements BaseCRUDDao {
    private ClienteDao clienteDao = new ClienteDao();
    private LivroDao livroDao = new LivroDao();

    @Override
    public boolean inserir(Model model) {
        AluguelModel aluguelModel = (AluguelModel) model;
        String sql = "INSERT INTO aluguel (id_cliente, id_livro, id_estado_aluguel, data_aluguel, data_devolucao, renovacoes) VALUES (?, ?, ?, ?, ?, ?)";

        ClienteModel cliente = (ClienteModel) clienteDao.consultarPorId(aluguelModel.getIdCliente());
        LivroModel livro = (LivroModel) livroDao.consultarPorId(aluguelModel.getIdLivro());

        if (livro == null || cliente == null || livro.getQuantidadeDisponivel() == 0 || !cliente.isAtivo()) {
            return false;
        }

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, aluguelModel.getIdCliente());
                statement.setInt(2, aluguelModel.getIdLivro());
                statement.setInt(3, aluguelModel.getIdEstadoAluguel());
                statement.setDate(4, aluguelModel.getDataAluguel());
                statement.setDate(5, aluguelModel.getDataDevolucao());
                statement.setInt(6, aluguelModel.getRenovacoes());
                statement.executeUpdate();

                statement.close();
                conexao.close();

                livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
                livroDao.atualizarPorId(livro.getId(), livro);

                return true;
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return false;
    }

    @Override
    public boolean deletarPorId(int id) {
        String sql = "DELETE FROM aluguel WHERE id = ?";

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
        AluguelModel aluguelModel = (AluguelModel) novoModel;
        String sql = "UPDATE aluguel SET id_cliente = ?, id_livro = ?, id_estado_aluguel = ?, data_aluguel = ?, data_devolucao = ?, renovacoes = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, aluguelModel.getIdCliente());
                statement.setInt(2, aluguelModel.getIdLivro());
                statement.setInt(3, aluguelModel.getIdEstadoAluguel());
                statement.setDate(4, aluguelModel.getDataAluguel());
                statement.setDate(5, aluguelModel.getDataDevolucao());
                statement.setInt(6, aluguelModel.getRenovacoes());
                statement.setInt(7, id);
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
    public List<AluguelModel> consultarTodos() {
        LinkedList<AluguelModel> alugueis = new LinkedList<>();
        String sql = "SELECT * FROM aluguel";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    alugueis.add(new AluguelModel(resultado.getInt("id"),
                            resultado.getInt("id_cliente"),
                            resultado.getInt("id_livro"),
                            resultado.getInt("id_estado_aluguel"),
                            resultado.getDate("data_aluguel"),
                            resultado.getDate("data_devolucao"),
                            resultado.getInt("renovacoes")));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return alugueis;
    }

    @Override
    public Model consultarPorId(int id) {
        AluguelModel aluguel = new AluguelModel();
        String sql = "SELECT * FROM aluguel WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    aluguel = new AluguelModel(resultado.getInt("id"),
                            resultado.getInt("id_cliente"),
                            resultado.getInt("id_livro"),
                            resultado.getInt("id_estado_aluguel"),
                            resultado.getDate("data_aluguel"),
                            resultado.getDate("data_devolucao"),
                            resultado.getInt("renovacoes"));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return aluguel;
    }
}
