
package Domain;

import java.awt.Color;

/**
 * La clase abstracta Ficha es la clase base para representar las fichas en el juego Gomoku.
 * Proporciona atributos y métodos comunes a todas las fichas, como posición en el tablero, color y tipo.
 */
public abstract class Ficha {
    private int F;
    private int C;
    private Color color;
    private String tipo;
    private int enTurno;
    protected char shape;

     /**
     * Constructor para la creación de una nueva ficha en una posición específica del tablero.
     *
     * @param F    La Fila en la que se coloca la ficha.
     * @param C La Columna en la que se coloca la ficha.
     * @param color   El color asociado a la ficha.
     * @param tipo    El tipo de la ficha (Normal, Pesada, Temporal, etc.).
     */
    public Ficha(int F, int C, Color color, String tipo) {
        this.F = F;
        this.C = C;
        this.color = color;
        this.tipo = tipo;
        this.enTurno = 0;
        this.shape = '⚫';
    }

    /**
     * Obtiene la F en la que se encuentra la ficha.
     *
     * @return La F de la ficha en el tablero.
     */
    public int getFila() {
        return F;
    }

    
    /**
     * Obtiene laC en la que se encuentra la ficha.
     *
     * @return LaC de la ficha en el tablero.
     */
    public int getColumna() {
        return C;
    }

    /**
     * Obtiene el color asociado a la ficha.
     *
     * @return El color de la ficha.
     */
    public Color getColor() {
        return color;
    }

     /**
     * Obtiene el tipo de la ficha.
     *
     * @return El tipo de la ficha (Normal, Pesada, Temporal, etc.).
     */
    public String getTipo() {
        return tipo;
    }
    
     /**
     * Obtiene el símbolo visual de la ficha en el tablero.
     *
     * @return El símbolo visual de la ficha.
     */
    public char getShape(){
        return shape;
    } 

     /**
     * Obtiene el enTurno en el que se creó la ficha.
     *
     * @return El enTurno en el que se creó la ficha.
     */
    public int getTurno(){
        return enTurno;
    }
    
      /**
     * "Mata" la ficha, cambiando su símbolo visual a espacio en blanco.
     * Este método se utiliza para indicar que la ficha ya no está en juego.
     */
    public void killFicha(){
        this.shape = ' ';
    }
    
     /**
     * Establece el enTurno en el que se creó la ficha.
     *
     * @param enTurnoDado El enTurno en el que se creó la ficha.
     */
    public void setTurno(int enTurnoDado){
        this.enTurno = enTurnoDado;
    }
}
