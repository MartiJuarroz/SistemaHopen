package com.hopen.SistemaHopen.colorTabla;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author lisan
 */
public class ColorCeldas extends DefaultTableCellRenderer {
    
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if(evaluarPar(row)){
                this.setBackground(new Color(0, 204, 255));
                this.setForeground(Color.white);
                //this.setFont(BOLD);
            }else {
                this.setBackground(new Color(0, 153, 255));
                this.setForeground(Color.white);
            }
        return this;
    }
    
    public boolean evaluarPar(int numero){
        return (numero % 2 == 0);
    }
    
    
}
