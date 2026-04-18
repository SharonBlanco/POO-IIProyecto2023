/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package poo.proyecto2.tirviatest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Usuario
 */
public class JugadorIT {
    
    public JugadorIT() {
    }

    /**
     * Test of obtenerNombreJugador method, of class Jugador.
     */
    @Test
    public void testObtenerNombreJugador() {
        System.out.println("obtenerNombreJugador");
        Jugador instance = null;
        String expResult = "";
        String result = instance.obtenerNombreJugador();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPuntaje method, of class Jugador.
     */
    @Test
    public void testObtenerPuntaje() {
        System.out.println("obtenerPuntaje");
        Jugador instance = null;
        short expResult = 0;
        short result = instance.obtenerPuntaje();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aumentarPuntaje method, of class Jugador.
     */
    @Test
    public void testAumentarPuntaje() {
        System.out.println("aumentarPuntaje");
        short puntos = 0;
        Jugador instance = null;
        short expResult = 0;
        short result = instance.aumentarPuntaje(puntos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
