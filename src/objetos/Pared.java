package objetos;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import graficos.Ventana;
import util.Imagen;

public class Pared {
    private static final int ANCHO = Ventana.TAMAÑO_BLOQUE;
    private static final int ALTO = Ventana.TAMAÑO_BLOQUE;
    private int x;
    private int y;
    private ImageIcon imagenPared;
    
    public Pared(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;        
        this.imagenPared = Imagen.cargar("pared.png", ANCHO, ALTO, 1);
    }
    
    public void dibujar(Graphics g) {
        g.drawImage(imagenPared.getImage(), this.x, this.y, ANCHO, ALTO, null);
        g.setColor(Color.BLACK);
    }

    public void actualizar() {

    }

    public int getAncho() {
        return ANCHO;
    }


    public int getAlto() {
        return ALTO;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
