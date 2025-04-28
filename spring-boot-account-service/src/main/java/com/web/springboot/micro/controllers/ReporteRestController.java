package com.web.springboot.micro.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.springboot.micro.exception.FechaInvalidaException;
import com.web.springboot.micro.model.dto.EstadoCuentaDto;
import com.web.springboot.micro.service.impl.ReporteServiceImpl;

@RestController
@RequestMapping("/reportes")
public class ReporteRestController {

	@Autowired
	private ReporteServiceImpl reporteService;

	@GetMapping
	public ResponseEntity<EstadoCuentaDto> obtenerEstadoCuenta(@RequestParam Long clienteId,
			@RequestParam String fechaInicio, @RequestParam String fechaFin) {

		LocalDate startDate;
		LocalDate endDate;

		try {
			startDate = LocalDate.parse(fechaInicio);
			endDate = LocalDate.parse(fechaFin);
		} catch (DateTimeParseException e) {
			throw new FechaInvalidaException("Formato de fecha inválido. Use YYYY-MM-DD.");
		}

		Date startDateUtil = java.sql.Date.valueOf(fechaInicio);
		Date endDateUtil = java.sql.Date.valueOf(fechaFin);

		EstadoCuentaDto estadoCuentaDto = reporteService.generarReporte(clienteId, startDateUtil, endDateUtil);
		return ResponseEntity.ok(estadoCuentaDto);
	}
	

}
