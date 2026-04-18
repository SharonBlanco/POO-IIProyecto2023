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
public class ClaseObservableIT {
    
    public ClaseObservableIT() {
    }

    /**
     * Test of addObservable method, of class ClaseObservable.
     */
    @Test
    public void testAddObservable() {
        System.out.println("addObservable");
        Observer o = null;
        ClaseObservable instance = new ClaseObservable();
        instance.addObservable(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteObserver method, of class ClaseObservable.
     */
    @Test
    public void testDeleteObserver() {
        System.out.println("deleteObserver");
        Observer o = null;
        ClaseObservable instance = new ClaseObservable();
        instance.deleteObserver(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObservers method, of class ClaseObservable.
     */
    @Test
    public void testNotifyObservers() {
        System.out.println("notifyObservers");
        String categoria = "";
        iPregunta pregunta = null;
        byte numero = 0;
        byte jugadorActual = 0;
        ArrayList<iJugador> listaJugadores = null;
        ClaseObservable instance = new ClaseObservable();
        instance.notifyObservers(categoria, pregunta, numero, jugadorActual, listaJugadores);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addObserver method, of class ClaseObservable.
     */
    @Test
    public void testAddObserver() {
        System.out.println("addObserver");
        Observer o = null;
        ClaseObservable instance = new ClaseObservable();
        instance.addObserver(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
