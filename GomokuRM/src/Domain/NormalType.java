package Domain;

/**
 * La clase NormalType implementa la interfaz TiposJuego y define métodos
 * específicos para la inicialización de fichas y tiempo en un juego normal de Gomoku.
 */
public class NormalType implements TiposJuego {
      /**
     * Inicializa las fichas para cada jugador en un juego normal de Gomoku.
     * Se asigna un número específico de fichas normales, pesadas y temporales
     * para cada jugador, dependiendo del tamaño del tablero.
     *
     * @param game El juego de Gomoku en el que se inicializarán las fichas.
     */
    @Override
    public void initializeFichas(Gomoku game) {
        // Inicialización de fichas igual al tamaño del tablero para cada jugador
        int cajasSize = game.getcajas().length * game.getcajas().length ;
        int r1 = (int) (cajasSize  * 0.4);
        game.getJugador1().initializeFichas("FichaNormal", r1);
        game.getJugador2().initializeFichas("FichaNormal", r1);
        int r2 =  (int) (cajasSize  * 0.2);
        game.getJugador1().initializeFichas("FichaPesada", r2);
        game.getJugador2().initializeFichas("FichaPesada", r2);
        int r3 =  (int) (cajasSize * 0.3);
        game.getJugador1().initializeFichas("FichaTemporal", r3);
        game.getJugador2().initializeFichas("FichaTemporal", r3);
    }
    
     /**
     * Inicializa el tiempo en un juego normal de Gomoku.
     * En un juego normal, el tiempo está configurado como infinito.
     *
     * @param game El juego de Gomoku en el que se inicializará el tiempo.
     */
    @Override
    public void initializeTime(Gomoku game) {
        game.setTime(Double.POSITIVE_INFINITY);
    }
}

