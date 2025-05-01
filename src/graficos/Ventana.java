package graficos;


import java.awt.Dimension;

import javax.swing.JFrame;

import control.Control;
import main.Juego;

public class Ventana extends JFrame {
    public static final int CANTIDAD_FILAS = 22;
    public static final int CANTIDAD_COLUMNAS = 22;
    public static final int TAMAÑO_BLOQUE = 32;
    public static final int ANCHO = CANTIDAD_COLUMNAS * TAMAÑO_BLOQUE;
    public static final int ALTO = CANTIDAD_FILAS * TAMAÑO_BLOQUE;
    public static final String TITULO = "Juego";
    private Juego juego;
    private Control control;
    
    public Ventana() {
        this.juego = new Juego();
        this.control = new Control();
        this.configurarVentana();
   }

   private void configurarVentana() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(ANCHO, ALTO));
    this.setResizable(false);
    this.setTitle(TITULO);
    this.setLocationRelativeTo(null);
    juego.addKeyListener(control);
    this.add(juego);
    this.pack();
    juego.requestFocus();
    
 

    
   }
}
