package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "manoObra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ManoObra extends Base{

    private float horasMO;
}
