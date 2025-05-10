package mapa;

import java.awt.Graphics;
import java.util.HashSet;

import enemigos.Caballero;
import graficos.Ventana;
import jugadores.Duende;
import objetos.Moneda;
import objetos.Pared;

public class Mapa{
    public static final int PARED = 1;
    public static final int COMIDA = 0;
    public static final int CABALLERO = 3;
    public static final int JUGADOR = 4;

    private int[][] mapa = Nivel.obtenerNivel();
    private HashSet<Pared> paredes;
    private HashSet<Caballero> caballeros;
    private HashSet<Moneda> monedas;
    private Duende jugador;

    public Mapa() {
        this.paredes = new HashSet<Pared>();
        this.monedas = new HashSet<Moneda>();
        this.caballeros = new HashSet<Caballero>();
        this.cargarMapa();

    }

    private void cargarMapa() {
        if (mapa.length != Ventana.CANTIDAD_FILAS || mapa[0].length != Ventana.CANTIDAD_COLUMNAS) {
            System.out.printf(
                    "El mapa tiene una dimension de %dx%d y tiene que tener una dimension de %dx%d para ser valido",
                    mapa.length,
                    mapa[0].length,
                    Ventana.CANTIDAD_FILAS,
                    Ventana.CANTIDAD_COLUMNAS);
            throw new ArrayIndexOutOfBoundsException();
        }

        for (int i = 0; i < Ventana.CANTIDAD_FILAS; i++) {
            for (int j = 0; j < Ventana.CANTIDAD_COLUMNAS; j++) {
                int x = Ventana.TAMAÑO_BLOQUE * j;
                int y = Ventana.TAMAÑO_BLOQUE * i;

                if (mapa[i][j] == PARED) {
                    Pared pared = new Pared(x, y, Ventana.TAMAÑO_BLOQUE, Ventana.TAMAÑO_BLOQUE);
                    this.paredes.add(pared);
                }

                if (mapa[i][j] == COMIDA) {
                    Moneda comida = new Moneda(x, y);
                    this.monedas.add(comida);
                }

                if (mapa[i][j] == CABALLERO) {
                    Caballero caballero = new Caballero(x, y);
                    this.caballeros.add(caballero);
                }

                if (mapa[i][j] == JUGADOR) {
                    this.jugador = new Duende(x, y);
                }
            }
        }
    }
    
    public void dibujar(Graphics g) {
        for (Pared pared : paredes) {
            pared.dibujar(g);
        }

        for (Moneda moneda : monedas) {
            moneda.dibujar(g);
        }

        for (Caballero caballero : caballeros) {
            caballero.dibujar(g);

        }
    }

    public void actualizar() {
        for (Caballero caballero : caballeros) {
            caballero.actualizarComportamiento(this.jugador.getX(), this.jugador.getY(), this.paredes);

        }
    }

    public HashSet<Pared> getParedes() {
        return paredes;
    }

    public Duende getJugador() {
        return jugador;
    }

    public HashSet<Caballero> getCaballeros() {
        return caballeros;
    }

    public HashSet<Moneda> getMonedas() {
        return monedas;
    }

    public int[][] getMapa() {
        return mapa;
    }
  
}


