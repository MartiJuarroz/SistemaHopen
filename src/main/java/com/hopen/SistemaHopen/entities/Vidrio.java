package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vidrio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vidrio extends DiferenciaRealPresupuesto {

    @Column
    private int cantPlanchas;

    @Column
    private int planchasUsadas;
}
