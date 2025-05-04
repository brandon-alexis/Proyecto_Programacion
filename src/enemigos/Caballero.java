package enemigos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import graficos.Ventana;
import objetos.Pared;

public class Caballero {
    private static final int ANCHO = Ventana.TAMAÑO_BLOQUE;
    private static final int ALTO = Ventana.TAMAÑO_BLOQUE;
    private final int VELOCIDAD = (int) ((Ventana.TAMAÑO_BLOQUE / Ventana.FRAME) * 0.8);
    private int x;
    private int y;
    private int velocidadX;
    private int velocidadY;
    private String direccion;
    private int dirX = 0;
    private int dirY = 0;

    public Caballero(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocidadX = VELOCIDAD;
        this.velocidadY = VELOCIDAD;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(this.x, this.y, this.ANCHO, this.ALTO);
    }

    public void mover(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void manejarTeclas(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                mover(0, -Ventana.TAMAÑO_BLOQUE);
                break;
            case KeyEvent.VK_DOWN:
                mover(0, Ventana.TAMAÑO_BLOQUE);
                break;
            case KeyEvent.VK_LEFT:
                mover(-Ventana.TAMAÑO_BLOQUE, 0);
                break;
            case KeyEvent.VK_RIGHT:
                mover(Ventana.TAMAÑO_BLOQUE, 0);
                break;
        }
    }

    public boolean detectarColisionPared(int nuevoX, int nuevoY, HashSet<Pared> paredes) {
        for (Pared pared : paredes) {
            if (nuevoX < pared.getX() + pared.getAncho() &&
                    nuevoX + this.ANCHO > pared.getX() &&
                    nuevoY < pared.getY() + pared.getAlto() &&
                    nuevoY + this.ALTO > pared.getY()) {
                return true;
            }
        }
        return false;

    }

    public void perseguir(int jugadorX, int jugadorY, HashSet<Pared> paredes) {
        int[][] direcciones = {
                { this.velocidadX, 0 },
                { -this.velocidadX, 0 },
                { 0, this.velocidadY },
                { 0, -this.velocidadY }
        };

        

        int mejorDX = 0;
        int mejorDY = 0;
        double mejorDistancia = Double.MAX_VALUE;

        for (int[] dir : direcciones) {
            int nuevoX = this.x + dir[0];
            int nuevoY = this.y + dir[1];

            if (!detectarColisionPared(nuevoX, nuevoY, paredes)) {
                double distancia = calcularDistancia(nuevoX, nuevoY, jugadorX, jugadorY);

                if (distancia < mejorDistancia) {
                    mejorDistancia = distancia;
                    mejorDX = dir[0];
                    mejorDY = dir[1];
                }
            }
        }

        // Si encontró alguna dirección válida, moverse
        if (mejorDistancia < Double.MAX_VALUE) {
            this.mover(mejorDX, mejorDY);
        }
    }

    public void moverAleatorio(HashSet<Pared> paredes) {
        // Si no hay dirección inicial, asigna una al azar
        if (dirX == 0 && dirY == 0) {
            int[][] direcciones = {
                    { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
            };
            int[] nuevaDir = direcciones[(int) (Math.random() * direcciones.length)];
            dirX = nuevaDir[0];
            dirY = nuevaDir[1];
        }

        int nuevoX = this.x + dirX * this.velocidadX;
        int nuevoY = this.y + dirY * this.velocidadY;

        if (!detectarColisionPared(nuevoX, nuevoY, paredes)) {
            this.mover(dirX * this.velocidadX, dirY * this.velocidadY);
        } else {
            // Cambiar de dirección si choca
            int[][] direcciones = {
                    { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
            };
            int intento = 0;
            do {
                int[] nuevaDir = direcciones[(int) (Math.random() * direcciones.length)];
                dirX = nuevaDir[0];
                dirY = nuevaDir[1];
                nuevoX = this.x + dirX * this.velocidadX;
                nuevoY = this.y + dirY * this.velocidadY;
                intento++;
            } while (detectarColisionPared(nuevoX, nuevoY, paredes) && intento < 10);

            if (!detectarColisionPared(nuevoX, nuevoY, paredes)) {
                this.mover(dirX * this.velocidadX, dirY * this.velocidadY);
            }
        }
    }

    public void actualizarComportamiento(int jugadorX, int jugadorY, HashSet<Pared> paredes) {
        double distancia = calcularDistancia(this.x, this.y, jugadorX, jugadorY);

        // Define un rango de persecución (por ejemplo, 5 bloques de tamaño
        // Ventana.TAMAÑO_BLOQUE)
        double rangoPersecucion = Ventana.TAMAÑO_BLOQUE * 5;

        if (distancia <= rangoPersecucion) {
            perseguir(jugadorX, jugadorY, paredes);
        } else {
            moverAleatorio(paredes);
        }
    }

    private double calcularDistancia(int x1, int y1, int x2, int y2) {
        return Math.hypot(x2 - x1, y2 - y1); // Distancia euclidiana
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

    //
}