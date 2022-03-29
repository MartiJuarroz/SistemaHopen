package com.hopen.SistemaHopen.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Factura {
    @Id
    @Column(name = "id", nullable = false)
    private  long id;

    @OneToOne//no estoy seguro de si es 1 a 1
    @JoinColumn(name = "detalleFactura_id")
    private DetalleFactura detalleFactura;

}
