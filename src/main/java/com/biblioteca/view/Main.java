package com.biblioteca.view;

import com.biblioteca.view.cadastro.CadastroCliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;
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

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Excluir usuário");
        mnNewMenu.add(mntmNewMenuItem_2);

        JMenu mnNewMenu_1 = new JMenu("Livros");
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Alugar livros");
        mnNewMenu_1.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Devolução de livros");
        mnNewMenu_1.add(mntmNewMenuItem_4);

        JMenu mnNewMenu_2 = new JMenu("Consultas");
        menuBar.add(mnNewMenu_2);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Livros alugados");
        mnNewMenu_2.add(mntmNewMenuItem_5);

        JMenuItem mntmNewMenuItem_7 = new JMenuItem("Livros disponiveis");
        mnNewMenu_2.add(mntmNewMenuItem_7);

        JMenu mnNewMenu_3 = new JMenu("Menu de livros");
        menuBar.add(mnNewMenu_3);

        JMenuItem mntmNewMenuItem_8 = new JMenuItem("Cadastrar novo livro");
        mnNewMenu_3.add(mntmNewMenuItem_8);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("Excluir livro cadastrado");
        mnNewMenu_3.add(mntmNewMenuItem_6);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 252, 252));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

//        setContentPane(contentPane);
        setContentPane(new CadastroCliente());

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
    }
}