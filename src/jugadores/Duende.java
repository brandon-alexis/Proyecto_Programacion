package jugadores;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.List;

import enemigos.Caballero;
import graficos.Ventana;
import objetos.Comida;
import objetos.Pared;

public class Duende {
    public static final int ANCHO = Ventana.TAMAÑO_BLOQUE;
    public static final int ALTO = Ventana.TAMAÑO_BLOQUE;
    public final int VELOCIDAD = 5;
    private int x;
    private int y;
    private int velocidadX;
    private int velocidadY;
    private String direccion;

    public Duende(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocidadX = this.VELOCIDAD;
        this.velocidadY = this.VELOCIDAD;
        this.direccion = "derecha";
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

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, this.ANCHO, this.ALTO);
    }

    public void actualizar(String direccion) {
        switch (direccion) {
            case "izquierda":
                this.x -= this.velocidadX;
                break;
            case "derecha":
                this.x += this.velocidadX;
                break;
            case "arriba":
                this.y -= this.velocidadY;
                break;
            case "abajo":
                this.y += this.velocidadY;
            default:
                break;
        }

    }

    public boolean detectarColisionPared(Pared pared) {
        if (this.x < pared.getX() + pared.getAncho() && this.x + this.ANCHO > pared.getX()
                && this.y < pared.getY() + pared.getAlto() && this.y + this.ALTO > pared.getY()) {

            return true;
        }

        return false;

    }

    public boolean detectarColisionCaballero(Caballero caballero) {
        if (this.x < caballero.getX() + caballero.getAncho() && this.x + this.ANCHO > caballero.getX()
                && this.y < caballero.getY() + caballero.getAlto() && this.y + this.ALTO > caballero.getY()) {

            return true;
        }

        return false;

    }

    

    public boolean detectarColisionComida(Comida comida) {
        if (this.x == comida.getX() && this.y == comida.getY()) {
            return true;

        }
        return false;

    }

    public void actualizarDireccion(String direccion) {
        if (direccion.equals("arriba")) {
            this.velocidadX = 0;
            this.velocidadY = -this.VELOCIDAD;
        } else if (direccion.equals("abajo")) {
            this.velocidadX = 0;
            this.velocidadY = this.VELOCIDAD;
        } else if (direccion.equals("izquierda")) {
            this.velocidadY = 0;
            this.velocidadX = -this.VELOCIDAD;
        } else if (direccion.equals("derecha")) {
            this.velocidadY = 0;
            this.velocidadX = this.VELOCIDAD;

            this.direccion = direccion;

        }
    }

    public void revertirMovimiento() {
        this.x -= this.velocidadX;
        this.y -= this.velocidadY;
    }
}
