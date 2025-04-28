package com.web.springboot.micro.exception;

public class CuentaNotFoundException extends RuntimeException {
	public CuentaNotFoundException(Long id) {
		super("Movimiento no encontrado con el ID: " + id);
	}
}
