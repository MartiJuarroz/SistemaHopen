package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vidrio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompraVidrio extends DiferenciaRealPresupuesto {

    @Column
    private int cantPlanchas;

    @Column
    private int planchasUsadas;

    @OneToMany
    @JoinColumn(name = "tipoVidrio_id")
    private List<TipoVidrio> listatipoVidrio = new ArrayList<TipoVidrio>();
}
