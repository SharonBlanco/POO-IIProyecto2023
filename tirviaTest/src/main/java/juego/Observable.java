/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package juego;

/**
 *
 * @author Usuario
 */
import java.util.ArrayList;
import poo.proyecto2.triviaquirk.iJugador;
import poo.proyecto2.triviaquirk.iPregunta;

/**
 * Interfaz que define un objeto observable que puede tener observadores
 * interesados en sus cambios de estado.
 *
 * <p>
 * Implementa el patrón de diseño Observer, permitiendo a objetos registrarse
 * como observadores y recibir notificaciones cuando el objeto observable
 * cambia.
 * </p>
 *
 * @author Usuario
 */
public interface Observable {

    /**
     * Agrega un observador al conjunto de observadores.
     *
     * @param o Observador a agregar.
     */
    void addObserver(Observer o);

    /**
     * Elimina un observador del conjunto de observadores.
     *
     * @param o Observador a eliminar.
     */
    void deleteObserver(Observer o);

    /**
     * Notifica a todos los observadores sobre un cambio en el estado del objeto
     * observable.
     *
     * @param categoria Categoría asociada al cambio.
     * @param pregunta Pregunta asociada al cambio.
     * @param numero Número asociado al cambio.
     * @param jugadorActual Número del jugador actual asociado al cambio.
     * @param listaJugadores Lista de jugadores asociada al cambio.
     */
    void notifyObservers(String categoria, iPregunta pregunta, byte numero, byte jugadorActual, ArrayList<iJugador> listaJugadores);
}
