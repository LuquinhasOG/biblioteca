package com.biblioteca.view;

import com.biblioteca.view.cadastro.CadastroAutor;
import com.biblioteca.view.cadastro.CadastroCliente;
import com.biblioteca.view.cadastro.CadastroEditora;
import com.biblioteca.view.cadastro.CadastroLivro;
import com.biblioteca.view.consulta.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Main extends JFrame {
    private JPanel contentPane;
    private BufferedImage img = null;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 680);
        setLocationRelativeTo(null);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        setTitle("Biblioteca");
        Color c1 = new Color(166,220,237);
        setBackground(c1);

        JMenu menu1 = new JMenu("Aluguel");
        JMenuItem itemMenuConsulta5 = new JMenuItem("Consulta");
        menu1.add(itemMenuConsulta5);

        JMenu menu2 = new JMenu("Livro");
        JMenuItem itemMenuCadastrar4 = new JMenuItem("Cadastro");
        JMenuItem itemMenuConsulta4 = new JMenuItem("Consulta");
        menu2.add(itemMenuCadastrar4);
        menu2.add(itemMenuConsulta4);

        JMenu menu3 = new JMenu("Cliente");
        JMenuItem itemMenuCadastrar1 = new JMenuItem("Cadastro");
        JMenuItem itemMenuConsulta1 = new JMenuItem("Consulta");
        menu3.add(itemMenuCadastrar1);
        menu3.add(itemMenuConsulta1);

        JMenu menu4 = new JMenu("Editora");
        JMenuItem itemMenuCadastrar2 = new JMenuItem("Cadastro");
        JMenuItem itemMenuConsulta2 = new JMenuItem("Consulta");
        menu4.add(itemMenuCadastrar2);
        menu4.add(itemMenuConsulta2);

        JMenu menu5 = new JMenu("Autor");
        JMenuItem itemMenuCadastrar3 = new JMenuItem("Cadastro");
        JMenuItem itemMenuConsulta3 = new JMenuItem("Consulta");
        menu5.add(itemMenuCadastrar3);
        menu5.add(itemMenuConsulta3);

        JMenu menu6 = new JMenu("Multas");
        JMenuItem itemMenuConsulta6 = new JMenuItem("Consulta");
        menu6.add(itemMenuConsulta6);

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);
        menuBar.add(menu5);
        menuBar.add(menu6);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 252, 252));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

//        try {
//            img = ImageIO.read(new File("C:\\Users\\Nivaldo\\eclipse-workspace\\NewAps\\src\\Books.jpg"));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        JLabel label = new JLabel("");
//        Image dimg = img.getScaledInstance(1050, 400, Image.SCALE_SMOOTH);
//        ImageIcon imageIcon = new ImageIcon(dimg);
//        label.setIcon(imageIcon);
        getContentPane().add(label);

        JLabel lblNewLabel = new JLabel("Bem Vindo!!");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 99));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(label))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(273)
                                                .addComponent(lblNewLabel)))
                                .addContainerGap(332, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(label)
                                .addGap(41)
                                .addComponent(lblNewLabel)
                                .addContainerGap(506, Short.MAX_VALUE))
        );

        contentPane.setLayout(gl_contentPane);

        contentPane.add(label);

        itemMenuCadastrar1.addActionListener((ActionEvent event) -> {
            setContentPane(new CadastroCliente());
        });

        itemMenuCadastrar2.addActionListener((ActionEvent event) -> {
            setContentPane(new CadastroEditora());
        });

        itemMenuCadastrar3.addActionListener((ActionEvent event) -> {
            setContentPane(new CadastroAutor());
        });

        itemMenuCadastrar4.addActionListener((ActionEvent event) -> {
            setContentPane(new CadastroLivro());
        });

        itemMenuConsulta1.addActionListener((ActionEvent event) -> {
            setContentPane(new ConsultaCliente());
        });

        itemMenuConsulta2.addActionListener((ActionEvent event) -> {
            setContentPane(new ConsultaEditora());
        });

        itemMenuConsulta3.addActionListener((ActionEvent event) -> {
            setContentPane(new ConsultaAutor());
        });

        itemMenuConsulta4.addActionListener((ActionEvent event) -> {
            setContentPane(new ConsultaLivro());
        });

        itemMenuConsulta5.addActionListener((ActionEvent event) -> {
            setContentPane(new ConsultaAluguel());
        });

        itemMenuConsulta6.addActionListener((ActionEvent event) -> {
            setContentPane(new ConsultaMulta());
        });
    }
}