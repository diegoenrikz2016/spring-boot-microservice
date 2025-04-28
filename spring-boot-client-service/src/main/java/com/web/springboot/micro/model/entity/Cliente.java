package com.web.springboot.micro.model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Persona {

	@Column(unique = true)
	private String contrasena;
	private boolean estado;

    public Cliente(String nombre, String genero, Integer edad, String identificacion,
                   String direccion, String telefono, String contrasena, boolean estado) {
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;
    }
}
