/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.hopen.SistemaHopen.UI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Martiniano
 */
public class Reportes extends javax.swing.JFrame {

    /**
     * Creates new form Reportes
     */
    public Reportes() {
        initComponents();
        llenarComboBox();
    }
    
    private void llenarComboBox(){
        PreparedStatement ps; 
        try{
            Connection con = ConexionDB.getConnection();
            String sql ="SELECT o.titular FROM obra o";
            ps = ConexionDB.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
        
        while(resultSet.next()){
            String item = resultSet.getString(1);
            cbReportes.addItem(item);
        }
        ConexionDB.endConnection(con);
         }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void mostrarDatos(){
        PreparedStatement ps;
        
        String nombreTitular = cbReportes.getSelectedItem().toString();
        
        try{
            //Primero busco los ids de la obra elegida
            Connection con = ConexionDB.getConnection();
            String sql = "SELECT aluminio_id, compra_accesorio_id, compra_vidrio_id, viaje_id, mano_obra_id FROM obra WHERE titular=?";
            ps = ConexionDB.getConnection().prepareStatement(sql);
            ps.setString(1, nombreTitular);
            ResultSet resultSet = ps.executeQuery();
            
            int idAlu =0;
            int idAcc =0;
            int idVidrio=0;
            int idViaje =0;
            int idMO =0;
            while(resultSet.next()){
                idAlu = resultSet.getInt(1);
                idAcc = resultSet.getInt(2);
                idVidrio = resultSet.getInt(3);
                idViaje = resultSet.getInt(4);
                idMO = resultSet.getInt(5);
            }
            
            //---------------------Aluminio-------------------
            String sql2 = "SELECT  total_presupuesto, total_real, kilo_presupuestado, kilo_factura FROM aluminio WHERE id=?";
            ps = ConexionDB.getConnection().prepareStatement(sql2);
            ps.setInt(1, idAlu);
            resultSet = ps.executeQuery();
            
            //declaracion de variables auxiliares
            double totPresupuesto = 0.0;
            double totReal = 0.0;
            int kiloPresu = 0;
            int kiloFact = 0;
            while(resultSet.next()){
                totPresupuesto = resultSet.getDouble("total_presupuesto");
                totReal = resultSet.getDouble("total_real");
                kiloPresu = resultSet.getInt("kilo_presupuestado");
                kiloFact = resultSet.getInt("kilo_factura");
            }
            //definir diferencias
            Double diferenciaPesos = totPresupuesto-totReal;
            int diferenciaKg = kiloPresu-kiloFact;
            //asignar valores a los textfields
            presuAluminioPesos.setText(Double.toString(totPresupuesto));
            presuAluminioKg.setText(Integer.toString(kiloPresu));
            facAluminioPesos.setText(Double.toString(totReal));
            facAluminioKg.setText(Integer.toString(kiloFact));
            difAluminioPesos.setText(Double.toString(diferenciaPesos));
            difAluminioKg.setText(Integer.toString(diferenciaKg));
            //calculo de porcentajes con validaciones
            
            DecimalFormat df = new DecimalFormat("#.00");
            if(kiloPresu == 0){
                porcAluminioKg.setText("0.0");
            }else{
                Double porcentajeKg =(((kiloFact*1.0)/(kiloPresu*1.0))-1)*100;
                porcAluminioKg.setText(df.format(porcentajeKg));
            }
            if(totPresupuesto == 0.0){
                porcAluminioPesos.setText("0.0");
            }else{
                Double porcentajePesos = ((totReal/totPresupuesto)-1)*100;
                porcAluminioPesos.setText(df.format(porcentajePesos));
            }
            
            //---------------------Accesorio-------------------
            String sql3 = "SELECT  total_presupuesto, total_real FROM compra_accesorio WHERE id=?";
            ps = ConexionDB.getConnection().prepareStatement(sql3);
            ps.setInt(1, idAcc);
            resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                totPresupuesto = resultSet.getDouble("total_presupuesto");
                totReal = resultSet.getDouble("total_real");
            }
            //definir diferencias
            diferenciaPesos = totPresupuesto-totReal;
            //asignar valores a los textfields
            presuAcc.setText(Double.toString(totPresupuesto));
            facAcc.setText(Double.toString(totReal));
            difAcc.setText(Double.toString(diferenciaPesos));
            //calculo de porcentajes con validaciones
            if(totPresupuesto == 0.0){
                porcAcc.setText("0.0");
            }else{
                Double porcentajePesos = ((totReal/totPresupuesto)-1)*100;
                porcAcc.setText(df.format(porcentajePesos));
            }
            //---------------------Vidrio-------------------
            String sql4 = "SELECT  total_presupuesto, total_real FROM compra_vidrio WHERE id=?";
            ps = ConexionDB.getConnection().prepareStatement(sql4);
            ps.setInt(1, idVidrio);
            resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                totPresupuesto = resultSet.getDouble("total_presupuesto");
                totReal = resultSet.getDouble("total_real");
            }
            //definir diferencias
            diferenciaPesos = totPresupuesto-totReal;
            //asignar valores a los textfields
            presuVidrio.setText(Double.toString(totPresupuesto));
            facVidrio.setText(Double.toString(totReal));
            difVidrio.setText(Double.toString(diferenciaPesos));
            //calculo de porcentajes con validaciones
            if(totPresupuesto == 0.0){
                porcVidrio.setText("0.0");
            }else{
                Double porcentajePesos = ((totReal/totPresupuesto)-1)*100;
                porcVidrio.setText(df.format(porcentajePesos));
            }
            //---------------------Viajes-------------------
            String sql5 = "SELECT  total_presupuesto, total_real FROM viaje WHERE id=?";
            ps = ConexionDB.getConnection().prepareStatement(sql5);
            ps.setInt(1, idViaje);
            resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                totPresupuesto = resultSet.getDouble("total_presupuesto");
                totReal = resultSet.getDouble("total_real");
            }
            //definir diferencias
            diferenciaPesos = totPresupuesto-totReal;
            //asignar valores a los textfields
            presuViajes.setText(Double.toString(totPresupuesto));
            facViajes.setText(Double.toString(totReal));
            difViajes.setText(Double.toString(diferenciaPesos));
            //calculo de porcentajes con validaciones
            if(totPresupuesto == 0.0){
                porcViajes.setText("0.0");
            }else{
                Double porcentajePesos = ((totReal/totPresupuesto)-1)*100;
                porcViajes.setText(df.format(porcentajePesos));
            }
            //---------------------Mano Obra-------------------
            String sql6 = "SELECT  total_presupuesto, total_real FROM mano_obra WHERE id=?";
            ps = ConexionDB.getConnection().prepareStatement(sql6);
            ps.setInt(1, idMO);
            resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                totPresupuesto = resultSet.getDouble("total_presupuesto");
                totReal = resultSet.getDouble("total_real");
            }
            //definir diferencias
            diferenciaPesos = totPresupuesto-totReal;
            //asignar valores a los textfields
            presuMO.setText(Double.toString(totPresupuesto));
            facMo.setText(Double.toString(totReal));
            difMO.setText(Double.toString(diferenciaPesos));
            //calculo de porcentajes con validaciones
            if(totPresupuesto == 0.0){
                porcMO.setText("0.0");
            }else{
                Double porcentajePesos = ((totReal/totPresupuesto)-1)*100;
                porcMO.setText(df.format(porcentajePesos));
            }
            
            ConexionDB.endConnection(con);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        reporteMateriales = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        titulo.setHorizontalAlignment(JLabel.CENTER);
        presupuesto = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        descripcion = new javax.swing.JLabel();
        diferencia = new javax.swing.JLabel();
        diferenciaPor = new javax.swing.JLabel();
        facturado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        presuAluminioPesos = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        cbReportes = new javax.swing.JComboBox<>();
        btnVerDatos = new javax.swing.JButton();
        facAluminioPesos = new javax.swing.JTextField();
        difAluminioPesos = new javax.swing.JTextField();
        porcAluminioPesos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        presuAluminioKg = new javax.swing.JTextField();
        facAluminioKg = new javax.swing.JTextField();
        difAluminioKg = new javax.swing.JTextField();
        porcAluminioKg = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        difAcc = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        facAcc = new javax.swing.JTextField();
        presuAcc = new javax.swing.JTextField();
        porcAcc = new javax.swing.JTextField();
        facVidrio = new javax.swing.JTextField();
        presuVidrio = new javax.swing.JTextField();
        porcVidrio = new javax.swing.JTextField();
        difVidrio = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        facViajes = new javax.swing.JTextField();
        difViajes = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        presuViajes = new javax.swing.JTextField();
        porcViajes = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        facMo = new javax.swing.JTextField();
        porcMO = new javax.swing.JTextField();
        difMO = new javax.swing.JTextField();
        presuMO = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        SalirBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 204, 255));

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));

        jTabbedPane1.setBackground(new java.awt.Color(0, 204, 255));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N

        reporteMateriales.setBackground(new java.awt.Color(255, 255, 255));

        titulo.setBackground(new java.awt.Color(255, 255, 255));
        titulo.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        titulo.setText("REPORTE");

        presupuesto.setBackground(new java.awt.Color(255, 255, 255));
        presupuesto.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        presupuesto.setText("Presupuesto");

        descripcion.setBackground(new java.awt.Color(255, 255, 255));
        descripcion.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        descripcion.setText("Descripción");

        diferencia.setBackground(new java.awt.Color(255, 255, 255));
        diferencia.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        diferencia.setText("Diferencia$");

        diferenciaPor.setBackground(new java.awt.Color(255, 255, 255));
        diferenciaPor.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        diferenciaPor.setText("Diferencia porcentual%");

        facturado.setBackground(new java.awt.Color(255, 255, 255));
        facturado.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        facturado.setText("Facturado");

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel1.setText("Aluminio $");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel7.setText("Obra: ");

        presuAluminioPesos.setEditable(false);
        presuAluminioPesos.setBackground(new java.awt.Color(255, 255, 255));
        presuAluminioPesos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        presuAluminioPesos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuAluminioPesos.setBorder(null);
        presuAluminioPesos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cbReportes.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        btnVerDatos.setBackground(new java.awt.Color(0, 204, 255));
        btnVerDatos.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btnVerDatos.setForeground(new java.awt.Color(255, 255, 255));
        btnVerDatos.setText("Ver datos");
        btnVerDatos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDatosActionPerformed(evt);
            }
        });

        facAluminioPesos.setEditable(false);
        facAluminioPesos.setBackground(new java.awt.Color(255, 255, 255));
        facAluminioPesos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        facAluminioPesos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facAluminioPesos.setBorder(null);
        facAluminioPesos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        difAluminioPesos.setEditable(false);
        difAluminioPesos.setBackground(new java.awt.Color(255, 255, 255));
        difAluminioPesos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        difAluminioPesos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difAluminioPesos.setBorder(null);
        difAluminioPesos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        porcAluminioPesos.setEditable(false);
        porcAluminioPesos.setBackground(new java.awt.Color(255, 255, 255));
        porcAluminioPesos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        porcAluminioPesos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcAluminioPesos.setBorder(null);
        porcAluminioPesos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel2.setText("Aluminio Kg");

        presuAluminioKg.setEditable(false);
        presuAluminioKg.setBackground(new java.awt.Color(255, 255, 255));
        presuAluminioKg.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        presuAluminioKg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuAluminioKg.setBorder(null);
        presuAluminioKg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        facAluminioKg.setEditable(false);
        facAluminioKg.setBackground(new java.awt.Color(255, 255, 255));
        facAluminioKg.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        facAluminioKg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facAluminioKg.setBorder(null);
        facAluminioKg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        difAluminioKg.setEditable(false);
        difAluminioKg.setBackground(new java.awt.Color(255, 255, 255));
        difAluminioKg.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        difAluminioKg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difAluminioKg.setBorder(null);
        difAluminioKg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        porcAluminioKg.setEditable(false);
        porcAluminioKg.setBackground(new java.awt.Color(255, 255, 255));
        porcAluminioKg.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        porcAluminioKg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcAluminioKg.setBorder(null);
        porcAluminioKg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        difAcc.setEditable(false);
        difAcc.setBackground(new java.awt.Color(255, 255, 255));
        difAcc.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        difAcc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difAcc.setBorder(null);
        difAcc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("Accesorios");

        facAcc.setEditable(false);
        facAcc.setBackground(new java.awt.Color(255, 255, 255));
        facAcc.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        facAcc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facAcc.setBorder(null);
        facAcc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        presuAcc.setEditable(false);
        presuAcc.setBackground(new java.awt.Color(255, 255, 255));
        presuAcc.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        presuAcc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuAcc.setBorder(null);
        presuAcc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        porcAcc.setEditable(false);
        porcAcc.setBackground(new java.awt.Color(255, 255, 255));
        porcAcc.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        porcAcc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcAcc.setBorder(null);
        porcAcc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        facVidrio.setEditable(false);
        facVidrio.setBackground(new java.awt.Color(255, 255, 255));
        facVidrio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        facVidrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facVidrio.setBorder(null);
        facVidrio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        presuVidrio.setEditable(false);
        presuVidrio.setBackground(new java.awt.Color(255, 255, 255));
        presuVidrio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        presuVidrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuVidrio.setBorder(null);
        presuVidrio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        porcVidrio.setEditable(false);
        porcVidrio.setBackground(new java.awt.Color(255, 255, 255));
        porcVidrio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        porcVidrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcVidrio.setBorder(null);
        porcVidrio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        difVidrio.setEditable(false);
        difVidrio.setBackground(new java.awt.Color(255, 255, 255));
        difVidrio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        difVidrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difVidrio.setBorder(null);
        difVidrio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setText("Vidrio");

        facViajes.setEditable(false);
        facViajes.setBackground(new java.awt.Color(255, 255, 255));
        facViajes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        facViajes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facViajes.setBorder(null);
        facViajes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        difViajes.setEditable(false);
        difViajes.setBackground(new java.awt.Color(255, 255, 255));
        difViajes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        difViajes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difViajes.setBorder(null);
        difViajes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setText("Viajes");

        presuViajes.setEditable(false);
        presuViajes.setBackground(new java.awt.Color(255, 255, 255));
        presuViajes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        presuViajes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuViajes.setBorder(null);
        presuViajes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        porcViajes.setEditable(false);
        porcViajes.setBackground(new java.awt.Color(255, 255, 255));
        porcViajes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        porcViajes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcViajes.setBorder(null);
        porcViajes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setText("Mano de Obra");

        facMo.setEditable(false);
        facMo.setBackground(new java.awt.Color(255, 255, 255));
        facMo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        facMo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facMo.setBorder(null);
        facMo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        porcMO.setEditable(false);
        porcMO.setBackground(new java.awt.Color(255, 255, 255));
        porcMO.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        porcMO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcMO.setBorder(null);
        porcMO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        difMO.setEditable(false);
        difMO.setBackground(new java.awt.Color(255, 255, 255));
        difMO.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        difMO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difMO.setBorder(null);
        difMO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        presuMO.setEditable(false);
        presuMO.setBackground(new java.awt.Color(255, 255, 255));
        presuMO.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        presuMO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuMO.setBorder(null);
        presuMO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        SalirBtn.setBackground(new java.awt.Color(255, 153, 51));
        SalirBtn.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        SalirBtn.setForeground(new java.awt.Color(255, 255, 255));
        SalirBtn.setText("Volver");
        SalirBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SalirBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalirBtnMouseClicked(evt);
            }
        });
        SalirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reporteMaterialesLayout = new javax.swing.GroupLayout(reporteMateriales);
        reporteMateriales.setLayout(reporteMaterialesLayout);
        reporteMaterialesLayout.setHorizontalGroup(
            reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reporteMaterialesLayout.createSequentialGroup()
                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reporteMaterialesLayout.createSequentialGroup()
                                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnVerDatos))
                                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(descripcion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(presupuesto)
                                        .addGap(114, 114, 114)
                                        .addComponent(facturado)
                                        .addGap(66, 66, 66)))
                                .addGap(22, 22, 22)
                                .addComponent(diferencia)
                                .addGap(69, 69, 69)
                                .addComponent(diferenciaPor)
                                .addGap(43, 43, 43))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                        .addGap(503, 503, 503)
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(presuAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                                .addComponent(presuAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                                .addComponent(presuAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                                .addComponent(presuVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                                .addComponent(presuViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                                .addComponent(presuMO, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facMo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difMO, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcMO, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reporteMaterialesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(SalirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(reporteMaterialesLayout.createSequentialGroup()
                    .addGap(433, 433, 433)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(750, Short.MAX_VALUE)))
        );
        reporteMaterialesLayout.setVerticalGroup(
            reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reporteMaterialesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(titulo)
                .addGap(36, 36, 36)
                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descripcion)
                    .addComponent(diferencia)
                    .addComponent(diferenciaPor)
                    .addComponent(facturado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuMO, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facMo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difMO, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcMO, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(119, 119, 119)
                .addComponent(SalirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(232, 232, 232))
            .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(reporteMaterialesLayout.createSequentialGroup()
                    .addGap(107, 107, 107)
                    .addComponent(jLabel7)
                    .addContainerGap(759, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Materiales", reporteMateriales);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1257, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Diferencias Generales", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1257, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Incidencias", jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1257, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1257, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 705, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 660, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDatosActionPerformed
        mostrarDatos();
    }//GEN-LAST:event_btnVerDatosActionPerformed

    private void SalirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirBtnActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Si vuelve atras no se guardarán los datos.", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
            == JOptionPane.YES_OPTION){
            dispose();
            try {
                new Menu().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(CargarObra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_SalirBtnActionPerformed

    private void SalirBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirBtnMouseClicked
        // TODO add your handling code here:
        dispose();
        new Menu().setVisible(true);
    }//GEN-LAST:event_SalirBtnMouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SalirBtn;
    private javax.swing.JButton btnVerDatos;
    private javax.swing.JComboBox<String> cbReportes;
    private javax.swing.JLabel descripcion;
    private javax.swing.JTextField difAcc;
    private javax.swing.JTextField difAluminioKg;
    private javax.swing.JTextField difAluminioPesos;
    private javax.swing.JTextField difMO;
    private javax.swing.JTextField difViajes;
    private javax.swing.JTextField difVidrio;
    private javax.swing.JLabel diferencia;
    private javax.swing.JLabel diferenciaPor;
    private javax.swing.JTextField facAcc;
    private javax.swing.JTextField facAluminioKg;
    private javax.swing.JTextField facAluminioPesos;
    private javax.swing.JTextField facMo;
    private javax.swing.JTextField facViajes;
    private javax.swing.JTextField facVidrio;
    private javax.swing.JLabel facturado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField porcAcc;
    private javax.swing.JTextField porcAluminioKg;
    private javax.swing.JTextField porcAluminioPesos;
    private javax.swing.JTextField porcMO;
    private javax.swing.JTextField porcViajes;
    private javax.swing.JTextField porcVidrio;
    private javax.swing.JTextField presuAcc;
    private javax.swing.JTextField presuAluminioKg;
    private javax.swing.JTextField presuAluminioPesos;
    private javax.swing.JTextField presuMO;
    private javax.swing.JTextField presuViajes;
    private javax.swing.JTextField presuVidrio;
    private javax.swing.JLabel presupuesto;
    private javax.swing.JPanel reporteMateriales;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
