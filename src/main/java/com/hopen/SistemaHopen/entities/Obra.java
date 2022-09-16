package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.CascadeType;
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
      @JoinColumn(name = "obra_id", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<Proveedor> proveedores = new ArrayList<Proveedor>();

      @OneToOne
      @JoinColumn(name = "viaje_id",cascade = CascadeType.ALL, orphanRemoval = true)
      private Viaje viaje;

      @OneToOne
      @JoinColumn(name = "manoObra_id",cascade = CascadeType.ALL, orphanRemoval = true)
      private ManoObra manoObra;

      @OneToOne
      @JoinColumn(name = "compraVidrio_id",cascade = CascadeType.ALL, orphanRemoval = true)
      private CompraVidrio compraVidrio;

      @OneToOne
      @JoinColumn(name = "compraAccesorio_id",cascade = CascadeType.ALL, orphanRemoval = true)
      private CompraAccesorio accesorios;

      @OneToOne
      @JoinColumn(name = "aluminio_id",cascade = CascadeType.ALL, orphanRemoval = true)
      private Aluminio aluminio;

      @ManyToOne(cascade = CascadeType.PERSIST)
      @JoinColumn(name = "estadoObra_id")
      private EstadoObra estadoObra;
     
      @OneToMany
      @JoinColumn(name = "obra_id")
      private List<Factura> facturas = new ArrayList<Factura>();
}
