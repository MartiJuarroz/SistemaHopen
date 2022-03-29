package com.hopen.SistemaHopen.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "estadoObra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadoObra extends Base{

    @Column
    private String nombreEstadoObra;

}
