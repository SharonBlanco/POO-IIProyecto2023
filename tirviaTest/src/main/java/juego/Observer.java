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
 * Interfaz que define un objeto observador que puede recibir notificaciones
 * sobre cambios en el estado de un objeto observable.
 * 
 * <p>
 * Implementa el patrón de diseño Observer, permitiendo a objetos
 * interesados en cambios registrarse como observadores y recibir
 * actualizaciones cuando el objeto observable cambia.
 * </p>
 * 
 * @author Usuario
 */
public interface Observer {
     /**
     * Método llamado por el objeto observable para notificar a este observador
     * sobre un cambio en su estado.
     * 
     * @param categoria Categoría asociada al cambio.
     * @param pregunta Pregunta asociada al cambio.
     * @param numero Número asociado al cambio.
     * @param listaJugadores Lista de jugadores asociada al cambio.
     * @return Valor que puede indicar algún resultado o confirmación del cambio.
     *         El significado específico dependerá de la implementación.
     */
    int update(String categoria, iPregunta pregunta, byte numero, ArrayList<iJugador> listaJugadores);
    
}
