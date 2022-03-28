package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "manoObra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ManoObra extends DiferenciaRealPresupuesto{

    @Column
    private float horasMO;
}
