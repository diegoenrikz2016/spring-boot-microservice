package com.web.springboot.micro.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.springboot.micro.model.entity.Movimiento;

public interface IMovimientoDao extends CrudRepository<Movimiento, Long> {

	List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, Date fechaInicio, Date fechaFin);

}
