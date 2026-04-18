/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proyecto2.tirviatest;

import poo.proyecto2.triviaquirk.iJugador;

/**
 * Clase que representa a un jugador en un juego.
 */
public class Jugador implements iJugador {

    /**
     * Nombre del jugador.
     */
    private final String nombre;
    /**
     * Puntaje actual del jugador.
     */
    private short puntaje;

    /**
     * Constructor de la clase Jugador.
     *
     * @param nombre El nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String obtenerNombreJugador() {
        return this.nombre;
    }

    /**
     * Obtiene el puntaje actual del jugador.
     *
     * @return El puntaje actual del jugador.
     */
    public short obtenerPuntaje() {
        return this.puntaje;
    }

    /**
     * Aumenta el puntaje del jugador en la cantidad especificada.
     *
     * @param puntos La cantidad de puntos a aumentar al puntaje del jugador.
     * @return El nuevo puntaje del jugador después de aumentar los puntos.
     */
    public short aumentarPuntaje(short puntos) {
        this.puntaje = (short) (this.puntaje + puntos);
        return this.puntaje;
    }
}
