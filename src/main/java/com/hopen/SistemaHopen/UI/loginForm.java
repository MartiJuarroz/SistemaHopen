package com.hopen.SistemaHopen.UI;

import com.hopen.SistemaHopen.entities.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginForm extends JDialog{
    private JTextField tfUsuario;
    private JPasswordField pfContraseña;
    private JButton btnEntrar;
    private JButton btnCancelar;
    private JPanel loginPanel;

    public loginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = tfUsuario.getText();
                String contraseña = String.valueOf(pfContraseña.getPassword());

                usuario = getAuthenticadedUser(nombreUsuario, contraseña);

                if (usuario != null) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(loginForm.this,
                            "Usuario o Contraseña invalidos",
                            "Intente nuevamente",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    public Usuario usuario;
    private Usuario getAuthenticadedUser(String nombreUsuario, String contraseña){
        Usuario usuario = null;
        return usuario;
    }


    public static void main (String[] args){

        loginForm loginForm = new loginForm(null);
    }

}
