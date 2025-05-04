package objetos;


import java.awt.Graphics;

import graficos.Ventana;

import java.awt.Color;

public class Comida {
    private int x;
    private int y;
    private boolean isEaten;

    // Tamaño de la comida
    public static final int ANCHO = Ventana.TAMAÑO_BLOQUE / 2; // 32
    public static final int ALTO = Ventana.TAMAÑO_BLOQUE / 2; // 32

    public Comida(int x, int y) {
        this.x = x;
        this.y = y;
        this.isEaten = false;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEaten() {
        return isEaten;
    }

    public void eat() {
        this.isEaten = true;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(this.x + 5, this.y + 5, ANCHO, ALTO);
    }

    // COMER COMIDA
    public void checkCollision(int playerX, int playerY) {
        if (!isEaten && playerX < x + ANCHO && playerX + ANCHO > x && playerY < y + ALTO && playerY + ALTO > y) {
            eat();
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setEaten(boolean isEaten) {
        this.isEaten = isEaten;
    }

    public static int getAncho() {
        return ANCHO;
    }

    public static int getAlto() {
        return ALTO;
    }

    
}
