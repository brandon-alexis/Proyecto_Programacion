package jugadores;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import control.ControlJuego;
import enemigos.Caballero;
import graficos.Ventana;
import main.Juego;
import mapa.Mapa;
import objetos.Comida;
import objetos.Pared;

public class Duende {
    public static final int ANCHO = Ventana.TAMAÑO_BLOQUE;
    public static final int ALTO = Ventana.TAMAÑO_BLOQUE;
    public final int VELOCIDAD = (int) ((Ventana.TAMAÑO_BLOQUE / Ventana.FRAME));
    private int x;
    private int y;
    private int velocidadX;
    private int velocidadY;
    private String direccion;
    private String proximaDireccion;
    private List<String> direccionesValidas;
    private int vidas;

    public Duende(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocidadX = this.VELOCIDAD;
        this.velocidadY = this.VELOCIDAD;
        this.direccionesValidas = List.of("arriba", "abajo", "izquierda", "derecha");
        this.direccion = ControlJuego.DERECHA;
        this.proximaDireccion = "derecha";
        this.vidas = 3;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(this.x, this.y, this.ANCHO, this.ALTO);
    }

    public void mover(HashSet<Pared> paredes) {
        boolean alineado = (this.x % ANCHO == 0 && this.y % ALTO == 0);

        if (alineado) {
            int dx = 0, dy = 0;
            switch (this.proximaDireccion) {
                case ControlJuego.ARRIBA:
                    dy = -VELOCIDAD;
                    break;
                case ControlJuego.ABAJO:
                    dy = VELOCIDAD;
                    break;
                case ControlJuego.IZQUIERDA:
                    dx = -VELOCIDAD;
                    break;
                case ControlJuego.DERECHA:
                    dx = VELOCIDAD;
                    break;
            }

            this.x += dx;
            this.y += dy;

            boolean puedeGirar = true;
            for (Pared pared : paredes) {
                if (detectarColisionPared(pared)) {
                    puedeGirar = false;
                    break;
                }
            }

            this.x -= dx;
            this.y -= dy;

            if (puedeGirar) {
                this.direccion = this.proximaDireccion;
                actualizarVelocidad();
            }
        }

        this.x += this.velocidadX;
        this.y += this.velocidadY;

        for (Pared pared : paredes) {
            if (this.detectarColisionPared(pared)) {
                this.x -= velocidadX;
                this.y -= velocidadY;
                break;
            }
        }
    }

    public void actualizarDireccion(String direccion, HashSet<Pared> paredes) {
        if (this.direccionesValidas.contains(direccion)) {
            this.proximaDireccion = direccion;
        }
    }

    public void actualizarVelocidad() {
        switch (this.direccion) {
            case ControlJuego.ARRIBA:
                this.velocidadX = 0;
                this.velocidadY = -VELOCIDAD;
                break;
            case ControlJuego.ABAJO:
                this.velocidadX = 0;
                this.velocidadY = VELOCIDAD;
                break;
            case ControlJuego.IZQUIERDA:
                this.velocidadX = -VELOCIDAD;
                this.velocidadY = 0;
                break;
            case ControlJuego.DERECHA:
                this.velocidadX = VELOCIDAD;
                this.velocidadY = 0;
                break;
            default:
                break;
        }
    }

    public void comer(HashSet<Comida> comidas) {
        Iterator<Comida> it = comidas.iterator();
        while (it.hasNext()) {
            Comida comida = it.next();
            if (this.detectarColisionComida(comida)) {
                it.remove();
                Juego.incrementarPuntaje();
            }
        }
    }

    public boolean detectarColisionPared(Pared pared) {
        return (this.x < pared.getX() + pared.getAncho()
                && this.x + this.ANCHO > pared.getX()
                && this.y < pared.getY() + pared.getAlto()
                && this.y + this.ALTO > pared.getY());
    }

    public boolean detectarColisionCaballero(Caballero caballero) {
        return (this.x < caballero.getX() + caballero.getAncho()
                && this.x + this.ANCHO > caballero.getX()
                && this.y < caballero.getY() + caballero.getAlto()
                && this.y + this.ALTO > caballero.getY());
    }

    public boolean detectarColisionComida(Comida comida) {
        return (this.x < comida.getX() + comida.getAncho()
                && this.x + this.ANCHO > comida.getX()
                && this.y < comida.getY() + comida.getAlto()
                && this.y + this.ALTO > comida.getY());

    }

    public void cambiarPosicion(int[][] mapa) {
        int nuevaX, nuevaY;
        boolean posicionValida = false;

        while (!posicionValida) {
            int columna = (int) (Math.random() * Ventana.CANTIDAD_COLUMNAS);
            int fila = (int) (Math.random() * Ventana.CANTIDAD_FILAS);

            if (mapa[fila][columna] != Mapa.PARED) {
                nuevaX = columna * Ventana.TAMAÑO_BLOQUE;
                nuevaY = fila * Ventana.TAMAÑO_BLOQUE;

                this.x = nuevaX;
                this.y = nuevaY;
                posicionValida = true;
            }
        }
    }

    public void perderVida(int[][] mapa) {
        this.vidas--;
        this.cambiarPosicion(mapa);
        System.out.println("Vidas: " + this.vidas);
        if (this.vidas <= 0) {
            // Aquí puedes manejar la lógica de fin de juego o reinicio
            System.out.println("El duende ha perdido todas sus vidas.");

        }
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

    public int getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(int velocidadX) {
        this.velocidadX = velocidadX;
    }

    public int getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(int velocidadY) {
        this.velocidadY = velocidadY;
    }

    public int getVELOCIDAD() {
        return VELOCIDAD;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (this.direccionesValidas.contains(direccion)) {
            this.direccion = direccion;
        }
    }

    public String getProximaDireccion() {
        return proximaDireccion;
    }

    public void setProximaDireccion(String proximaDireccion) {
        if (this.direccionesValidas.contains(proximaDireccion)) {
            this.proximaDireccion = proximaDireccion;
        }
    }

    public int getVidas() {
        

        return vidas;

    }
    // metodo para cuando el duende pierde las 3 vidas pare el juego y no se pueda
    // jugar mas

   

}
