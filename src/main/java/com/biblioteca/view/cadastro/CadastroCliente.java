package com.biblioteca.view.cadastro;

import com.biblioteca.dao.ClienteDao;
import com.biblioteca.model.ClienteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;

public class CadastroCliente extends JPanel {
    private JPanel painelPrincipal;

    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoDataNascimento;
    private JTextField campoNumeroTelefone;
    private JTextField campoRua;
    private JTextField campoBairro;
    private JTextField campoNumero;
    private JTextField campoComplemento;

    public CadastroCliente() {
        painelPrincipal = new JPanel();

        JLabel label1 = new JLabel("Nome");
        JLabel label2 = new JLabel("CPF");
        JLabel label3 = new JLabel("Data Nascimento");
        JLabel label4 = new JLabel("Telefone");
        JLabel label5 = new JLabel("Rua");
        JLabel label6 = new JLabel("Bairro");
        JLabel label7 = new JLabel("Numero");
        JLabel label8 = new JLabel("Complemento");

        JPanel panelEsquerda = new JPanel(new GridLayout(8, 1));
        JPanel panelDireita = new JPanel(new GridLayout(8, 1));

        JButton btnCadastrar = new JButton("Cadastrar");

        campoNome = new JTextField();
        campoCPF = new JTextField();
        campoDataNascimento = new JTextField();
        campoNumeroTelefone = new JTextField();
        campoRua = new JTextField();
        campoBairro = new JTextField();
        campoNumero = new JTextField();
        campoComplemento = new JTextField();

        campoNome.setColumns(20);
        campoCPF.setColumns(20);
        campoDataNascimento.setColumns(20);

        panelEsquerda.add(label1);
        panelEsquerda.add(label2);
        panelEsquerda.add(label3);
        panelEsquerda.add(label4);
        panelEsquerda.add(label5);
        panelEsquerda.add(label6);
        panelEsquerda.add(label7);
        panelEsquerda.add(label8);
        panelDireita.add(campoNome);
        panelDireita.add(campoCPF);
        panelDireita.add(campoDataNascimento);
        panelDireita.add(campoNumeroTelefone);
        panelDireita.add(campoRua);
        panelDireita.add(campoBairro);
        panelDireita.add(campoNumero);
        panelDireita.add(campoComplemento);

        JPanel formulario = new JPanel();
        JPanel campos = new JPanel(new GridLayout(1, 2));
        campos.add(panelEsquerda);
        campos.add(panelDireita);
        formulario.add(campos);
        formulario.add(btnCadastrar);

        painelPrincipal.add(formulario);

        this.add(painelPrincipal);

        btnCadastrar.addActionListener((ActionEvent event) -> {
            ClienteDao clienteDao = new ClienteDao();
            ClienteModel cliente = new ClienteModel(campoNome.getText(),
                    campoCPF.getText(),
                    Date.valueOf(campoDataNascimento.getText()),
                    campoNumeroTelefone.getText(),
                    campoRua.getText(),
                    campoBairro.getText(),
                    Integer.valueOf(campoNumero.getText()),
                    campoComplemento.getText(),
                    true);

            clienteDao.inserir(cliente);
        });
    }
}
