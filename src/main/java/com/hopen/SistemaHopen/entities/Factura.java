package com.hopen.SistemaHopen.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Factura extends Base{

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "detalleFactura_id")
    private List<DetalleFactura> detallesFactura = new ArrayList<DetalleFactura>();

    @Column
    private Date fechaFactura;

    @Column
    private String numeroFactura;

    @Column
    private double totalFactura;

}
