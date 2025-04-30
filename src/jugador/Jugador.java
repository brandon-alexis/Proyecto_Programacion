package jugador;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import ventana.Ventana;

public class Jugador {
    private static final int ANCHO = Ventana.TAMAÑO_BLOQUE;
    private static final int ALTO = Ventana.TAMAÑO_BLOQUE;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private final int VELOCIDAD = 1;
    private List<String> teclasDisponibles;

    public Jugador(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, this.ANCHO, this.ALTO);
    }

    public void actualizar() {
       this.x += this.dx;
       this.y += this.dy;

       //
    }

    public void presionalTecla(char tecla) {
        
        switch (tecla) {
            case 'w':
                this.dy = -this.VELOCIDAD;
                break;
            case 's':
                this.dy = this.VELOCIDAD;
                break;
            case 'a':
                this.dx = -this.VELOCIDAD;
                break;
            case 'd':
                this.dx = this.VELOCIDAD;
                break;
            default:
                break;
        }
    }

    public void soltarTecla(char tecla) {
        switch (tecla) {
            case 'w':
                this.dy = 0;
                break;
            case 's':
                this.dy = 0;
                break;
            case 'a':
                this.dx = 0;
                break;
            case 'd':
                this.dx = 0;
                break;
            default:
                break;
        }

    }

}
