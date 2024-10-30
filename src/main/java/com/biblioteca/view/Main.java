package com.biblioteca.view;

import com.biblioteca.view.cadastro.CadastroCliente;
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
        JMenu mnNewMenu = new JMenu("Usuários");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("Cadastrar usuário");
        mnNewMenu.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Consultar usuário");
        mnNewMenu.add(mntmNewMenuItem_1);

        JMenu mnNewMenu_1 = new JMenu("Livros");
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Alugar livros");
        mnNewMenu_1.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Devolução de livros");
        mnNewMenu_1.add(mntmNewMenuItem_4);

        JMenu mnNewMenu_2 = new JMenu("Consultas");
        menuBar.add(mnNewMenu_2);

        JMenuItem itemMenuConsulta1 = new JMenuItem("Clientes");
        JMenuItem itemMenuConsulta2 = new JMenuItem("Editoras");
        JMenuItem itemMenuConsulta3 = new JMenuItem("Autor");
        JMenuItem itemMenuConsulta4 = new JMenuItem("Livros");
        JMenuItem itemMenuConsulta5 = new JMenuItem("Aluguel");
        JMenuItem itemMenuConsulta6 = new JMenuItem("Multa");
        mnNewMenu_2.add(itemMenuConsulta1);
        mnNewMenu_2.add(itemMenuConsulta2);
        mnNewMenu_2.add(itemMenuConsulta3);
        mnNewMenu_2.add(itemMenuConsulta4);
        mnNewMenu_2.add(itemMenuConsulta5);
        mnNewMenu_2.add(itemMenuConsulta6);


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

        mntmNewMenuItem.addActionListener((ActionEvent event) -> {
            setContentPane(new CadastroCliente());
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