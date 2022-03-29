package com.hopen.SistemaHopen.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "detalleFactura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetalleFactura extends Base{

   @Column
   private int cantidad;

   @Column
   private float subtotal;

   // aca el detalle tendria que tener una relacion con lo que se factura pero
   // no se bien que es

}
