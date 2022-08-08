package com.hopen.SistemaHopen.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario extends Base{

    @Column
    private String nombreUsuario;

    @Column
    private String contraseña;
    
    @Column
    private String emailUsuario;
    
    @ManyToOne
    @JoinColumn(name = "tipoUsuario_id")
    private TipoUsuario tipoUsuario;
    
}
