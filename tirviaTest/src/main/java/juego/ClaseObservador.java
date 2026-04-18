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
import java.util.logging.Level;
import java.util.logging.Logger;
import poo.proyecto2.triviaquirk.excepciones.excepcionPreguntasNoDisponibles;
import poo.proyecto2.triviaquirk.iJugador;
import poo.proyecto2.triviaquirk.iPregunta;

/**
 * La clase ClaseObservador implementa la interfaz Observer y proporciona
 * funcionalidad para observar y responder a cambios en un objeto observado.
 *
 * @see Observer
 */
public class ClaseObservador implements Observer {

    /**
     * Constructor por defecto de la clase ClaseObservador. Este constructor se
     * llama automáticamente al crear una instancia de la clase. No realiza
     * ninguna operación específica.
     */
    public ClaseObservador() {
    }
    
    /**
     * Actualiza la información recibida y realiza acciones específicas.
     *
     * @param categoria La categoría de la pregunta actualizada.
     * @param pregunta La pregunta actualizada.
     * @param numero El número asociado a la pregunta actualizada.
     * @param listaJugadores La lista de jugadores actualizada.
     * @return Un valor entero que puede representar información adicional o ser
     * utilizado de alguna manera específica.
     */
    @Override
    public int update(String categoria, iPregunta pregunta, byte numero, ArrayList<iJugador> listaJugadores) {

        int value = Juegos.AgregarAarchivo(categoria, pregunta, numero);
        return value;
    }

}
