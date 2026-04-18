# TriviaQuirk

Proyecto realizado para el curso de Programación Orientada a Objetos.

TriviaQuirk es un juego de preguntas y respuestas con interfaz gráfica, donde uno o varios jugadores compiten respondiendo preguntas de diferentes categorías.

---

## Descripción

El juego permite seleccionar categorías y número de jugadores, y luego desarrolla una partida por turnos donde cada jugador responde preguntas dentro de un tiempo límite.

El sistema calcula los puntajes automáticamente y al final muestra un informe con los resultados y el ganador.

---

## Funcionalidades principales

- Modo de juego:
  - Un jugador
  - Multijugador (2 a 4 jugadores)

- Selección de categorías:
  - Cultura general
  - Cine
  - Países
  - Sorpresa

- Sistema de turnos entre jugadores
- Temporizador por cada pregunta
- Validación de respuestas correctas e incorrectas
- Efectos de sonido durante el juego
- Pantalla final con puntajes y ganador

---

## Tecnologías utilizadas

- Java
- Swing (interfaz gráfica)
- JSON (para almacenamiento de preguntas)
- NetBeans

---

## Estructura general del proyecto

- `Inicio`
  - Maneja el menú principal, selección de jugadores y categorías

- `Juegos`
  - Contiene la lógica principal del juego
  - Controla las preguntas, turnos, temporizador y resultados

- `Jugador`
  - Representa a cada jugador y su puntaje

- Interfaces (`iJugador`, `iPregunta`, etc.)
  - Permiten desacoplar la lógica del juego

- `ArchivoJason`
  - Se encarga de leer y guardar datos en archivos JSON

- Categorías
  - Cada categoría genera preguntas de forma independiente

---

## Cómo ejecutar

1. Abrir el proyecto en NetBeans  
2. Ejecutar la clase: TriviaQuirk, asimismo, las direcciones de las imagenes están configuradas para linux, para otro SO hay que modificarlas
