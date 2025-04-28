package com.web.springboot.micro.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDto {
	private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private boolean estado;
    private Long clienteId;
    private List<MovimientoDto> movimientos;
    private double saldo;
}
