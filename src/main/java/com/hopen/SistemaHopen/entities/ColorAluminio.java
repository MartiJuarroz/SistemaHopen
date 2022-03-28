package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "colorAluminio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ColorAluminio extends Base{

    @Column
    private String nombreColor;
}
