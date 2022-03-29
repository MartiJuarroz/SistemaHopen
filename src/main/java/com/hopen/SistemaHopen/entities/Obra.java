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
      //la agregue por ahora, pero creo q el nombre iba a ser la clave
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

      @ManyToOne
      @JoinColumn(name = "proveedor_id")
      private Proveedor proveedor;

      @OneToOne
      @JoinColumn(name = "viaje_id")
      private Viaje viaje;

      @OneToOne
      @JoinColumn(name = "manoObra_id")
      private ManoObra manoObra;

      @OneToMany
      @JoinColumn(name = "vidrio_id")
      private Vidrio vidrio;//aca no entiendo q pija pasa

      @OneToOne
      @JoinColumn(name = "aluminio_id")
      private Aluminio aluminio;

      @ManyToOne
      @JoinColumn(name = "estadoObra")
      private EstadoObra estadoObra;

}
