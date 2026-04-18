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
public class TemporizadorListenerIT {
    
    public TemporizadorListenerIT() {
    }

    /**
     * Test of temporizadorFinalizado method, of class TemporizadorListener.
     */
    @Test
    public void testTemporizadorFinalizado() {
        System.out.println("temporizadorFinalizado");
        TemporizadorListener instance = new TemporizadorListenerImpl();
        instance.temporizadorFinalizado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TemporizadorListenerImpl implements TemporizadorListener {

        public void temporizadorFinalizado() {
        }
    }
    
}
