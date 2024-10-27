package com.biblioteca.dao;

import com.biblioteca.model.LivroModel;
import com.biblioteca.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class LivroDao implements BaseCRUDDao {
    @Override
    public boolean inserir(Model model) {
        LivroModel livroModel = (LivroModel) model;
        String sql = "INSERT INTO livro (nome, isbn, preco_aluguel, sinopse, id_editora, id_autor, quantidade_estoque, quantidade_disponivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, livroModel.getNome());
                statement.setString(2, livroModel.getIsbn());
                statement.setDouble(3, livroModel.getPrecoAluguel());
                statement.setString(4, livroModel.getSinopse());
                statement.setInt(5, livroModel.getIdEditora());
                statement.setInt(6, livroModel.getIdAutor());
                statement.setInt(7, livroModel.getQuantidadeEstoque());
                statement.setInt(8, livroModel.getQuantidadeDisponivel());
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
        String sql = "DELETE FROM livro WHERE id = ?";

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
        LivroModel livroModel = (LivroModel) novoModel;
        String sql = "UPDATE livro SET nome = ?, isbn = ?, preco_aluguel = ?, sinopse = ?, id_editora = ?, id_autor = ?, quantidade_estoque = ?, quantidade_disponivel = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, livroModel.getNome());
                statement.setString(2, livroModel.getIsbn());
                statement.setDouble(3, livroModel.getPrecoAluguel());
                statement.setString(4, livroModel.getSinopse());
                statement.setInt(5, livroModel.getIdEditora());
                statement.setInt(6, livroModel.getIdAutor());
                statement.setInt(7, livroModel.getQuantidadeEstoque());
                statement.setInt(8, livroModel.getQuantidadeDisponivel());
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
    public List<LivroModel> consultarTodos() {
        LinkedList<LivroModel> livros = new LinkedList<>();
        String sql = "SELECT * FROM livro";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    livros.add(new LivroModel(resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("isbn"),
                            resultado.getDouble("preco_aluguel"),
                            resultado.getString("sinopse"),
                            resultado.getInt("id_editora"),
                            resultado.getInt("id_autor"),
                            resultado.getInt("quantidade_estoque"),
                            resultado.getInt("quantidade_disponivel")));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return livros;
    }

    @Override
    public Model consultarPorId(int id) {
        LivroModel livroModel = new LivroModel();
        String sql = "SELECT * FROM livro WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();

            if (conexao != null) {
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    livroModel = new LivroModel(resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("isbn"),
                            resultado.getDouble("preco_aluguel"),
                            resultado.getString("sinopse"),
                            resultado.getInt("id_editora"),
                            resultado.getInt("id_autor"),
                            resultado.getInt("quantidade_estoque"),
                            resultado.getInt("quantidade_disponivel"));
                }

                statement.close();
                conexao.close();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return livroModel;
    }
}
