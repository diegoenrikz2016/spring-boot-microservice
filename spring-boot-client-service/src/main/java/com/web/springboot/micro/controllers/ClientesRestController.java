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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.springboot.micro.exception.ClienteNotFoundException;
import com.web.springboot.micro.model.dto.ClienteDto;
import com.web.springboot.micro.model.entity.Cliente;
import com.web.springboot.micro.model.mapper.ClienteMapper;
import com.web.springboot.micro.service.IClienteService;
import com.web.springboot.micro.service.impl.KafkaProducerService;

@RestController
@RequestMapping("/clientes")
public class ClientesRestController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ClienteMapper clienteMapper;

	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping
	public ResponseEntity<List<ClienteDto>> getClientes() {
		List<Cliente> listaClientes = clienteService.findAll();
		List<ClienteDto> clienteDtos = clienteMapper.toDtoList(listaClientes);
		return ResponseEntity.ok(clienteDtos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> getCliente(@PathVariable(value = "id") Long id) {

		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			throw new ClienteNotFoundException(id);
		}

		ClienteDto clienteDto = clienteMapper.toDto(cliente);
		try {
	        String clienteJson = objectMapper.writeValueAsString(clienteDto);
	        // Enviar el cliente a Kafka
	        kafkaProducerService.sendMessage("client-topic", clienteJson);
	    } catch (Exception e) {
	        System.err.println("Error al serializar el cliente: " + e.getMessage());
	    }
		return ResponseEntity.ok(clienteDto);
	}

	@PostMapping
	public ResponseEntity<ClienteDto> addCliente(@RequestBody ClienteDto clienteDto) {
		Cliente cliente = clienteMapper.toEntity(clienteDto);
		Cliente savedCliente = clienteService.save(cliente);
		ClienteDto savedClienteDto = clienteMapper.toDto(savedCliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedClienteDto);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable(value = "id") Long id) {
		Cliente existingCliente = clienteService.findOne(id);
		if (existingCliente == null) {
			throw new ClienteNotFoundException(id);
		}

		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> updateCliente(@PathVariable(value = "id") Long id,
			@RequestBody ClienteDto clienteDto) {
		Cliente clienteEntity = clienteService.findOne(id);
		if (clienteEntity == null) {
			throw new ClienteNotFoundException(id);
		}
		Cliente updatedCliente = clienteMapper.toEntity(clienteDto);
		updatedCliente.setId(id);
		Cliente savedCliente = clienteService.save(updatedCliente);
		ClienteDto savedClienteDto = clienteMapper.toDto(savedCliente);
		return ResponseEntity.ok(savedClienteDto);
	}

}
