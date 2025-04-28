package com.web.springboot.micro.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.springboot.micro.model.dao.ICuentaDao;
import com.web.springboot.micro.model.dto.ClienteDto;
import com.web.springboot.micro.model.entity.Cuenta;
import com.web.springboot.micro.service.ICuentaService;

import reactor.core.publisher.Mono;

@Service
public class CuentaServiceImpl implements ICuentaService {

	@Autowired
	private ICuentaDao cuentaDao;

	private final WebClient webClient;

	private static final Logger logger = LoggerFactory.getLogger(CuentaServiceImpl.class);

	@Autowired
	public CuentaServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://client-service:8086").build();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cuenta> findAll() {
		return (List<Cuenta>) cuentaDao.findAll();
	}

	@Override
	@Transactional
	public Cuenta save(Cuenta cuenta) {

		ClienteDto clienteDto = obtenerCliente(cuenta.getClienteId()).block();

		if (clienteDto == null) {
			logger.error("No se puede guardar la cuenta. El cliente con ID " + cuenta.getClienteId() + " no existe.");
			throw new IllegalArgumentException("El cliente con ID " + cuenta.getClienteId() + " no existe.");
		}

		return cuentaDao.save(cuenta);

	}

	@Override
	@Transactional(readOnly = true)
	public Cuenta findOne(Long id) {
		return cuentaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cuentaDao.deleteById(id);

	}

	public Mono<ClienteDto> obtenerCliente(Long clienteId) {
		return webClient.get().uri("/clientes/{id}", clienteId).retrieve().bodyToMono(ClienteDto.class)
				.doOnError(error -> System.err.println("Error al obtener el cliente: " + error.getMessage()));
	}

	private Mono<Void> deleteCliente(Long clienteId) {
		return webClient.delete().uri("/clientes/{id}", clienteId).retrieve().bodyToMono(Void.class);
	}

	@KafkaListener(topics = "client-topic", groupId = "my-group")
	public void listen(String message) {
		try {
			ClienteDto clienteDto = new ObjectMapper().readValue(message, ClienteDto.class);
			Long clienteId = clienteDto.getId();
			logger.info("Cliente recibido: " + clienteDto);
			// Aquí puedes procesar el clienteDto y ejemplo eliminar con el servicio
			// deleteCliente
			/*
			 * deleteCliente(clienteId).subscribe( response -> logger.info("Cliente con ID "
			 * + clienteId + " eliminado exitosamente."), error ->
			 * logger.error("Error al eliminar el cliente: " + error.getMessage()));
			 */
		} catch (Exception e) {
			logger.error("Error al deserializar el mensaje: " + e.getMessage());
		}
	}

}
