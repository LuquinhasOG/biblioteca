package com.biblioteca.view.cadastro;

import com.biblioteca.dao.AutorDao;
import com.biblioteca.model.AutorModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CadastroAutor extends JPanel {
    private JPanel painelPrincipal;

    private JTextField campoNome;

    public CadastroAutor() {
        painelPrincipal = new JPanel();

        JLabel titulo = new JLabel("Cadastro Autor");
        JLabel label1 = new JLabel("Nome");
        titulo.setFont(new Font("arial", Font.BOLD, 16));

        JPanel panelEsquerda = new JPanel(new GridLayout(8, 1));
        JPanel panelDireita = new JPanel(new GridLayout(8, 1));

        JButton btnCadastrar = new JButton("Cadastrar");

        campoNome = new JTextField();

        campoNome.setColumns(20);

        panelEsquerda.add(label1);
        panelDireita.add(campoNome);

        JPanel formulario = new JPanel(new BorderLayout());
        JPanel campos = new JPanel(new GridLayout(1, 2));
        campos.add(panelEsquerda);
        campos.add(panelDireita);
        formulario.add(campos, BorderLayout.CENTER);
        formulario.add(titulo, BorderLayout.NORTH);
        formulario.add(btnCadastrar, BorderLayout.SOUTH);

        painelPrincipal.add(formulario);

        this.add(painelPrincipal);

        btnCadastrar.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent event) {
        AutorDao autorDao = new AutorDao();
        AutorModel autor = new AutorModel(campoNome.getText());

        if (autorDao.inserir(autor)) {
            JOptionPane.showMessageDialog(null, "Autor cadastrado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o autor");
        }
    }
}
