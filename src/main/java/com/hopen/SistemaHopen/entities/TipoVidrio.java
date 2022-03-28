package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tipoVidrio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TipoVidrio extends Base{

    private String nombreTipoVidrio;
}
