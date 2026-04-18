/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package poo.proyecto2.tirviatest;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import poo.proyecto2.triviaquirk.iPregunta;

/**
 *
 * @author Usuario
 */
public class ArchivoJasonIT {
    
    public ArchivoJasonIT() {
    }

    /**
     * Test of cargarDatosDesdeArchivo method, of class ArchivoJason.
     */
    @Test
    public void testCargarDatosDesdeArchivo() {
        System.out.println("cargarDatosDesdeArchivo");
        String nombreArchivo = "";
        ArchivoJason instance = null;
        JSONObject expResult = null;
        JSONObject result = instance.cargarDatosDesdeArchivo(nombreArchivo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GuardarPuntajeGlobal method, of class ArchivoJason.
     */
    @Test
    public void testGuardarPuntajeGlobal() {
        System.out.println("GuardarPuntajeGlobal");
        Partida partida = null;
        JSONObject datosJSON = null;
        String nombreArchivo = "";
        ArchivoJason.GuardarPuntajeGlobal(partida, datosJSON, nombreArchivo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarDatoALista method, of class ArchivoJason.
     */
    @Test
    public void testAgregarDatoALista() {
        System.out.println("agregarDatoALista");
        JSONObject datosJSON = null;
        String nombreArchivo = "";
        iPregunta pregunta = null;
        int respuesta = 0;
        int expResult = 0;
        int result = ArchivoJason.agregarDatoALista(datosJSON, nombreArchivo, pregunta, respuesta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
