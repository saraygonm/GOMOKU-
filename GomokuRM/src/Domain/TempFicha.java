package Domain;
import java.awt.Color;

/**
 * La clase FichaTemporal representa una ficha temporal en el juego Gomoku.
 * Hereda de la clase Ficha e implementa las características específicas de una ficha temporal.
 */
public class TempFicha extends Ficha {

    /**
     * Constructor para la creación de una nueva ficha temporal en una posición específica del tablero.
     *
     * @param F    La F en la que se coloca la ficha temporal.
     * @param C La C en la que se coloca la ficha temporal.
     * @param color   El color asociado a la ficha temporal.
     */
    public TempFicha(int F, int C, Color color) {
        super(F, C, color, "Temporal");
        setShape('★');
    }
    
    /**
     * Establece el símbolo visual de la ficha temporal en el tablero.
     * Este método se utiliza para definir el aspecto único de la ficha temporal.
     *
     * @param s El símbolo visual de la ficha temporal.
     */
    private void setShape(char s){
        this.shape = s;
    }
}