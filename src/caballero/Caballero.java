package caballero;

import java.awt.Color;
import java.awt.Graphics;

import ventana.Ventana;

public class Caballero {
    private int x;
    private int y;
    private final int ANCHO = Ventana.TAMAÑO_BLOQUE;
    private final int ALTO = Ventana.TAMAÑO_BLOQUE;

    public Caballero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(this.x, this.y, this.ANCHO, this.ALTO);
    }
    public void mover(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    public void manejarTeclas(int keyCode) {
        switch (keyCode) {
            case java.awt.event.KeyEvent.VK_UP:
                mover(0, -Ventana.TAMAÑO_BLOQUE);
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                mover(0, Ventana.TAMAÑO_BLOQUE);
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                mover(-Ventana.TAMAÑO_BLOQUE, 0);
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                mover(Ventana.TAMAÑO_BLOQUE, 0);
                break;
        }
    }


}
