package com.biblioteca.view.cadastro;

import com.biblioteca.dao.AutorDao;
import com.biblioteca.dao.EditoraDao;
import com.biblioteca.dao.LivroDao;
import com.biblioteca.model.AutorModel;
import com.biblioteca.model.EditoraModel;
import com.biblioteca.model.LivroModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CadastroLivro extends JPanel {
    private JPanel painelPrincipal;

    private JTextField campoNome;
    private JTextField campoISBN;
    private JTextField campoPrecoAluguel;
    private JTextField campoSinopse;
    private JTextField campoIdEditora;
    private JTextField campoIdAutor;
    private JTextField campoEstoque;
    private String nomeAutor;
    private String nomeEditora;
    JLabel confirmacao = new JLabel();

    public CadastroLivro() {
        painelPrincipal = new JPanel();

        JLabel titulo = new JLabel("Cadastro Livro");
        JLabel label1 = new JLabel("Nome");
        JLabel label2 = new JLabel("ISBN");
        JLabel label3 = new JLabel("Preço aluguel");
        JLabel label4 = new JLabel("Sinopse");
        JLabel label5 = new JLabel("Id editora");
        JLabel label6 = new JLabel("Id autor");
        JLabel label7 = new JLabel("Estoque");
        titulo.setFont(new Font("arial", Font.BOLD, 16));

        JPanel panelEsquerda = new JPanel(new GridLayout(8, 1));
        JPanel panelDireita = new JPanel(new GridLayout(8, 1));

        JButton btnCadastrar = new JButton("Cadastrar");

        campoNome = new JTextField();
        campoISBN = new JTextField();
        campoPrecoAluguel = new JTextField();
        campoSinopse = new JTextField();
        campoIdEditora = new JTextField();
        campoIdAutor = new JTextField();
        campoEstoque = new JTextField();

        campoNome.setColumns(20);

        panelEsquerda.add(label1);
        panelEsquerda.add(label2);
        panelEsquerda.add(label3);
        panelEsquerda.add(label4);
        panelEsquerda.add(label5);
        panelEsquerda.add(label6);
        panelEsquerda.add(label7);
        panelDireita.add(campoNome);
        panelDireita.add(campoISBN);
        panelDireita.add(campoPrecoAluguel);
        panelDireita.add(campoSinopse);
        panelDireita.add(campoIdEditora);
        panelDireita.add(campoIdAutor);
        panelDireita.add(campoEstoque);

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
        campoIdEditora.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent event) {
                EditoraDao editoraDao = new EditoraDao();
                EditoraModel editora = (EditoraModel) editoraDao.consultarPorId(Integer.parseInt(campoIdEditora.getText()));

                if (editora == null) {
                    nomeEditora = null;
                } else {
                    nomeEditora = editora.getNome();
                }

                atualizarTextoInformacao();
            }
        });

        campoIdAutor.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent event) {
                AutorDao autorDao = new AutorDao();
                AutorModel autor = (AutorModel) autorDao.consultarPorId(Integer.parseInt(campoIdAutor.getText()));

                if (autor == null) {
                    nomeAutor = null;
                } else {
                    nomeAutor = autor.getNome();
                }

                atualizarTextoInformacao();
            }
        });
    }

    public void actionPerformed(ActionEvent event) {
        LivroDao livroDao = new LivroDao();
        LivroModel livro = new LivroModel(campoNome.getText(),
                campoISBN.getText(),
                Double.parseDouble(campoPrecoAluguel.getText()),
                campoSinopse.getText(),
                Integer.parseInt(campoIdEditora.getText()),
                Integer.parseInt(campoIdAutor.getText()),
                Integer.parseInt(campoEstoque.getText()),
                Integer.parseInt(campoEstoque.getText()));

        if (livroDao.inserir(livro)) {
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o livro");
        }
    }

    public void atualizarTextoInformacao() {
        if (nomeEditora == null) {
            confirmacao.setText("Autor: " + nomeAutor);
        } else if (nomeAutor == null) {
            confirmacao.setText("Editora: " + nomeEditora);
        } else {
            confirmacao.setText("Editora: " + nomeEditora + ", Autor: " + nomeAutor);
        }
    }
}
