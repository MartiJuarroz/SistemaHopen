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
    
    private double presuTotal = 0;
    private double facturaTotal = 0;
    
    private void mostrarDatos(){
        PreparedStatement ps;
        
        String nombreTitular = cbReportes.getSelectedItem().toString();
        nombreObra.setText(nombreTitular);
        nombreObra2.setText(nombreTitular);
        
        try{
            //Primero busco los ids de la obra elegida
            Connection con = ConexionDB.getConnection();
            String sql = "SELECT aluminio_id, compra_accesorio_id, compra_vidrio_id, viaje_id, mano_obra_id, ganancia_pretendida, comision FROM obra WHERE titular=?";
            ps = ConexionDB.getConnection().prepareStatement(sql);
            ps.setString(1, nombreTitular);
            ResultSet resultSet = ps.executeQuery();
            
            int idAlu =0;
            int idAcc =0;
            int idVidrio=0;
            int idViaje =0;
            int idMO =0;
            double gananciaPret = 0.0;
            double comision = 0.0;
            while(resultSet.next()){
                idAlu = resultSet.getInt(1);
                idAcc = resultSet.getInt(2);
                idVidrio = resultSet.getInt(3);
                idViaje = resultSet.getInt(4);
                idMO = resultSet.getInt(5);
                gananciaPret = resultSet.getInt(6);
                comision = resultSet.getInt(7);
            }
            
            //---------------------Aluminio-------------------
            String sql2 = "SELECT  total_presupuesto, total_real, kilo_presupuestado, kilo_factura FROM aluminio WHERE id=?";
            ps = ConexionDB.getConnection().prepareStatement(sql2);
            ps.setInt(1, idAlu);
            resultSet = ps.executeQuery();
            
            //declaracion de variables auxiliares
            double totPresupuestoAlu = 0.0;
            double totRealAlu = 0.0;
            int kiloPresu = 0;
            int kiloFact = 0;
            while(resultSet.next()){
                totPresupuestoAlu = resultSet.getDouble("total_presupuesto");
                totRealAlu = resultSet.getDouble("total_real");
                kiloPresu = resultSet.getInt("kilo_presupuestado");
                kiloFact = resultSet.getInt("kilo_factura");
            }
            //definir diferencias
            Double diferenciaPesos = totPresupuestoAlu-totRealAlu;
            int diferenciaKg = kiloPresu-kiloFact;
            //asignar valores a los textfields
            presuAluminioPesos.setText(Double.toString(totPresupuestoAlu));
            presuAluminioKg.setText(Integer.toString(kiloPresu));
            facAluminioPesos.setText(Double.toString(totRealAlu));
            facAluminioKg.setText(Integer.toString(kiloFact));
            difAluminioPesos.setText(Double.toString(diferenciaPesos));
            difAluminioKg.setText(Integer.toString(diferenciaKg));
            
            //sumatoria de totales
            presuTotal = presuTotal + totPresupuestoAlu;
            facturaTotal = facturaTotal + totRealAlu;
            
            //calculo de porcentajes con validaciones
            DecimalFormat df = new DecimalFormat("#.00");
            if(kiloPresu == 0){
                porcAluminioKg.setText("0.0");
            }else{
                Double porcentajeKg =(((kiloFact*1.0)/(kiloPresu*1.0))-1)*100;
                porcAluminioKg.setText(df.format(porcentajeKg));
            }
            if(totPresupuestoAlu == 0.0){
                porcAluminioPesos.setText("0.0");
            }else{
                Double porcentajePesos = ((totRealAlu/totPresupuestoAlu)-1)*100;
                porcAluminioPesos.setText(df.format(porcentajePesos));
            }
            
            //---------------------Accesorio-------------------
            String sql3 = "SELECT  total_presupuesto, total_real FROM compra_accesorio WHERE id=?";
            ps = ConexionDB.getConnection().prepareStatement(sql3);
            ps.setInt(1, idAcc);
            resultSet = ps.executeQuery();
            
            double totPresupuestoAcc = 0.0;
            double totRealAcc = 0.0;
            while(resultSet.next()){
                totPresupuestoAcc = resultSet.getDouble("total_presupuesto");
                totRealAcc = resultSet.getDouble("total_real");
            }
            //definir diferencias
            diferenciaPesos = totPresupuestoAcc-totRealAcc;
            //asignar valores a los textfields
            presuAcc.setText(Double.toString(totPresupuestoAcc));
            facAcc.setText(Double.toString(totRealAcc));
            difAcc.setText(Double.toString(diferenciaPesos));
            
            //sumatoria de totales
            presuTotal = presuTotal + totPresupuestoAcc;
            facturaTotal = facturaTotal + totRealAcc;
            
            //calculo de porcentajes con validaciones
            if(totPresupuestoAcc == 0.0){
                porcAcc.setText("0.0");
            }else{
                Double porcentajePesos = ((totRealAcc/totPresupuestoAcc)-1)*100;
                porcAcc.setText(df.format(porcentajePesos));
            }
            //---------------------Vidrio-------------------
            String sql4 = "SELECT  total_presupuesto, total_real FROM compra_vidrio WHERE id=?";
            ps = ConexionDB.getConnection().prepareStatement(sql4);
            ps.setInt(1, idVidrio);
            resultSet = ps.executeQuery();
            
            double totPresupuestoVid = 0.0;
            double totRealVid = 0.0;
            while(resultSet.next()){
                totPresupuestoVid = resultSet.getDouble("total_presupuesto");
                totRealVid = resultSet.getDouble("total_real");
            }
            //definir diferencias
            diferenciaPesos = totPresupuestoVid-totRealVid;
            //asignar valores a los textfields
            presuVidrio.setText(Double.toString(totPresupuestoVid));
            facVidrio.setText(Double.toString(totRealVid));
            difVidrio.setText(Double.toString(diferenciaPesos));
            
            //sumatoria de totales
            presuTotal = presuTotal + totPresupuestoVid;
            facturaTotal = facturaTotal + totRealVid;            
            
            //calculo de porcentajes con validaciones
            if(totPresupuestoVid == 0.0){
                porcVidrio.setText("0.0");
            }else{
                Double porcentajePesos = ((totRealVid/totPresupuestoVid)-1)*100;
                porcVidrio.setText(df.format(porcentajePesos));
            }
            //---------------------Viajes-------------------
            String sql5 = "SELECT  total_presupuesto, total_real FROM viaje WHERE id=?";
            ps = ConexionDB.getConnection().prepareStatement(sql5);
            ps.setInt(1, idViaje);
            resultSet = ps.executeQuery();
            
            double totPresupuestoViaje = 0.0;
            double totRealViaje = 0.0;
            while(resultSet.next()){
                totPresupuestoViaje = resultSet.getDouble("total_presupuesto");
                totRealViaje = resultSet.getDouble("total_real");
            }
            //definir diferencias
            diferenciaPesos = totPresupuestoViaje-totRealViaje;
            //asignar valores a los textfields
            presuViajes.setText(Double.toString(totPresupuestoViaje));
            facViajes.setText(Double.toString(totRealViaje));
            difViajes.setText(Double.toString(diferenciaPesos));
            
            //sumatoria de totales
            presuTotal = presuTotal + totPresupuestoViaje;
            facturaTotal = facturaTotal + totRealViaje;
            
            //calculo de porcentajes con validaciones
            if(totPresupuestoViaje == 0.0){
                porcViajes.setText("0.0");
            }else{
                Double porcentajePesos = ((totRealViaje/totPresupuestoViaje)-1)*100;
                porcViajes.setText(df.format(porcentajePesos));
            }
            //---------------------Mano Obra-------------------
            String sql6 = "SELECT  total_presupuesto, total_real FROM mano_obra WHERE id=?";
            ps = ConexionDB.getConnection().prepareStatement(sql6);
            ps.setInt(1, idMO);
            resultSet = ps.executeQuery();
            
            double totPresupuestoMO = 0.0;
            double totRealMO = 0.0;
            while(resultSet.next()){
                totPresupuestoMO = resultSet.getDouble("total_presupuesto");
                totRealMO = resultSet.getDouble("total_real");
            }
            //definir diferencias
            diferenciaPesos = totPresupuestoMO-totRealMO;
            //asignar valores a los textfields
            presuMO.setText(Double.toString(totPresupuestoMO));
            facMo.setText(Double.toString(totRealMO));
            difMO.setText(Double.toString(diferenciaPesos));
            
            //sumatoria de totales
            presuTotal = presuTotal + totPresupuestoMO;
            facturaTotal = facturaTotal + totRealMO;
            
            //calculo de porcentajes con validaciones
            if(totPresupuestoMO == 0.0){
                porcMO.setText("0.0");
            }else{
                Double porcentajePesos = ((totRealMO/totPresupuestoMO)-1)*100;
                porcMO.setText(df.format(porcentajePesos));
            }
            
            double gananciaR = presuTotal-facturaTotal;
            double var = gananciaR/presuTotal;
            precioAbertura.setText(Double.toString(presuTotal));
            gastoReal.setText(Double.toString(facturaTotal));
            gananciaReal.setText(Double.toString(gananciaR));
            variacion.setText(df.format(var*100));
            //sumatoria de costos no esta muy claro
            
            incidenciaAlu.setText(df.format((totRealAlu/presuTotal*100)));
            incidenciaAcc.setText(df.format(totRealAcc/presuTotal*100));
            incidenciaVidrio.setText(df.format(totRealVid/presuTotal*100));
            incidenciaMO.setText(df.format(totRealMO/presuTotal*100));
            incidenciaViaje.setText(df.format(totRealViaje/presuTotal*100));
            incidenciaGan.setText(df.format(gananciaPret/presuTotal*100));
            incidenciaCom.setText(df.format(comision/presuTotal*100));
            
            
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
        difGeneral = new javax.swing.JPanel();
        titulo1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbReportes = new javax.swing.JComboBox<>();
        btnVerDatosDif = new javax.swing.JButton();
        SalirBtn1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        precioAbertura = new javax.swing.JTextField();
        gastoReal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        gananciaReal = new javax.swing.JTextField();
        variacion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        sumCostos = new javax.swing.JTextField();
        SalirBtn3 = new javax.swing.JButton();
        reporteMateriales = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
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
        nombreObra = new javax.swing.JTextField();
        incidencias = new javax.swing.JPanel();
        titulo2 = new javax.swing.JLabel();
        presupuesto1 = new javax.swing.JLabel();
        descripcion1 = new javax.swing.JLabel();
        diferencia1 = new javax.swing.JLabel();
        diferenciaPor1 = new javax.swing.JLabel();
        facturado1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        incidenciaAlu = new javax.swing.JTextField();
        btnVerDatos2 = new javax.swing.JButton();
        SalirBtn2 = new javax.swing.JButton();
        presupuesto2 = new javax.swing.JLabel();
        presupuesto3 = new javax.swing.JLabel();
        presupuesto4 = new javax.swing.JLabel();
        incidenciaAcc = new javax.swing.JTextField();
        incidenciaVidrio = new javax.swing.JTextField();
        incidenciaMO = new javax.swing.JTextField();
        incidenciaViaje = new javax.swing.JTextField();
        incidenciaGan = new javax.swing.JTextField();
        incidenciaCom = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        SalirBtn4 = new javax.swing.JButton();
        nombreObra2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 204, 255));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));

        jTabbedPane1.setBackground(new java.awt.Color(0, 204, 255));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N

        difGeneral.setBackground(new java.awt.Color(255, 255, 255));

        titulo1.setBackground(new java.awt.Color(255, 255, 255));
        titulo1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        titulo1.setText("REPORTE");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Obra: ");

        cbReportes.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        btnVerDatosDif.setBackground(new java.awt.Color(0, 204, 255));
        btnVerDatosDif.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btnVerDatosDif.setForeground(new java.awt.Color(255, 255, 255));
        btnVerDatosDif.setText("Ver datos");
        btnVerDatosDif.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerDatosDif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDatosDifActionPerformed(evt);
            }
        });

        SalirBtn1.setBackground(new java.awt.Color(255, 153, 51));
        SalirBtn1.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        SalirBtn1.setForeground(new java.awt.Color(255, 255, 255));
        SalirBtn1.setText("Volver");
        SalirBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SalirBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalirBtn1MouseClicked(evt);
            }
        });
        SalirBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirBtn1ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Precio de abertura ($):");

        precioAbertura.setEditable(false);
        precioAbertura.setBackground(new java.awt.Color(255, 255, 255));
        precioAbertura.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        gastoReal.setEditable(false);
        gastoReal.setBackground(new java.awt.Color(255, 255, 255));
        gastoReal.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setText("Gasto Real");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setText("Ganacia Real");

        gananciaReal.setEditable(false);
        gananciaReal.setBackground(new java.awt.Color(255, 255, 255));
        gananciaReal.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        variacion.setEditable(false);
        variacion.setBackground(new java.awt.Color(255, 255, 255));
        variacion.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel12.setText("Variación");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel13.setText("Sumatoria de costos");

        sumCostos.setEditable(false);
        sumCostos.setBackground(new java.awt.Color(255, 255, 255));
        sumCostos.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        SalirBtn3.setBackground(new java.awt.Color(255, 153, 51));
        SalirBtn3.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        SalirBtn3.setForeground(new java.awt.Color(255, 255, 255));
        SalirBtn3.setText("Volver");
        SalirBtn3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SalirBtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalirBtn3MouseClicked(evt);
            }
        });
        SalirBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirBtn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout difGeneralLayout = new javax.swing.GroupLayout(difGeneral);
        difGeneral.setLayout(difGeneralLayout);
        difGeneralLayout.setHorizontalGroup(
            difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(difGeneralLayout.createSequentialGroup()
                .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(difGeneralLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(53, 53, 53)
                        .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(gastoReal, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(precioAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(gananciaReal, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(variacion, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sumCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(difGeneralLayout.createSequentialGroup()
                        .addGap(503, 503, 503)
                        .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(584, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, difGeneralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerDatosDif)
                .addGap(414, 414, 414))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, difGeneralLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SalirBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SalirBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
            .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(difGeneralLayout.createSequentialGroup()
                    .addGap(433, 433, 433)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(750, Short.MAX_VALUE)))
        );
        difGeneralLayout.setVerticalGroup(
            difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(difGeneralLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(titulo1)
                .addGap(36, 36, 36)
                .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerDatosDif, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(precioAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(gastoReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(gananciaReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(variacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(sumCostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(SalirBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(384, 384, 384)
                .addComponent(SalirBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(232, 232, 232))
            .addGroup(difGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(difGeneralLayout.createSequentialGroup()
                    .addGap(107, 107, 107)
                    .addComponent(jLabel9)
                    .addContainerGap(1186, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Diferencias Generales", difGeneral);

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

        nombreObra.setEditable(false);
        nombreObra.setBackground(new java.awt.Color(255, 255, 255));
        nombreObra.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        nombreObra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombreObra.setBorder(null);
        nombreObra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombreObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreObraActionPerformed(evt);
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
                                .addGap(40, 40, 40)
                                .addComponent(descripcion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(presupuesto)
                                .addGap(114, 114, 114)
                                .addComponent(facturado)
                                .addGap(88, 88, 88)
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
                        .addGap(48, 48, 48)
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(presuAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcAluminioPesos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(presuAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcAluminioKg, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(presuAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(presuVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(presuViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reporteMaterialesLayout.createSequentialGroup()
                                .addComponent(presuMO, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(facMo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(difMO, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(porcMO, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62))
                    .addGroup(reporteMaterialesLayout.createSequentialGroup()
                        .addGap(505, 505, 505)
                        .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreObra, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGap(37, 37, 37)
                .addComponent(nombreObra, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(reporteMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(descripcion)
                    .addComponent(presupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(facturado)
                    .addComponent(diferencia)
                    .addComponent(diferenciaPor))
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
                    .addContainerGap(761, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Materiales", reporteMateriales);

        incidencias.setBackground(new java.awt.Color(255, 255, 255));

        titulo2.setBackground(new java.awt.Color(255, 255, 255));
        titulo2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        titulo2.setText("REPORTE");

        presupuesto1.setBackground(new java.awt.Color(255, 255, 255));
        presupuesto1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        presupuesto1.setText("Aluminio");

        descripcion1.setBackground(new java.awt.Color(255, 255, 255));
        descripcion1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        descripcion1.setText("Descripción");

        diferencia1.setBackground(new java.awt.Color(255, 255, 255));
        diferencia1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        diferencia1.setText("Viajes");

        diferenciaPor1.setBackground(new java.awt.Color(255, 255, 255));
        diferenciaPor1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        diferenciaPor1.setText("Comisión");

        facturado1.setBackground(new java.awt.Color(255, 255, 255));
        facturado1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        facturado1.setText("Vidrio");

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Incidencias");

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel15.setText("Obra: ");

        incidenciaAlu.setEditable(false);
        incidenciaAlu.setBackground(new java.awt.Color(255, 255, 255));
        incidenciaAlu.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        incidenciaAlu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        incidenciaAlu.setBorder(null);
        incidenciaAlu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnVerDatos2.setBackground(new java.awt.Color(0, 204, 255));
        btnVerDatos2.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btnVerDatos2.setForeground(new java.awt.Color(255, 255, 255));
        btnVerDatos2.setText("Ver datos");
        btnVerDatos2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerDatos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDatos2ActionPerformed(evt);
            }
        });

        SalirBtn2.setBackground(new java.awt.Color(255, 153, 51));
        SalirBtn2.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        SalirBtn2.setForeground(new java.awt.Color(255, 255, 255));
        SalirBtn2.setText("Volver");
        SalirBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SalirBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalirBtn2MouseClicked(evt);
            }
        });
        SalirBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirBtn2ActionPerformed(evt);
            }
        });

        presupuesto2.setBackground(new java.awt.Color(255, 255, 255));
        presupuesto2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        presupuesto2.setText("Mano de Obra");

        presupuesto3.setBackground(new java.awt.Color(255, 255, 255));
        presupuesto3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        presupuesto3.setText("Ganacias");

        presupuesto4.setBackground(new java.awt.Color(255, 255, 255));
        presupuesto4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        presupuesto4.setText("Accesorio");

        incidenciaAcc.setEditable(false);
        incidenciaAcc.setBackground(new java.awt.Color(255, 255, 255));
        incidenciaAcc.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        incidenciaAcc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        incidenciaAcc.setBorder(null);
        incidenciaAcc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        incidenciaVidrio.setEditable(false);
        incidenciaVidrio.setBackground(new java.awt.Color(255, 255, 255));
        incidenciaVidrio.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        incidenciaVidrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        incidenciaVidrio.setBorder(null);
        incidenciaVidrio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        incidenciaMO.setEditable(false);
        incidenciaMO.setBackground(new java.awt.Color(255, 255, 255));
        incidenciaMO.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        incidenciaMO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        incidenciaMO.setBorder(null);
        incidenciaMO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        incidenciaViaje.setEditable(false);
        incidenciaViaje.setBackground(new java.awt.Color(255, 255, 255));
        incidenciaViaje.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        incidenciaViaje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        incidenciaViaje.setBorder(null);
        incidenciaViaje.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        incidenciaGan.setEditable(false);
        incidenciaGan.setBackground(new java.awt.Color(255, 255, 255));
        incidenciaGan.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        incidenciaGan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        incidenciaGan.setBorder(null);
        incidenciaGan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        incidenciaCom.setEditable(false);
        incidenciaCom.setBackground(new java.awt.Color(255, 255, 255));
        incidenciaCom.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        incidenciaCom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        incidenciaCom.setBorder(null);
        incidenciaCom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        SalirBtn4.setBackground(new java.awt.Color(255, 153, 51));
        SalirBtn4.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        SalirBtn4.setForeground(new java.awt.Color(255, 255, 255));
        SalirBtn4.setText("Volver");
        SalirBtn4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SalirBtn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalirBtn4MouseClicked(evt);
            }
        });
        SalirBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirBtn4ActionPerformed(evt);
            }
        });

        nombreObra2.setEditable(false);
        nombreObra2.setBackground(new java.awt.Color(255, 255, 255));
        nombreObra2.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        nombreObra2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombreObra2.setBorder(null);
        nombreObra2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombreObra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreObra2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout incidenciasLayout = new javax.swing.GroupLayout(incidencias);
        incidencias.setLayout(incidenciasLayout);
        incidenciasLayout.setHorizontalGroup(
            incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(incidenciasLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcion1))
                .addGap(64, 64, 64)
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(incidenciaAlu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(presupuesto1))
                .addGap(81, 81, 81)
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(incidenciaAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(presupuesto4))
                .addGap(59, 59, 59)
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(incidenciaVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facturado1))
                .addGap(61, 61, 61)
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(incidenciaMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(presupuesto2))
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(incidenciasLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(incidenciaViaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(incidenciasLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(diferencia1)))
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(incidenciasLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(incidenciaGan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, incidenciasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(presupuesto3)
                        .addGap(63, 63, 63)))
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(diferenciaPor1)
                    .addComponent(incidenciaCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, incidenciasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(SalirBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(735, 735, 735)
                .addComponent(SalirBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, incidenciasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, incidenciasLayout.createSequentialGroup()
                        .addComponent(btnVerDatos2)
                        .addGap(350, 350, 350))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, incidenciasLayout.createSequentialGroup()
                        .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 1242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(899, 899, 899))))
            .addGroup(incidenciasLayout.createSequentialGroup()
                .addGap(503, 503, 503)
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreObra2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(incidenciasLayout.createSequentialGroup()
                    .addGap(433, 433, 433)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1640, Short.MAX_VALUE)))
        );
        incidenciasLayout.setVerticalGroup(
            incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(incidenciasLayout.createSequentialGroup()
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(incidenciasLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(titulo2)
                        .addGap(37, 37, 37)
                        .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVerDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreObra2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(diferencia1)
                            .addComponent(presupuesto3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(diferenciaPor1)
                            .addComponent(presupuesto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(incidenciasLayout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(presupuesto4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(incidenciasLayout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(presupuesto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(incidenciasLayout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(facturado1, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(descripcion1, javax.swing.GroupLayout.Alignment.CENTER))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incidenciaAlu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incidenciaAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incidenciaVidrio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incidenciaMO, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incidenciaViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incidenciaCom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incidenciaGan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(incidenciasLayout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(SalirBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, incidenciasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SalirBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250))))
            .addGroup(incidenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(incidenciasLayout.createSequentialGroup()
                    .addGap(107, 107, 107)
                    .addComponent(jLabel15)
                    .addContainerGap(775, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Incidencias", incidencias);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1257, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1257, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 660, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

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

    private void btnVerDatosDifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDatosDifActionPerformed
        // TODO add your handling code here:
        mostrarDatos();
    }//GEN-LAST:event_btnVerDatosDifActionPerformed

    private void SalirBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirBtn1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirBtn1MouseClicked

    private void SalirBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirBtn1ActionPerformed

    private void btnVerDatos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDatos2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerDatos2ActionPerformed

    private void SalirBtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirBtn2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirBtn2MouseClicked

    private void SalirBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirBtn2ActionPerformed

    private void SalirBtn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirBtn3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirBtn3MouseClicked

    private void SalirBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirBtn3ActionPerformed
        // TODO add your handling code here:
        dispose();
        try {
                new Menu().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(CargarObra.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_SalirBtn3ActionPerformed

    private void SalirBtn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirBtn4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirBtn4MouseClicked

    private void SalirBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirBtn4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirBtn4ActionPerformed

    private void nombreObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreObraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreObraActionPerformed

    private void nombreObra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreObra2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreObra2ActionPerformed
    
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
    private javax.swing.JButton SalirBtn1;
    private javax.swing.JButton SalirBtn2;
    private javax.swing.JButton SalirBtn3;
    private javax.swing.JButton SalirBtn4;
    private javax.swing.JButton btnVerDatos2;
    private javax.swing.JButton btnVerDatosDif;
    private javax.swing.JComboBox<String> cbReportes;
    private javax.swing.JLabel descripcion;
    private javax.swing.JLabel descripcion1;
    private javax.swing.JTextField difAcc;
    private javax.swing.JTextField difAluminioKg;
    private javax.swing.JTextField difAluminioPesos;
    private javax.swing.JPanel difGeneral;
    private javax.swing.JTextField difMO;
    private javax.swing.JTextField difViajes;
    private javax.swing.JTextField difVidrio;
    private javax.swing.JLabel diferencia;
    private javax.swing.JLabel diferencia1;
    private javax.swing.JLabel diferenciaPor;
    private javax.swing.JLabel diferenciaPor1;
    private javax.swing.JTextField facAcc;
    private javax.swing.JTextField facAluminioKg;
    private javax.swing.JTextField facAluminioPesos;
    private javax.swing.JTextField facMo;
    private javax.swing.JTextField facViajes;
    private javax.swing.JTextField facVidrio;
    private javax.swing.JLabel facturado;
    private javax.swing.JLabel facturado1;
    private javax.swing.JTextField gananciaReal;
    private javax.swing.JTextField gastoReal;
    private javax.swing.JTextField incidenciaAcc;
    private javax.swing.JTextField incidenciaAlu;
    private javax.swing.JTextField incidenciaCom;
    private javax.swing.JTextField incidenciaGan;
    private javax.swing.JTextField incidenciaMO;
    private javax.swing.JTextField incidenciaViaje;
    private javax.swing.JTextField incidenciaVidrio;
    private javax.swing.JPanel incidencias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nombreObra;
    private javax.swing.JTextField nombreObra2;
    private javax.swing.JTextField porcAcc;
    private javax.swing.JTextField porcAluminioKg;
    private javax.swing.JTextField porcAluminioPesos;
    private javax.swing.JTextField porcMO;
    private javax.swing.JTextField porcViajes;
    private javax.swing.JTextField porcVidrio;
    private javax.swing.JTextField precioAbertura;
    private javax.swing.JTextField presuAcc;
    private javax.swing.JTextField presuAluminioKg;
    private javax.swing.JTextField presuAluminioPesos;
    private javax.swing.JTextField presuMO;
    private javax.swing.JTextField presuViajes;
    private javax.swing.JTextField presuVidrio;
    private javax.swing.JLabel presupuesto;
    private javax.swing.JLabel presupuesto1;
    private javax.swing.JLabel presupuesto2;
    private javax.swing.JLabel presupuesto3;
    private javax.swing.JLabel presupuesto4;
    private javax.swing.JPanel reporteMateriales;
    private javax.swing.JTextField sumCostos;
    javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo1;
    private javax.swing.JLabel titulo2;
    private javax.swing.JTextField variacion;
    // End of variables declaration//GEN-END:variables
}
