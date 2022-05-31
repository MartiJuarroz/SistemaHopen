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
      private double totalPresupuesto;

      @Column
      private double comision;

      @Column
      private double ganancia_pretendida;

      @Column
      @Temporal(TemporalType.DATE)
      private Date fechaPresupuesto;

      @Column
      private double costosFijos;

      @OneToMany
      @JoinColumn(name = "obra_id")
      private List<Proveedor> proveedores = new ArrayList<Proveedor>();

      @OneToOne
      @JoinColumn(name = "viaje_id")
      private Viaje viaje;

      @OneToOne
      @JoinColumn(name = "manoObra_id")
      private ManoObra manoObra;

      @OneToOne
      @JoinColumn(name = "compraVidrio_id")
      private CompraVidrio compraVidrio;

      @OneToOne
      @JoinColumn(name = "compraAccesorio_id")
      private CompraAccesorio accesorios;

      @OneToOne
      @JoinColumn(name = "aluminio_id")
      private Aluminio aluminio;

      @ManyToOne(cascade = CascadeType.PERSIST)
      @JoinColumn(name = "estadoObra_id")
      private EstadoObra estadoObra;
     
      @OneToMany
      @JoinColumn(name = "obra_id")
      private List<Factura> facturas = new ArrayList<Factura>();
}
