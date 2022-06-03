package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "viaje")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Viaje extends DiferenciaRealPresupuesto{

    @Column
    private String nombreEmpresa;

    @Column
    private int cantViajesPresupuesto;
    
    @Column
    private int cantViajesReal;
}
