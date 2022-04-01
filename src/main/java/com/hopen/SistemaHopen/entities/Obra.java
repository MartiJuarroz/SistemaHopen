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
      private float ganancia_pretendida;

      @Column
      @Temporal(TemporalType.DATE)
      private Date fechaPresupuesto;

      @Column
      private float costosFijos;

      @OneToMany
    //  @JoinColumn(name = "proveedor_id")
      private List<Proveedor> proveedores = new ArrayList<Proveedor>();

      @OneToOne
      @JoinColumn(name = "viaje_id")
      private Viaje viaje;

      @OneToOne
      @JoinColumn(name = "manoObra_id")
      private ManoObra manoObra;

      @OneToMany
    //  @JoinColumn(name = "vidrio_id")
      private List<Vidrio> listaVidrio = new ArrayList<Vidrio>();

      @OneToMany
    //  @JoinColumn(name = "accesorio_id")
      private List<Accesorio> listaAccesorios = new ArrayList<Accesorio>();

      @OneToOne
      @JoinColumn(name = "aluminio_id")
      private Aluminio aluminio;

      @ManyToOne(cascade = CascadeType.PERSIST)
      @JoinColumn(name = "estadoObra_id")
      private EstadoObra estadoObra;

}
