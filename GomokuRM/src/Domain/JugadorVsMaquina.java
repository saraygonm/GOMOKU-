package Domain;

import java.awt.Color;

public class JugadorVsMaquina implements ModoJuego {
    private Maquina maquina;
    
    public JugadorVsMaquina() {
    	
        this.maquina = new Maquina(Color.RED);
    }

    @Override
    public void playMove(Gomoku game, int fila, int columna, Color color) {
        // Jugador realiza su movimiento
        game.putFicha(fila, columna, color, "Normal");

        // Verificación de victoria después del movimiento del jugador
        if (checkVictory(game, color)) {
            // Manejo de la victoria del jugador humano
            return;
        }

        // Turno de la máquina
        maquina.realizarMejorJugada(game, maquina.getColor());

        // Verificación de victoria después del movimiento de la máquina
        if (checkVictory(game, maquina.getColor())) {
            // Manejo de la victoria de la máquina
            return;
        }
    }
    
    @Override
    public void playMachineMove(Gomoku game) {
        maquina.realizarMejorJugada(game, maquina.getColor());
    }


    @Override
    public boolean checkVictory(Gomoku game, Color color) {
        return game.victoria(color);
    }
    
    public Maquina getMaquina() {
        return maquina;
    }
}
