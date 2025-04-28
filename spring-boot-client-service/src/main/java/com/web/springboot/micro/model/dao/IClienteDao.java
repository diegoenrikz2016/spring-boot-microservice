package com.web.springboot.micro.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.web.springboot.micro.model.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
