/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package juego;

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
public interface TemporizadorListener {
    /**
     * Método llamado por el objeto observable para notificar a este observador
     * sobre un cambio en su estado.
     *
     */
    void temporizadorFinalizado();
}
