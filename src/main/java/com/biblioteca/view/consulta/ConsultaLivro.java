package com.biblioteca.view.consulta;

import com.biblioteca.dao.AutorDao;
import com.biblioteca.dao.EditoraDao;
import com.biblioteca.dao.LivroDao;
import com.biblioteca.model.AutorModel;
import com.biblioteca.model.EditoraModel;
import com.biblioteca.model.LivroModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConsultaLivro extends JPanel {
    private JPanel painelPrincipal;
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private LivroDao livroDao = new LivroDao();
    private EditoraDao editoraDao = new EditoraDao();
    private AutorDao autorDao = new AutorDao();
    private CampoConsulta campoConsulta = new CampoConsulta();

    public ConsultaLivro() {
        painelPrincipal = new JPanel(new BorderLayout());

        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("ISBN");
        modelo.addColumn("Preço aluguel");
        modelo.addColumn("Sinopse");
        modelo.addColumn("Editora");
        modelo.addColumn("Autor");
        modelo.addColumn("Quantidade estoque");
        modelo.addColumn("Quantidade disponível");
        pesquisarTodos();

        JPanel painelTable = new JPanel(new BorderLayout());
        JPanel botoesBaixo = new JPanel();

        JButton btnExcluir = new JButton("excluir");

        campoConsulta.setConsultaIdActionListener(this::consultarPorId);
        campoConsulta.setConsultaNomeActionListener(this::consultarPorNome);
        btnExcluir.addActionListener(this::eventoExcluir);

        painelTable.add(tabela.getTableHeader(), BorderLayout.NORTH);
        painelTable.add(tabela);

        botoesBaixo.add(btnExcluir);

        painelPrincipal.add(campoConsulta, BorderLayout.NORTH);
        painelPrincipal.add(painelTable);
        painelPrincipal.add(botoesBaixo, BorderLayout.SOUTH);

        this.add(painelPrincipal);
    }

    public void pesquisarTodos() {
        atualizaTabela(livroDao.consultarTodos());
    }

    public void pesquisarIntervaloId(int inicio, int fim) {
        java.util.List<LivroModel> lista = livroDao.consultarTodos();
        for (int i = 0; i < lista.size(); i++) {
            LivroModel aux = lista.get(i);
            if (aux.getId() < inicio || aux.getId() > fim) {
                lista.remove(aux);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void pesquisarNome(String nome) {
        java.util.List<LivroModel> lista = livroDao.consultarTodos();
        for (int i = 0; i < lista.size(); i++) {
            LivroModel aux = lista.get(i);
            if (!aux.getNome().toLowerCase().contains(nome.toLowerCase())) {
                lista.remove(aux);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void deletar(int id) {
        livroDao.deletarPorId(id);
        System.out.println(id);
        atualizaTabela(livroDao.consultarTodos());
    }

    public void atualizaTabela(List<LivroModel> lista) {
        modelo.setNumRows(0);
        for (LivroModel c : lista) {
            AutorModel autor = (AutorModel) autorDao.consultarPorId(c.getIdAutor());
            EditoraModel editora = (EditoraModel) editoraDao.consultarPorId(c.getIdEditora());

            modelo.addRow(new Object[] {
                    c.getId(),
                    c.getNome(),
                    c.getIsbn(),
                    c.getPrecoAluguel(),
                    c.getSinopse(),
                    editora.getNome(),
                    autor.getNome(),
                    c.getQuantidadeEstoque(),
                    c.getQuantidadeDisponivel()
            });
        }
    }

    public void consultarPorId(ActionEvent event) {
        pesquisarIntervaloId(Integer.parseInt(campoConsulta.getIdInicio()), Integer.parseInt(campoConsulta.getIdFim()));
    }

    public void consultarPorNome(ActionEvent event) {
        pesquisarNome(campoConsulta.getNome());
    }

    public void eventoExcluir(ActionEvent event) {
        deletar((Integer) modelo.getValueAt(tabela.getSelectedRow(), 0));
    }
}
