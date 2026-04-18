/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package juego;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Usuario
 */
public class InicioIT {
    
    public InicioIT() {
    }

    /**
     * Test of music method, of class Inicio.
     */
    @Test
    public void testMusic() {
        System.out.println("music");
        String archivo = "";
        Inicio instance = new Inicio();
        instance.music(archivo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sonido method, of class Inicio.
     */
    @Test
    public void testSonido() {
        System.out.println("sonido");
        String archivo = "";
        Inicio instance = new Inicio();
        instance.sonido(archivo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Inicio.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Inicio.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
