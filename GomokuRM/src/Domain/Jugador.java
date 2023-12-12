
package Domain;

import java.awt.Color;
import java.util.HashMap;

import java.util.Map;

/**
 * Clase `Jugador` que representa a un jugador en el juego de Gomoku.
 * Cada jugador tiene un nombre, un color asociado, un tipo de ficha actual, puntos acumulados
 * y un conjunto de contadores para cada tipo de ficha.
 */
public class Jugador {
    private String name;
    private Color color;
    private String currentFicha;
    private int puntos;
    private Map<String, Integer> fichaCounters;

    /**
     * Constructor que crea un nuevo jugador con el nombre y color especificados.
     * Inicializa la ficha actual como "FichaNormal", los puntos a 0 y los contadores de fichas.
     *
     * @param name  Nombre del jugador.
     * @param color Color asociado al jugador.
     */
    public Jugador(String name, Color color) {
        this.name = name;
        this.color = color;
        this.fichaCounters = new HashMap<>();
        this.currentFicha = "FichaNormal";
        this.puntos = 0;
        fichaCounters.put("FichaNormal", 0);
        fichaCounters.put("FichaPesada",0);
        fichaCounters.put("FichaTemporal", 0);
    }

    /**
     * Devuelve el color asociado al jugador.
     *
     * @return Color asociado al jugador.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Devuelve el nombre del jugador.
     *
     * @return Nombre del jugador.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Devuelve el tipo de ficha actual del jugador.
     *
     * @return Tipo de ficha actual del jugador.
     */
    public String getCurrentFicha() {
        return currentFicha;
    }
    
    /**
     * Establece el tipo de ficha actual del jugador.
     *
     * @param type Tipo de ficha a establecer.
     */
    public void SetActualFicha(String type) {
        this.currentFicha = type;
    }

    /**
     * Devuelve la cantidad de fichas de un tipo específico que tiene el jugador.
     *
     * @param fichaType Tipo de ficha.
     * @return Cantidad de fichas del tipo especificado.
     */
    public int getFichasCount(String fichaType) {
        return fichaCounters.get(fichaType);
    }

     /**
     * Incrementa el contador de fichas del tipo especificado.
     *
     * @param fichaType Tipo de ficha a incrementar.
     */
    public void incrementFichaCount(String fichaType) {
        int currentCount = fichaCounters.getOrDefault(fichaType, 0);
        fichaCounters.put(fichaType, currentCount + 1);
    }

     /**
     * Decrementa el contador de fichas del tipo especificado, si es posible.
     *
     * @param fichaType Tipo de ficha a decrementar.
     */
    public void decrementFichaCount(String fichaType) {
        int currentCount = fichaCounters.getOrDefault(fichaType, 0);
        if (currentCount > 0) {
            fichaCounters.put(fichaType, currentCount - 1);
        }
    }

     /**
     * Inicializa el contador de fichas del tipo especificado con un valor inicial.
     *
     * @param fichaType    Tipo de ficha.
     * @param initialCount Valor inicial del contador.
     */
    public void initializeFichas(String fichaType, int initialCount) {
        fichaCounters.put(fichaType, initialCount);        
    }
    
    /**
     * Devuelve los puntos acumulados por el jugador.
     *
     * @return Puntos acumulados por el jugador.
     */
    public int getPuntos(){
        return puntos;
    }
    
     /**
     * Modifica los puntos del jugador según la cantidad especificada.
     *
     * @param nuevos Cantidad de puntos a modificar.
     */
    public void modifyPuntos(int nuevos){
        puntos = puntos + nuevos;
    }
    
     /**
     * Incrementa el contador de "FichaPesada" si los puntos alcanzan un múltiplo de 1000.
     */
    public void winFicha(){
        if(puntos % 1000 == 0 && puntos != 0){
            incrementFichaCount("FichaPesada");
        }
    }
    
     /**
     * Reinicia la ficha actual, los puntos y los contadores de fichas del jugador.
     */
    public void reset() {
        this.currentFicha = "FichaNormal";
        this.puntos = 0;
        // Reiniciar contadores de fichas
        for (String fichaType : fichaCounters.keySet()) {
            fichaCounters.put(fichaType, 0);
        }
    }

	public void setFichaCount(String string, int i) {
		// TODO Auto-generated method stub
		
	}


    
}
