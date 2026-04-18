/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proyecto2.triviaquirk.excepciones;

/**
 *
 * @author Usuario
 */
public class excepcionPreguntasNoDisponibles extends Exception {
  public excepcionPreguntasNoDisponibles() {
    super("No se cuenta con la cantidad de preguntas disponibles en esta partida");
  }
}
