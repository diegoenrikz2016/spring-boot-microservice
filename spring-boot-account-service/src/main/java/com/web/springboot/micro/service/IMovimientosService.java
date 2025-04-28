package com.web.springboot.micro.service;

import java.util.List;

import com.web.springboot.micro.model.entity.Movimiento;

public interface IMovimientosService {
	
	public List<Movimiento> findAll();
	
	public Movimiento save(Movimiento movimiento);
	
	public Movimiento findOne(Long id);
	
	public void delete(Long id);
	
	public Movimiento registrarMovimiento(Movimiento movimiento);
	

}
