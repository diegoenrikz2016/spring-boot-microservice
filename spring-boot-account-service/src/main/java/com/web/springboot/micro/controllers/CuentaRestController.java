package com.web.springboot.micro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.springboot.micro.exception.CuentaNotFoundException;
import com.web.springboot.micro.model.dto.ClienteDto;
import com.web.springboot.micro.model.dto.CuentaDto;
import com.web.springboot.micro.model.entity.Cuenta;
import com.web.springboot.micro.model.mapper.CuentaMapper;
import com.web.springboot.micro.service.ICuentaService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cuentas")
public class CuentaRestController {

	@Autowired
	private ICuentaService cuentaService;

	@Autowired
	private CuentaMapper cuentaMapper;

	@GetMapping
	public ResponseEntity<List<CuentaDto>> getCuentas() {
		List<Cuenta> listaCuentas = cuentaService.findAll();
		List<CuentaDto> cuentaDtos = cuentaMapper.toDtoList(listaCuentas);
		return ResponseEntity.ok(cuentaDtos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CuentaDto> getCuenta(@PathVariable(value = "id") Long id) {

		Cuenta cuenta = cuentaService.findOne(id);

		if (cuenta == null) {
			throw new CuentaNotFoundException(id);
		}

		CuentaDto cuentaDto = cuentaMapper.toDto(cuenta);

		return ResponseEntity.ok(cuentaDto);
	}

	@PostMapping
	public ResponseEntity<CuentaDto> addCuenta(@RequestBody CuentaDto cuentaDto) {

		Cuenta cuenta = cuentaMapper.toEntity(cuentaDto);
		Cuenta savedCuenta = cuentaService.save(cuenta);
		CuentaDto savedCuentaDto = cuentaMapper.toDto(savedCuenta);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCuentaDto);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCuenta(@PathVariable(value = "id") Long id) {
		Cuenta cuenta = cuentaService.findOne(id);
		if (cuenta == null) {
			throw new CuentaNotFoundException(id);
		}

		cuentaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CuentaDto> updateCuentas(@PathVariable(value = "id") Long id,
			@RequestBody CuentaDto CuentaDto) {
		Cuenta cuentaEntity = cuentaService.findOne(id);

		if (cuentaEntity == null) {
			throw new CuentaNotFoundException(id);
		}
		Cuenta cuenta = cuentaMapper.toEntity(CuentaDto);
		cuenta.setId(id);
		Cuenta savedCuenta = cuentaService.save(cuenta);
		CuentaDto savedCuentaDto = cuentaMapper.toDto(savedCuenta);
		return ResponseEntity.ok(savedCuentaDto);
	}

	@GetMapping("/cliente/{id}")
	public Mono<ResponseEntity<ClienteDto>> obtenerCliente(@PathVariable Long id) {
		return cuentaService.obtenerCliente(id).map(clienteDto -> ResponseEntity.ok(clienteDto))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
