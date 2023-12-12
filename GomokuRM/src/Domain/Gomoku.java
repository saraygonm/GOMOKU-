package Domain;

import java.awt.*;
import java.util.Random;

import javax.swing.*;
/**
 * La clase GomokuGame representa la lógica del juego Gomoku.
 * Contiene el tablero, jugadores, reglas del juego y métodos para gestionar el desarrollo del juego.
 */
public class Gomoku {
    private Caja[][] cajas;
    private TiposJuego TipoG;
    private Jugador Jugador1;
    private Jugador Jugador2;
    private Jugador JugadorActual;
    private Jugador ganador;
    private int turno;
    private double time;
    private int tamanoTablero;
    
     /**
     * Constructor que inicializa una instancia de GomokuGame con parámetros dados.
     *
     * @param tamanoTablero Tamaño del tablero de juego.
     * @param Jugador1      Jugador 1.
     * @param Jugador2      Jugador 2.
     * @param TipoG     Tipo de juego que define las reglas.
     */
    public Gomoku(int tamanoTablero, Jugador Jugador1, Jugador Jugador2, TiposJuego TipoG) {
        this.tamanoTablero = tamanoTablero; 
        cajas = new Caja[tamanoTablero][tamanoTablero];
        initializeCajas();
        this.Jugador1 = Jugador1;
        this.Jugador2 = Jugador2;
        this.JugadorActual = null;
        this.TipoG = TipoG;
        this.turno = 0;
        this.ganador = null;
        //TipoG.initializeFichas(this);
        TipoG.initializeTime(this);
    }

    /**
     * Inicializa el tablero del juego con Cajas vacías.
     */
    private void initializeCajas() {
        Random random = new Random();
        int maxMineCajas = (tamanoTablero * tamanoTablero) / 5;
        int maxTeleportCajas = (tamanoTablero * tamanoTablero) / 5;

        for (int i = 0; i < cajas.length; i++) {
            for (int j = 0; j < cajas[i].length; j++) {
                if (random.nextBoolean() && maxMineCajas > 0) {
                    cajas[i][j] = new MineCaja(i, j);
                    maxMineCajas--;
                } else if (random.nextBoolean() && maxTeleportCajas > 0) {
                    cajas[i][j] = new TeleportCaja(i, j);
                    maxTeleportCajas--;
                } else {
                    cajas[i][j] = new CajaN(i, j);
                }
            }
        }
    }


    /**
     * Coloca una ficha en una posición específica del tablero.
     *
     * @param fila    Fila en la que se coloca la ficha.
     * @param columna Columna en la que se coloca la ficha.
     * @param color   Color de la ficha.
     * @param tipo    Tipo de ficha a colocar.
     * @return true si la colocación de la ficha es exitosa, false de lo contrario.
     */
    public boolean putFicha(int fila, int columna, Color color, String tipo) {
    	System.out.println("Gomoku: Poniendo ficha en fila " + fila + ", columna " + columna);
        Ficha ficha = crearFicha(fila, columna, color, tipo);
        
        if (!cajas[fila][columna].isOcupada() && ficha != null) {
            Caja caja = cajas[fila][columna];
            caja.setOcupada();
            caja.setFicha(ficha);
            caja.setFichaColor(color);
            GetJugadorActual().SetActualFicha(tipo);
            GetJugadorActual().winFicha();

            if (caja instanceof MineCaja) {
                MineCaja mineCaja = (MineCaja) caja;
                mineCaja.realizarAccion(this, fila, columna);
                
            } else if (caja instanceof TeleportCaja) {
                TeleportCaja teleportCaja = (TeleportCaja) caja;
                teleportCaja.realizarAccion(this, fila, columna);
                
            }

            if (victoria(color)) {
                setWinner(GetJugadorActual().getName());
                System.out.println(GetJugadorActual().getName());
            }
            switchJugador();
            return true;
        } else {
            return false;
        }
    }


    
     /**
     * Crea una nueva ficha según el tipo especificado.
     *
     * @param fila    Fila de la ficha.
     * @param columna Columna de la ficha.
     * @param color   Color de la ficha.
     * @param tipo    Tipo de ficha a crear.
     * @return Nueva instancia de la ficha creada.
     */
    private Ficha crearFicha(int fila,int columna,Color color,String tipo){
        Ficha ficha = null;
        Jugador p = GetJugadorActual();
        if(tipo.equals("FichaNormal")){
            ficha = new FichaN(fila,columna,color);
            turno += 1;
        }
        
        if(tipo.equals("FichaPesada") && hayEspacio(fila,columna)){
            ficha = new fichaBig(fila,columna,color);
            p.modifyPuntos(100);
            turno += 1;
        }
        
        if(tipo.equals("FichaTemporal")){
            ficha = new TempFicha(fila,columna,color);
            p.modifyPuntos(100);
            ficha.setTurno(turno);
            turno += 1;
        }
        p.decrementFichaCount(tipo);
        return ficha;
    }
    
    /**
     * Verifica si hay una victoria en el juego para un color específico.
     *
     * @param color Color a verificar para la victoria.
     * @return true si hay victoria, false de lo contrario.
     */
    public boolean victoria(Color color) {
        // Verificación horizontal
        for (int i = 0; i < cajas.length; i++) {
            for (int j = 0; j <= cajas.length - 5; j++) {
                int fichasContadas = 0;
                int pesadasContadas = 0;
                for (int k = 0; k < 5; k++) {
                    if (cajas[i][j + k].isOcupada()) {
                        Ficha ficha = cajas[i][j + k].getFicha();
                        if (ficha.getColor() == color) {
                            if (ficha.getTipo().equals("Pesada")) {
                                pesadasContadas++;
                            } else {
                                fichasContadas++;
                            }
                        }
                    }
                }
                if (fichasContadas + pesadasContadas * 2 >= 5) {
                    return true;
                }
            }
        }
    
        // Verificación vertical
        for (int i = 0; i <= cajas.length - 5; i++) {
            for (int j = 0; j < cajas.length; j++) {
                int fichasContadas = 0;
                int pesadasContadas = 0;
                for (int k = 0; k < 5; k++) {
                    if (cajas[i + k][j].isOcupada()) {
                        Ficha ficha = cajas[i + k][j].getFicha();
                        if (ficha.getColor() == color) {
                            if (ficha.getTipo().equals("Pesada")) {
                                pesadasContadas++;
                            } else {
                                fichasContadas++;
                            }
                        }
                    }
                }
                if (fichasContadas + pesadasContadas * 2 >= 5) {
                    return true;
                }
            }
        }
    
        // Verificación diagonal ascendente
        for (int i = 4; i < cajas.length; i++) {
            for (int j = 0; j <= cajas.length - 5; j++) {
                int fichasContadas = 0;
                int pesadasContadas = 0;
                for (int k = 0; k < 5; k++) {
                    if (cajas[i - k][j + k].isOcupada()) {
                        Ficha ficha = cajas[i - k][j + k].getFicha();
                        if (ficha.getColor() == color) {
                            if (ficha.getTipo().equals("Pesada")) {
                                pesadasContadas++;
                            } else {
                                fichasContadas++;
                            }
                        }
                    }
                }
                if (fichasContadas + pesadasContadas * 2 >= 5) {
                    return true;
                }
            }
        }
    
        // Verificación diagonal descendente
        for (int i = 0; i <= cajas.length - 5; i++) {
            for (int j = 0; j <= cajas.length - 5; j++) {
                int fichasContadas = 0;
                int pesadasContadas = 0;
                for (int k = 0; k < 5; k++) {
                    if (cajas[i + k][j + k].isOcupada()) {
                        Ficha ficha = cajas[i + k][j + k].getFicha();
                        if (ficha.getColor() == color) {
                            if (ficha.getTipo().equals("Pesada")) {
                                pesadasContadas++;
                            } else {
                                fichasContadas++;
                            }
                        }
                    }
                }
                if (fichasContadas + pesadasContadas * 2 >= 5) {
                    return true;
                }
            }
        }
    
        return false;
    }

    
    /**
     * Verifica y elimina fichas temporales que hayan alcanzado su límite de turnos.
     */
    public void verificarTurnos(){
        for (int i = 0; i < cajas.length; i++) {
            for (int j = 0; j < cajas[i].length; j++) {
                Caja caja = cajas[i][j];
                if (caja != null && caja.isOcupada() && caja.getFicha() != null) {
                    if (caja.getFichaTipo().equals("Temporal") && caja.getFichaTurno() == turno-4) {
                        caja.delFicha();
                    }
                }
            }
        }
    }

     
    /**
     * Verifica si hay espacio para colocar una ficha pesada en una posición específica.
     *
     * @param fila    Fila a verificar.
     * @param columna Columna a verificar.
     * @return true si hay espacio, false de lo contrario.
     */
    public boolean hayEspacio(int fila, int columna) {
        if (fila >= 2 && fila <= cajas.length && columna >= 0 && columna <= cajas[0].length-2) {
            if (!cajas[fila - 1][columna].isOcupada() && !cajas[fila][columna + 1].isOcupada() && !cajas[fila - 1][columna + 1].isOcupada()) {
                return true;
            }
        }
        return false;
    }   
    
    /**
     * Determina el fin del juego y declara al jugador con más puntos como ganador.
     *
     * @return true si hay un ganador, false si hay empate.
     */
    public boolean endGame(){
        if(Jugador1.getPuntos() > Jugador2.getPuntos()){
            setWinner(Jugador1.getName());
            return true;
        }
        else if(Jugador1.getPuntos() < Jugador2.getPuntos()){
            setWinner(Jugador2.getName());
            return true;
        }
        else{
            return false;
        }
    }
    
     /**
     * Obtiene la ficha en una posición específica del tablero.
     *
     * @param fila    Fila de la ficha.
     * @param columna Columna de la ficha.
     * @return Ficha en la posición dada.
     */
    public Ficha getFicha(int fila,int columna){
        return cajas[fila][columna].getFicha();    
    }

     /**
     * Obtiene el tablero de juego.
     *
     * @return Tablero de juego.
     */
    public Caja[][] getcajas() {   
        verificarTurnos();
        return cajas;
    }
    
    /**
     * Establece al jugador especificado como ganador.
     *
     * @param name Nombre del jugador ganador.
     */
    public void setWinner(String name){
        if(name != null && name.equals(Jugador1.getName())){
            ganador = Jugador1;
        }else{
            ganador = Jugador2;
        }
    }
    
    /**
     * Obtiene al jugador ganador.
     *
     * @return Jugador ganador.
     */
    public Jugador getWinner() {
        return ganador;
    }

    /**
     * Obtiene al jugador 1.
     *
     * @return Jugador 1.
     */
    public Jugador getJugador1() {
        return Jugador1;
    }
    
    /**
     * Obtiene al jugador 2.
     *
     * @return Jugador 2.
     */
    public Jugador getJugador2() {
        return Jugador2;
    }
    
    /**
     * Obtiene al jugador actual.
     *
     * @return Jugador actual.
     */
    public Jugador GetJugadorActual() {
        if(JugadorActual == null){
            this.JugadorActual = Jugador1;
            return JugadorActual;
        }
        return JugadorActual;
    }
    
     /**
     * Cambia al siguiente jugador en el turno.
     */
    public void switchJugador() {
        if (GetJugadorActual() == Jugador1) {
            JugadorActual = Jugador2;
        } else {
            JugadorActual = Jugador1;
        }
    }
    
    /**
     * Establece los jugadores del juego.
     *
     * @param Jugador1 Jugador 1.
     * @param Jugador2 Jugador 2.
     */
    public void setJugadors(Jugador Jugador1, Jugador Jugador2) {
        this.Jugador1 = Jugador1;
        this.Jugador2 = Jugador2;
    }
    
     /**
     * Establece el tipo de juego con sus reglas.
     *
     * @param TipoG Tipo de juego a establecer.
     */
    public void setGameType(TiposJuego  TipoG){
        this.TipoG = TipoG;
    }
    
     /**
     * Establece el tiempo total de juego.
     *
     * @param time Tiempo total de juego.
     */
    public void setTime(double time){
        this.time = time;
    }
    
    public int getTamanoTablero() {
        return tamanoTablero;
    }

    /**
     * Establece el tamaño del tablero de juego.
     *
     * @param tamanoTablero Nuevo tamaño del tablero.
     * @throws IllegalArgumentException Si el tamaño del tablero es menor que 10.
     */
    public int setTamanoTablero() {
        String input = JOptionPane.showInputDialog("Ingrese el número de filas y columnas (separados por espacio):");
        String[] partes = input.split(" ");
        
        if (partes.length != 2) {
            throw new IllegalArgumentException("Debe ingresar exactamente dos valores (filas y columnas).");
        }
        
        int filas = Integer.parseInt(partes[0]);
        int columnas = Integer.parseInt(partes[1]);
        
        if (filas <= 0 || columnas <= 0) {
            throw new IllegalArgumentException("El número de filas y columnas debe ser mayor que cero.");
        }
        
        this.tamanoTablero = Math.max(filas, columnas);
        cajas = new Caja[this.tamanoTablero][this.tamanoTablero];
        initializeCajas();
        return this.tamanoTablero;
    }
        
    /**
     * Reinicia todas las variables y configuraciones del juego para iniciar una nueva partida.
     */
    public void restartGame() {
        // Reiniciar todas las variables y configuraciones del juego
        initializeCajas();
        this.turno = 0;
        this.ganador = null;
        // Reiniciar jugadores
        Jugador1.reset();
        Jugador2.reset();
        // Reiniciar tipo de juego
        TipoG.initializeFichas(this);
        TipoG.initializeTime(this);
        // Establecer el primer jugador como el jugador actual
        JugadorActual = null;
    }
    
    /**
     * Obtiene el tiempo total de juego.
     *
     * @return Tiempo total de juego.
     */
    public double getTime(){
        return time;
    }

	 
}

