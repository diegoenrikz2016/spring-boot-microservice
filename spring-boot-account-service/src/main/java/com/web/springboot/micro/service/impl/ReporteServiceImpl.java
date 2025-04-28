package com.web.springboot.micro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.springboot.micro.model.dao.ICuentaDao;
import com.web.springboot.micro.model.dao.IMovimientoDao;
import com.web.springboot.micro.model.dto.ClienteDto;
import com.web.springboot.micro.model.dto.CuentaDto;
import com.web.springboot.micro.model.dto.EstadoCuentaDto;
import com.web.springboot.micro.model.dto.MovimientoDto;
import com.web.springboot.micro.model.entity.Cuenta;
import com.web.springboot.micro.model.entity.Movimiento;
import com.web.springboot.micro.model.mapper.CuentaMapper;
import com.web.springboot.micro.model.mapper.MovimientosMapper;
import com.web.springboot.micro.service.ICuentaService;

import reactor.core.publisher.Mono;

@Service
public class ReporteServiceImpl {

	@Autowired
	private ICuentaDao cuentaDao;

	@Autowired
	private IMovimientoDao movimientoDao;

	@Autowired
	private MovimientosMapper movimientosMapper;
	
	@Autowired
	private ICuentaService cuentaService;
	
	@Autowired
	private CuentaMapper cuentaMapper;

	public EstadoCuentaDto generarReporte(Long clienteId, Date fechaInicio, Date fechaFin) {
		List<Cuenta> cuentas = cuentaDao.findByClienteId(clienteId);
		EstadoCuentaDto estadoCuentaDto = new EstadoCuentaDto();
		estadoCuentaDto.setClienteId(clienteId);
		ClienteDto clienteDto = cuentaService.obtenerCliente(clienteId).block();
		estadoCuentaDto.setNombreCliente(clienteDto.getNombre()); 
		List<CuentaDto> cuentaDtos = new ArrayList<>();

		for (Cuenta cuenta : cuentas) {
			CuentaDto cuentaDto = cuentaMapper.toDto(cuenta);
			cuentaDto.setSaldo(cuenta.getSaldoInicial());

			List<Movimiento> movimientos = movimientoDao.findByCuentaIdAndFechaBetween(cuenta.getId(), fechaInicio,
					fechaFin);
			List<MovimientoDto> movimientoDtos = movimientosMapper.toDtoList(movimientos);

			cuentaDto.setMovimientos(movimientoDtos);
			cuentaDtos.add(cuentaDto);
		}

		estadoCuentaDto.setCuentas(cuentaDtos);
		return estadoCuentaDto;
	}
	
}