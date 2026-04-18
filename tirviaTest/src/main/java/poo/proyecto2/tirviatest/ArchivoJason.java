/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proyecto2.tirviatest;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import poo.proyecto2.triviaquirk.iPregunta;

/**
 * La clase ArchivoJason se utiliza para cargar datos desde un archivo en
 * formato JSON.
 *
 */
public class ArchivoJason {

    /**
     * Constructor de la clase ArchivoJason. Crea una instancia de ArchivoJason
     * y carga automáticamente los datos desde el archivo JSON especificado.
     *
     * @param nombreArchivo El nombre del archivo en formato JSON del cual se
     * cargarán los datos.
     */
    public ArchivoJason(String nombreArchivo) {
        cargarDatosDesdeArchivo(nombreArchivo);
    }

    /**
     * Carga datos desde un archivo JSON y devuelve un objeto JSONObject
     * representando esos datos. Si el archivo no existe o no se puede parsear,
     * se crea un nuevo objeto JSON con una lista vacía.
     *
     * @param nombreArchivo El nombre del archivo en formato JSON del cual se
     * cargarán los datos.
     * @return Un objeto JSONObject que representa los datos cargados desde el
     * archivo o un nuevo objeto con una lista vacía.
     */
    public JSONObject cargarDatosDesdeArchivo(String nombreArchivo) {
        JSONParser parser = new JSONParser();
        JSONObject datosJSON = new JSONObject();

        try (FileReader archivo = new FileReader(nombreArchivo)) {
            Object obj = parser.parse(archivo);
            datosJSON = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            // Si el archivo no existe o no se puede parsear, crea uno nuevo.
            datosJSON.put("lista", new JSONArray());
        }

        return datosJSON;
    }

    /**
     * Método estático para guardar el puntaje global de una partida en un
     * archivo JSON.
     *
     * <p>
     * Este método agrega la información de la partida al archivo JSON existente
     * o crea uno nuevo si no existe.
     * </p>
     *
     * @param partida La partida cuyo puntaje se va a guardar.
     * @param datosJSON El objeto JSON que contiene la lista de puntajes
     * globales.
     * @param nombreArchivo El nombre del archivo JSON en el que se guardarán
     * los datos.
     */
    public static void GuardarPuntajeGlobal(Partida partida, JSONObject datosJSON, String nombreArchivo) {
        try {
            JSONArray lista = (JSONArray) datosJSON.get("lista");
            lista.add(partida.toJsonObject());
            guardarDatosEnArchivo(datosJSON, nombreArchivo);
        } catch (Exception e) {
            // Si el archivo no existe o no se puede parsear, crea uno nuevo.
            datosJSON.put("lista", new JSONArray());
            JSONArray lista = (JSONArray) datosJSON.get("lista");
            lista.add(partida.toJsonObject());
            guardarDatosEnArchivo(datosJSON, nombreArchivo);
        }

    }

    /**
     * Método estático para agregar datos a la lista de estadísticas en un
     * archivo JSON.
     *
     * <p>
     * Este método busca la pregunta en la lista y actualiza las estadísticas
     * correspondientes. Si la pregunta no se encuentra, se agrega a la lista
     * con las estadísticas iniciales.
     * </p>
     *
     * @param datosJSON El objeto JSON que contiene la lista de estadísticas.
     * @param nombreArchivo El nombre del archivo JSON en el que se guardarán
     * los datos.
     * @param pregunta La pregunta a la que se agregarán las estadísticas.
     * @param respuesta La respuesta dada por el jugador.
     * @return Un valor que indica el resultado de la operación (1: Acierto, 2:
     * Media Acierto, 3: Fallo).
     */
    public static int agregarDatoALista(JSONObject datosJSON, String nombreArchivo, iPregunta pregunta, int respuesta) {
        int valor = 0;
        boolean encontrado = false;
        JSONArray lista = (JSONArray) datosJSON.get("lista");

        for (Object objeto : lista) {
            JSONObject registroJson = (JSONObject) objeto;
            int numPregunta = Integer.parseInt(registroJson.get("Numero de pregunta").toString());

            if (numPregunta == pregunta.obtenernumeroPregunta()) {

                int aciertos = Integer.parseInt(registroJson.get("Aciertos").toString());
                int desaciertos = Integer.parseInt(registroJson.get("Desaciertos").toString());
                //opcion elegida por ahora va a hacer el numero random
                if (pregunta.obtenerRespuestaCorrecta() == respuesta) {
                    aciertos++;

                    if (desaciertos + aciertos < 10) {
                        valor = 1;
                    } else if (desaciertos * 100 / (desaciertos + aciertos) < 33) {
                        valor = 1;
                    } else if (desaciertos * 100 / (desaciertos + aciertos) < 66) {
                        valor = 2;
                    } else {
                        valor = 3;
                    }
                } else {
                    desaciertos = +1;
                }
                registroJson.put("Aciertos", aciertos);
                registroJson.put("Desaciertos", desaciertos);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            if (pregunta.obtenerRespuestaCorrecta() == respuesta) {
                lista.add(new RegistroEstadistico(pregunta.obtenernumeroPregunta(), 1, 0).toJsonObject());
                valor = 1;
            } else {
                lista.add(new RegistroEstadistico(pregunta.obtenernumeroPregunta(), 0, 1).toJsonObject());
            }
            guardarDatosEnArchivo(datosJSON, nombreArchivo);

        }
        return valor;
    }

    /**
     * Guarda un objeto JSONObject en un archivo en formato JSON.
     *
     * @param datosJSON El objeto JSONObject que se va a guardar en el archivo.
     * @param nombreArchivo El nombre del archivo en formato JSON donde se
     * guardarán los datos.
     */
    private static void guardarDatosEnArchivo(JSONObject datosJSON, String nombreArchivo) {
        try (FileWriter archivo = new FileWriter(nombreArchivo)) {

            archivo.write(datosJSON.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
