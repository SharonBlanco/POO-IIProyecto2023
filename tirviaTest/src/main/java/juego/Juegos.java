/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package juego;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import poo.proyecto2.cine.CategoriaCine;
import poo.proyecto2.general.CategoriaGeneral;
import poo.proyecto2.historiacomputacion.CategoriaHistoriaComputacion;
import poo.proyecto2.paises.CategoriaPais;
import poo.proyecto2.tirviatest.ArchivoJason;
import poo.proyecto2.tirviatest.Jugador;
import poo.proyecto2.triviaquirk.excepciones.excepcionPartidaNoDisponible;
import poo.proyecto2.triviaquirk.excepciones.excepcionPreguntasNoDisponibles;
import poo.proyecto2.triviaquirk.excepciones.excepcionRangoMayor;
import poo.proyecto2.triviaquirk.iCategorias;
import poo.proyecto2.triviaquirk.iJugador;
import poo.proyecto2.triviaquirk.iPregunta;
import poo.proyecto2.triviaquirk.iSuscriptorPreguntas;

import poo.proyecto2.tirviatest.ArchivoJason;
import poo.proyecto2.tirviatest.Partida;


import java.util.Comparator;

/**
 * La clase que maneja el juego principal.
 */
public class Juegos extends javax.swing.JFrame {

    /**
     * Almacena datos JSON relacionados con la categoría "Paises".
     */
    private static JSONObject datosJSONPaises;
    /**
     * Almacena datos JSON relacionados con la categoría "General".
     */
    private static JSONObject datosJSONGeneral;
    /**
     * Almacena datos JSON relacionados con la categoría "Cine".
     */
    private static JSONObject datosJSONCine;
    /**
     * Almacena datos JSON relacionados con una categoría hipotética (HC).
     */
    private static JSONObject datosJSONHC;
    
    
    
    /**
     * Almacena datos JSON globales para el juego.
     */
    private static JSONObject datosJSONGlobal = new JSONObject();
    /**
     * Lista de categorías obtenida de la clase Inicio.
     */
    private static ArrayList<String> categorias = Inicio.lista;
    /**
     * Modo de juego (un jugador o multijugador) obtenido de la clase Inicio.
     */
    private static byte soloOMultijugador = Inicio.numeroJugadores;
    /**
     * Estado del temporizador en el juego.
     */
    private boolean temporizadorFinalizado = false;
    /**
     * Mapa que almacena preguntas por categoría obtenido de la clase Inicio.
     */
    private Map<String, ArrayList<iPregunta>> PreguntasPorCategoria = Inicio.PreguntasPorCategoria;
    /**
     * Clip de sonido para efectos de sonido.
     */
    private static Clip clipSound;
    /**
     * Clip de música para la banda sonora del juego.
     */
    private static Clip clipMusic;
    
    
    
    public String ruta = System.getProperty("user.dir") + "/src/main/java/assets/Sounds/";
    
    /**
     * Ruta para los archivos de sonido.
     */
    //private String ruta = System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Sounds\\";
    /**
     * Número de la pregunta actual.
     */
    private static byte nPregunta = 0;
    /**
     * Indicador de reproducción de música.
     */
    private static boolean music = false;
    /**
     * Indicador de finalización del juego.
     */
    private static boolean terminado = false;
    /**
     * Respuesta seleccionada por el jugador.
     */
    private byte respuesta = 2;
    /**
     * Estado de edición del juego.
     */
    private boolean edicion = true;
    /**
     * Botón para la respuesta correcta.
     */
    private JButton correctoButton;
    /**
     * Temporizador principal del juego.
     */
    private Timer timer;
    /**
     * Temporizador para la respuesta correcta.
     */
    private Timer correcto;
    /**
     * Categoría actual en el juego.
     */
    private String categoriaActual;
    /**
     * Pregunta actual en el juego.
     */
    private static iPregunta preguntaActual;

    /**
     * Constructor de la clase Juegos.
     *
     * @throws excepcionPreguntasNoDisponibles Lanza una excepción si no hay
     * preguntas disponibles.
     * 
     * 
     */
  
    
    private String rutaAsset(String rutaRelativa) {
    return System.getProperty("user.dir") + "/src/main/java/assets/" + rutaRelativa;
}

private javax.swing.ImageIcon icono(String rutaRelativa) {
    String rutaCompleta = rutaAsset(rutaRelativa);
    java.io.File archivo = new java.io.File(rutaCompleta);

    if (!archivo.exists()) {
        System.out.println("No se encontró la imagen: " + rutaCompleta);
    }

    return new javax.swing.ImageIcon(rutaCompleta);
}
    public Juegos() throws excepcionPreguntasNoDisponibles {
        // Inicializa los componentes gráficos
        initComponents();
        setIconImage(icono("icon.png").getImage());
        setTitle("TriviaQuirk | Pregunta #" + (nPregunta + 1));
        // Incrementa el número de pregunta
        nPregunta++;
        // Oculta algunos elementos gráficos al inicio del juego
        count.setVisible(false);
        Informe.setVisible(false);
        InfoCategoria.setVisible(false);
        tiempoFuera.setVisible(false);
        confirmacion.setVisible(false);
        si.setVisible(false);
        no.setVisible(false);
        // Establece el número de pregunta en la interfaz gráfica
        numPregunta.setText("" + nPregunta);
        // Variable para representar la categoría de países
        CategoriaPais categoriaPais = null;

        // Verifica si la música ya está en reproducción
        if (music == false) {
            // Reproduce la música del juego
            music("MusicGame");
            music = true;
        }
        // Verifica si el número de pregunta es menor o igual a 30
        if (nPregunta <= 30) {

            Ganaste.setVisible(false);
            volver.setVisible(false);
            puntajeFinal.setVisible(false);
        } else {
            terminado();
        }

        //Map<String, ArrayList<iPregunta>>  preguntasPorCategoria = elegirPreguntas(categorias);
        for (Map.Entry<String, ArrayList<iPregunta>> entry : this.PreguntasPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            this.categoriaActual = categoria;
            ArrayList<iPregunta> preguntas = entry.getValue();

            if (preguntas.isEmpty()) {
                // Si no hay preguntas en la categoría, puedes manejarlo de acuerdo a tus necesidades
                System.out.println("No hay preguntas disponibles en la categoría: " + categoria);
                continue;
            }
            // Realiza el cambio de jugador
            iPregunta lapregunta = preguntas.get(0);
            this.preguntaActual = lapregunta;

            Inicio.jugadorActual = CambiarJugador(Inicio.listaJugadores.size(), Inicio.jugadorActual);
            // Configura la interfaz gráfica con la pregunta y opciones
            setText(lapregunta.obtenerDescripcion(), lapregunta.obtenerRespuesta1(), lapregunta.obtenerRespuesta2(), lapregunta.obtenerRespuesta3(), categoria, "Jugador " + Inicio.jugadorActual, (byte) Inicio.listaJugadores.get(Inicio.jugadorActual-1).obtenerPuntaje());

            // Remueve la pregunta procesada de la lista de preguntas
            preguntas.remove(lapregunta);

            // Inicia el temporizador para la pregunta actual
            iniciarTemporizador();

            // Rompe el bucle después de procesar la primera pregunta
            break;
        }
    }

    /**
     * Obtiene la pregunta actual del juego.
     *
     * @return La pregunta actual del juego.
     */
    public static iPregunta getPreguntaActual() {
        return preguntaActual;
    }

    /**
     * Cambia el jugador actual en función del número total de jugadores.
     *
     * @param numero El número total de jugadores.
     * @param j El jugador actual.
     * @return El siguiente jugador en función del número total de jugadores.
     * @throws IllegalArgumentException Si el valor de j no es válido para el
     * número dado de jugadores.
     */
    public byte CambiarJugador(int numero, byte j) {
        if (numero == 2) {
            if (j == 1) {
                return 2;
            } else if (j == 2) {
                return 1;
            } else {
                // Manejo de error para valores de j no esperados
                throw new IllegalArgumentException("Valor de j no válido para 2 jugadores");
            }
        } else if (numero == 3) {
            switch (j) {
                case 1:
                    return 2;
                case 2:
                    return 3;
                case 3:
                    return 1;
                default:
                    // Manejo de error para valores de j no esperados
                    throw new IllegalArgumentException("Valor de j no válido para 3 jugadores");

            }
        } else if (numero == 4) {
            switch (j) {
                case 1:
                    return 2;
                case 2:
                    return 3;
                case 3:
                    return 4;

                case 4:
                    return 1;
                default:
                    // Manejo de error para valores de j no esperados
                    throw new IllegalArgumentException("Valor de j no válido para 4 jugadores");
            }
        }
        return 0;
    }

    /**
     * Agrega una pregunta al archivo JSON correspondiente según la categoría
     * especificada.
     *
     * @param categoria La categoría de la pregunta.
     * @param pregunta La pregunta a agregar.
     * @param numero El número de la pregunta.
     * @return El valor indicando el éxito de la operación (1 si se agregó
     * correctamente, 0 si ocurrió un error).
     */
    public static int AgregarAarchivo(String categoria, iPregunta pregunta, byte numero) {
        ArchivoJason archivoJson = null;
        String nombreArchivo = "datosJSON";
        JSONObject datosJSON = new JSONObject();
        if (categoria.equals("Cine")) {
            archivoJson = new ArchivoJason("datosJSONCine.json");
            nombreArchivo = "datosJSONCine.json";
            datosJSONCine = archivoJson.cargarDatosDesdeArchivo(nombreArchivo);
            datosJSON = datosJSONCine;

        } else if (categoria.equals("Historia de la Computacion")) {
            archivoJson = new ArchivoJason("datosJSONHC.json");
            nombreArchivo = "datosJSONHC.json";
            datosJSONHC = archivoJson.cargarDatosDesdeArchivo(nombreArchivo);
            datosJSON = datosJSONHC;
        } else if (categoria.equals("Cultura General")) {
            archivoJson = new ArchivoJason("datosJSONGeneral.json");
            nombreArchivo = "datosJSONGeneral.json";
            datosJSONGeneral = archivoJson.cargarDatosDesdeArchivo(nombreArchivo);
            datosJSON = datosJSONGeneral;
        } else if (categoria.equals(" Paises")) {
            archivoJson = new ArchivoJason("datosJSONPaises.json");
            nombreArchivo = "datosJSONPaises.json";
            datosJSONPaises = archivoJson.cargarDatosDesdeArchivo(nombreArchivo);
            datosJSON = datosJSONPaises;
        }

        int valor;
        valor = archivoJson.agregarDatoALista(datosJSON, nombreArchivo, pregunta, numero);
        return valor;
    }

    /**
     * Determina al ganador y muestra los puntajes finales.
     *
     * @param listaJugadores La lista de jugadores.
     * @return Una cadena que muestra los puntajes finales y al ganador.
     */
   public static String SaberGanador(ArrayList<iJugador> listaJugadores) {
    Collections.sort(listaJugadores, Comparator.comparing(iJugador::obtenerPuntaje).reversed());

    StringBuilder sb = new StringBuilder();
    sb.append("<html><div style='text-align:center;'>");

    for (iJugador jugador : listaJugadores) {
        sb.append("<span style='font-size:20px; color:white;'>")
          .append(jugador.obtenerNombreJugador())
          .append(": ")
          .append(jugador.obtenerPuntaje())
          .append(" pts")
          .append("</span><br>");
    }

    if (!listaJugadores.isEmpty()) {
        iJugador ganador = listaJugadores.get(0);

        sb.append("<br>");
        sb.append("<span style='font-size:24px; color:yellow;'><b>GANADOR: ")
          .append(ganador.obtenerNombreJugador())
          .append("</b></span><br>");
        sb.append("<span style='font-size:20px; color:white;'>Con ")
          .append(ganador.obtenerPuntaje())
          .append(" puntos</span>");
    }

    sb.append("</div></html>");

    puntajeFinal.setText(sb.toString());
    return sb.toString();
}

    /**
     * Establece el texto en los componentes de la interfaz gráfica del juego.
     *
     * @param pregunta La descripción de la pregunta.
     * @param respuesta1 La primera opción de respuesta.
     * @param respuesta2 La segunda opción de respuesta.
     * @param respuesta3 La tercera opción de respuesta.
     * @param jugadorActual El nombre del jugador actual.
     * @param categoria La categoría de la pregunta.
     * @param puntaje El puntaje del jugador actual.
     */
    private void setText(String pregunta, String Respuesta1, String Respuesta2, String Respuesta3, String JugadorActual, String categoria, byte puntaje) {
        this.pregunta.setText(pregunta);
        this.Respuesta1.setText(Respuesta1);
        this.Respuesta2.setText(Respuesta2);
        this.Respuesta3.setText(Respuesta3);
        this.JugadorActual.setText(JugadorActual);
        this.puntaje1.setText(String.valueOf(puntaje));
        this.Categoria.setText(categoria);
    }

    /**
     * Elige preguntas aleatorias de las categorías seleccionadas y las agrupa
     * por categoría.
     *
     * @param categorias Lista de categorías seleccionadas.
     * @return Un mapa que contiene listas de preguntas por cada categoría.
     * @throws excepcionPreguntasNoDisponibles Si no hay suficientes preguntas
     * disponibles.
     */
    public static Map<String, ArrayList<iPregunta>> ElegirPreguntas(ArrayList<String> categorias) throws excepcionPreguntasNoDisponibles {
        Map<String, ArrayList<iPregunta>> preguntasPorCategoria = new HashMap<>();

        Map<String, iCategorias> categoriasMap = new HashMap<>();
        ArrayList<iCategorias> lista = new ArrayList<>();

        // Mapeo de nombres de categorías a instancias de clases
        categoriasMap.put("Paises", CategoriaPais.getInstance());
        categoriasMap.put("Sorpresa", CategoriaHistoriaComputacion.getInstance());
        categoriasMap.put("General", CategoriaGeneral.getInstance());
        categoriasMap.put("Cine", CategoriaCine.getInstance());
        
      

        // Que categorías se eligieron
        for (String categoria : categorias) {
            iCategorias categoriaInstance = categoriasMap.get(categoria);
            if (categoriaInstance != null) {
                lista.add(categoriaInstance);
            }
        }

        for (iCategorias laCategoria : lista) {
            int numeroPartida = laCategoria.registrarPartida();
            byte i = 0;
            switch (lista.size()) {
                case 3:
                    i = 10;
                    break;
                case 2:
                    i = 15;
                    break;
                case 1:
                    i = 30;
                    break;
                case 4:
                    i = 4;
                    break;
                default:
                    break;
            }
            for (byte j = 0; j < i; j++) {
                iPregunta pregunta = laCategoria.obtenerPreguntaAleatoria(numeroPartida);

                // Obtener o crear la lista de preguntas para la categoría
                ArrayList<iPregunta> preguntasDeCategoria = preguntasPorCategoria.getOrDefault(laCategoria.nombreCategoria(), new ArrayList<>());

                // Agregar la nueva pregunta a la lista
                preguntasDeCategoria.add(pregunta);

                // Guardar la lista actualizada en el mapa
                preguntasPorCategoria.put(laCategoria.nombreCategoria(), preguntasDeCategoria);
            }
        }
        return preguntasPorCategoria;

    }

    /**
     * Creates new form Juegos
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">    
    @SuppressWarnings("unchecked")
private void initComponents() {

    InfoCategoria = new javax.swing.JLabel();
    Informe = new javax.swing.JLabel();
    volver = new javax.swing.JButton();
    puntajeFinal = new javax.swing.JLabel();
    Ganaste = new javax.swing.JLabel();
    no = new javax.swing.JButton();
    si = new javax.swing.JButton();
    confirmacion = new javax.swing.JLabel();
    terminar = new javax.swing.JButton();
    numPregunta = new javax.swing.JLabel();
    info = new javax.swing.JLabel();
    tiempoFuera = new javax.swing.JLabel();
    pregunta = new javax.swing.JLabel();
    count = new javax.swing.JLabel();
    puntaje1 = new javax.swing.JLabel();
    tiempo = new javax.swing.JLabel();
    tiempoFondo = new javax.swing.JLabel();
    Respuesta3 = new javax.swing.JButton();
    Respuesta2 = new javax.swing.JButton();
    Respuesta1 = new javax.swing.JButton();
    JugadorActual = new javax.swing.JLabel();
    Categoria = new javax.swing.JLabel();
    fondo = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setMinimumSize(new java.awt.Dimension(1270, 700));
    setResizable(false);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    InfoCategoria.setFont(new java.awt.Font("Gagalin", 0, 36)); // NOI18N
    InfoCategoria.setForeground(new java.awt.Color(255, 255, 255));
    InfoCategoria.setText("Categoria:");
    getContentPane().add(InfoCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, -1));

    Informe.setFont(new java.awt.Font("Gagalin", 0, 40));
Informe.setForeground(new java.awt.Color(255, 255, 255));
Informe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
Informe.setText("RESULTADO FINAL");
getContentPane().add(Informe, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 650, 50));

    volver.setIcon(icono("Pantalla Inicio/Cerrar.png"));
    volver.setBorderPainted(false);
    volver.setContentAreaFilled(false);
    volver.setRolloverIcon(icono("Pantalla Inicio/Cerrar_selec.png"));
    volver.setRolloverSelectedIcon(icono("Pantalla Inicio/Cerrar_selec.png"));
    volver.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            volverMouseEntered(evt);
        }
    });
    volver.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            volverActionPerformed(evt);
        }
    });
    getContentPane().add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, 460, 120));

    puntajeFinal.setFont(new java.awt.Font("Gagalin", 1, 24));
puntajeFinal.setForeground(new java.awt.Color(255, 255, 255));
puntajeFinal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
puntajeFinal.setVerticalAlignment(javax.swing.SwingConstants.TOP);
puntajeFinal.setText("0");
    getContentPane().add(puntajeFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 700, 170));

    Ganaste.setIcon(icono("Juego/Ganaste.png"));
    getContentPane().add(Ganaste, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    no.setIcon(icono("Juego/no.png"));
    no.setBorderPainted(false);
    no.setContentAreaFilled(false);
    no.setRolloverIcon(icono("Juego/no Cursor.png"));
    no.setRolloverSelectedIcon(icono("Juego/no Cursor.png"));
    no.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            noMouseEntered(evt);
        }
    });
    no.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            noActionPerformed(evt);
        }
    });
    getContentPane().add(no, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, 190, 80));

    si.setIcon(icono("Juego/si.png"));
    si.setBorderPainted(false);
    si.setContentAreaFilled(false);
    si.setDefaultCapable(false);
    si.setRolloverIcon(icono("Juego/si Cursor.png"));
    si.setRolloverSelectedIcon(icono("Juego/si Cursor.png"));
    si.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            siMouseEntered(evt);
        }
    });
    si.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            siActionPerformed(evt);
        }
    });
    getContentPane().add(si, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 190, 80));

    confirmacion.setIcon(icono("Juego/confirmacion.png"));
    getContentPane().add(confirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    terminar.setIcon(icono("Juego/terminar.png"));
    terminar.setBorderPainted(false);
    terminar.setContentAreaFilled(false);
    terminar.setRolloverIcon(icono("Juego/terminar Cursor.png"));
    terminar.setRolloverSelectedIcon(icono("Juego/terminar Cursor.png"));
    terminar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            terminarActionPerformed(evt);
        }
    });
    getContentPane().add(terminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 280, 60));

    numPregunta.setFont(new java.awt.Font("Gagalin", 0, 48)); // NOI18N
    numPregunta.setForeground(new java.awt.Color(255, 255, 255));
    numPregunta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    numPregunta.setText("1");
    getContentPane().add(numPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 50, -1));

    info.setFont(new java.awt.Font("Gagalin", 0, 48)); // NOI18N
    info.setForeground(new java.awt.Color(255, 255, 255));
    info.setText("de 30");
    getContentPane().add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

    tiempoFuera.setIcon(icono("Juego/tiempo!!.png"));
    getContentPane().add(tiempoFuera, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, -1));

    pregunta.setFont(new java.awt.Font("Gagalin", 0, 24)); // NOI18N
    pregunta.setForeground(new java.awt.Color(255, 255, 255));
    pregunta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    pregunta.setText("¡Preparate!");
    pregunta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    getContentPane().add(pregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1270, 100));

    count.setFont(new java.awt.Font("Gagalin", 0, 80)); // NOI18N
    count.setForeground(new java.awt.Color(255, 255, 255));
    count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    count.setText("3");
    count.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    getContentPane().add(count, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 100, 70));

    puntaje1.setFont(new java.awt.Font("Gagalin", 0, 48)); // NOI18N
    puntaje1.setForeground(new java.awt.Color(255, 255, 255));
    puntaje1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    puntaje1.setText("0");
    puntaje1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    getContentPane().add(puntaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 90, 50));

    tiempo.setFont(new java.awt.Font("Gagalin", 0, 80)); // NOI18N
    tiempo.setForeground(new java.awt.Color(255, 255, 255));
    tiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    tiempo.setText("20");
    tiempo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    getContentPane().add(tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 430, 110, 70));

    tiempoFondo.setIcon(icono("Juego/tiempo_verde.png"));
    getContentPane().add(tiempoFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 370, 200, 180));

    Respuesta3.setFont(new java.awt.Font("Gagalin", 0, 24)); // NOI18N
    Respuesta3.setForeground(new java.awt.Color(255, 255, 255));
    Respuesta3.setIcon(icono("Juego/botonSimple.png"));
    Respuesta3.setText("jButton3");
    Respuesta3.setBorderPainted(false);
    Respuesta3.setContentAreaFilled(false);
    Respuesta3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Respuesta3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    Respuesta3.setRolloverIcon(icono("Juego/botonCursor.png"));
    Respuesta3.setRolloverSelectedIcon(icono("Juego/botonCursor.png"));
    Respuesta3.setSelectedIcon(icono("Juego/botonVerde.png"));
    Respuesta3.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            Respuesta3MouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            Respuesta3MouseExited(evt);
        }
    });
    Respuesta3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Respuesta3ActionPerformed(evt);
        }
    });
    getContentPane().add(Respuesta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, 360, 90));

    Respuesta2.setFont(new java.awt.Font("Gagalin", 0, 24)); // NOI18N
    Respuesta2.setForeground(new java.awt.Color(255, 255, 255));
    Respuesta2.setIcon(icono("Juego/botonSimple.png"));
    Respuesta2.setText("jButton2");
    Respuesta2.setBorderPainted(false);
    Respuesta2.setContentAreaFilled(false);
    Respuesta2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Respuesta2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    Respuesta2.setRolloverIcon(icono("Juego/botonCursor.png"));
    Respuesta2.setRolloverSelectedIcon(icono("Juego/botonCursor.png"));
    Respuesta2.setSelectedIcon(icono("Juego/botonVerde.png"));
    Respuesta2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            Respuesta2MouseEntered(evt);
        }
    });
    Respuesta2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Respuesta2ActionPerformed(evt);
        }
    });
    getContentPane().add(Respuesta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, 360, 100));

    Respuesta1.setFont(new java.awt.Font("Gagalin", 0, 24)); // NOI18N
    Respuesta1.setForeground(new java.awt.Color(255, 255, 255));
    Respuesta1.setIcon(icono("Juego/botonSimple.png"));
    Respuesta1.setText("jButton1");
    Respuesta1.setBorderPainted(false);
    Respuesta1.setContentAreaFilled(false);
    Respuesta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Respuesta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    Respuesta1.setRolloverIcon(icono("Juego/botonCursor.png"));
    Respuesta1.setRolloverSelectedIcon(icono("Juego/botonCursor.png"));
    Respuesta1.setSelectedIcon(icono("Juego/botonVerde.png"));
    Respuesta1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            Respuesta1MouseEntered(evt);
        }
    });
    Respuesta1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Respuesta1ActionPerformed(evt);
        }
    });
    getContentPane().add(Respuesta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 360, 90));

    JugadorActual.setFont(new java.awt.Font("Gagalin", 0, 36)); // NOI18N
    JugadorActual.setForeground(new java.awt.Color(255, 255, 255));
    JugadorActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    JugadorActual.setText("jLabel1");
    getContentPane().add(JugadorActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 60, 200, 50));

    Categoria.setFont(new java.awt.Font("Gagalin", 0, 36)); // NOI18N
    Categoria.setForeground(new java.awt.Color(255, 255, 255));
    Categoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Categoria.setText("jLabel1");
    getContentPane().add(Categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 410, 50));

    fondo.setIcon(icono("Juego/Fondo Juego.png"));
    getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    pack();
}// </editor-fold>
    
    
    
    /*@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InfoCategoria = new javax.swing.JLabel();
        Informe = new javax.swing.JLabel();
        volver = new javax.swing.JButton();
        puntajeFinal = new javax.swing.JLabel();
        Ganaste = new javax.swing.JLabel();
        no = new javax.swing.JButton();
        si = new javax.swing.JButton();
        confirmacion = new javax.swing.JLabel();
        terminar = new javax.swing.JButton();
        numPregunta = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        tiempoFuera = new javax.swing.JLabel();
        pregunta = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        puntaje1 = new javax.swing.JLabel();
        tiempo = new javax.swing.JLabel();
        tiempoFondo = new javax.swing.JLabel();
        Respuesta3 = new javax.swing.JButton();
        Respuesta2 = new javax.swing.JButton();
        Respuesta1 = new javax.swing.JButton();
        JugadorActual = new javax.swing.JLabel();
        Categoria = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1270, 630));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InfoCategoria.setFont(new java.awt.Font("Gagalin", 0, 36)); // NOI18N
        InfoCategoria.setForeground(new java.awt.Color(255, 255, 255));
        InfoCategoria.setText("Categoria:");
        getContentPane().add(InfoCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, -1));

        Informe.setFont(new java.awt.Font("Gagalin", 0, 48)); // NOI18N
        Informe.setForeground(new java.awt.Color(255, 255, 255));
        Informe.setText("Informe de Puntajes:");
        getContentPane().add(Informe, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, -1));

        volver.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Pantalla Inicio\\Cerrar.png"));
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setRolloverIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Pantalla Inicio\\Cerrar_selec.png"));
        volver.setRolloverSelectedIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Pantalla Inicio\\Cerrar_selec.png"));
        volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                volverMouseEntered(evt);
            }
        });
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        getContentPane().add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, 460, 120));

        puntajeFinal.setFont(new java.awt.Font("Gagalin", 1, 24)); // NOI18N
        puntajeFinal.setForeground(new java.awt.Color(255, 255, 255));
        puntajeFinal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        puntajeFinal.setText("0");
        puntajeFinal.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(puntajeFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 920, 280));

        Ganaste.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Fondo.png"));
        getContentPane().add(Ganaste, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        no.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\no.png"));
        no.setBorderPainted(false);
        no.setContentAreaFilled(false);
        no.setRolloverIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\no Cursor.png"));
        no.setRolloverSelectedIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\no Cursor.png"));
        no.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                noMouseEntered(evt);
            }
        });
        no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noActionPerformed(evt);
            }
        });
        getContentPane().add(no, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, 190, 80));

        si.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\si.png"));
        si.setBorderPainted(false);
        si.setContentAreaFilled(false);
        si.setDefaultCapable(false);
        si.setRolloverIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\si Cursor.png"));
        si.setRolloverSelectedIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\si Cursor.png"));
        si.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                siMouseEntered(evt);
            }
        });
        si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siActionPerformed(evt);
            }
        });
        getContentPane().add(si, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 190, 80));

        confirmacion.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\confirmacion.png"));
        getContentPane().add(confirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        terminar.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\terminar.png"));
        terminar.setBorderPainted(false);
        terminar.setContentAreaFilled(false);
        terminar.setRolloverIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\terminar Cursor.png"));
        terminar.setRolloverSelectedIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\terminar Cursor.png"));
        terminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarActionPerformed(evt);
            }
        });
        getContentPane().add(terminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 280, 60));

        numPregunta.setFont(new java.awt.Font("Gagalin", 0, 48)); // NOI18N
        numPregunta.setForeground(new java.awt.Color(255, 255, 255));
        numPregunta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        numPregunta.setText("1");
        getContentPane().add(numPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 50, -1));

        info.setFont(new java.awt.Font("Gagalin", 0, 48)); // NOI18N
        info.setForeground(new java.awt.Color(255, 255, 255));
        info.setText("de 30");
        getContentPane().add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        tiempoFuera.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\tiempo!!.png"));
        getContentPane().add(tiempoFuera, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, -1));

        pregunta.setFont(new java.awt.Font("Gagalin", 0, 24)); // NOI18N
        pregunta.setForeground(new java.awt.Color(255, 255, 255));
        pregunta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pregunta.setText("¡Preparate!");
        pregunta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(pregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1270, 100));

        count.setFont(new java.awt.Font("Gagalin", 0, 80)); // NOI18N
        count.setForeground(new java.awt.Color(255, 255, 255));
        count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count.setText("3");
        count.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(count, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 100, 70));

        puntaje1.setFont(new java.awt.Font("Gagalin", 0, 48)); // NOI18N
        puntaje1.setForeground(new java.awt.Color(255, 255, 255));
        puntaje1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        puntaje1.setText("0");
        puntaje1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(puntaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 90, 50));

        tiempo.setFont(new java.awt.Font("Gagalin", 0, 80)); // NOI18N
        tiempo.setForeground(new java.awt.Color(255, 255, 255));
        tiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tiempo.setText("20");
        tiempo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 430, 110, 70));

        tiempoFondo.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\tiempo_verde.png"));
        getContentPane().add(tiempoFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 370, 200, 180));

        Respuesta3.setFont(new java.awt.Font("Gagalin", 0, 24)); // NOI18N
        Respuesta3.setForeground(new java.awt.Color(255, 255, 255));
        Respuesta3.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonSimple.png"));
        Respuesta3.setText("jButton3");
        Respuesta3.setBorderPainted(false);
        Respuesta3.setContentAreaFilled(false);
        Respuesta3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Respuesta3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Respuesta3.setRolloverIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonCursor.png"));
        Respuesta3.setRolloverSelectedIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonCursor.png"));
        Respuesta3.setSelectedIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonVerde.png"));
        Respuesta3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Respuesta3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Respuesta3MouseExited(evt);
            }
        });
        Respuesta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Respuesta3ActionPerformed(evt);
            }
        });
        getContentPane().add(Respuesta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, 360, 90));

        Respuesta2.setFont(new java.awt.Font("Gagalin", 0, 24)); // NOI18N
        Respuesta2.setForeground(new java.awt.Color(255, 255, 255));
        Respuesta2.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonSimple.png"));
        Respuesta2.setText("jButton2");
        Respuesta2.setBorderPainted(false);
        Respuesta2.setContentAreaFilled(false);
        Respuesta2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Respuesta2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Respuesta2.setRolloverIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonCursor.png"));
        Respuesta2.setRolloverSelectedIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonCursor.png"));
        Respuesta2.setSelectedIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonVerde.png"));
        Respuesta2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Respuesta2MouseEntered(evt);
            }
        });
        Respuesta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Respuesta2ActionPerformed(evt);
            }
        });
        getContentPane().add(Respuesta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, 360, 100));

        Respuesta1.setFont(new java.awt.Font("Gagalin", 0, 24)); // NOI18N
        Respuesta1.setForeground(new java.awt.Color(255, 255, 255));
        Respuesta1.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonSimple.png"));
        Respuesta1.setText("jButton1");
        Respuesta1.setBorderPainted(false);
        Respuesta1.setContentAreaFilled(false);
        Respuesta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Respuesta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Respuesta1.setRolloverIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonCursor.png"));
        Respuesta1.setRolloverSelectedIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonCursor.png"));
        Respuesta1.setSelectedIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\botonVerde.png"));
        Respuesta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Respuesta1MouseEntered(evt);
            }
        });
        Respuesta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Respuesta1ActionPerformed(evt);
            }
        });
        getContentPane().add(Respuesta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 360, 90));

        JugadorActual.setFont(new java.awt.Font("Gagalin", 0, 36)); // NOI18N
        JugadorActual.setForeground(new java.awt.Color(255, 255, 255));
        JugadorActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JugadorActual.setText("jLabel1");
        getContentPane().add(JugadorActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 60, 200, 50));

        Categoria.setFont(new java.awt.Font("Gagalin", 0, 36)); // NOI18N
        Categoria.setForeground(new java.awt.Color(255, 255, 255));
        Categoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Categoria.setText("jLabel1");
        getContentPane().add(Categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 410, 50));

        fondo.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\Fondo Juego.png"));
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
/***/
     
    public void botonCorrecto() {
        switch (Juegos.getPreguntaActual().obtenerRespuestaCorrecta()) {
            case 1:
                correctoButton = Respuesta1;
                break;
            case 2:
                correctoButton = Respuesta2;
                break;
            case 3:
                correctoButton = Respuesta3;
                break;
            default:
                // Manejar el caso por defecto si es necesario
                break;
        }
    }

    /**
     * Maneja el evento de hacer clic en el botón de la Respuesta 1. Notifica a
     * los observadores sobre la elección del jugador y actualiza la apariencia
     * de los botones.
     *
     * @param evt Evento de acción generado al hacer clic en el botón.
     */
    private void Respuesta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Respuesta1ActionPerformed
                                        

    ClaseObservador claseObservador = new ClaseObservador();
    ClaseObservable claseObservable = new ClaseObservable();
    claseObservable.addObservable(claseObservador);
    claseObservable.notifyObservers(this.categoriaActual, preguntaActual, (byte) 1, Inicio.jugadorActual, Inicio.listaJugadores);

    if (edicion == true) {
        JButton botonPresionado = (JButton) evt.getSource();
        botonCorrecto();
        pausarTemporizador();
        iniciarTempoCorrecto();

        if (Juegos.getPreguntaActual().obtenerRespuestaCorrecta() == 1) {
            botonPresionado.setIcon(icono("Juego/botonVerde.png"));
            botonPresionado.setRolloverIcon(icono("Juego/botonVerde.png"));
            botonPresionado.setSelectedIcon(icono("Juego/botonVerde.png"));
            Respuesta1.setEnabled(false);
            sonido("correct");
        } else {
            botonPresionado.setIcon(icono("Juego/botonRojo.png"));
            botonPresionado.setRolloverIcon(icono("Juego/botonRojo.png"));
            botonPresionado.setSelectedIcon(icono("Juego/botonRojo.png"));

            correctoButton.setIcon(icono("Juego/botonVerde.png"));
            correctoButton.setRolloverIcon(icono("Juego/botonVerde.png"));
            correctoButton.setSelectedIcon(icono("Juego/botonVerde.png"));
            sonido("incorrect");
        }
    }
    edicion = false;

    }//GEN-LAST:event_Respuesta1ActionPerformed
    /**
     * Maneja el evento de hacer clic en el botón de la Respuesta 2. Notifica a
     * los observadores sobre la elección del jugador y actualiza la apariencia
     * de los botones.
     *
     * @param evt Evento de acción generado al hacer clic en el botón.
     */
    private void Respuesta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Respuesta2ActionPerformed
        ClaseObservador claseObservador = new ClaseObservador();
    ClaseObservable claseObservable = new ClaseObservable();
    claseObservable.addObservable(claseObservador);
    claseObservable.notifyObservers(this.categoriaActual, preguntaActual, (byte) 2, Inicio.jugadorActual, Inicio.listaJugadores);

    if (edicion == true) {
        JButton botonPresionado = (JButton) evt.getSource();
        botonCorrecto();
        pausarTemporizador();
        iniciarTempoCorrecto();

        if (Juegos.getPreguntaActual().obtenerRespuestaCorrecta() == 2) {
            botonPresionado.setIcon(icono("Juego/botonVerde.png"));
            botonPresionado.setRolloverIcon(icono("Juego/botonVerde.png"));
            botonPresionado.setSelectedIcon(icono("Juego/botonVerde.png"));
            sonido("correct");
        } else {
            botonPresionado.setIcon(icono("Juego/botonRojo.png"));
            botonPresionado.setRolloverIcon(icono("Juego/botonRojo.png"));
            botonPresionado.setSelectedIcon(icono("Juego/botonRojo.png"));

            correctoButton.setIcon(icono("Juego/botonVerde.png"));
            correctoButton.setRolloverIcon(icono("Juego/botonVerde.png"));
            correctoButton.setSelectedIcon(icono("Juego/botonVerde.png"));
            sonido("incorrect");
        }

    }
    edicion = false;
    }//GEN-LAST:event_Respuesta2ActionPerformed
    /**
     * Maneja el evento de hacer clic en el botón de la Respuesta 3. Notifica a
     * los observadores sobre la elección del jugador y actualiza la apariencia
     * de los botones.
     *
     * @param evt Evento de acción generado al hacer clic en el botón.
     */
    private void Respuesta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Respuesta3ActionPerformed
    ClaseObservador claseObservador = new ClaseObservador();
    ClaseObservable claseObservable = new ClaseObservable();
    claseObservable.addObservable(claseObservador);
    claseObservable.notifyObservers(this.categoriaActual, this.preguntaActual, (byte) 3, Inicio.jugadorActual, Inicio.listaJugadores);

    if (edicion == true) {
        JButton botonPresionado = (JButton) evt.getSource();
        botonCorrecto();
        pausarTemporizador();
        iniciarTempoCorrecto();

        if (Juegos.getPreguntaActual().obtenerRespuestaCorrecta() == 3) {
            botonPresionado.setIcon(icono("Juego/botonVerde.png"));
            botonPresionado.setRolloverIcon(icono("Juego/botonVerde.png"));
            botonPresionado.setSelectedIcon(icono("Juego/botonVerde.png"));
            sonido("correct");
        } else {
            botonPresionado.setIcon(icono("Juego/botonRojo.png"));
            botonPresionado.setRolloverIcon(icono("Juego/botonRojo.png"));
            botonPresionado.setSelectedIcon(icono("Juego/botonRojo.png"));

            correctoButton.setIcon(icono("Juego/botonVerde.png"));
            correctoButton.setRolloverIcon(icono("Juego/botonVerde.png"));
            correctoButton.setSelectedIcon(icono("Juego/botonVerde.png"));
            sonido("incorrect");
        }
    }
    edicion = false;
    }//GEN-LAST:event_Respuesta3ActionPerformed
    /**
     * Maneja el evento de pasar el ratón sobre el botón de la Respuesta 1.
     * Reproduce un sonido si la edición está activada.
     *
     * @param evt Evento de ratón generado al pasar el ratón sobre el botón.
     */
    private void Respuesta1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Respuesta1MouseEntered
        // TODO add your handling code here:
        if (edicion == true) {
            sonido("Clickwav");
        }

    }//GEN-LAST:event_Respuesta1MouseEntered
    /**
     * Maneja el evento de entrada del mouse al pasar sobre el botón de
     * Respuesta 2. Reproduce un sonido si la edición está habilitada.
     *
     * @param evt Evento de ratón generado al pasar sobre el botón.
     */
    private void Respuesta2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Respuesta2MouseEntered
        // TODO add your handling code here:
        if (edicion == true) {
            sonido("Clickwav");
        }
    }//GEN-LAST:event_Respuesta2MouseEntered
    /**
     * Maneja el evento de salida del mouse al alejarse del botón de Respuesta
     * 3. Este método no realiza ninguna acción específica.
     *
     * @param evt Evento de ratón generado al alejarse del botón.
     */
    private void Respuesta3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Respuesta3MouseExited
        // TODO add your handling code here:
        //detenerMusica();
    }//GEN-LAST:event_Respuesta3MouseExited
    /**
     * Maneja el evento de entrada del mouse al pasar sobre el botón de
     * Respuesta 3. Reproduce un sonido si la edición está habilitada.
     *
     * @param evt Evento de ratón generado al pasar sobre el botón.
     */
    private void Respuesta3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Respuesta3MouseEntered
        // TODO add your handling code here:
        if (edicion == true) {
            sonido("Clickwav");
        }
    }//GEN-LAST:event_Respuesta3MouseEntered
    /**
     * Maneja el evento de hacer clic en el botón "Volver". Cierra la aplicación
     * al presionar el botón "Volver".
     *
     * @param evt Evento de acción generado al hacer clic en el botón "Volver".
     */
    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        // TODO add your handling code here:
        detenerMusica();
        System.exit(0);

    }//GEN-LAST:event_volverActionPerformed
    /**
     * Maneja el evento de entrada del mouse al pasar sobre el botón "Volver".
     * Reproduce un sonido al pasar el mouse sobre el botón "Volver".
     *
     * @param evt Evento de ratón generado al pasar sobre el botón "Volver".
     */
    private void volverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseEntered
        // TODO add your handling code here:
        sonido("Clickwav");
    }//GEN-LAST:event_volverMouseEntered
    /**
     * Maneja el evento de hacer clic en el botón "Terminar". Muestra la
     * confirmación para terminar el juego. Oculta los botones de respuesta.
     *
     * @param evt Evento de acción generado al hacer clic en el botón
     * "Terminar".
     */
    private void terminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarActionPerformed
        detenerMusica();
        confirmacion.setVisible(true);
        si.setVisible(true);
        no.setVisible(true);
        Respuesta1.setVisible(false);
        Respuesta2.setVisible(false);
        Respuesta3.setVisible(false);
    }//GEN-LAST:event_terminarActionPerformed
    /**
     * Maneja el evento de hacer clic en el botón "Sí" en la confirmación de
     * terminar el juego. Llama al método "terminado" para finalizar el juego.
     *
     * @param evt Evento de acción generado al hacer clic en el botón "Sí".
     */
    private void siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siActionPerformed
        // TODO add your handling code here:
        terminado();
    }//GEN-LAST:event_siActionPerformed
    /**
     * Maneja el evento de hacer clic en el botón "No" en la confirmación de
     * terminar el juego. Oculta la confirmación y vuelve a mostrar los botones
     * de respuesta.
     *
     * @param evt Evento de acción generado al hacer clic en el botón "No".
     */
    private void noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noActionPerformed
        // TODO add your handling code here:
        confirmacion.setVisible(false);
        si.setVisible(false);
        no.setVisible(false);
        Respuesta1.setVisible(true);
        Respuesta2.setVisible(true);
        Respuesta3.setVisible(true);
    }//GEN-LAST:event_noActionPerformed
    /**
     * Maneja el evento de mouse al entrar en el área del botón "si".
     *
     * @param evt El evento de mouse asociado al ingreso del ratón.
     */
    private void siMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_siMouseEntered
        // TODO add your handling code here:
        sonido("Clickwav");
    }//GEN-LAST:event_siMouseEntered
    /**
     * Maneja el evento de mouse al entrar en el área del botón "no".
     *
     * @param evt El evento de mouse asociado al ingreso del ratón.
     */
    private void noMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noMouseEntered
        // TODO add your handling code here:
        sonido("Clickwav");
    }//GEN-LAST:event_noMouseEntered

    /**
     * Muestra la puntuación final y realiza la lógica de fin de juego.
     */
    private void mostrarPuntajeFinal() {
        // Lógica para mostrar el puntaje final, por ejemplo:
        Juegos.SaberGanador(Inicio.listaJugadores);
        puntajeFinal.setVisible(true);
        Partida partida = new Partida(1, Inicio.lista, Inicio.listaJugadores);
        ArchivoJason.GuardarPuntajeGlobal(partida, datosJSONGlobal, "JsonDatosGlobales");
    }

    /**
     * Realiza acciones cuando el juego ha terminado, como mostrar la puntuación
     * final y volver a la pantalla de inicio.
     */
    public void terminado() {
    edicion = false;

    detenerMusica();
    music("juegoTerminado");

    terminado = true;
    pausarTempoCorrecto();
    pausarTemporizador();

    // Ocultar todo lo del juego
    pregunta.setVisible(false);
    Respuesta1.setVisible(false);
    Respuesta2.setVisible(false);
    Respuesta3.setVisible(false);
    tiempo.setVisible(false);
    tiempoFondo.setVisible(false);
    tiempoFuera.setVisible(false);
    numPregunta.setVisible(false);
    info.setVisible(false);
    puntaje1.setVisible(false);
    JugadorActual.setVisible(false);
    Categoria.setVisible(false);
    terminar.setVisible(false);
    count.setVisible(false);
    confirmacion.setVisible(false);
    si.setVisible(false);
    no.setVisible(false);

    // Mostrar solo lo del final
    Ganaste.setVisible(true);
    Informe.setVisible(false);
    puntajeFinal.setVisible(true);
    volver.setVisible(true);

    // Este ya no lo ocupas en final
    InfoCategoria.setVisible(false);

    mostrarPuntajeFinal();
}

    
    
    public void detenerMusica() {
    try {
        if (Inicio.pidMusica != null) {
            new ProcessBuilder(
                    "flatpak-spawn", "--host",
                    "kill", "-9", String.valueOf(Inicio.pidMusica)
            ).start().waitFor();

            Inicio.pidMusica = null;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    /**
     * Reproduce música de fondo según el nombre de archivo proporcionado.
     *
     * @param archivo El nombre del archivo de música (sin extensión) que se va
     * a reproducir.
     */
    public void music(String archivo) {
    try {
        detenerMusica();

        String rutaArchivo = ruta + archivo + ".wav";

        Process p = new ProcessBuilder(
                "flatpak-spawn", "--host",
                "bash", "-lc",
                "paplay \"" + rutaArchivo + "\" >/dev/null 2>&1 & echo $!"
        ).start();

        java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(p.getInputStream())
        );

        String pidTexto = reader.readLine();

        if (pidTexto != null && !pidTexto.isBlank()) {
            Inicio.pidMusica = Long.parseLong(pidTexto.trim());
        }

    } catch (Exception e1) {
        try {
            detenerMusica();

            String rutaArchivo = ruta + archivo + ".wav";

            Process p = new ProcessBuilder(
                    "flatpak-spawn", "--host",
                    "bash", "-lc",
                    "aplay -q \"" + rutaArchivo + "\" >/dev/null 2>&1 & echo $!"
            ).start();

            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(p.getInputStream())
            );

            String pidTexto = reader.readLine();

            if (pidTexto != null && !pidTexto.isBlank()) {
                Inicio.pidMusica = Long.parseLong(pidTexto.trim());
            }

        } catch (Exception e2) {
            System.out.println("No se pudo reproducir la música: " + archivo);
            e2.printStackTrace();
        }
    }
}

    /**
     * Reproduce un efecto de sonido según el nombre de archivo proporcionado.
     *
     * @param archivo El nombre del archivo de sonido (sin extensión) que se va
     * a reproducir.
     */
    public void sonido(String archivo) {
    try {
        String rutaArchivo = ruta + archivo + ".wav";

        new ProcessBuilder(
                "flatpak-spawn", "--host",
                "paplay", rutaArchivo
        ).start();

    } catch (Exception e1) {
        try {
            String rutaArchivo = ruta + archivo + ".wav";

            new ProcessBuilder(
                    "flatpak-spawn", "--host",
                    "aplay", "-q", rutaArchivo
            ).start();

        } catch (Exception e2) {
            System.out.println("No se pudo reproducir el sonido: " + archivo);
            e2.printStackTrace();
        }
    }
}

    /**
     * Inicia un temporizador que se ejecuta cada segundo para gestionar el
     * tiempo de respuesta del jugador. Actualiza un contador de tiempo restante
     * y realiza acciones específicas cuando el tiempo se agota.
     */
    private void iniciarTemporizador() {
        // Crear el Timer que se ejecutará cada segundo
        timer = new Timer(1000, new ActionListener() {
            /**
             * Tiempo restante en segundos
             */
            private int tiempoRestante = 20;

            /**
             * Obtiene el tiempo restante en segundos.
             *
             * @return El tiempo restante actual en segundos.
             */
            public int getTiempoRestante() {
                return tiempoRestante;
            }
            /**
             * Este método se llama cada vez que el temporizador realiza una
             * acción, es decir, cada segundo. Actualiza el tiempo restante,
             * muestra el contador en la interfaz gráfica y realiza acciones
             * adicionales cuando el temporizador llega a cero, como cambiar la
             * imagen del fondo del tiempo, mostrar un mensaje de tiempo agotado
             * y redirigir a otra pantalla.
             *
             * @param e El evento de acción que desencadena la llamada a este
             * método.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar el tiempo restante
                tiempoRestante--;

                // Actualizar el texto del JLabel
                tiempo.setText(Integer.toString(tiempoRestante));
                // Cambiar la imagen del fondo del tiempo si el tiempo es menor o igual a 10
                if (tiempoRestante <= 10) {
                    tiempoFondo.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\Juego\\tiempo-rojo.png"));

                }

                // Verificar si el tiempo ha llegado a 0
                if (tiempoRestante == 0) {
                    ((Timer) e.getSource()).stop();
                    tiempoFuera.setVisible(true);
                    edicion = false;
                    temporizadorFinalizado = true;

                    // Puedes realizar acciones adicionales cuando el temporizador llega a 0
                    // Crear un Timer adicional para ocultar tiempoFuera después de 3 segundos
                    Timer ocultarTimer = new Timer(3000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            tiempoFuera.setVisible(false);
                            ((Timer) evt.getSource()).stop();
                            Juegos juego;
                            setVisible(false);

                            try {
                                juego = new Juegos();
                                juego.setVisible(true);

                            } catch (excepcionPreguntasNoDisponibles ex) {
                                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

                    // Iniciar el temporizador para ocultar tiempoFuera
                    if (terminado == false) {
                        ocultarTimer.start();
                    }

                }
            }
        });

        // Iniciar el temporizador
        timer.start();

    }

    /**
     * Inicia un temporizador corto para gestionar el tiempo entre la respuesta
     * correcta y la siguiente pregunta. Este temporizador proporciona una breve
     * pausa antes de mostrar la siguiente pregunta.
     */
    private void iniciarTempoCorrecto() {
        // Crear el Timer que se ejecutará cada segundo
        sonido("3seconds");

        count.setVisible(true);
        count.setText("3");
        correcto = new Timer(1000, new ActionListener() {
            /**
             * Tiempo restante en segundos
             */
            private int tiempoRestante = 3;

            /**
             * Obtiene el tiempo restante en segundos.
             *
             * @return El tiempo restante actual en segundos.
             */
            public int getTiempoRestante() {
                return tiempoRestante;

            }

            /**
             * Este método se llama cada vez que el temporizador realiza una
             * acción, es decir, cada segundo. Actualiza el tiempo restante,
             * muestra el contador en la interfaz gráfica y realiza acciones
             * adicionales cuando el temporizador llega a cero, como detener el
             * temporizador, ocultar elementos visuales y redirigir a otra
             * pantalla.
             *
             * @param e El evento de acción que desencadena la llamada a este
             * método.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar el tiempo restante

                tiempoRestante--;

                count.setText(Integer.toString(tiempoRestante));

                // Verificar si el tiempo ha llegado a 0
                if (tiempoRestante == 0) {
                    ((Timer) e.getSource()).stop();

                    temporizadorFinalizado = true;
                    tiempoFuera.setVisible(false);
                    ((Timer) e.getSource()).stop();
                    if (terminado == false) {
                        Juegos juego;
                        setVisible(false);
                        try {
                            juego = new Juegos();
                            juego.setVisible(true);

                        } catch (excepcionPreguntasNoDisponibles ex) {
                            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }
        });

        // Iniciar el temporizador
        correcto.start();

    }

    /**
     * Pausa el temporizador principal si está en ejecución.
     */
    private void pausarTemporizador() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    /**
     * Pausa el temporizador corto utilizado entre la respuesta correcta y la
     * siguiente pregunta si está en ejecución.
     */
    private void pausarTempoCorrecto() {
        if (correcto != null && correcto.isRunning()) {
            correcto.stop();
        }
    }

    /**
     * Verifica si el temporizador principal ha finalizado.
     *
     * @return true si el temporizador ha finalizado, false de lo contrario.
     */
    public boolean isTemporizadorFinalizado() {
        return temporizadorFinalizado;
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este
     * contexto).
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*Juegos juego;
                try {
                    juego = new Juegos();
                } catch (excepcionPreguntasNoDisponibles ex) {
                    Logger.getLogger(Juegos.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                   juego.setVisible(true);*/

            }
        });
    }

    /** @hidden */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Categoria;
    private javax.swing.JLabel Ganaste;
    private javax.swing.JLabel InfoCategoria;
    private javax.swing.JLabel Informe;
    private javax.swing.JLabel JugadorActual;
    private static javax.swing.JButton Respuesta1;
    private static javax.swing.JButton Respuesta2;
    private static javax.swing.JButton Respuesta3;
    private javax.swing.JLabel confirmacion;
    private javax.swing.JLabel count;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel info;
    private javax.swing.JButton no;
    private javax.swing.JLabel numPregunta;
    public static javax.swing.JLabel pregunta;
    private javax.swing.JLabel puntaje1;
    private static javax.swing.JLabel puntajeFinal;
    private javax.swing.JButton si;
    private javax.swing.JButton terminar;
    private javax.swing.JLabel tiempo;
    private javax.swing.JLabel tiempoFondo;
    private javax.swing.JLabel tiempoFuera;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables

    /**
     * Obtiene el componente JLabel asociado a la pregunta actual.
     *
     * @return Componente JLabel de la pregunta actual.
     */
    public static JLabel getPregunta() {
        return pregunta;
    }

    /**
     * Obtiene el componente JButton asociado a la primera respuesta.
     *
     * @return Componente JButton de la primera respuesta.
     */
    public static JButton getRespuesta1() {
        return Respuesta1;
    }

    /**
     * Obtiene el componente JButton asociado a la segunda respuesta.
     *
     * @return Componente JButton de la segunda respuesta.
     */
    public static JButton getRespuesta2() {
        return Respuesta2;
    }

    /**
     * Obtiene el componente JButton asociado a la tercera respuesta.
     *
     * @return Componente JButton de la tercera respuesta.
     */
    public static JButton getRespuesta3() {
        return Respuesta3;
    }

}
