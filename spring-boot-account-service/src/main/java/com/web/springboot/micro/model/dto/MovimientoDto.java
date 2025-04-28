package com.web.springboot.micro.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDto {
	private Long id;
    private Date fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;
    private Long cuentaId;
}
