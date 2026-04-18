/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poo.proyecto2.triviaquirk;

/**
 *
 * @author Usuario
 */
import java.util.ArrayList;
import poo.proyecto2.triviaquirk.excepciones.excepcionPartidaNoDisponible;
import poo.proyecto2.triviaquirk.excepciones.excepcionPreguntasNoDisponibles;

public interface iCategorias {
  public static final ArrayList<iSuscriptorPreguntas> suscriptores = new ArrayList<>();
  
  String nombreCategoria();
  
  int registrarPartida();
  
  void finalizarPartida(int paramInt) throws excepcionPartidaNoDisponible;
  
  iPregunta obtenerPreguntaAleatoria(int paramInt) throws excepcionPreguntasNoDisponibles;
  
  void publicarEnSuscriptores(int paramInt) throws excepcionPartidaNoDisponible;
  
  void agregarSuscriptor(int paramInt, iSuscriptorPreguntas paramiSuscriptorPreguntas) throws excepcionPartidaNoDisponible;
  
  short cantidadDePreguntasExistentes();
  
  void agregarJugador(int paramInt, iJugador paramiJugador) throws excepcionPartidaNoDisponible;
}
