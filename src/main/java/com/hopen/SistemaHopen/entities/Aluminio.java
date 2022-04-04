package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "aluminio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Aluminio extends DiferenciaRealPresupuesto {

    @Column
    private double kiloPresupuestado;

    @Column
    private double kiloFactura;

    @Column
    private String remito;

    @ManyToOne
    @JoinColumn(name = "colorAluminio_id")
    private ColorAluminio colorAluminio;
    
}
