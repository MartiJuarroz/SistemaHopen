/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hopen.SistemaHopen.UI;

import com.hopen.SistemaHopen.entities.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lisan
 */
public class ConexionDB {
    
    public ConexionDB(String query){
        
        conexion(query);
       
    }
    
        final String DB_URL = "jdbc:mysql://localhost:3306/db_hopen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        private void conexion(String query){
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = query;
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
        }
    
    
    
    
}
