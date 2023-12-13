package Domain;
import java.awt.Color;

public class JugadorVsJugadorMode implements ModoJuego {
    @Override
    public void playMove(Gomoku game, int fila, int columna, Color color) {
        game.putFicha(fila, columna, color,"Normal");
    }

    @Override
    public boolean checkVictory(Gomoku game, Color color) {
        return game.victoria(color);
    }

	@Override
	public void playMachineMove(Gomoku game) {
		// TODO Auto-generated method stub
		
	}
}
