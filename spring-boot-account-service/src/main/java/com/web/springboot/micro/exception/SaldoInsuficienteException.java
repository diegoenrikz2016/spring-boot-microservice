package com.web.springboot.micro.exception;

public class SaldoInsuficienteException extends RuntimeException {
	public SaldoInsuficienteException(String message) {
		super(message);
	}
}