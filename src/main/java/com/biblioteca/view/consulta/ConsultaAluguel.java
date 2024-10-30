package com.biblioteca.view.consulta;

import com.biblioteca.dao.AluguelDao;
import com.biblioteca.dao.ClienteDao;
import com.biblioteca.dao.EstadoAluguelDao;
import com.biblioteca.dao.LivroDao;
import com.biblioteca.model.AluguelModel;
import com.biblioteca.model.ClienteModel;
import com.biblioteca.model.EstadoAluguelModel;
import com.biblioteca.model.LivroModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.List;

public class ConsultaAluguel extends JPanel {
    private JPanel painelPrincipal;
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private AluguelDao aluguelDao = new AluguelDao();
    private ClienteDao clienteDao = new ClienteDao();
    private LivroDao livroDao = new LivroDao();
    private EstadoAluguelDao estadoAluguelDao = new EstadoAluguelDao();
    private CampoConsulta campoConsulta = new CampoConsulta();

    public ConsultaAluguel() {
        painelPrincipal = new JPanel(new BorderLayout());

        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Cliente");
        modelo.addColumn("Livro");
        modelo.addColumn("Data aluguel");
        modelo.addColumn("Data devolução");
        modelo.addColumn("Estado");
        modelo.addColumn("Renovações");
        pesquisarTodos();

        JPanel painelTable = new JPanel(new BorderLayout());
        JPanel botoesBaixo = new JPanel();

        JButton btnDevolver = new JButton("devolver");
        JButton btnRenovar = new JButton("renovar");

        campoConsulta.setConsultaIdActionListener(this::consultarPorId);
        campoConsulta.setConsultaNomeActionListener(this::consultarPorNome);
        btnDevolver.addActionListener(this::eventoDevolver);
        btnRenovar.addActionListener(this::eventoRenovar);

        painelTable.add(tabela.getTableHeader(), BorderLayout.NORTH);
        painelTable.add(tabela);

        botoesBaixo.add(btnDevolver);
        botoesBaixo.add(btnRenovar);

        painelPrincipal.add(campoConsulta, BorderLayout.NORTH);
        painelPrincipal.add(painelTable);
        painelPrincipal.add(botoesBaixo, BorderLayout.SOUTH);

        this.add(painelPrincipal);
    }

    public void pesquisarTodos() {
        atualizaTabela(aluguelDao.consultarTodos());
    }

    public void pesquisarIntervaloId(int inicio, int fim) {
        java.util.List<AluguelModel> lista = aluguelDao.consultarTodos();

        for (int i = 0; i < lista.size(); i++) {
            AluguelModel aux = lista.get(i);
            if (aux.getId() < inicio || aux.getId() > fim) {
                lista.remove(aux);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void pesquisarNome(String nome) {
        java.util.List<AluguelModel> lista = aluguelDao.consultarTodos();

        for (int i = 0; i < lista.size(); i++) {
            AluguelModel aux = lista.get(i);
            ClienteModel cliente = (ClienteModel) clienteDao.consultarPorId(aux.getIdCliente());

            if (!cliente.getNome().toLowerCase().contains(nome.toLowerCase())) {
                lista.remove(aux);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void devolver(int id) {
        AluguelModel aluguel = (AluguelModel) aluguelDao.consultarPorId(id);
        aluguel.setDataDevolucao(new Date(System.currentTimeMillis()));
        aluguelDao.atualizarPorId(id, aluguel);

        atualizaTabela(aluguelDao.consultarTodos());
    }

    public void renovar(int id) {
        AluguelModel aluguel = (AluguelModel) aluguelDao.consultarPorId(id);
        if (aluguel.getIdEstadoAluguel() != 2 && aluguel.getRenovacoes() < 3) {
            aluguel.setRenovacoes(aluguel.getRenovacoes()+1);
            aluguelDao.atualizarPorId(id, aluguel);
            JOptionPane.showMessageDialog(null, "Aluguel renovado");
            atualizaTabela(aluguelDao.consultarTodos());
        } else {
            JOptionPane.showMessageDialog(null, "Livro devolvido ou limite de renovação excedido");
        }
    }

    public void atualizaTabela(List<AluguelModel> lista) {
        modelo.setNumRows(0);
        for (AluguelModel c : lista) {
            LivroModel livro = (LivroModel) livroDao.consultarPorId(c.getIdLivro());
            ClienteModel cliente = (ClienteModel) clienteDao.consultarPorId(c.getIdCliente());
            EstadoAluguelModel estadoAluguel = (EstadoAluguelModel) estadoAluguelDao.consultarPorId(c.getIdEstadoAluguel());

            modelo.addRow(new Object[] {
                    c.getId(),
                    cliente.getNome(),
                    livro.getNome(),
                    c.getDataAluguel(),
                    c.getDataDevolucao(),
                    estadoAluguel.getDescricao(),
                    c.getRenovacoes()
            });
        }
    }

    public void consultarPorId(ActionEvent event) {
        pesquisarIntervaloId(Integer.parseInt(campoConsulta.getIdInicio()), Integer.parseInt(campoConsulta.getIdFim()));
    }

    public void consultarPorNome(ActionEvent event) {
        pesquisarNome(campoConsulta.getNome());
    }

    public void eventoDevolver(ActionEvent event) {
        devolver((Integer) modelo.getValueAt(tabela.getSelectedRow(), 0));
    }

    public void eventoRenovar(ActionEvent event) {
        renovar((Integer) modelo.getValueAt(tabela.getSelectedRow(), 0));
    }
}
