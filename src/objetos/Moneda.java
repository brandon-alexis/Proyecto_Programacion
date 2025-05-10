package objetos;


import java.awt.Graphics;
import java.awt.Image;

import graficos.Ventana;

import java.awt.Color;

import java.net.URL;

import javax.swing.ImageIcon;


public class Moneda {
    private int x;
    private int y;
    private ImageIcon imagenOriginalMoneda;
    private ImageIcon imagenMoneda;
    private URL urlMoneda;

    // Tamaño de la comida
    public static final int ANCHO = Ventana.TAMAÑO_BLOQUE / 2; // 32
    public static final int ALTO = Ventana.TAMAÑO_BLOQUE / 2; // 32



    public Moneda(int x, int y) {
        this.x = x;
        this.y = y;
        this.urlMoneda = Moneda.class.getResource("../recursos/imagenes/moneda.png");
        this.imagenOriginalMoneda = new  ImageIcon(urlMoneda);
        Image imagenEscalada = imagenOriginalMoneda.getImage().getScaledInstance(ANCHO * 3, ALTO * 3, Image.SCALE_SMOOTH);
        this.imagenMoneda = new ImageIcon(imagenEscalada);
    }

    public void dibujar(Graphics g) {
      g.drawImage(imagenMoneda.getImage(), this.x - 9, this.y - 9, null);
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static int getAncho() {
        return ANCHO;
    }

    public static int getAlto() {
        return ALTO;
    }
    
}
