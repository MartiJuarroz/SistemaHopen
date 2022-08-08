/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.hopen.SistemaHopen.UI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;

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
            String sql = "SELECT aluminio_id FROM obra WHERE titular=?";
            ps = ConexionDB.getConnection().prepareStatement(sql);
            ps.setString(1, nombreTitular);
            ResultSet resultSet = ps.executeQuery();
            
            int idAlu =0;
            while(resultSet.next()){
                idAlu = resultSet.getInt(1);
            }
            
            //Busco los datos de aluminio
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
            Double diferenciaPesos = totReal-totPresupuesto;
            int diferenciaKg = kiloFact-kiloPresu;
            //asignar valores a los textfields
            presuAluminioPesos.setText(Double.toString(totPresupuesto));
            presuAluminioKg.setText(Integer.toString(kiloPresu));
            facAluminioPesos.setText(Double.toString(totReal));
            facAluminioKg.setText(Integer.toString(kiloFact));
            difAluminioPesos.setText(Double.toString(diferenciaPesos));
            difAluminioKg.setText(Integer.toString(diferenciaKg));
            //calculo de porcentajes con validaciones
            if(kiloPresu == 0){
                porcAluminioPesos.setText("0.0");
            }else{
                int porcentajeKg = ((kiloFact/kiloPresu)-1)*100;
                porcAluminioKg.setText(Double.toString(porcentajeKg));
            }
            if(totPresupuesto == 0.0){
                porcAluminioPesos.setText("0.0");
            }else{
                Double porcentajePesos = ((totReal/totPresupuesto)-1)*100;
                porcAluminioKg.setText(Double.toString(porcentajePesos));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
        difAluminioPesos = new javax.swing.JTextField();
        porcAluminioPesos = new javax.swing.JTextField();
        facAluminioPesos = new javax.swing.JTextField();
        difAluminioKg = new javax.swing.JTextField();
        porcAluminioKg = new javax.swing.JTextField();
        facAluminioKg = new javax.swing.JTextField();
        presuAluminioKg = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        porcVidrio = new javax.swing.JTextField();
        presuVidrio = new javax.swing.JTextField();
        difVidrio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        facVidrio = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        difViajes = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        facViajes = new javax.swing.JTextField();
        porcViajes = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        presuViajes = new javax.swing.JTextField();
        difMO = new javax.swing.JTextField();
        presuMO = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        facMO = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        porcMO = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        presuAcc = new javax.swing.JTextField();
        facAcc = new javax.swing.JTextField();
        difAcc = new javax.swing.JTextField();
        porcAcc = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        cbReportes = new javax.swing.JComboBox<>();
        btnVerDatos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        presuAluminioPesos.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        presuAluminioPesos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuAluminioPesos.setText("500");
        presuAluminioPesos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        difAluminioPesos.setEditable(false);
        difAluminioPesos.setBackground(new java.awt.Color(255, 255, 255));
        difAluminioPesos.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        difAluminioPesos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difAluminioPesos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        porcAluminioPesos.setEditable(false);
        porcAluminioPesos.setBackground(new java.awt.Color(255, 255, 255));
        porcAluminioPesos.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        porcAluminioPesos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcAluminioPesos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        facAluminioPesos.setEditable(false);
        facAluminioPesos.setBackground(new java.awt.Color(255, 255, 255));
        facAluminioPesos.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        facAluminioPesos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facAluminioPesos.setText("500");
        facAluminioPesos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        facAluminioPesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facAluminioPesosActionPerformed(evt);
            }
        });

        difAluminioKg.setEditable(false);
        difAluminioKg.setBackground(new java.awt.Color(255, 255, 255));
        difAluminioKg.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        difAluminioKg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difAluminioKg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        porcAluminioKg.setEditable(false);
        porcAluminioKg.setBackground(new java.awt.Color(255, 255, 255));
        porcAluminioKg.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        porcAluminioKg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcAluminioKg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        facAluminioKg.setEditable(false);
        facAluminioKg.setBackground(new java.awt.Color(255, 255, 255));
        facAluminioKg.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        facAluminioKg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facAluminioKg.setText("500");
        facAluminioKg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        facAluminioKg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facAluminioKgActionPerformed(evt);
            }
        });

        presuAluminioKg.setEditable(false);
        presuAluminioKg.setBackground(new java.awt.Color(255, 255, 255));
        presuAluminioKg.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        presuAluminioKg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuAluminioKg.setText("500");
        presuAluminioKg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Aluminio Kg");

        porcVidrio.setEditable(false);
        porcVidrio.setBackground(new java.awt.Color(255, 255, 255));
        porcVidrio.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        porcVidrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcVidrio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        presuVidrio.setEditable(false);
        presuVidrio.setBackground(new java.awt.Color(255, 255, 255));
        presuVidrio.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        presuVidrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuVidrio.setText("500");
        presuVidrio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        difVidrio.setEditable(false);
        difVidrio.setBackground(new java.awt.Color(255, 255, 255));
        difVidrio.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        difVidrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difVidrio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Vidrio");

        facVidrio.setEditable(false);
        facVidrio.setBackground(new java.awt.Color(255, 255, 255));
        facVidrio.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        facVidrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facVidrio.setText("500");
        facVidrio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        facVidrio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facVidrioActionPerformed(evt);
            }
        });

        difViajes.setEditable(false);
        difViajes.setBackground(new java.awt.Color(255, 255, 255));
        difViajes.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        difViajes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difViajes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        facViajes.setEditable(false);
        facViajes.setBackground(new java.awt.Color(255, 255, 255));
        facViajes.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        facViajes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facViajes.setText("500");
        facViajes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        facViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facViajesActionPerformed(evt);
            }
        });

        porcViajes.setEditable(false);
        porcViajes.setBackground(new java.awt.Color(255, 255, 255));
        porcViajes.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        porcViajes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcViajes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setText("Viajes");

        presuViajes.setEditable(false);
        presuViajes.setBackground(new java.awt.Color(255, 255, 255));
        presuViajes.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        presuViajes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuViajes.setText("500");
        presuViajes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        difMO.setEditable(false);
        difMO.setBackground(new java.awt.Color(255, 255, 255));
        difMO.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        difMO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difMO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        presuMO.setEditable(false);
        presuMO.setBackground(new java.awt.Color(255, 255, 255));
        presuMO.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        presuMO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuMO.setText("500");
        presuMO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        facMO.setEditable(false);
        facMO.setBackground(new java.awt.Color(255, 255, 255));
        facMO.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        facMO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facMO.setText("500");
        facMO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        facMO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facMOActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setText("Mano de Obra");

        porcMO.setEditable(false);
        porcMO.setBackground(new java.awt.Color(255, 255, 255));
        porcMO.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        porcMO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcMO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel12.setText("Accesorios");

        presuAcc.setEditable(false);
        presuAcc.setBackground(new java.awt.Color(255, 255, 255));
        presuAcc.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        presuAcc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presuAcc.setText("500");
        presuAcc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        facAcc.setEditable(false);
        facAcc.setBackground(new java.awt.Color(255, 255, 255));
        facAcc.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        facAcc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facAcc.setText("500");
        facAcc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        facAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facAccActionPerformed(evt);
            }
        });

        difAcc.setEditable(false);
        difAcc.setBackground(new java.awt.Color(255, 255, 255));
        difAcc.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        difAcc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        difAcc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        porcAcc.setEditable(false);
        porcAcc.setBackground(new java.awt.Color(255, 255, 255));
        porcAcc.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        porcAcc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        porcAcc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnVerDatos))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(descripcion)
                                        .addGap(437, 437, 437)
                                        .addComponent(presupuesto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                        .addComponent(facturado)))
                                .addGap(51, 51, 51)
                                .addComponent(diferencia)
                                .addGap(40, 40, 40)
                                .addComponent(diferenciaPor)
                                .addGap(43, 43, 43))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(426, 426, 426)
                                .addComponent(presuAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(426, 426, 426)
                                .addComponent(presuAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(426, 426, 426)
                                .addComponent(presuVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(426, 426, 426)
                                .addComponent(presuViajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(409, 409, 409)
                                .addComponent(presuMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(426, 426, 426)
                                .addComponent(presuAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(503, 503, 503)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(facAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(difAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(porcAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(facAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(difAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(porcAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(facVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(difVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(porcVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(facViajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(difViajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(porcViajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(facMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(difMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(porcMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(facAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(difAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(porcAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(433, 433, 433)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(703, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(titulo)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descripcion)
                    .addComponent(diferencia)
                    .addComponent(diferenciaPor)
                    .addComponent(facturado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuViajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difViajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcViajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facViajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presuMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porcMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(107, 107, 107)
                    .addComponent(jLabel7)
                    .addContainerGap(496, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void facAluminioPesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facAluminioPesosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facAluminioPesosActionPerformed

    private void facAluminioKgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facAluminioKgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facAluminioKgActionPerformed

    private void facVidrioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facVidrioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facVidrioActionPerformed

    private void facViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facViajesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facViajesActionPerformed

    private void facMOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facMOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facMOActionPerformed

    private void facAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facAccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facAccActionPerformed

    private void btnVerDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDatosActionPerformed
        mostrarDatos();
    }//GEN-LAST:event_btnVerDatosActionPerformed
    
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
    private javax.swing.JTextField facMO;
    private javax.swing.JTextField facViajes;
    private javax.swing.JTextField facVidrio;
    private javax.swing.JLabel facturado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
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
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
