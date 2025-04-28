package com.web.springboot.micro.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuentaDto {
	private Long clienteId; 
    private String nombreCliente; 
    private List<CuentaDto> cuentas;
}
