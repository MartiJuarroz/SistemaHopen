package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table (name = "obra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titular;

    @Column
    private float totalPresupuesto;

    @Column
    private float comision;

    @Column
    private float gananciaPrentendida;

    @Column
    private Date fechaPresupuesto;

    @Column
    private float costosFijos;



}
