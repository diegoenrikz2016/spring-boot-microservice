package com.web.springboot.micro.exception;

public class ClienteNotFoundException extends RuntimeException {
	public ClienteNotFoundException(Long id) {
		super("Cliente no encontrado con el ID: " + id);
	}
}
