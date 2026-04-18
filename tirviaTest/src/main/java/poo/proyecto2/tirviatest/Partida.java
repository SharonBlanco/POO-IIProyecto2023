/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proyecto2.tirviatest;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import poo.proyecto2.triviaquirk.iCategorias;
import poo.proyecto2.triviaquirk.iJugador;
import poo.proyecto2.triviaquirk.iSuscriptorPreguntas;

/**
 * La clase Partida representa una partida en un juego, con un número de
 * partida, una lista de jugadores y una categoría asociada.
 */
public class Partida {

    /**
     * Número único de la partida.
     */
    public final int numeroPartida;

    /**
     * Lista de jugadores que participan en la partida.
     */
    public final ArrayList<iJugador> listadoJugadores;
    /**
     * Categoría asociada a la partida.
     */
    public final ArrayList<String> categoria;

    /**
     * Constructor de la clase Partida.
     *
     * @param numeroPartida Número único de la partida.
     * @param categoria Categoría asociada a la partida.
     * @param listaJugadores Lista de jugadores que participan en la partida.
     */
    public Partida(int numeroPartida, ArrayList<String> categoria, ArrayList<iJugador> listaJugadores) {
        this.numeroPartida = numeroPartida;
        this.listadoJugadores = listaJugadores;
        this.categoria = categoria;
    }

    /**
     * Convierte la partida a un objeto JSON.
     *
     * @return Un objeto JSON que representa la información de la partida.
     */
    public JSONObject toJsonObject() {
        JSONObject jsonRegistro = new JSONObject();
        jsonRegistro.put("Numero de partida", numeroPartida);
        jsonRegistro.put("Categoria", categoria);
        jsonRegistro.put("Lista Jugadores", listadoJugadores);

        // Agregar eventos si la lista no es nula
        return jsonRegistro;
    }

    /**
     * Agrega un jugador a la lista de jugadores de la partida.
     *
     * @param jugador El jugador que se va a agregar a la partida.
     */
    public void addJugador(iJugador jugador) {
        this.listadoJugadores.add(jugador);
    }

    /**
     * Obtiene la lista de jugadores que participan en la partida.
     *
     * @return La lista de jugadores de la partida.
     */
    public ArrayList<iJugador> getListadoJugadores() {
        return this.listadoJugadores;
    }

    /**
     * Obtiene el número único de la partida.
     *
     * @return El número único de la partida.
     */
    public int getNumeroPartida() {
        return this.numeroPartida;
    }

}
