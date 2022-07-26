/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hopen.SistemaHopen.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Martiniano
 */
@Entity
@Table(name = "tipoUsuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TipoUsuario extends Base{
    
    @Column
    private String nombreTipoUsuario;
    
}
