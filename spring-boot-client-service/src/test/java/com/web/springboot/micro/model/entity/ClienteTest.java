package com.web.springboot.micro.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setIdentificacion("12345678");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setTelefono("987654321");
        cliente.setContrasena("contrasena123");
        cliente.setEstado(true);
    }

    @Test
    public void testGetId() {
        assertEquals(1L, cliente.getId());
    }

    @Test
    public void testGetNombre() {
        assertEquals("Juan", cliente.getNombre());
    }

    @Test
    public void testGetGenero() {
        assertEquals("Masculino", cliente.getGenero());
    }

    @Test
    public void testGetEdad() {
        assertEquals(30, cliente.getEdad());
    }

    @Test
    public void testGetIdentificacion() {
        assertEquals("12345678", cliente.getIdentificacion());
    }

    @Test
    public void testGetDireccion() {
        assertEquals("Calle Falsa 123", cliente.getDireccion());
    }

    @Test
    public void testGetTelefono() {
        assertEquals("987654321", cliente.getTelefono());
    }

    @Test
    public void testGetContrasena() {
        assertEquals("contrasena123", cliente.getContrasena());
    }

    @Test
    public void testGetEstado() {
        assertEquals(true, cliente.isEstado());
    }

    @Test
    public void testSetNombre() {
        cliente.setNombre("Pedro");
        assertEquals("Pedro", cliente.getNombre());
    }
    
    @Test
    public void testConstructor() {
        Cliente clienteTest = new Cliente("Pedro", "Masculino", 25, "87654321", 
                                           "Calle Real 456", "123456789", 
                                           "password", true);

        assertEquals("Pedro", clienteTest.getNombre());
        assertEquals("Masculino", clienteTest.getGenero());
        assertEquals(25, clienteTest.getEdad());
        assertEquals("87654321", clienteTest.getIdentificacion());
        assertEquals("Calle Real 456", clienteTest.getDireccion());
        assertEquals("123456789", clienteTest.getTelefono());
        assertEquals("password", clienteTest.getContrasena());
        assertEquals(true, clienteTest.isEstado());
    }
}