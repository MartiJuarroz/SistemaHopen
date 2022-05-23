package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compraVidrio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompraVidrio extends DiferenciaRealPresupuesto {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraVidrioDetalle> listaDetalleCompra = new ArrayList<CompraVidrioDetalle>();
}
