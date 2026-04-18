/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proyecto2.tirviatest;

/**
 * Excepción lanzada cuando no se cuentan con la cantidad de preguntas
 * disponibles en una partida.
 */
public class excepcionPreguntasNoDisponibles extends Exception {

    /**
     * Construye una nueva excepción con un mensaje predeterminado. Indica que
     * no se cuenta con la cantidad de preguntas disponibles en la partida.
     */
    public excepcionPreguntasNoDisponibles() {
        super("No se cuenta con la cantidad de preguntas disponibles en esta partida");
    }
}
