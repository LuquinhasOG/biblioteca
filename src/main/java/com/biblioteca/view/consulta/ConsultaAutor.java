package com.biblioteca.view.consulta;

import com.biblioteca.dao.AutorDao;
import com.biblioteca.model.AutorModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConsultaAutor extends JPanel {
    private JPanel painelPrincipal;
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private AutorDao autorDao = new AutorDao();
    private CampoConsulta campoConsulta = new CampoConsulta();

    public ConsultaAutor() {
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
        atualizaTabela(autorDao.consultarTodos());
    }

    public void pesquisarIntervaloId(int inicio, int fim) {
        java.util.List<AutorModel> lista = autorDao.consultarTodos();

        for (int i = 0; i < lista.size(); i++) {
            AutorModel aux = lista.get(i);
            if (aux.getId() < inicio || aux.getId() > fim) {
                lista.remove(aux);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void pesquisarNome(String nome) {
        java.util.List<AutorModel> lista = autorDao.consultarTodos();

        for (int i = 0; i < lista.size(); i++) {
            AutorModel aux = lista.get(i);
            if (!aux.getNome().toLowerCase().contains(nome.toLowerCase())) {
                lista.remove(aux);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void deletar(int id) {
        autorDao.deletarPorId(id);
        System.out.println(id);
        atualizaTabela(autorDao.consultarTodos());
    }

    public void atualizaTabela(List<AutorModel> lista) {
        modelo.setNumRows(0);
        for (AutorModel c : lista) {
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
