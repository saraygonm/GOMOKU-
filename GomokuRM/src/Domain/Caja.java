package Domain;
import java.awt.Color;

/**
 * La clase Caja representa una Caja en el tablero de Gomoku.
 * Cada Caja puede estar ocupada por una ficha y tiene información sobre su estado.
 */
public abstract class Caja {
    private int fila;
    private int columna;
    private boolean ocupada;
    private Ficha ficha;
    private Color fichaColor;

    /**
     * Construye una nueva Caja con la posición especificada en el tablero.
     * La Caja se inicializa como no ocupada.
     *
     * @param fila    La fila de la Caja en el tablero.
     * @param columna La columna de la Caja en el tablero.
     */
    public Caja(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.ocupada = false;
    }

      /**
     * Obtiene la fila de la Caja en el tablero.
     *
     * @return La fila de la Caja.
     */
    public int getFila() {
        return fila;
    }

     /**
     * Obtiene la columna de la Caja en el tablero.
     *
     * @return La columna de la Caja.
     */
    public int getColumna() {
        return columna;
    }

    
    /**
     * Verifica si la Caja está ocupada por una ficha.
     *
     * @return true si la Caja está ocupada, false de lo contrario.
     */
    public boolean isOcupada() {
        return ocupada;
    }

     /**
     * Marca la Caja como ocupada.
     */
    public void setOcupada() {
        this.ocupada = true;
    }
    
     /**
     * Establece la ficha en la Caja.
     *
     * @param ficha La ficha que se colocará en la Caja.
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
     /**
     * Obtiene la ficha actualmente ubicada en la Caja.
     *
     * @return La ficha en la Caja, o null si la Caja no está ocupada.
     */
    public Ficha getFicha() {
        return ficha;
    }
    
    /**
     * Establece el color de la ficha en la Caja.
     *
     * @param color El color de la ficha.
     */
    public void setFichaColor(Color x) {
        this.fichaColor = x ;
    }
    
     /**
     * Obtiene el color de la ficha en la Caja.
     *
     * @return El color de la ficha.
     */
    public Color getFichaColor() {
        return fichaColor ;
    }
    
    /**
     * Obtiene el tipo de la ficha en la Caja.
     *
     * @return El tipo de la ficha.
     */
    public String getFichaTipo() {
        return ficha.getTipo();
    }
    
     /**
     * Obtiene la forma de la ficha en la Caja.
     *
     * @return La forma de la ficha.
     */
    public char getFichaShape() {
        return ficha.getShape();
    }
    
     /**
     * Obtiene el turno en el que se colocó la ficha en la Caja.
     *
     * @return El turno de la ficha.
     */
    public int getFichaTurno() {
        return ficha.getTurno();
    }
    
    /**
     * Elimina la ficha de la Caja, marcándola como no ocupada.
     */
    /**
     * Elimina la ficha de la Caja, marcándola como no ocupada.
     */
    public void delFicha() {
        if (ficha != null) {
            ficha.killFicha();
            
        }
        ficha = null; // Asegúrate de anular la referencia a la ficha después de eliminarla
        this.ocupada = false;
    }

    

	public void realizarAccion(Gomoku gomoku, int fila, int columna) {
		// TODO Auto-generated method stub
		
	}

	public void realizarAccion() {
		// TODO Auto-generated method stub
		
	}
}
