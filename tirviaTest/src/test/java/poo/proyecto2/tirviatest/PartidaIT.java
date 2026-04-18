/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package poo.proyecto2.tirviatest;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import poo.proyecto2.triviaquirk.iJugador;

/**
 *
 * @author Usuario
 */
public class PartidaIT {
    
    public PartidaIT() {
    }

    /**
     * Test of toJsonObject method, of class Partida.
     */
    @Test
    public void testToJsonObject() {
        System.out.println("toJsonObject");
        Partida instance = null;
        JSONObject expResult = null;
        JSONObject result = instance.toJsonObject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addJugador method, of class Partida.
     */
    @Test
    public void testAddJugador() {
        System.out.println("addJugador");
        iJugador jugador = null;
        Partida instance = null;
        instance.addJugador(jugador);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListadoJugadores method, of class Partida.
     */
    @Test
    public void testGetListadoJugadores() {
        System.out.println("getListadoJugadores");
        Partida instance = null;
        ArrayList<iJugador> expResult = null;
        ArrayList<iJugador> result = instance.getListadoJugadores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumeroPartida method, of class Partida.
     */
    @Test
    public void testGetNumeroPartida() {
        System.out.println("getNumeroPartida");
        Partida instance = null;
        int expResult = 0;
        int result = instance.getNumeroPartida();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
