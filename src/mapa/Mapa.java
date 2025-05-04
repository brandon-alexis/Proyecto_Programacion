package mapa;

import java.awt.Graphics;
import java.util.HashSet;

import enemigos.Caballero;
import graficos.Ventana;
import jugadores.Duende;
import objetos.Comida;
import objetos.Pared;

public class Mapa {
    private static final int PARED = 1;
    private static final int COMIDA = 0;
    private static final int CABALLERO = 3;
    private static final int JUGADOR = 4;

    private final int[][] mapa = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1 },
            { 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 3, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 2, 1, 0, 1, 1, 0, 1 },
            { 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 3, 1 },
            { 1, 2, 1, 0, 0, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1 },
            { 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 1, 1, 0, 0, 3, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 1, 3, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 3, 1, 0, 1, 1, 1, 3, 1 },
            { 1, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
    };


     
    private HashSet<Pared> paredes;
    private HashSet<Caballero> caballeros;
    private HashSet<Comida> comidas;
    private Duende jugador;

    public Mapa() {
        this.paredes = new HashSet<Pared>();
        this.comidas = new HashSet<Comida>();
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
                    Comida comida = new Comida(x, y);
                    this.comidas.add(comida);
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

        for (Comida comida : comidas) {
            comida.dibujar(g);
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

    public HashSet<Comida> getComidas() {
        return comidas;
    }

    void mostrarMapa() {
        for (int[] fila : mapa) {
            for (int celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }

    
}


