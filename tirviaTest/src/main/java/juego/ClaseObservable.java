/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 *
 *
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import poo.proyecto2.triviaquirk.iJugador;
import poo.proyecto2.triviaquirk.iPregunta;

/**
 * La clase ClaseObservable implementa la interfaz Observable y proporciona
 * funcionalidad para gestionar y notificar observadores.
 *
 * @see Observable
 */
public class ClaseObservable implements Observable {

    /**
     * Constructor por defecto de la clase ClaseObservador. Este constructor se
     * llama automáticamente al crear una instancia de la clase. No realiza
     * ninguna operación específica.
     */
    public ClaseObservable() {
    }


    /**
     * Conjunto de observadores registrados.
     */
    Set<Observer> observerset = new HashSet<>();

    /**
     * Agrega un observador al conjunto de observadores.
     *
     * @param o El observador a agregar.
     */
    public void addObservable(Observer o) {
        observerset.add(o);

    }

    /**
     * Elimina un observador del conjunto de observadores.
     *
     * @param o El observador a eliminar.
     */
    public void deleteObserver(Observer o) {
        observerset.remove(o);
    }

    /**
     * Notifica a todos los observadores con la información proporcionada y
     * actualiza el puntaje de los jugadores en la lista de jugadores.
     *
     * @param categoria La categoría de la pregunta.
     * @param pregunta La pregunta a notificar.
     * @param numero El número asociado a la pregunta.
     * @param jugadorActual El índice del jugador actual.
     * @param listaJugadores La lista de jugadores a actualizar.
     */
    public void notifyObservers(String categoria, iPregunta pregunta, byte numero, byte jugadorActual, ArrayList<iJugador> listaJugadores) {
        for (Observer observer : observerset) {
            int value = observer.update(categoria, pregunta, numero, listaJugadores);
            Inicio.listaJugadores.get(jugadorActual-1).aumentarPuntaje((short) value);
        }
    }

    /**
     * Agrega un observador al conjunto de observadores.
     *
     * @param o El observador a agregar.
     */
    @Override
    public void addObserver(Observer o) {
        observerset.add(o);
    }

}
