package com.web.springboot.micro.service;

import java.util.List;

import com.web.springboot.micro.model.dto.ClienteDto;
import com.web.springboot.micro.model.entity.Cuenta;

import reactor.core.publisher.Mono;

public interface ICuentaService {
	
	public List<Cuenta> findAll();
	
	public Cuenta save(Cuenta cliente);
	
	public Cuenta findOne(Long id);
	
	public void delete(Long id);
	
	public Mono<ClienteDto> obtenerCliente(Long id);
	

}
