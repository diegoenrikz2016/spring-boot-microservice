package com.web.springboot.micro.service;

import java.util.List;

import com.web.springboot.micro.model.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	

}
