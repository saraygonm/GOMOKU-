package Pruebas;

import Domain.Gomoku;
import Domain.Jugador;
import Domain.TiposJuego;

import org.junit.Before;
import org.junit.Test;
import java.awt.Color;

import static org.junit.Assert.*;

public class GomokuTest {

    private Gomoku game;

    @Before
    public void setUp() {
        TiposJuego tipoJuego = new TiposJuego() {
            @Override
            public void initializeFichas(Gomoku game) {
                game.getJugador1().setFichaCount("FichaNormal", 8);
                game.getJugador2().setFichaCount("FichaNormal", 8);
                game.getJugador1().setFichaCount("FichaPesada", 2);
                game.getJugador2().setFichaCount("FichaPesada", 2);
                game.getJugador1().setFichaCount("FichaTemporal", 2);
                game.getJugador2().setFichaCount("FichaTemporal", 2);
            }

            @Override
            public void initializeTime(Gomoku game) {
                game.setTime(60);
            }
        };

        game = new Gomoku(15, new Jugador("Jugador 1", Color.BLACK), new Jugador("Jugador 2", Color.WHITE), tipoJuego);
    }


    @Test
    public void testSwitchJugador() {
        assertEquals(game.getJugador1(), game.GetJugadorActual());
        game.switchJugador();
        assertEquals(game.getJugador2(), game.GetJugadorActual());
    }
    @Test
    public void testHayEspacio() {
        assertTrue(game.hayEspacio(2, 2)); // Hay espacio en el centro del tablero
        assertFalse(game.hayEspacio(0, 0)); // No hay espacio en el borde izquierdo
        assertFalse(game.hayEspacio(1, 0)); // No hay espacio en el borde izquierdo
        assertFalse(game.hayEspacio(0, 1)); // No hay espacio en la parte superior
    }

    @Test
    public void testVerificarTurnos() {
        game.putFicha(0, 0, Color.BLACK, "FichaTemporal");
        game.putFicha(1, 1, Color.WHITE, "FichaTemporal");
        game.putFicha(2, 2, Color.BLACK, "FichaTemporal");
        game.putFicha(3, 3, Color.WHITE, "FichaTemporal");
        game.putFicha(4, 4, Color.BLACK, "FichaTemporal");
        game.verificarTurnos();
        assertNull(game.getFicha(0, 0)); // La ficha en la posición (0, 0) debería ser eliminada
    }

    @Test
    public void testRestartGame() {
        game.putFicha(0, 0, Color.BLACK, "FichaNormal");
        game.restartGame();
        assertNull(game.getFicha(0, 0)); // El juego debería reiniciarse y no debería haber fichas
        assertEquals(game.getJugador1(), game.GetJugadorActual()); // El jugador actual debería ser el jugador 1
    }


    @Test
    public void testSetWinner() {
        // Establecer manualmente a Jugador 1 como ganador
        game.setWinner(game.getJugador1().getName());
        
        // Verificar que el ganador sea Jugador 1
        assertEquals(game.getJugador1(), game.getWinner());
    }
       
    
    /**
     * @Test
    public void testEmpate() {
        // Llena el tablero sin que haya un ganador
        for (int i = 0; i < game.getTamanoTablero(); i++) {
            for (int j = 0; j < game.getTamanoTablero(); j++) {
                assertTrue(game.putFicha(i, j, Color.BLACK, "FichaNormal"));
            }
        }

        // Verificar que el juego termine en empate
        assertFalse(game.endGame());
    }
    */
    
    @Test
    public void testReinicioJugadoresDiferentes() {
        // Colocar algunas fichas y reiniciar el juego
        game.putFicha(0, 0, Color.BLACK, "FichaNormal");
        game.restartGame();

        // Configurar nuevos jugadores
        Jugador nuevoJugador1 = new Jugador("Nuevo Jugador 1", Color.RED);
        Jugador nuevoJugador2 = new Jugador("Nuevo Jugador 2", Color.BLUE);
        game.setJugadors(nuevoJugador1, nuevoJugador2);

        // Verificar que los jugadores se hayan reiniciado correctamente
        assertEquals(nuevoJugador1, game.getJugador1());
        assertEquals(nuevoJugador2, game.getJugador2());
    }
    
    @Test
    public void testEliminarFichasTemporales() {
        // Jugador 1 coloca una ficha temporal en la fila 0, columna 0
        assertTrue(game.putFicha(0, 0, Color.BLACK, "FichaTemporal"));

        // Avanza el turno hasta que la ficha temporal alcance su límite
        for (int i = 0; i < 5; i++) {
            game.switchJugador();
        }

        // Verificar que la ficha temporal se ha eliminado
        assertNull(game.getFicha(0, 0));
    }
    
    @Test
    public void testSetTamanoTablero() {
        // Establece un nuevo tamaño de tablero
        int nuevoTamano = game.setTamanoTablero();

        // Verificar que el tamaño del tablero se ha establecido correctamente
        assertEquals(nuevoTamano, game.getTamanoTablero());
    }

    @Test
    public void testTurnoJugadores() {
        // Jugador 1 coloca una ficha normal en la fila 0, columna 0
        assertTrue(game.putFicha(0, 0, Color.BLACK, "FichaNormal"));

        // Verificar que el turno ha cambiado al Jugador 2
        assertEquals(game.getJugador2(), game.GetJugadorActual());
    }
  
    @Test
    public void testLimiteTiempoSinJuegoFinalizado() {
        // Avanzar el tiempo sin llegar al límite
        game.setTime(30);

        // Verificar que el juego no ha terminado debido al límite de tiempo
        assertFalse(game.endGame());
    }
    
    @Test
    public void testEspacioParaFichaPesada() {
        // Verificar que hay espacio para colocar una ficha pesada en la fila 2, columna 2
        assertTrue(game.hayEspacio(2, 2));
    }
    
    @Test
    public void testFinDeJuegoLimiteFichasPesadas() {
        // Reiniciar el juego
        game.restartGame();

        // Llenar el tablero con fichas pesadas
        for (int i = 0; i < game.getTamanoTablero(); i++) {
            for (int j = 0; j < game.getTamanoTablero(); j++) {
                game.putFicha(i, j, Color.BLACK, "FichaPesada");
                game.switchJugador();
            }
        }

        // Verificar que el juego ha terminado por alcanzar el límite de fichas pesadas
        assertTrue(game.endGame());
    }
    
    @Test
    public void testLimiteTiempoSinVictoria() {
        // Reiniciar el juego
        game.restartGame();

        // Establecer un tiempo límite bajo
        game.setTime(1);

        // No colocar fichas, esperar a que se agote el tiempo
        // No debería haber un ganador al finalizar el tiempo
        assertFalse(game.endGame());
        assertNull(game.getWinner());
    }
   
    @Test
    public void testEmpateParcialTableroSinGanador() {
        // Reiniciar el juego
        game.restartGame();

        // Llenar parcialmente el tablero sin que haya un ganador
        for (int i = 0; i < game.getTamanoTablero() / 2; i++) {
            for (int j = 0; j < game.getTamanoTablero(); j++) {
                game.putFicha(i, j, Color.BLACK, "FichaNormal");
                game.switchJugador();
            }
        }

        // Verificar que no hay un ganador al llenar parcialmente el tablero
        assertFalse(game.endGame());
        assertNull(game.getWinner());
    }
    
    @Test
    public void testCambioJugadorDespuesFichaNormal() {
        // Reiniciar el juego
        game.restartGame();

        // Colocar una ficha normal en el centro del tablero
        game.putFicha(game.getTamanoTablero() / 2, game.getTamanoTablero() / 2, Color.BLACK, "FichaNormal");

        // Verificar que el jugador actual cambia después de colocar una ficha normal
        assertEquals(game.getJugador2(), game.GetJugadorActual());
    }
    
    @Test
    public void testCambioJugadorDespuesFichaTemporal() {
        // Reiniciar el juego
        game.restartGame();

        // Colocar una ficha temporal en el centro del tablero
        game.putFicha(game.getTamanoTablero() / 2, game.getTamanoTablero() / 2, Color.BLACK, "FichaTemporal");

        // Verificar que el jugador actual cambia después de colocar una ficha temporal
        assertEquals(game.getJugador2(), game.GetJugadorActual());
    }
}
