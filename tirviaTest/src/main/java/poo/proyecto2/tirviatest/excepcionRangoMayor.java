/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proyecto2.tirviatest;

/**
 * Excepción lanzada cuando se supera el rango permitido de opciones de
 * respuestas para una pregunta.
 */
public class excepcionRangoMayor extends Exception {

    /**
     * Construye una nueva excepción con un mensaje predeterminado. Indica que
     * se supera el rango permitido de opciones de respuestas para la pregunta.
     */
    public excepcionRangoMayor() {
        super("Se supera el rango mpermitido de opciones de respuestas para la pregunta");
    }
}
