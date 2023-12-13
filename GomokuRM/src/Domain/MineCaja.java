package Domain;

import javax.swing.*;
public class MineCaja extends Caja {
    public MineCaja(int fila, int columna) {
        super(fila, columna);
    }
    
    @Override
    public void realizarAccion(Gomoku gomoku, int fila, int columna) {
        // Eliminar la ficha colocada sobre la MineCaja
        gomoku.getcajas()[fila][columna].delFicha();

        // Eliminar las fichas de las cajas alrededor
     // Eliminar las fichas de las cajas alrededor
        for (int i = Math.max(0, fila - 1); i <= Math.min(gomoku.getTamanoTablero() - 1, fila + 1); i++) {
            for (int j = Math.max(0, columna - 1); j <= Math.min(gomoku.getTamanoTablero() - 1, columna + 1); j++) {
                Caja caja = gomoku.getcajas()[i][j];
                if (caja != null) {
                    caja.delFicha();
                }
            }
        }


        // Mostrar mensaje informativo
        JOptionPane.showMessageDialog(null, "¡SOY UNA MINE CAJA! Ficha eliminada y fichas de las cajas alrededor también.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }

	
}

