package ventana;
import java.awt.Dimension;

import javax.swing.JFrame;

import app.Juego;

public class Ventana extends JFrame {
    public static final int CANTIDAD_FILAS = 22;
    public static final int CANTIDAD_COLUMNAS = 22;
    public static final int TAMAÑO_BLOQUE = 32;
    public static final int ANCHO = CANTIDAD_COLUMNAS * TAMAÑO_BLOQUE;
    public static final int ALTO = CANTIDAD_FILAS * TAMAÑO_BLOQUE;
    public static final String TITULO = "Juego";
    public static Juego juego;
    
    public Ventana() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(this.ANCHO, this.ALTO));
        this.setResizable(false);
        this.setTitle(Ventana.TITULO);
        this.setLocationRelativeTo(null);

        this.juego = new Juego();
        this.add(juego);
        this.pack();
        juego.requestFocus();
   }
}