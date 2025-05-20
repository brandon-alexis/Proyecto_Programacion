package graficos;


import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import main.Juego;
import util.Imagen;

public class Ventana extends JFrame {
    public static final int CANTIDAD_FILAS = 22;
    public static final int CANTIDAD_COLUMNAS = 22;
    public static final int TAMAÑO_BLOQUE = 32;
    public static final int ANCHO = CANTIDAD_COLUMNAS * TAMAÑO_BLOQUE;
    public static final int ALTO = CANTIDAD_FILAS * TAMAÑO_BLOQUE;
    public static final int FRAME = 16;
    public static final int FPS = FRAME / 1000;
    public static final String TITULO = "Laberinto del Honor";
    public static ImageIcon icono;
    private Juego juego;
    
    public Ventana() {
        this.juego = new Juego();
        this.configurarVentana();
   }

   private void configurarVentana() {
    this.icono = Imagen.cargar("icono.png", 64, 64, 1);
    this.setIconImage(this.icono.getImage());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(ANCHO, ALTO));
    this.setResizable(false);
    this.setTitle(TITULO);
    this.setLocationRelativeTo(null);
    this.add(juego);
    this.pack();
    juego.requestFocus(); 
   }
}
