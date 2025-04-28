package com.web.springboot.micro.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaTest {

    private Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta("123456789");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);
    }

    @Test
    public void testGetId() {
        assertEquals(1L, cuenta.getId());
    }

    @Test
    public void testGetNumeroCuenta() {
        assertEquals("123456789", cuenta.getNumeroCuenta());
    }

    @Test
    public void testGetTipoCuenta() {
        assertEquals("Ahorro", cuenta.getTipoCuenta());
    }

    @Test
    public void testGetSaldoInicial() {
        assertEquals(1000.0, cuenta.getSaldoInicial());
    }

    @Test
    public void testGetEstado() {
        assertEquals(true, cuenta.isEstado());
    }

    @Test
    public void testGetClienteId() {
        assertEquals(1L, cuenta.getClienteId());
    }

    @Test
    public void testSetNumeroCuenta() {
        cuenta.setNumeroCuenta("987654321");
        assertEquals("987654321", cuenta.getNumeroCuenta());
    }
    
    @Test
    public void testConstructor() {
        Cuenta cuentaTest = new Cuenta(2L, "876543210", "Corriente", 2000.0, false, 2L);

        assertEquals(2L, cuentaTest.getId());
        assertEquals("876543210", cuentaTest.getNumeroCuenta());
        assertEquals("Corriente", cuentaTest.getTipoCuenta());
        assertEquals(2000.0, cuentaTest.getSaldoInicial());
        assertEquals(false, cuentaTest.isEstado());
        assertEquals(2L, cuentaTest.getClienteId());
    }
}