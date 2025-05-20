package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Juego;

public class ControlJuego extends KeyAdapter {
    public static final String ARRIBA = "arriba";
    public static final String ABAJO = "abajo";
    public static final String IZQUIERDA = "izquierda";
    public static final String DERECHA = "derecha";
    private String direccion;
    private Juego juego;

    public ControlJuego(Juego juego) {
        this.direccion = DERECHA;
        this.juego = juego;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        int estado = this.juego.estadoJuego;

        if (estado == this.juego.ESTADO_JUEGO_JUGANDO) {
            switch (tecla) {
                case KeyEvent.VK_LEFT -> this.direccion = IZQUIERDA;
                case KeyEvent.VK_RIGHT -> this.direccion = DERECHA;
                case KeyEvent.VK_UP -> this.direccion = ARRIBA;
                case KeyEvent.VK_DOWN -> this.direccion = ABAJO;
                default -> this.direccion = "cero";
            }
        } else if (estado == this.juego.ESTADO_JUEGO_PERDER) {
            switch (tecla) {
                case  KeyEvent.VK_R -> this.juego.reiniciarNivel();
                case KeyEvent.VK_Q -> System.exit(0);
            }
        } else if (estado == this.juego.ESTADO_JUEGO_GANAR) {
            switch (tecla) {
                case KeyEvent.VK_ENTER -> this.juego.pasarAlSiguienteNivel();
                case KeyEvent.VK_Q -> System.exit(0);
            }
        }
    }

}