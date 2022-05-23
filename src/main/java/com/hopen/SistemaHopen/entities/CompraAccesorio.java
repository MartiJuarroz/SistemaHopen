package com.hopen.SistemaHopen.entities;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
@Table(name = "accesorio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompraAccesorio extends DiferenciaRealPresupuesto{

    @Column
    private Date fechaCompra;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoAccesorio> listaTipoAccesorio = new ArrayList<TipoAccesorio>();
}
