package com.hopen.SistemaHopen.UI;


import com.hopen.SistemaHopen.entities.Obra;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class CargarObra extends JDialog{
    private JTextField tfTitular;
    private JTextField tfMontoTotal;
    private JTextField tfComision;
    private JTextField tfGanPre;
    private JTextField tfCostosFijos;
    private JButton siguienteButton;
    private JButton salirButton;
    private JSpinner.DateEditor spinner$DateEditor1;
    private JPanel cargarObraPanel;

    public CargarObra(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(cargarObraPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            salirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String titular = tfTitular.getText();
                String gananciaPretendida = tfGanPre.getText();
                float montoTotal = Float.parseFloat(tfMontoTotal.getText());
                if(titular ) {
                    // validacion de string
                }


                Obra obra = new Obra();
                obra.setTitular(titular);
                obra.setGananciaPrentendida(gananciaPretendida);

            }
        });
    }
    }

