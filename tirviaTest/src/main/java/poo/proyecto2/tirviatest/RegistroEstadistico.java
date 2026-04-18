/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proyecto2.tirviatest;

import java.io.Serializable;
import org.json.simple.JSONObject;

/**
 * La clase RegistroEstadistico representa estadísticas relacionadas con una
 * pregunta, incluyendo el número de pregunta, la cantidad de aciertos y la
 * cantidad de desaciertos.
 */
public class RegistroEstadistico implements Serializable {

    /**
     * Número de la pregunta asociada a las estadísticas.
     */
    public int numPregunta;
    /**
     * Cantidad de aciertos para la pregunta.
     */
    public int aciertos;
    /**
     * Cantidad de desaciertos para la pregunta.
     */
    public int desaciertos;

    /**
     * Constructor de la clase RegistroEstadistico.
     *
     * @param numPregunta Número de la pregunta asociada a las estadísticas.
     * @param aciertos Cantidad de aciertos para la pregunta.
     * @param desaciertos Cantidad de desaciertos para la pregunta.
     */
    public RegistroEstadistico(int numPregunta, int aciertos, int desaciertos) {
        this.aciertos = aciertos;
        this.numPregunta = numPregunta;
        this.desaciertos = desaciertos;
    }

    /**
     * Convierte el registro estadístico a un objeto JSON.
     *
     * @return Un objeto JSON que representa la información del registro
     * estadístico.
     */
    public JSONObject toJsonObject() {
        JSONObject jsonRegistro = new JSONObject();
        jsonRegistro.put("Numero de pregunta", numPregunta);
        jsonRegistro.put("Aciertos", aciertos);
        jsonRegistro.put("Desaciertos", desaciertos);

        // Agregar eventos si la lista no es nula
        return jsonRegistro;
    }

    /**
     * Representación en cadena del registro estadístico.
     *
     * @return Una cadena que muestra el número de pregunta, aciertos y
     * desaciertos.
     */
    public String toString() {
        return String.format("#p:%d a:%d d:%d", new Object[]{Integer.valueOf(this.numPregunta), Integer.valueOf(this.aciertos), Integer.valueOf(this.desaciertos)});
    }
}
