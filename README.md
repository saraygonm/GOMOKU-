# GomokuPOOs

## Descripción
GomokuPOOs es una aplicación Java que implementa el juego Gomoku (también conocido como 'Cinco en línea' o 'Gobang') con funcionalidades avanzadas y opciones de personalización. El juego está diseñado para dos jugadores que compiten por conectar cinco fichas del mismo color en cualquier dirección en un tablero de 15x15.

## Características principales
- **Tablero personalizable**: Los jugadores pueden elegir entre tres tamaños de tablero: 10x10, 15x15 y 20x20.
- **Modos de juego**: Se ofrecen tres modos de juego distintos para variar la dinámica:
  - **Normal**: Sin límite de tiempo para las acciones.
  - **Quicktime**: Límite de tiempo para cada acción, con penalización por exceder el tiempo.
  - **Fichas Limitadas**: Los jugadores tienen un número limitado de fichas para ganar.
- **Fichas especiales**: Se introducen fichas especiales con comportamientos únicos que pueden alterar el curso del juego.
- **Casillas especiales**: El tablero puede contener casillas especiales con efectos particulares, como minas, teletransporte y fichas doradas.

## Requisitos funcionales
La aplicación cumple con una serie de requisitos funcionales para garantizar una experiencia de juego completa:
- **Selección de oponente**: Los usuarios pueden elegir entre jugar contra otro jugador o contra la máquina, con la opción de seleccionar el tipo de IA.
- **Personalización de datos**: Los jugadores pueden ingresar sus nombres y seleccionar el color de sus fichas.
- **Configuración del modo de juego**: Se permite definir el modo de juego y el tiempo disponible para cada turno en el modo Quicktime.
- **Interacción en el juego**: Los jugadores pueden colocar fichas y realizar acciones correspondientes en el tablero.
- **Visualización de información**: Se muestra información relevante de cada jugador y el estado del juego en todo momento.
- **Gestión del tiempo**: En el modo Quicktime, se muestra y gestiona el tiempo de cada jugador, con la posibilidad de ejecutar correctamente la excepción cuando se excede el tiempo establecido.
- **Finalización del juego**: Los jugadores pueden finalizar el juego en cualquier momento, con la notificación del ganador.
- **Gestión de partidas**: Se permite abrir, guardar y reiniciar el estado de un juego en curso.
- **Sistema de puntos**: Los jugadores obtienen puntos por eliminar fichas enemigas, con la posibilidad de obtener fichas extra al alcanzar cierta cantidad de puntos.

## Requisitos de diseño
La aplicación sigue una serie de pautas de diseño para garantizar su usabilidad y flexibilidad:
- **Extensibilidad**: La aplicación está diseñada para facilitar la incorporación de nuevas funcionalidades, como diferentes tipos de fichas, casillas especiales, modos de juego y jugadores máquina.
- **Visualización atractiva**: El juego presenta una representación gráfica adecuada que permite a los jugadores comprender fácilmente el estado del juego, diferenciar entre tipos de fichas y casillas, y disfrutar de una experiencia visual agradable.
- **Manejo de excepciones**: Se implementa un sistema de manejo de excepciones con una clase dedicada para gestionar las excepciones propias del juego, junto con un registro de errores para los programadores.

## Integrantes del equipo:
- Saray Mendivelso []
- Andres Rodriguez []

