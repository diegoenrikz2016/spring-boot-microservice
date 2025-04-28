package com.web.springboot.micro.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.springboot.micro.exception.CuentaNotFoundException;
import com.web.springboot.micro.exception.SaldoInsuficienteException;
import com.web.springboot.micro.model.dao.IMovimientoDao;
import com.web.springboot.micro.model.entity.Cuenta;
import com.web.springboot.micro.model.entity.Movimiento;
import com.web.springboot.micro.service.ICuentaService;
import com.web.springboot.micro.service.IMovimientosService;

@Service
public class MovimientosServiceImpl implements IMovimientosService {

	@Autowired
	private IMovimientoDao movimientosDao;

	@Autowired
	private ICuentaService cuentaService;

	@Override
	@Transactional(readOnly = true)
	public List<Movimiento> findAll() {
		return (List<Movimiento>) movimientosDao.findAll();
	}

	@Override
	@Transactional
	public Movimiento save(Movimiento movimientos) {
		return movimientosDao.save(movimientos);

	}

	@Override
	@Transactional(readOnly = true)
	public Movimiento findOne(Long id) {
		return movimientosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		movimientosDao.deleteById(id);

	}

	@Override
	public Movimiento registrarMovimiento(Movimiento movimiento) {

		Cuenta cuenta = cuentaService.findOne(movimiento.getCuentaId());
		if (cuenta == null) {
			throw new CuentaNotFoundException(movimiento.getCuentaId());
		}

		if ("RETIRO".equalsIgnoreCase(movimiento.getTipoMovimiento())
				&& movimiento.getValor() > cuenta.getSaldoInicial()) {
			throw new SaldoInsuficienteException("Saldo no disponible");
		}

		if ("DEPOSITO".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
			cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimiento.getValor());
		} else if ("RETIRO".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
			cuenta.setSaldoInicial(cuenta.getSaldoInicial() - movimiento.getValor());
		} else {
			throw new IllegalArgumentException("Tipo de movimiento no válido: " + movimiento.getTipoMovimiento());
		}

		movimiento.setSaldo(cuenta.getSaldoInicial());
		movimiento.setFecha(new Date());
		Movimiento savedMovimiento = movimientosDao.save(movimiento);

		cuentaService.save(cuenta);

		return savedMovimiento;
	}

}
