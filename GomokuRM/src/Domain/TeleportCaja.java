package Domain;

import javax.swing.JOptionPane;
import java.util.Random;

public class TeleportCaja extends Caja {

    public TeleportCaja(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public void realizarAccion(Gomoku gomoku, int fila, int columna) {
        // Obtén la lista de cajas normales no ocupadas
        Caja[][] cajas = gomoku.getcajas();
        int tamanoTablero = gomoku.getTamanoTablero();
        Random random = new Random();

        // Encuentra una caja normal no ocupada de manera aleatoria
        while (true) {
            int nuevaFila = random.nextInt(tamanoTablero);
            int nuevaColumna = random.nextInt(tamanoTablero);

            if (!cajas[nuevaFila][nuevaColumna].isOcupada()) {
                // Muestra un mensaje informativo
                System.out.println("Ficha teletransportada a fila " + nuevaFila + ", columna " + nuevaColumna);
                
                // Teletransporta la ficha a la nueva caja
                gomoku.putFicha(nuevaFila, nuevaColumna, getFichaColor(), getFichaTipo());
                // Elimina la ficha de la caja original
                delFicha();
                
                JOptionPane.showMessageDialog(null, "¡Ficha teletransportada a una CajaNormal!");
            }
                break;
            }
        }
    }
