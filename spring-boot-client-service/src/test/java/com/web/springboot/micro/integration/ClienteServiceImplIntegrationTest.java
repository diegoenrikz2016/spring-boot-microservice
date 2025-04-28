package com.web.springboot.micro.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.web.springboot.micro.model.dao.IClienteDao;
import com.web.springboot.micro.model.entity.Cliente;
import com.web.springboot.micro.service.impl.ClienteServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ClienteServiceImplIntegrationTest {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private IClienteDao clienteDao;

    @Test
    public void testSaveAndFindOne() {
        // Crear un nuevo cliente
        Cliente cliente = new Cliente("Juan Pérez", "Masculino", 30, "12345678", "Direción Test 1", "0992993432", "contrasena123", true);
        
        // Guardar el cliente
        Cliente savedCliente = clienteService.save(cliente);
        assertNotNull(savedCliente);
        assertEquals("Juan Pérez", savedCliente.getNombre());

        // Buscar el cliente por ID
        Cliente foundCliente = clienteService.findOne(savedCliente.getId());
        assertNotNull(foundCliente);
        assertEquals("Juan Pérez", foundCliente.getNombre());
    }
}

