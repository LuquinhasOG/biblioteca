package com.biblioteca.view.consulta;

import com.biblioteca.dao.EditoraDao;
import com.biblioteca.model.EditoraModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConsultaEditora extends JPanel {
    private JPanel painelPrincipal;
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private EditoraDao editoraDao = new EditoraDao();
    private CampoConsulta campoConsulta = new CampoConsulta();

    public ConsultaEditora() {
        painelPrincipal = new JPanel(new BorderLayout());

        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
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
        atualizaTabela(editoraDao.consultarTodos());
    }

    public void pesquisarIntervaloId(int inicio, int fim) {
        java.util.List<EditoraModel> lista = editoraDao.consultarTodos();
        for (int i = 0; i < lista.size(); i++) {
            EditoraModel aux = lista.get(i);
            if (aux.getId() < inicio || aux.getId() > fim) {
                lista.remove(aux);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void pesquisarNome(String nome) {
        java.util.List<EditoraModel> lista = editoraDao.consultarTodos();
        for (int i = 0; i < lista.size(); i++) {
            EditoraModel aux = lista.get(i);
            if (!aux.getNome().toLowerCase().contains(nome.toLowerCase())) {
                lista.remove(aux);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void deletar(int id) {
        editoraDao.deletarPorId(id);
        System.out.println(id);
        atualizaTabela(editoraDao.consultarTodos());
    }

    public void atualizaTabela(List<EditoraModel> lista) {
        modelo.setNumRows(0);
        for (EditoraModel c : lista) {
            modelo.addRow(new Object[] {
                    c.getId(),
                    c.getNome()
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
