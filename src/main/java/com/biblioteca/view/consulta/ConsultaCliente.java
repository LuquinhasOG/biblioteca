package com.biblioteca.view.consulta;

import com.biblioteca.dao.ClienteDao;
import com.biblioteca.model.ClienteModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConsultaCliente extends JPanel {
    private JPanel painelPrincipal;
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private ClienteDao clienteDao = new ClienteDao();
    private CampoConsulta campoConsulta = new CampoConsulta();

    public ConsultaCliente() {
        painelPrincipal = new JPanel(new BorderLayout());

        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Data nascimento");
        modelo.addColumn("Número Telefone");
        modelo.addColumn("Rua");
        modelo.addColumn("Bairro");
        modelo.addColumn("Número");
        modelo.addColumn("Complemento");
        modelo.addColumn("Ativo");
        pesquisarTodos();

        JPanel painelTable = new JPanel(new BorderLayout());
        JPanel botoesBaixo = new JPanel();

        JButton btnDesativarCliente = new JButton("desativar cliente");
        JButton btnAtivarCliente = new JButton("ativar cliente");

        campoConsulta.setConsultaIdActionListener(this::consultarPorId);
        campoConsulta.setConsultaNomeActionListener(this::consultarPorNome);
        btnDesativarCliente.addActionListener(this::desativarCliente);
        btnAtivarCliente.addActionListener(this::ativarCliente);

        painelTable.add(tabela.getTableHeader(), BorderLayout.NORTH);
        painelTable.add(tabela);

        botoesBaixo.add(btnAtivarCliente);
        botoesBaixo.add(btnDesativarCliente);

        painelPrincipal.add(campoConsulta, BorderLayout.NORTH);
        painelPrincipal.add(painelTable);
        painelPrincipal.add(botoesBaixo, BorderLayout.SOUTH);

        this.add(painelPrincipal);
    }

    public void pesquisarTodos() {
        modelo.setNumRows(0);
        atualizaTabela(clienteDao.consultarTodos());
    }

    public void pesquisarIntervaloId(int inicio, int fim) {
        modelo.setNumRows(0);
        List<ClienteModel> lista = clienteDao.consultarTodos();

        for (int i = 0; i < lista.size(); i++) {
            ClienteModel cliente = lista.get(i);
            if (cliente.getId() < inicio || cliente.getId() > fim) {
                lista.remove(cliente);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void pesquisarNome(String nome) {
        modelo.setNumRows(0);
        List<ClienteModel> lista = clienteDao.consultarTodos();

        for (int i = 0; i < lista.size(); i++) {
            ClienteModel cliente = lista.get(i);
            if (!cliente.getNome().toLowerCase().contains(nome.toLowerCase())) {
                lista.remove(cliente);
                i--;
            }
        }

        atualizaTabela(lista);
    }

    public void setAtivoCliente(int id, boolean ativo) {
        modelo.setNumRows(0);
        ClienteModel cliente = (ClienteModel) clienteDao.consultarPorId(id);
        cliente.setAtivo(ativo);
        clienteDao.atualizarPorId(cliente.getId(), cliente);
    }

    public void atualizaTabela(List<ClienteModel> lista) {
        for (ClienteModel c : lista) {
            modelo.addRow(new Object[] {
                    c.getId(),
                    c.getNome(),
                    c.getCpf(),
                    c.getDataNascimento(),
                    c.getNumeroTelefone(),
                    c.getRua(),
                    c.getBairro(),
                    c.getNumero(),
                    c.getComplemento(),
                    c.isAtivo()
            });
        }
    }

    public void consultarPorId(ActionEvent event) {
        pesquisarIntervaloId(Integer.parseInt(campoConsulta.getIdInicio()), Integer.parseInt(campoConsulta.getIdFim()));
    }

    public void consultarPorNome(ActionEvent event) {
        pesquisarNome(campoConsulta.getNome());
    }

    public void desativarCliente(ActionEvent event) {
        setAtivoCliente((Integer) modelo.getValueAt(tabela.getSelectedRow(), 0), false);
    }

    public void ativarCliente(ActionEvent event) {
        setAtivoCliente((Integer) modelo.getValueAt(tabela.getSelectedRow(), 0), true);
    }
}
