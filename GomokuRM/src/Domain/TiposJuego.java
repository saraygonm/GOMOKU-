package Domain;


/**
 * La interfaz TiposJuego define los métodos necesarios para inicializar
 * las fichas y el tiempo en un juego de Gomoku.
 */
public interface TiposJuego {
    /**
     * Inicializa las fichas del juego en función de la implementación específica.
     *
     * @param game El juego de Gomoku en el que se inicializarán las fichas.
     */
    void initializeFichas(Gomoku game);
    
    /**
     * Inicializa el tiempo del juego en función de la implementación específica.
     *
     * @param game El juego de Gomoku en el que se inicializará el tiempo.
     */
    void initializeTime(Gomoku game);
    
}

