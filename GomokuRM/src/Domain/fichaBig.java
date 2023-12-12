package Domain;

import java.awt.Color;

/**
 * La clase fichaBig representa una ficha pesada en el juego Gomoku.
 * Hereda de la clase Ficha e implementa las características específicas de una ficha pesada.
 */
public class fichaBig extends Ficha {
    /**
     * Constructor para la creación de una nueva ficha pesada en una posición específica del tablero.
     *
     * @param fila    La fila en la que se coloca la ficha pesada.
     * @param columna La columna en la que se coloca la ficha pesada.
     * @param color   El color asociado a la ficha pesada.
     */
    public fichaBig(int fila, int columna, Color color) {
        super(fila, columna, color, "Pesada");
        setShape('☠'); 
    }
    
     /**
     * Establece el símbolo visual de la ficha pesada en el tablero.
     * Este método se utiliza para definir el aspecto único de la ficha pesada.
     *
     * @param s El símbolo visual de la ficha pesada.
     */
    private void setShape(char s){
        this.shape = s;
    }
}

