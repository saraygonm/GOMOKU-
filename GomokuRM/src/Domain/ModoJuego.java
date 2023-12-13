package Domain;
import java.awt.Color;

public interface ModoJuego {
    void playMove(Gomoku game, int fila, int columna, Color color);
    boolean checkVictory(Gomoku game, Color color);
    void playMachineMove(Gomoku game);
}
