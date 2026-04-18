/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proyecto2.tirviatest;

/**
 * Excepción lanzada cuando se intenta acceder a una partida no disponible.
 */
public class excepcionPartidaNoDisponible extends Exception {

    /**
     * Construye una nueva excepción con un mensaje detallado.
     *
     * @param msg El mensaje detallado que describe la causa de la excepción.
     */
    public excepcionPartidaNoDisponible(String msg) {
        super(msg);
    }
}
