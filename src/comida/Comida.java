package comida;

import java.awt.Graphics;

import ventana.Ventana;

import java.awt.Color;

public class Comida {
    private int x;
    private int y;
    private boolean isEaten;

    

    // Tamaño de la comida
    public static final int ANCHO = Ventana.TAMAÑO_BLOQUE; //32
    public static final int ALTO = Ventana.TAMAÑO_BLOQUE; // 32
 
    


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

    @Override
    public String toString() {
        return "Comida{" +
                "x=" + x +
                ", y=" + y +
                ", isEaten=" + isEaten +
                '}';
    }

    public void dibujar(Graphics g) {
        if (!isEaten) {
            g.setColor(Color.GREEN);
            g.fillRect(this.x, this.y, ANCHO, ALTO);
            g.setColor(Color.WHITE);
            g.drawRect(this.x, this.y, ANCHO, ALTO);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(this.x, this.y, ANCHO, ALTO);
            g.setColor(Color.WHITE);
            g.drawRect(this.x, this.y, ANCHO, ALTO);
        }
    }

    //COMER COMIDA 
    public void checkCollision(int playerX, int playerY) {
        if (!isEaten && playerX < x + ANCHO && playerX + ANCHO > x && playerY < y + ALTO && playerY + ALTO > y) {
            eat();
        }
    }
    }

        

    
