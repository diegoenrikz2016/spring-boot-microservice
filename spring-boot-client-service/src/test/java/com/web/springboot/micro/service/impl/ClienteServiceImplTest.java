package com.web.springboot.micro.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.web.springboot.micro.model.dao.IClienteDao;
import com.web.springboot.micro.model.entity.Cliente;

public class ClienteServiceImplTest {

	@InjectMocks
	private ClienteServiceImpl clienteService;

	@Mock
	private IClienteDao clienteDao;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindAll() {
		when(clienteDao.findAll()).thenReturn(Arrays.asList(
				new Cliente("Juan Pérez", "Masculino", 30, "12345678", "Direción Test 1", "0992993432", "contrasena123",
						true),
				new Cliente("Ana Gómez", "Femenino", 25, "87654321", "Direción Test 2", "0992993431",
						"contrasena456", true)));

		List<Cliente> clientes = clienteService.findAll();

		assertNotNull(clientes);
		assertEquals(2, clientes.size());
		verify(clienteDao, times(1)).findAll();
	}

	@Test
	public void testSave() {
		Cliente cliente = new Cliente("Juan Pérez", "Masculino", 30, "12345678", "Direción Test 1", "0992993432",
				"contrasena123", true);

		when(clienteDao.save(cliente)).thenReturn(cliente);

		Cliente savedCliente = clienteService.save(cliente);

		assertNotNull(savedCliente);
		assertEquals("Juan Pérez", savedCliente.getNombre());
		verify(clienteDao, times(1)).save(cliente);
	}

	@Test
	public void testFindOne() {
		Cliente cliente = new Cliente("Juan Pérez", "Masculino", 30, "12345678", "Direción Test 1", "0992993432",
				"contrasena123", true);
		when(clienteDao.findById(1L)).thenReturn(Optional.of(cliente));

		Cliente foundCliente = clienteService.findOne(1L);

		assertNotNull(foundCliente);
		assertEquals("Juan Pérez", foundCliente.getNombre());
		verify(clienteDao, times(1)).findById(1L);
	}

	@Test
	public void testDelete() {
		clienteService.delete(1L);
		verify(clienteDao, times(1)).deleteById(1L);
	}
}