package Domain;

import java.awt.Color;

/**
 * La clase FichaLoca representa una ficha especial en el juego Gomoku.
 * Después de 5 turnos, se convierte en una FichaTemporal o FichaBig.
 */
public class FichaLoca extends Ficha {
    private int turnosTranscurridos;

    /**
     * Constructor para la creación de una nueva ficha loca en una posición específica del tablero.
     *
     * @param fila    La fila en la que se coloca la ficha loca.
     * @param columna La columna en la que se coloca la ficha loca.
     * @param color   El color asociado a la ficha loca.
     */
    public FichaLoca(int fila, int columna, Color color) {
        super(fila, columna, color, "Loca");
        setShape('⚡'); // Símbolo visual para la ficha loca
        this.turnosTranscurridos = 0;
    }

    private void setShape(char s) {
    	
    	this.shape = s;
		
	}

	/**
     * Realiza las acciones necesarias después de cada turno.
     */
    public void turno() {
        turnosTranscurridos++;
        
        // Después de 5 turnos, convertir la ficha loca
        
        if (turnosTranscurridos == 5) {
        	
            // Aquí decides qué tipo de ficha se convertirá, ya sea FichaTemporal o FichaBig
            // Puedes agregar la lógica según tus necesidades.
            // Por ahora, lo marcaré como FichaTemporal
        	
        	convertirEnFichaBig();
        }
    }

    /**
     * Convierte la ficha loca en una FichaTemporal.
     */ 
    
    
    private void convertirEnFichaBig() {
        fichaBig fichaBig = new fichaBig(getFila(), getColumna(), getColor());
        
    }
}
