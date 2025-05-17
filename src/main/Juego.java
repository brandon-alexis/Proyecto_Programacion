package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.Timer;

import sonido.Sonido;
import control.ControlJuego;
import enemigos.Caballero;
import graficos.Ventana;
import jugadores.Duende;
import mapa.Mapa;
import mapa.Nivel;
import objetos.Moneda;
import objetos.Pared;

public class Juego extends JPanel implements ActionListener {
    private Mapa mapa;
    private Duende jugador;
    private Timer timer;
    private ControlJuego control;
    private HashSet<Pared> paredes;
    private HashSet<Moneda> monedas;
    public static int PUNTAJE;
    private Sonido sonido;

    public Juego() {
        this.mapa = new Mapa();
        this.jugador = mapa.getJugador();
        this.paredes = mapa.getParedes();
        this.monedas = mapa.getMonedas();
        this.control = new ControlJuego();
        this.timer = new Timer(Ventana.FRAME, this);
        this.sonido = new Sonido();

        PUNTAJE = 0;
        timer.start();

        this.setPreferredSize(new Dimension(Ventana.ALTO, Ventana.ANCHO));

        this.addKeyListener(control);
    }

    public void dibujar(Graphics g) {
        this.dibujarFondo(g);
        this.mapa.dibujar(g);
        this.jugador.dibujar(g);
        this.mostrarPuntaje(g);
        this.mostrarVidas(g);

        if (this.jugador.getVidas() <= 0) {
            this.mostrarMensajeGameOver(g);
            this.detenerJuego();
        }

        detenerJuegoComidas(g);
    }

    public void detenerJuegoComidas(Graphics g) {
        if (this.monedas.isEmpty() && Nivel.getNivel() < 3) {
            PUNTAJE = 0;
            Nivel.pasarNivel();
            this.mapa.cargarMapa();
            this.mapa = new Mapa();
            this.jugador = mapa.getJugador();
            this.paredes = mapa.getParedes();
            this.monedas = mapa.getMonedas();

        }
    }

    public static void incrementarPuntaje() {
        PUNTAJE++;
    }

    public void actualizar() {
        String direccion = this.control.getDireccion();
        // System.out.println(direccion);
        this.jugador.actualizarDireccion(direccion, this.paredes);
        this.jugador.mover(this.paredes);
        this.jugador.capturar(monedas);
        this.mapa.actualizar();
        this.sonido.reproducirSonido("juego");

        for (Caballero caballero : mapa.getCaballeros()) {
            if (this.jugador.detectarColisionCaballero(caballero)) {
                this.jugador.perderVida(this.mapa.getMapa());
            }
        }

        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        dibujar(g);
    }

    public void mostrarPuntaje(Graphics g) {
        g.fillRect(0, 0, 160, 30);
        g.setColor(Color.WHITE);
        g.drawString("Puntaje: " + PUNTAJE, 10, 20);
    }

    public void mostrarVidas(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawString("Vidas: " + this.jugador.getVidas(), 90, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.actualizar();
        this.repaint();
    }

    public void detenerJuego() {
        this.timer.stop();
    }

    public void mostrarMensajeGameOver(Graphics g) {
        if (this.jugador.getVidas() == 0) {
            g.setFont(g.getFont().deriveFont(80f));
            g.setColor(Color.red);

            g.drawString("GAME OVER", Ventana.ANCHO / 2 - 250, Ventana.ALTO / 2);

        }
    }

    public void mostrarMensajeGanar(Graphics g) {
        if (this.monedas.isEmpty()) {
            g.setFont(g.getFont().deriveFont(80f));
            g.setColor(Color.green);
            g.drawString("GANASTE", Ventana.ANCHO / 2 - 200, Ventana.ALTO / 2);
        }
    }

    public void dibujarFondo(Graphics g) {
        int nivel = Nivel.getNivel();

        if (nivel == 0) {
            g.setColor(Color.black);
        } else if (nivel == 1) {
            g.setColor(new Color(47, 53, 66));
        } else if (nivel == 2) {
            g.setColor(new Color(126, 81, 9));
        } else if (nivel == 3) {
            g.setColor(Color.GRAY);
        }

        g.fillRect(0, 0, Ventana.ANCHO, Ventana.ALTO);

    }

    //crear un metodo para reiniciae el juego cuan
}