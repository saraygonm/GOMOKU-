package Presentation;
import Domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * La clase GomokuGUI representa la interfaz gráfica de usuario para el juego Gomoku.
 * Proporciona funciones para iniciar, cargar, guardar y gestionar el desarrollo del juego.
 */
public class GomokuGUI extends JFrame {
    private Gomoku gomoku;
    private JButton[][] botonC;
    private JFrame Config;
    private JTextField jugador1TextField;
    private JTextField jugador2TextField;
    
    private JButton BotonJ1;
    private JButton BotonJ2;
    private Color J1Color; 
    private Color J2Color;
    private JComboBox<String> tipoJuegoComboBox;
    private JComboBox<String> modoJuegoComboBox;
    private JLabel enTurno;
    private JLabel FichaenJUego;
    private JLabel J1Fichas;
    private JLabel J2Fichas;
    private JLabel J1puntos;
    private JLabel J2puntos;
    
    
     /**
     * Constructor que inicializa la interfaz gráfica de usuario y el juego Gomoku.
     */
    private GomokuGUI() {
        initializeGame();
        prepareMainMenu();
    }
    
    /**
     * Inicializa la instancia del juego Gomoku.
     */
    private void initializeGame() {
        gomoku = new Gomoku(0,null,null,new NormalType());
    }
    
    
     /**
     * Prepara la primera pantalla de la interfaz gráfica con opciones iniciales.
     */
    private void prepareMainMenu() {
        JFrame mainScreen = new JFrame("Gomoku");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        mainScreen.setSize(width, height);
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Configurar un panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
    
        // Agregar un JLabel con el título grande
        JLabel titleLabel = new JLabel("Gomoku1.0");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 150));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
    
        // Configurar un panel para los botones con BoxLayout para alinear verticalmente
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    
        // Agregar botones "Jugar" y "Salir"
        JButton play = new JButton("Jugar");
        JButton exitButton = new JButton("Salir");
        JButton loadGameButton = new JButton("Cargar Juego");
    
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        play.addActionListener(e -> {
            
            mainScreen.dispose();  // Cerrar la pantalla principal
            PrepareConfig();
            gomoku.setTamanoTablero();
        });
    
        exitButton.addActionListener(e -> {
            confirmExit();
            System.exit(0);
        });
    
        loadGameButton.addActionListener(e -> {
            mainScreen.dispose(); // Cerrar la pantalla de opciones
            openFile();
        });
    
        // Agregar espacio vertical entre los botones
        int verticalSpace = 10;
        buttonPanel.add(Box.createVerticalStrut(verticalSpace));
    
        // Agregar botones al panel
        buttonPanel.add(play);
        buttonPanel.add(Box.createVerticalStrut(verticalSpace));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalStrut(verticalSpace));
        buttonPanel.add(loadGameButton);
    
        // Agregar el panel de botones al panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    
        // Configurar la ubicación de la pantalla principal
        mainScreen.setLocationRelativeTo(null);
    
        // Hacer visible la pantalla principal
        mainScreen.setContentPane(mainPanel);
        mainScreen.setVisible(true);
    }


        
    ////arreglar aqui////
    /**
     * Prepara la tercera pantalla de la interfaz gráfica para la configuración de jugadores y el inicio del juego.
     */
    private void PrepareConfig() {
    Config = new JFrame("Configuración de Jugadores");
    Config.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Config.setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);  // Añadir espacio entre componentes
    
    // Jugador 1
    gbc.gridx = 0;
    gbc.gridy = 0;
    Config.add(new JLabel("Jugador 1:"), gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 0;
    jugador1TextField = new JTextField();
    jugador1TextField.setPreferredSize(new Dimension(150, 25));
    Config.add(jugador1TextField, gbc);
    
    // Jugador 2
    gbc.gridx = 0;
    gbc.gridy = 1;
    Config.add(new JLabel("Jugador 2:"), gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 1;
    jugador2TextField = new JTextField();
    jugador2TextField.setPreferredSize(new Dimension(150, 25));
    Config.add(jugador2TextField, gbc);
    
    // Color Jugador 1
    gbc.gridx = 0;
    gbc.gridy = 2;
    Config.add(new JLabel("Color Jugador 1:"), gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 2;
    BotonJ1 = new JButton("Color Jugador 1");
    BotonJ1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            J1Color = JColorChooser.showDialog(Config, "Seleccionar Color Jugador 1", J1Color);
        }
    });
    Config.add(BotonJ1, gbc);
    
    // Color Jugador 2
    gbc.gridx = 0;
    gbc.gridy = 3;
    Config.add(new JLabel("Color Jugador 2:"), gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 3;
    BotonJ2 = new JButton("Color Jugador 2");
    BotonJ2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            J2Color = JColorChooser.showDialog(Config, "Seleccionar Color Jugador 2", J2Color);
        }
    });
    Config.add(BotonJ2, gbc);
    
    // Tipo de Juego
    gbc.gridx = 0;
    gbc.gridy = 4;
    Config.add(new JLabel("Tipo de Juego:"), gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 4;
    tipoJuegoComboBox = new JComboBox<>(new String[]{"Normal", "Quicktime", "Piedras Limitadas"});
    Config.add(tipoJuegoComboBox, gbc);
    
    // Modo de Juego
    gbc.gridx = 0;
    gbc.gridy = 5;
    Config.add(new JLabel("Modo de Juego:"), gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 5;
    modoJuegoComboBox = new JComboBox<>(new String[]{"Jugador vs Jugador", "Jugador vs Máquina"});
    Config.add(modoJuegoComboBox, gbc);
    
    // Iniciar Juego Button
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 2;
    JButton iniciarJuegoButton = new JButton("Iniciar Juego");
    iniciarJuegoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TiposJuego tipoJuego = null;
            String jugador1Nombre = jugador1TextField.getText();         
            Jugador Jugador1 = new Jugador(jugador1Nombre, J1Color);
            String jugador2Nombre = jugador2TextField.getText();
            Jugador Jugador2 = new Jugador(jugador2Nombre, J2Color);
            // Crear tipo de juego
            String tipo = (String) tipoJuegoComboBox.getSelectedItem();
            if(tipo.equals("Normal")){
                tipoJuego = new NormalType();
            }
            String modoJuego = (String) modoJuegoComboBox.getSelectedItem();
            ModoJuego modo ;
            if(modoJuego.equals("Jugador vs Jugador")){
            	modo = new JugadorVsJugadorMode();
            	           	 
            }else{
            	
            	modo = new JugadorVsMaquina();
            	 
            }
            
            iniciarJuego(modo, Jugador1, Jugador2, tipoJuego);
            
        }
    });
    Config.add(iniciarJuegoButton, gbc);
    
    Config.pack();
    Config.setLocationRelativeTo(null);
    Config.setVisible(true);
}

    
    private void iniciarJuego(ModoJuego modo, Jugador Jugador1, Jugador Jugador2, TiposJuego tipoJuego) {
        int tamanoTablero = gomoku.getTamanoTablero();

        JOptionPane.showMessageDialog(Config,
                "Configuración seleccionada:\n" +
                "Nombres de jugadores: " + Jugador1.getName() + " y " + Jugador2.getName() + "\n" +
                "Tamaño del tablero: " + tamanoTablero + "x" + tamanoTablero,
                "Información",
                JOptionPane.INFORMATION_MESSAGE);

        gomoku.setJugadors(Jugador1, Jugador2);
        gomoku.setGameType(tipoJuego);
        tipoJuego.initializeFichas(gomoku);
        prepareElements();
        Config.dispose();
    }
    
    
    /**
     * Prepara los elementos de la interfaz gráfica para el desarrollo del juego.
     */
    private void prepareElements() {
        // Configurar el comportamiento de cierre de la ventana
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Establecer el tamaño predeterminado de la ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width, height);
        setLocationRelativeTo(null);
        // Crear un panel para organizar los botones en un menú desplegable
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Crear un menú desplegable en el lado izquierdo
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Opciones");
        // Crear elementos del menú
        JMenuItem newGameItem = new JMenuItem("Nuevo Juego");
        JMenuItem endGameItem = new JMenuItem("Terminar Juego");
        JMenuItem loadGameItem = new JMenuItem("Cargar Partida");
        JMenuItem saveGameItem = new JMenuItem("Guardar Partida");
        JMenuItem exitItem = new JMenuItem("Salir");
        JMenuItem restartItem = new JMenuItem("Reiniciar");
        // Agregar elementos al menú
        optionsMenu.add(newGameItem);
        optionsMenu.add(endGameItem);
        optionsMenu.add(loadGameItem);
        optionsMenu.add(saveGameItem);
        optionsMenu.add(restartItem);
        optionsMenu.add(exitItem);
        // Agregar el menú al menú desplegable
        menuBar.add(optionsMenu);
        // Configurar el manejador de eventos para el menú
        setJMenuBar(menuBar);
        // Añadir un panel al centro de la ventana para el tablero
        mainPanel.add(new JPanel(), BorderLayout.CENTER);
        // Crear un panel al centro de la ventana para el tablero
        botonC = new JButton[gomoku.getcajas().length][gomoku.getcajas().length];
        JPanel cajasPanel = new JPanel(new GridLayout(gomoku.getcajas().length, gomoku.getcajas().length));
   
        JPanel info1Container = new JPanel();
        info1Container.add(infoJugadores());
        
        JPanel info2Container = new JPanel();
        info2Container.add(selectFicha());
        

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(cajasPanel, BorderLayout.CENTER);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, info1Container, info2Container);
        splitPane.setResizeWeight(1.0);
        
        //agregar todo al mainPanel
        add(splitPane, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        prepareElementscajas(cajasPanel);
        // Hacer visible la ventana
        prepareActions();
        setVisible(true);
    }
    
     /**
     * Crea y devuelve un panel con información sobre los jugadores.
     *
     * @return Panel con información sobre los jugadores.
     */
    private JPanel infoJugadores(){
        //Jugador1
        String nombreJugador1 = gomoku.getJugador1().getName();
        JLabel tiempoLabel = new JLabel("Tiempo: " + gomoku.getTime()); 
        J1puntos = new JLabel("Puntaje: " + gomoku.getJugador1().getPuntos()); 
        JLabel jugador1Label = new JLabel("Jugador 1: " + nombreJugador1);
        jugador1Label.setForeground(gomoku.getJugador1().getColor());
        J1Fichas = new JLabel("Normales: " + gomoku.getJugador1().getFichasCount("FichaNormal")  +
         " Pesadas: " + gomoku.getJugador1().getFichasCount("FichaPesada") + " Temporales: " + 
         gomoku.getJugador1().getFichasCount("FichaTemporal"));
        //Jugador2
        String nombreJugador2 = gomoku.getJugador2().getName();
        JLabel jugador2Label = new JLabel("Jugador 2: " + nombreJugador2); 
        J2puntos = new JLabel("Puntaje: " + gomoku.getJugador2().getPuntos()); 
        jugador2Label.setForeground(gomoku.getJugador2().getColor());
        J2Fichas = new JLabel("Normales: " + gomoku.getJugador2().getFichasCount("FichaNormal")  + 
         " Pesadas: " + gomoku.getJugador2().getFichasCount(
         "FichaPesada") + " Temporales: " + gomoku.getJugador2().getFichasCount("FichaTemporal"));
        //add
        JPanel info1Panel = new JPanel(new GridLayout(7, 1));
        info1Panel.add(tiempoLabel);
        info1Panel.add(jugador1Label);
        info1Panel.add(J1puntos);
        info1Panel.add(J1Fichas);
        info1Panel.add(jugador2Label);
        info1Panel.add(J2puntos);
        info1Panel.add(J2Fichas);
        
        return info1Panel;
    }
    
     /**
     * Crea y devuelve un panel con opciones para seleccionar el tipo de ficha.
     *
     * @return Panel con opciones para seleccionar el tipo de ficha.
     */
    private JPanel selectFicha(){
        //crear botones seleccion de ficha
        JPanel info2Panel = new JPanel(new GridLayout(6, 1));
        enTurno = new JLabel("Turno: " + gomoku.GetJugadorActual().getName());
        info2Panel.add(enTurno);
        JLabel seleccionarFichaLabel = new JLabel("Seleccionar ficha");
        info2Panel.add(seleccionarFichaLabel);
        FichaenJUego = new JLabel("Ficha seleccionada: " + gomoku.GetJugadorActual().getCurrentFicha());
        info2Panel.add(FichaenJUego);
        JButton normalButton = new JButton("Normal");
        JButton pesadaButton = new JButton("Pesada");
        JButton temporalButton = new JButton("Temporal");
        info2Panel.add(normalButton);
        info2Panel.add(pesadaButton);
        info2Panel.add(temporalButton);
          normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gomoku.GetJugadorActual().SetActualFicha("FichaNormal"); 
                FichaenJUego.setText("Ficha seleccionada: " + gomoku.GetJugadorActual().getCurrentFicha());                
            }
        });
        
         pesadaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gomoku.GetJugadorActual().SetActualFicha("FichaPesada");
                FichaenJUego.setText("Ficha seleccionada: " + gomoku.GetJugadorActual().getCurrentFicha());
            }
        });
         temporalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gomoku.GetJugadorActual().SetActualFicha("FichaTemporal");
                FichaenJUego.setText("Ficha seleccionada: " + gomoku.GetJugadorActual().getCurrentFicha());
            }
        });
        return info2Panel;
    }
    
    
     /**
     * Prepara los botones del tablero y sus ActionListener.
     *
     * @param panelcajas Panel que contiene los botones del tablero.
     */
    private void prepareElementscajas(JPanel panelcajas) {
        for (int i = 0; i < gomoku.getcajas().length; i++) {
            for (int j = 0; j < gomoku.getcajas().length; j++) {
                // Crear un botón para cada cuadro del tablero
                
            	Caja caja = gomoku.getcajas()[i][j];
            	JButton button = new JButton();

            	if (caja instanceof MineCaja) {
            	    button.setBackground(Color.BLUE);
            	} else if (caja instanceof TeleportCaja) {
            	    button.setBackground(Color.YELLOW); // Cambia el color a amarillo
            	} else {
            	    button.setBackground(Color.decode("#FFFFFF"));
            	}

            	
                final int filaFinal = i;
                
                final int columnaFinal = j; 
                // Agregar ActionListener para manejar clics en los botones
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!play(filaFinal, columnaFinal)){
                            JOptionPane.showMessageDialog(GomokuGUI.this, "Posición no valida");
                        }
                    }
                });
                botonC[i][j] = button;
                panelcajas.add(button);
            }
        }
    }
    
    /**
     * Realiza una jugada en el tablero en la posición dada.
     *
     * @param fila    Fila en la que se realiza la jugada.
     * @param columna Columna en la que se realiza la jugada.
     * @return true si la jugada es válida y se realizó con éxito, false de lo contrario.
     */
    private boolean play(int fila, int columna) {
        String tipo = gomoku.GetJugadorActual().getCurrentFicha();
        Color currentJugadorColor = gomoku.GetJugadorActual().getColor();
        if (gomoku.putFicha(fila, columna, currentJugadorColor,tipo)) {
            if (gomoku.victoria(currentJugadorColor)) {
                JOptionPane.showMessageDialog(GomokuGUI.this, "Ha ganado el jugador "+ gomoku.getWinner().getName() );
                int result = JOptionPane.showConfirmDialog(this, "¿Nuevo Juego?", "Nuevo Juego", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    prepareNewGame();
                }
                else{
                    System.exit(0);
                }
            }
            refresh();
            return true;
        }
        return false;
    }
    
    /**
     * Actualiza el color y texto de las casillas.
     */
    private void refresh() {
        Caja[][] cajas = gomoku.getcajas();
        for(int i = 0; i < gomoku.getcajas().length; i++){     
            for(int j = 0; j < gomoku.getcajas().length; j++){
                if(cajas[i][j].getFicha() != null){
                botonC[i][j].setText(Character.toString(cajas[i][j].getFichaShape()));
                botonC[i][j].setForeground(cajas[i][j].getFichaColor());
            }
            else{
                botonC[i][j].setText(" ");
            }
            }
            
        }
        enTurno.setText("Turno: " + gomoku.GetJugadorActual().getName());
        //actualizar cantidad de fichas
        FichaenJUego.setText("Ficha Seleccionada: " + gomoku.GetJugadorActual().getCurrentFicha());
        J1Fichas.setText("Normales: " + gomoku.getJugador1().getFichasCount("FichaNormal")  +
         " Pesadas: " + gomoku.getJugador1().getFichasCount("FichaPesada")
         + " Temporales: " + gomoku.getJugador1().getFichasCount("FichaTemporal"));
        J2Fichas.setText("Normales: " + gomoku.getJugador2().getFichasCount("FichaNormal")  + 
         " Pesadas: " + gomoku.getJugador2().getFichasCount(
         "FichaPesada") + " Temporales: " + gomoku.getJugador2().getFichasCount("FichaTemporal"));
        //actualizar puntaje
        J1puntos.setText("Puntaje: " + gomoku.getJugador1().getPuntos()); 
        J2puntos.setText("Puntaje: " + gomoku.getJugador2().getPuntos()); 
    }
    
     /**
     * Prepara las acciones y manejadores de eventos para los elementos de la interfaz gráfica.
     */
    private void prepareActions() {
        // Configurar manejadores de eventos para los elementos del menú
        JMenuItem newGameItem = getJMenuBar().getMenu(0).getItem(0);
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareNewGame();
            }
        });
        //Añadir un oyente para manejar el evento de terminar el juego
        JMenuItem endGameItem = getJMenuBar().getMenu(0).getItem(1);
        endGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( gomoku.endGame()){
                    JOptionPane.showMessageDialog(GomokuGUI.this, "Ha ganado el jugador "+ gomoku.getWinner().getName() );
                    prepareNewGame();
                }
                else{
                    JOptionPane.showMessageDialog(GomokuGUI.this, "Empate ");
                    prepareNewGame();
                }
            }
        });
        //Añadir un oyente para manejar el evento de guardar partida
        JMenuItem loadGameItem = getJMenuBar().getMenu(0).getItem(2);
        loadGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para cargar una partida
                JOptionPane.showMessageDialog(GomokuGUI.this, "Cargar partida");
                openFile();
            }
        });
        //Añadir un oyente para manejar el evento de guardar la partida
        JMenuItem saveGameItem = getJMenuBar().getMenu(0).getItem(3);
        saveGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para guardar una partida
                JOptionPane.showMessageDialog(GomokuGUI.this, "Guardar partida");
                saveFile();
            }
        });
        //Añadir un oyente para manejar el evento de reiniciar el juego
        JMenuItem restartItem = getJMenuBar().getMenu(0).getItem(4);
        restartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gomoku.restartGame();
                refresh();
            }
        });
        //Añadir un oyente para manejar el evento de cierre de la ventana
        JMenuItem exitItem = getJMenuBar().getMenu(0).getItem(5);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirmar antes de salir
                confirmExit();
            }
        });
        // Añadir un oyente para manejar el evento de cierre de la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                confirmExit();
            }
        });
    }
    

     /**
     * Confirma la salida del programa, mostrando un cuadro de diálogo de confirmación.
     */
    private void confirmExit() {
        int result = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    /**
     * Abre un cuadro de diálogo para seleccionar un archivo y muestra un mensaje de construcción.
     */
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Funcionalidad de abrir en construcción. Archivo seleccionado: " + archivoSeleccionado.getName());
        }
    }

    /**
     * Abre un cuadro de diálogo para seleccionar la ubicación de guardado y muestra un mensaje de construcción.
     */
    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showSaveDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Funcionalidad de salvar en construcción. Archivo seleccionado: " + archivoSeleccionado.getName());
        }
    }
    
    /**
     * Prepara un nuevo juego, cerrando la ventana actual y creando una nueva instancia de GomokuGUI.
     */
    private void prepareNewGame() {
        // Cerrar la ventana actual
        dispose();
    }

     /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        GomokuGUI gomokuGUI = new GomokuGUI();
    }
}
