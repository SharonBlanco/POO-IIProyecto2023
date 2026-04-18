/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package juego;

import java.util.ArrayList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import poo.proyecto2.triviaquirk.excepciones.excepcionPreguntasNoDisponibles;
import poo.proyecto2.triviaquirk.iJugador;
import poo.proyecto2.triviaquirk.iPregunta;

/**
 *
 * @author Usuario
 */
public class JuegosIT {
    
    public JuegosIT() {
    }

    /**
     * Test of getPreguntaActual method, of class Juegos.
     */
    @Test
    public void testGetPreguntaActual() {
        System.out.println("getPreguntaActual");
        iPregunta expResult = null;
        iPregunta result = Juegos.getPreguntaActual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CambiarJugador method, of class Juegos.
     */
    @Test
    public void testCambiarJugador() throws excepcionPreguntasNoDisponibles {
        System.out.println("CambiarJugador");
        int numero = 0;
        byte j = 0;
        Juegos instance = new Juegos();
        byte expResult = 0;
        byte result = instance.CambiarJugador(numero, j);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AgregarAarchivo method, of class Juegos.
     */
    @Test
    public void testAgregarAarchivo() {
        System.out.println("AgregarAarchivo");
        String categoria = "";
        iPregunta pregunta = null;
        byte numero = 0;
        int expResult = 0;
        int result = Juegos.AgregarAarchivo(categoria, pregunta, numero);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SaberGanador method, of class Juegos.
     */
    @Test
    public void testSaberGanador() {
        System.out.println("SaberGanador");
        ArrayList<iJugador> listaJugadores = null;
        String expResult = "";
        String result = Juegos.SaberGanador(listaJugadores);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ElegirPreguntas method, of class Juegos.
     */
    @Test
    public void testElegirPreguntas() throws Exception {
        System.out.println("ElegirPreguntas");
        ArrayList<String> categorias = null;
        Map<String, ArrayList<iPregunta>> expResult = null;
        Map<String, ArrayList<iPregunta>> result = Juegos.ElegirPreguntas(categorias);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of botonCorrecto method, of class Juegos.
     */
    @Test
    public void testBotonCorrecto() throws excepcionPreguntasNoDisponibles {
        System.out.println("botonCorrecto");
        Juegos instance = new Juegos();
        instance.botonCorrecto();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of terminado method, of class Juegos.
     */
    @Test
    public void testTerminado() throws excepcionPreguntasNoDisponibles {
        System.out.println("terminado");
        Juegos instance = new Juegos();
        instance.terminado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of music method, of class Juegos.
     */
    @Test
    public void testMusic() throws excepcionPreguntasNoDisponibles {
        System.out.println("music");
        String archivo = "";
        Juegos instance = new Juegos();
        instance.music(archivo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sonido method, of class Juegos.
     */
    @Test
    public void testSonido() throws excepcionPreguntasNoDisponibles {
        System.out.println("sonido");
        String archivo = "";
        Juegos instance = new Juegos();
        instance.sonido(archivo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTemporizadorFinalizado method, of class Juegos.
     */
    @Test
    public void testIsTemporizadorFinalizado() throws excepcionPreguntasNoDisponibles {
        System.out.println("isTemporizadorFinalizado");
        Juegos instance = new Juegos();
        boolean expResult = false;
        boolean result = instance.isTemporizadorFinalizado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Juegos.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Juegos.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPregunta method, of class Juegos.
     */
    @Test
    public void testGetPregunta() {
        System.out.println("getPregunta");
        JLabel expResult = null;
        JLabel result = Juegos.getPregunta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRespuesta1 method, of class Juegos.
     */
    @Test
    public void testGetRespuesta1() {
        System.out.println("getRespuesta1");
        JButton expResult = null;
        JButton result = Juegos.getRespuesta1();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRespuesta2 method, of class Juegos.
     */
    @Test
    public void testGetRespuesta2() {
        System.out.println("getRespuesta2");
        JButton expResult = null;
        JButton result = Juegos.getRespuesta2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRespuesta3 method, of class Juegos.
     */
    @Test
    public void testGetRespuesta3() {
        System.out.println("getRespuesta3");
        JButton expResult = null;
        JButton result = Juegos.getRespuesta3();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
