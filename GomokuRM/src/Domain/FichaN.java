package Domain;

import java.awt.Color;

/**
 * La clase FichaN es una subclase de Ficha que representa una ficha normal en el juego Gomoku.
 * Cada ficha normal ocupa una celda en el tablero y tiene un color asociado.
 */
public class FichaN extends Ficha {
     /**
     * Constructor para la creación de una nueva ficha normal en una posición específica del tablero.
     *
     * @param fila    La fila en la que se coloca la ficha.
     * @param columna La columna en la que se coloca la ficha.
     * @param color   El color asociado a la ficha.
     */
    public FichaN(int F, int C, Color color) {
        super(F, C, color,"Normal");
    }
}
   
