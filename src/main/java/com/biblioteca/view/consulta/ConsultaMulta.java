package com.biblioteca.view.consulta;

import com.biblioteca.dao.AluguelDao;
import com.biblioteca.dao.ClienteDao;
import com.biblioteca.dao.MultaDao;
import com.biblioteca.model.AluguelModel;
import com.biblioteca.model.ClienteModel;
import com.biblioteca.model.MultaModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConsultaMulta extends JPanel {
    private JPanel painelPrincipal;
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private MultaDao multaDao = new MultaDao();
    private ClienteDao clienteDao = new ClienteDao();
    private AluguelDao aluguelDao = new AluguelDao();
    private CampoConsulta campoConsulta = new CampoConsulta();

    public ConsultaMulta() {
        painelPrincipal = new JPanel(new BorderLayout());

        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Id aluguel");
        modelo.addColumn("Cliente");
        modelo.addColumn("Valor");
        modelo.addColumn("Pago");
        pesquisarTodos();

        JPanel painelTable = new JPanel(new BorderLayout());
        JPanel botoesBaixo = new JPanel();

        JButton btnPagar = new JButton("pagar");

        campoConsulta.setConsultaIdActionListener(this::consultarPorId);
        campoConsulta.setConsultaNomeActionListener(this::consultarPorNome);
        btnPagar.addActionListener(this::eventoPagar);

        painelTable.add(tabela.getTableHeader(), BorderLayout.NORTH);
        painelTable.add(tabela);

        botoesBaixo.add(btnPagar);

        painelPrincipal.add(campoConsulta, BorderLayout.NORTH);
        painelPrincipal.add(painelTable);
        painelPrincipal.add(botoesBaixo, BorderLayout.SOUTH);

        this.add(painelPrincipal);
    }

    public void pesquisarTodos() {
        atualizaTabela(multaDao.consultarTodos());
    }

    public void pesquisarIntervaloId(int inicio, int fim) {
        java.util.List<MultaModel> lista = multaDao.consultarTodos();

        for (int i = 0; i < lista.size(); i++) {
            MultaModel aux = lista.get(i);
            if (aux.getId() < inicio || aux.getId() > fim) {
                lista.remove(aux);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void pesquisarNome(String nome) {
        java.util.List<MultaModel> lista = multaDao.consultarTodos();

        for (int i = 0; i < lista.size(); i++) {
            MultaModel aux = lista.get(i);
            AluguelModel aluguel = (AluguelModel) aluguelDao.consultarPorId(aux.getIdAluguel());
            ClienteModel cliente = (ClienteModel) clienteDao.consultarPorId(aluguel.getIdCliente());

            if (!cliente.getNome().toLowerCase().contains(nome.toLowerCase())) {
                lista.remove(aux);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void pagar(int id) {
        MultaModel multa = (MultaModel) multaDao.consultarPorId(id);
        multa.setPago(true);
        if (multaDao.atualizarPorId(id, multa))
            JOptionPane.showMessageDialog(null, "Multa paga");

        atualizaTabela(multaDao.consultarTodos());
    }

    public void atualizaTabela(List<MultaModel> lista) {
        modelo.setNumRows(0);
        for (MultaModel c : lista) {
            AluguelModel aluguel = (AluguelModel) aluguelDao.consultarPorId(c.getIdAluguel());
            ClienteModel cliente = (ClienteModel) clienteDao.consultarPorId(aluguel.getIdCliente());

            modelo.addRow(new Object[] {
                    c.getId(),
                    c.getIdAluguel(),
                    cliente.getNome(),
                    c.getValor(),
                    c.isPago()
            });
        }
    }

    public void consultarPorId(ActionEvent event) {
        pesquisarIntervaloId(Integer.parseInt(campoConsulta.getIdInicio()), Integer.parseInt(campoConsulta.getIdFim()));
    }

    public void consultarPorNome(ActionEvent event) {
        pesquisarNome(campoConsulta.getNome());
    }

    public void eventoPagar(ActionEvent event) {
        pagar((Integer) modelo.getValueAt(tabela.getSelectedRow(), 0));
    }
}
