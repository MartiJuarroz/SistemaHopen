package com.hopen.SistemaHopen.UI;

import com.hopen.SistemaHopen.entities.Obra;
import com.hopen.SistemaHopen.metodos.TextPrompt;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Locale;

public class CargarObra extends JFrame {
    private JTextField tfTitular;
    private JTextField tfMontoTotal;
    private JTextField tfComision;
    private JTextField tfGanPre;
    private JTextField tfCostosFijos;
    private JButton siguienteButton;
    private JButton salirButton;
    private JPanel cargarObraPanel;

    public CargarObra() {
        //     super(parent);
        setTitle("Login");
        setContentPane(cargarObraPanel);
        setMinimumSize(new Dimension(501, 500));
        //  setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        TextPrompt placeholderMontoTotal = new TextPrompt("Use el formato 100.000", tfMontoTotal);

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
                float gananciaPretendida = Float.parseFloat(tfGanPre.getText());
                float montoTotal = Float.parseFloat(tfMontoTotal.getText());
         /*       if(montoTotal ) {
                    // validacion de string
                }*/


      /*          Obra obra = new Obra();
                obra.setTitular(titular);
                obra.setGananciaPrentendida(gananciaPretendida);
*/
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {

        //   LoginForm loginForm = new LoginForm(null);
        CargarObra co = new CargarObra();
    }

}

