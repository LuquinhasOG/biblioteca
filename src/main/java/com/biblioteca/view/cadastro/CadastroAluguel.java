package com.biblioteca.view.cadastro;

import com.biblioteca.dao.AluguelDao;
import com.biblioteca.dao.ClienteDao;
import com.biblioteca.dao.LivroDao;
import com.biblioteca.model.AluguelModel;
import com.biblioteca.model.ClienteModel;
import com.biblioteca.model.LivroModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;

public class CadastroAluguel extends JPanel {
    private JPanel painelPrincipal;

    private JTextField campoIdLivro;
    private JTextField campoIdCliente;
    private String nomeLivro;
    private String nomeCliente;
    JLabel confirmacao = new JLabel();

    public CadastroAluguel() {
        painelPrincipal = new JPanel();

        JLabel titulo = new JLabel("Aluguel de Livro");
        JLabel label1 = new JLabel("Id livro");
        JLabel label2 = new JLabel("Id cliente");
        titulo.setFont(new Font("arial", Font.BOLD, 16));

        JPanel panelEsquerda = new JPanel(new GridLayout(8, 1));
        JPanel panelDireita = new JPanel(new GridLayout(8, 1));

        JButton btnCadastrar = new JButton("Cadastrar");

        campoIdLivro = new JTextField();
        campoIdCliente = new JTextField();

        campoIdLivro.setColumns(20);

        panelEsquerda.add(label1);
        panelEsquerda.add(label2);
        panelDireita.add(campoIdLivro);
        panelDireita.add(campoIdCliente);

        JPanel formulario = new JPanel(new BorderLayout());
        JPanel campos = new JPanel(new GridLayout(1, 2));
        JPanel baixo = new JPanel(new BorderLayout());
        campos.add(panelEsquerda);
        campos.add(panelDireita);
        baixo.add(confirmacao, BorderLayout.CENTER);
        baixo.add(btnCadastrar, BorderLayout.SOUTH);
        formulario.add(campos, BorderLayout.CENTER);
        formulario.add(titulo, BorderLayout.NORTH);
        formulario.add(baixo, BorderLayout.SOUTH);

        painelPrincipal.add(formulario);

        this.add(painelPrincipal);

        btnCadastrar.addActionListener(this::actionPerformed);
        campoIdLivro.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent event) {
                LivroDao livroDao = new LivroDao();
                LivroModel livro = (LivroModel) livroDao.consultarPorId(Integer.parseInt(campoIdLivro.getText()));

                if (livro == null) {
                    nomeLivro = null;
                } else {
                    nomeLivro = livro.getNome();
                }

                atualizarTextoInformacao();
            }
        });

        campoIdCliente.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent event) {
                ClienteDao clienteDao = new ClienteDao();
                ClienteModel cliente = (ClienteModel) clienteDao.consultarPorId(Integer.parseInt(campoIdCliente.getText()));

                if (cliente == null) {
                    nomeCliente = null;
                } else {
                    nomeCliente = cliente.getNome();
                }

                atualizarTextoInformacao();
            }
        });
    }

    public void actionPerformed(ActionEvent event) {
        AluguelDao aluguelDao = new AluguelDao();
        AluguelModel aluguel = new AluguelModel(Integer.parseInt(campoIdCliente.getText()),
                Integer.parseInt(campoIdLivro.getText()),
                1,
                new Date(System.currentTimeMillis()),
                null,
                0);

        if (aluguelDao.inserir(aluguel)) {
            JOptionPane.showMessageDialog(null, "Aluguel realizado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível alugual o livro");
        }
    }

    public void atualizarTextoInformacao() {
        if (nomeLivro == null) {
            confirmacao.setText("Cliente: " + nomeCliente);
        } else if (nomeCliente == null) {
            confirmacao.setText("Livro: " + nomeLivro);
        } else {
            confirmacao.setText("Livro: " + nomeLivro + ", Cliente: " + nomeCliente);
        }
    }
}
