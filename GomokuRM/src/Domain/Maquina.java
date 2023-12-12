package Domain;

import java.awt.Color;

public class Maquina {
    private Color color;

    public Maquina(Color color) {
        this.color = color;
    }
    /**
     * Realiza la mejor jugada para la máquina en el juego Gomoku.
     *
     * @param cajas       Tablero de juego.
     * @param colorMaquina Color de la máquina.
     * @param colorJugador Color del jugador humano.
     */
    public void realizarMejorJugada(Gomoku game, Color colorJugador) {
        // Aquí implementa la lógica para determinar la mejor jugada de la máquina
        // Puedes utilizar algoritmos como minimax, alpha-beta pruning, u otros según tus necesidades

        // Ejemplo: Realizar una jugada aleatoria por ahora
        jugarAleatoriamente(game.getcajas());
    }

    /**
     * Realiza una jugada aleatoria para la máquina.
     *
     * @param cajas Tablero de juego.
     * @param color Color de la máquina.
     */
    private void jugarAleatoriamente(Caja[][] cajas) {
        // Encuentra una posición aleatoria vacía en el tablero y coloca una ficha de la máquina
        int tamanoTablero = cajas.length;
        while (true) {
            int fila = (int) (Math.random() * tamanoTablero);
            int columna = (int) (Math.random() * tamanoTablero);

            if (!cajas[fila][columna].isOcupada()) {
                cajas[fila][columna].setOcupada();
                cajas[fila][columna].setFicha(new FichaN(fila, columna, color));  // Se asume una ficha normal por ahora
                break;
            }
        }
    }
    
    public Color getColor() {
		return color;
    	
    }
}
