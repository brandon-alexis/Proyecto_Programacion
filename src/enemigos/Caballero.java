package enemigos;

import java.awt.Graphics;
import java.util.HashSet;
import javax.swing.ImageIcon;

import graficos.Ventana;
import objetos.Pared;
import util.Imagen;
import control.ControlJuego;

public class Caballero {
    private static final int ANCHO = Ventana.TAMAÑO_BLOQUE;
    private static final int ALTO = Ventana.TAMAÑO_BLOQUE;
    private final int VELOCIDAD = (int) ((Ventana.TAMAÑO_BLOQUE / Ventana.FRAME) * 0.8);
    private int x;
    private int y;
    private int velocidadX;
    private int velocidadY;
    private String direccion = ControlJuego.ABAJO;
    private int dirX = 0;
    private int dirY = 0;
    private ImageIcon imagenArriba;
    private ImageIcon imagenAbajo;
    private ImageIcon imagenIzquierda;
    private ImageIcon imagenDerecha;

    public Caballero(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocidadX = VELOCIDAD;
        this.velocidadY = VELOCIDAD;
        this.imagenArriba = Imagen.cargar("caballero/arriba.png", ANCHO, ALTO, 1);
        this.imagenAbajo = Imagen.cargar("caballero/abajo.png", ANCHO, ALTO, 1);
        this.imagenIzquierda = Imagen.cargar("caballero/izquierda.png", ANCHO, ALTO, 1);
        this.imagenDerecha = Imagen.cargar("caballero/derecha.png", ANCHO, ALTO, 1);
        this.direccion = ControlJuego.ABAJO;

    }

    public void dibujar(Graphics g) {
        if (direccion.equals(ControlJuego.DERECHA)) {
            g.drawImage(this.imagenDerecha.getImage(), this.x, this.y, ANCHO, ALTO, null);

        }

        if (direccion.equals(ControlJuego.IZQUIERDA)) {
            g.drawImage(this.imagenIzquierda.getImage(), this.x, this.y, ANCHO, ALTO, null);
        }

        if (direccion.equals(ControlJuego.ARRIBA)) {
            g.drawImage(this.imagenArriba.getImage(), this.x, this.y, ANCHO, ALTO, null);
        }

        if (direccion.equals(ControlJuego.ABAJO)) {
            g.drawImage(this.imagenAbajo.getImage(), this.x, this.y, ANCHO, ALTO, null);
        }

    }

    public void mover(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean detectarColisionPared(int nuevoX, int nuevoY, HashSet<Pared> paredes) {
        for (Pared pared : paredes) {
            if (nuevoX < pared.getX() + pared.getAncho() &&
                    nuevoX + ANCHO > pared.getX() &&
                    nuevoY < pared.getY() + pared.getAlto() &&
                    nuevoY + ALTO > pared.getY()) {
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
            // Establecer la dirección correspondiente
            if (mejorDX > 0) {
                direccion = ControlJuego.DERECHA;
            } else if (mejorDX < 0) {
                direccion = ControlJuego.IZQUIERDA;
            } else if (mejorDY > 0) {
                direccion = ControlJuego.ABAJO;
            } else if (mejorDY < 0) {
                direccion = ControlJuego.ARRIBA;
            }

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
            actualizarDireccion(dirX, dirY);
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
                actualizarDireccion(dirX, dirY);
                this.mover(dirX * this.velocidadX, dirY * this.velocidadY);
            }
        }
    }

    private void actualizarDireccion(int dx, int dy) {
        if (dx > 0) {
            direccion = ControlJuego.DERECHA;
        } else if (dx < 0) {
            direccion = ControlJuego.IZQUIERDA;
        } else if (dy > 0) {
            direccion = ControlJuego.ABAJO;
        } else if (dy < 0) {
            direccion = ControlJuego.ARRIBA;
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