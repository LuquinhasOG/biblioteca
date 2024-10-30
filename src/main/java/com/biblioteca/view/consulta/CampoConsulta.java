package com.biblioteca.view.consulta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CampoConsulta extends JPanel {
    private JTextField campoIdInicio = new JTextField();
    private JTextField campoIdFim = new JTextField();
    private JTextField campoNome = new JTextField();
    private JButton btnConsultarId = new JButton("consultar");
    private JButton btnConsultarNome = new JButton("consultar");
    private JPanel consultas = new JPanel(new GridLayout(2, 1));
    private JPanel consultaId = new JPanel();
    private JPanel consultaNome = new JPanel();

    public CampoConsulta() {
        campoIdInicio.setText("0");
        campoIdInicio.setColumns(10);
        campoIdFim.setText("9999");
        campoIdFim.setColumns(10);
        campoNome.setColumns(26);

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

        this.add(consultas);
    }

    public String getIdInicio() {
        return campoIdInicio.getText();
    }

    public String getIdFim() {
        return campoIdFim.getText();
    }

    public String getNome() {
        return campoNome.getText();
    }

    public void setConsultaIdActionListener(ActionListener listener) {
        btnConsultarId.addActionListener(listener);
    }

    public void setConsultaNomeActionListener(ActionListener listener) {
        btnConsultarNome.addActionListener(listener);
    }
}
