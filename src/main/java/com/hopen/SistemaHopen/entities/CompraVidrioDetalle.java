/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Martiniano
 */
@Entity
@Table(name = "compraVidrio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompraVidrioDetalle extends Base{
    
    @Column
    private int cantPlanchas;

    @Column
    private int planchasUsadas;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tipovidrio_id")
    private TipoVidrio tipoVidrio;
}
