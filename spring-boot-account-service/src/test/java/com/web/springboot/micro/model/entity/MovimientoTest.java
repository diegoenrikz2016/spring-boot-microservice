package com.web.springboot.micro.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class MovimientoTest {

    private Movimiento movimiento;

    @BeforeEach
    public void setUp() {
        movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setFecha(new Date());
        movimiento.setTipoMovimiento("Deposito");
        movimiento.setValor(500.0);
        movimiento.setSaldo(1500.0);
        movimiento.setCuentaId(1L);
    }

    @Test
    public void testGetId() {
        assertEquals(1L, movimiento.getId());
    }

    @Test
    public void testGetFecha() {
        assertEquals(movimiento.getFecha(), movimiento.getFecha());
    }

    @Test
    public void testGetTipoMovimiento() {
        assertEquals("Deposito", movimiento.getTipoMovimiento());
    }

    @Test
    public void testGetValor() {
        assertEquals(500.0, movimiento.getValor());
    }

    @Test
    public void testGetSaldo() {
        assertEquals(1500.0, movimiento.getSaldo());
    }

    @Test
    public void testGetCuentaId() {
        assertEquals(1L, movimiento.getCuentaId());
    }

    @Test
    public void testSetTipoMovimiento() {
        movimiento.setTipoMovimiento("Retiro");
        assertEquals("Retiro", movimiento.getTipoMovimiento());
    }
    
    @Test
    public void testConstructor() {
        Movimiento movimientoTest = new Movimiento(2L, new Date(), "Retiro", 300.0, 1200.0, 2L);

        assertEquals(2L, movimientoTest.getId());
        assertEquals("Retiro", movimientoTest.getTipoMovimiento());
        assertEquals(300.0, movimientoTest.getValor());
        assertEquals(1200.0, movimientoTest.getSaldo());
        assertEquals(2L, movimientoTest.getCuentaId());
    }
}