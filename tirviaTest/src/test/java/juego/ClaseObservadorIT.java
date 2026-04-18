/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package juego;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import poo.proyecto2.triviaquirk.iJugador;
import poo.proyecto2.triviaquirk.iPregunta;

/**
 *
 * @author Usuario
 */
public class ClaseObservadorIT {
    
    public ClaseObservadorIT() {
    }

    /**
     * Test of update method, of class ClaseObservador.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String categoria = "";
        iPregunta pregunta = null;
        byte numero = 0;
        ArrayList<iJugador> listaJugadores = null;
        ClaseObservador instance = new ClaseObservador();
        int expResult = 0;
        int result = instance.update(categoria, pregunta, numero, listaJugadores);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
