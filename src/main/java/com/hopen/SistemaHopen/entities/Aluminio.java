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
public class Aluminio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float kiloPresupuestado;

    @Column
    private float kiloFactura;

    @Column
    private String remito;

}
