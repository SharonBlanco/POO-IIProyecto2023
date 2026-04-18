/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poo.proyecto2.triviaquirk;

/**
 *
 * @author Usuario
 */
import java.io.FileNotFoundException;
import poo.proyecto2.triviaquirk.excepciones.excepcionRangoMayor;

public interface iPregunta {
  String obtenerDescripcion();
  
  String obtenerRespuesta1();
  
  String obtenerRespuesta2();
  
  String obtenerRespuesta3();
  Byte obtenerRespuestaCorrecta();
  
  int obtenernumeroPregunta();
  
  byte esCorrecta(byte paramByte) throws excepcionRangoMayor, FileNotFoundException;
  
  void falloPorTiempoRespuesta(byte paramByte) throws excepcionRangoMayor, FileNotFoundException;
}
