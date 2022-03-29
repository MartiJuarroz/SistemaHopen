package com.hopen.SistemaHopen.UI;

import com.hopen.SistemaHopen.entities.Usuario;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Locale;

public class LoginForm extends JDialog {
    private JTextField tfUsuario;
    private JPasswordField pfContraseña;
    private JButton btnEntrar;
    private JButton btnCancelar;
    private JPanel loginPanel;
    private JCheckBox recordarmeCheckBox;
    private JLabel recuperarContraseña;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnEntrar.addActionListener(e -> {
            String nombreUsuario = tfUsuario.getText();
            String contraseña = String.valueOf(pfContraseña.getPassword());

            usuario = getAuthenticadedUser(nombreUsuario, contraseña);

            if (usuario != null) {
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginForm.this,
                        "Usuario o Contraseña invalidos",
                        "Intente nuevamente",
                        JOptionPane.ERROR_MESSAGE);
                pfContraseña.setText("");
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public Usuario usuario;

    private Usuario getAuthenticadedUser(String nombre_usuario, String contraseña) {
        Usuario usuario = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/db_hopen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM usuario WHERE nombre_usuario=? AND contraseña=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nombre_usuario);
            preparedStatement.setString(2, contraseña);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                String nombreU = resultSet.getString("nombre_usuario");
                String pass = resultSet.getString("contraseña");
                System.out.println("Successful Authentication of: " + nombreU);
            }


            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }


    public static void main(String[] args) {

        LoginForm loginForm = new LoginForm(null);
        Usuario usuario = loginForm.usuario;
   /*     if (usuario != null) {
            System.out.println("Successful Authentication of: " + nombreU);
        } else {
            System.out.println("Authentication canceled");
        }*/
    }

}


