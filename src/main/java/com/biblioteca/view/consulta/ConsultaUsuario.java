package com.biblioteca.view.consulta;

import com.biblioteca.dao.ClienteDao;
import com.biblioteca.model.ClienteModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConsultaUsuario extends JPanel {
    private JPanel painelPrincipal;
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private ClienteDao clienteDao = new ClienteDao();
    JTextField campoIdInicio = new JTextField();
    JTextField campoIdFim = new JTextField();
    JTextField campoNome = new JTextField();

    public ConsultaUsuario() {
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
        JPanel consultas = new JPanel(new GridLayout(2, 1));
        JPanel consultaId = new JPanel();
        JPanel consultaNome = new JPanel();
        JPanel botoesBaixo = new JPanel();

        JButton btnConsultarId = new JButton("consultar");
        JButton btnConsultarNome = new JButton("consultar");
        JButton btnDesativarCliente = new JButton("desativar cliente");
        JButton btnAtivarCliente = new JButton("ativar cliente");

        campoIdInicio.setText("0");
        campoIdInicio.setColumns(10);
        campoIdFim.setText("9999");
        campoIdFim.setColumns(10);
        campoNome.setColumns(20);
        btnConsultarId.addActionListener(this::consultarPorId);
        btnConsultarNome.addActionListener(this::consultarPorNome);
        btnDesativarCliente.addActionListener(this::desativarCliente);
        btnAtivarCliente.addActionListener(this::ativarCliente);

        consultaId.add(new Label("id início:"));
        consultaId.add(campoIdInicio);
        consultaId.add(new Label("id fim:"));
        consultaId.add(campoIdFim);
        consultaId.add(btnConsultarId);

        consultaNome.add(new Label("Nome:"));
        consultaNome.add(campoNome);
        consultaNome.add(btnConsultarNome);

        consultas.add(consultaNome);
        consultas.add(consultaId);

        painelTable.add(tabela.getTableHeader(), BorderLayout.NORTH);
        painelTable.add(tabela);

        botoesBaixo.add(btnAtivarCliente);
        botoesBaixo.add(btnDesativarCliente);

        painelPrincipal.add(consultas, BorderLayout.NORTH);
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
        pesquisarIntervaloId(Integer.parseInt(campoIdInicio.getText()), Integer.parseInt(campoIdFim.getText()));
    }

    public void consultarPorNome(ActionEvent event) {
        pesquisarNome(campoNome.getText());
    }

    public void desativarCliente(ActionEvent event) {
        setAtivoCliente((Integer) modelo.getValueAt(tabela.getSelectedRow(), 0), false);
    }

    public void ativarCliente(ActionEvent event) {
        setAtivoCliente((Integer) modelo.getValueAt(tabela.getSelectedRow(), 0), true);
    }
}
