package com.web.springboot.micro.exception;

public class MovimientosNotFoundException extends RuntimeException {
	public MovimientosNotFoundException(Long id) {
		super("Cuenta no encontrado con el ID: " + id);
	}
}
