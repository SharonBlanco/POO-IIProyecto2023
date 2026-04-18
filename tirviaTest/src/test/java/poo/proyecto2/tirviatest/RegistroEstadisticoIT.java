/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package poo.proyecto2.tirviatest;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Usuario
 */
public class RegistroEstadisticoIT {
    
    public RegistroEstadisticoIT() {
    }

    /**
     * Test of toJsonObject method, of class RegistroEstadistico.
     */
    @Test
    public void testToJsonObject() {
        System.out.println("toJsonObject");
        RegistroEstadistico instance = null;
        JSONObject expResult = null;
        JSONObject result = instance.toJsonObject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class RegistroEstadistico.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        RegistroEstadistico instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
