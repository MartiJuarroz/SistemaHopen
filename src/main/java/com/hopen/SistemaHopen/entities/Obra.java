package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "obra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Obra extends Base {

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

      @ManyToOne
      @JoinColumn(name = "proveedor_id")
      private Proveedor proveedor;

      @ManyToOne
      @JoinColumn(name = "viaje_id")
      private Viaje viaje;


      private ManoObra manoObra;
      private Vidrio vidrio;
      private Aluminio aluminio;

}
