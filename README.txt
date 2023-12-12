public boolean putFicha(int fila, int columna, Color color, String tipo) {
    System.out.println("Gomoku: Poniendo ficha en fila " + fila + ", columna " + columna);
    
    Ficha ficha = crearFicha(fila, columna, color, tipo);

    if (esMovimientoValido(fila, columna, ficha)) {
        Caja caja = cajas[fila][columna];
        
        colocarFichaEnCaja(ficha, caja, color, tipo);
        realizarAccionEspecial(caja, fila, columna);

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

private boolean esMovimientoValido(int fila, int columna, Ficha ficha) {
    return !cajas[fila][columna].isOcupada() && ficha != null;
}

private void colocarFichaEnCaja(Ficha ficha, Caja caja, Color color, String tipo) {
    caja.setOcupada();
    caja.setFicha(ficha);
    caja.setFichaColor(color);
    GetJugadorActual().SetActualFicha(tipo);
    GetJugadorActual().winFicha();
}

private void realizarAccionEspecial(Caja caja, int fila, int columna) {
    if (caja instanceof MineCaja) {
        ((MineCaja) caja).realizarAccion(this, fila, columna);
    } else if (caja instanceof TeleportCaja) {
        ((TeleportCaja) caja).realizarAccion(this, fila, columna);
    }
}




public boolean victoria(Color color) {
    return verificaLineas(color, 0, 1) ||       // Verificación horizontal
           verificaLineas(color, 1, 0) ||       // Verificación vertical
           verificaLineas(color, 1, 1) ||       // Verificación diagonal ascendente
           verificaLineas(color, -1, 1);        // Verificación diagonal descendente
}

private boolean verificaLineas(Color color, int deltaFila, int deltaColumna) {
    for (int i = 0; i < cajas.length; i++) {
        for (int j = 0; j < cajas.length; j++) {
            if (verificaLinea(color, i, j, deltaFila, deltaColumna)) {
                return true;
            }
        }
    }
    return false;
}

private boolean verificaLinea(Color color, int fila, int columna, int deltaFila, int deltaColumna) {
    int fichasContadas = 0;
    int pesadasContadas = 0;

    for (int k = 0; k < 5; k++) {
        int nuevaFila = fila + k * deltaFila;
        int nuevaColumna = columna + k * deltaColumna;

        if (nuevaFila >= 0 && nuevaFila < cajas.length && nuevaColumna >= 0 && nuevaColumna < cajas.length) {
            if (cajas[nuevaFila][nuevaColumna].isOcupada()) {
                Ficha ficha = cajas[nuevaFila][nuevaColumna].getFicha();
                if (ficha.getColor() == color) {
                    if (ficha.getTipo().equals("Pesada")) {
                        pesadasContadas++;
                    } else {
                        fichasContadas++;
                    }
                }
            }
        }
    }

    return fichasContadas + pesadasContadas * 2 >= 5;
}

