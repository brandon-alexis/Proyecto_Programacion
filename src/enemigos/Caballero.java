package enemigos;


import java.awt.Color;
import java.awt.Graphics;

import graficos.Ventana;

public class Caballero {
    public static final int ANCHO = Ventana.TAMAÑO_BLOQUE;
    public static final int ALTO = Ventana.TAMAÑO_BLOQUE;
    public final int VELOCIDAD = 2;
    private int x;
    private int y;
    private int velocidadX;
    private int velocidadY;
    private String direccion;

    public Caballero(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocidadX = VELOCIDAD;
        this.velocidadY = VELOCIDAD;
    }

    

    public static int getAncho() {
        return ANCHO;
    }



    public static int getAlto() {
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

    //generar movimiento aleatorio
    public void moverAleatorio() {
        int dx = (int) (Math.random() * 3) - 1; // -1, 0 o 1
        int dy = (int) (Math.random() * 3) - 1; // -1, 0 o 1
        mover(dx * Ventana.TAMAÑO_BLOQUE, dy * Ventana.TAMAÑO_BLOQUE);
    }

    // perseguir al jugador tendra en cuenta los bloques con los que colisiona al estilo de pacman
    public void perseguir(int jugadorX, int jugadorY) {
        if (Math.abs(this.x - jugadorX) > Math.abs(this.y - jugadorY)) {
            if (this.x < jugadorX) {
                mover(this.velocidadX, 0);
            } else {
                mover(-this.velocidadX, 0);
            }
        } else {
            if (this.y < jugadorY) {
                mover(0, this.velocidadY);
            } else {
                mover(0, -this.velocidadY);
            }
        }
    }	

    // detectar colision con el jugador
    public boolean colision(int jugadorX, int jugadorY) {
        return (this.x < jugadorX + Ventana.TAMAÑO_BLOQUE && this.x + this.ANCHO > jugadorX &&
                this.y < jugadorY + Ventana.TAMAÑO_BLOQUE && this.y + this.ALTO > jugadorY);
    }

    //no puede pasar por encima de los bloques
    public boolean colisionBloque(int bloqueX, int bloqueY) {
        return (this.x < bloqueX + Ventana.TAMAÑO_BLOQUE && this.x + this.ANCHO > bloqueX &&
                this.y < bloqueY + Ventana.TAMAÑO_BLOQUE && this.y + this.ALTO > bloqueY);
    }
}
