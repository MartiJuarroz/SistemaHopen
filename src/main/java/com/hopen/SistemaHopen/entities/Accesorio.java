package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "accesorio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Accesorio extends DiferenciaRealPresupuesto{

    @Column
    private String nombreAccesorio;

    @Column
    private Date fechaCompra;
}
