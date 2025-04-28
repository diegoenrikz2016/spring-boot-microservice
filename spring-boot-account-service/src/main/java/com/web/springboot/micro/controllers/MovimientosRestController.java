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
import com.web.springboot.micro.exception.MovimientosNotFoundException;
import com.web.springboot.micro.model.dto.MovimientoDto;
import com.web.springboot.micro.model.entity.Movimiento;
import com.web.springboot.micro.model.mapper.MovimientosMapper;
import com.web.springboot.micro.service.ICuentaService;
import com.web.springboot.micro.service.IMovimientosService;

@RestController
@RequestMapping("/movimientos")
public class MovimientosRestController {

	@Autowired
	private IMovimientosService movimientosService;

	@Autowired
	private MovimientosMapper movimientosMapper;

	@Autowired
	private ICuentaService cuentaService;

	@GetMapping
	public ResponseEntity<List<MovimientoDto>> getMovimientos() {
		List<Movimiento> listaMovimientos = movimientosService.findAll();
		List<MovimientoDto> listaMovimientoDtos = movimientosMapper.toDtoList(listaMovimientos);
		return ResponseEntity.ok(listaMovimientoDtos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<MovimientoDto> getMovimiento(@PathVariable(value = "id") Long id) {

		Movimiento movimientos = movimientosService.findOne(id);

		if (movimientos == null) {
			throw new MovimientosNotFoundException(id);
		}

		MovimientoDto movimientoDto = movimientosMapper.toDto(movimientos);

		return ResponseEntity.ok(movimientoDto);

	}

	@PostMapping
	public ResponseEntity<MovimientoDto> addMovimiento(@RequestBody MovimientoDto movimientoDto) throws Exception {

		if (cuentaService.findOne(movimientoDto.getCuentaId()) == null) {
			throw new CuentaNotFoundException(movimientoDto.getCuentaId());
		}

		Movimiento movimiento = movimientosMapper.toEntity(movimientoDto);
		Movimiento savedMovimiento = movimientosService.registrarMovimiento(movimiento);
		MovimientoDto savedMovimientoDto = movimientosMapper.toDto(savedMovimiento);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMovimientoDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteMovimientos(@PathVariable(value = "id") Long id) {
		Movimiento movimiento = movimientosService.findOne(id);
		if (movimiento == null) {
			throw new MovimientosNotFoundException(id);
		}

		movimientosService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MovimientoDto> updateCuentas(@PathVariable(value = "id") Long id,
			@RequestBody MovimientoDto movimientoDto) {

		Movimiento movimientos = movimientosService.findOne(id);

		if (movimientos == null) {
			throw new MovimientosNotFoundException(id);
		}
		Movimiento movimiento = movimientosMapper.toEntity(movimientoDto);
		movimiento.setId(id);
		Movimiento savedMovimiento = movimientosService.save(movimiento);
		MovimientoDto savedMovimientoDto = movimientosMapper.toDto(savedMovimiento);
		return ResponseEntity.ok(savedMovimientoDto);
	}

}
