package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "viaje")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Viaje extends Base{

    private String nombreEmpresa;

    private int cantViajesPresupuesto;
}
