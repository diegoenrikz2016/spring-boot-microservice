package com.web.springboot.micro.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.springboot.micro.model.entity.Cuenta;

public interface ICuentaDao extends CrudRepository<Cuenta, Long> {
	
	List<Cuenta> findByClienteId(Long clienteId); 

}
