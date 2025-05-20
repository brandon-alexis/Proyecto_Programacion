package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import control.ControlJuego;
import enemigos.Caballero;
import graficos.Ventana;
import jugadores.Duende;
import mapa.Mapa;
import mapa.Nivel;
import objetos.Moneda;
import objetos.Pared;
import util.Imagen;
import util.Sonido;

public class Juego extends JPanel implements ActionListener {
    public static int puntaje;
    private Mapa mapa;
    private Duende jugador;
    private Timer timer;
    private ControlJuego control;
    private HashSet<Pared> paredes;
    private HashSet<Moneda> monedas;
    private Sonido sonido;
    public final int ESTADO_JUEGO_JUGANDO = 0;
    public final int ESTADO_JUEGO_GANAR = 1;
    public final int ESTADO_JUEGO_PERDER = 2;
    public int estadoJuego;

    public Juego() {
        this.mapa = new Mapa();
        this.jugador = mapa.getJugador();
        this.paredes = mapa.getParedes();
        this.monedas = mapa.getMonedas();
        this.control = new ControlJuego(this);
        this.timer = new Timer(Ventana.FRAME, this);
        this.sonido = new Sonido();
        this.estadoJuego = ESTADO_JUEGO_JUGANDO;

        puntaje = 0;
        timer.start();

        this.setPreferredSize(new Dimension(Ventana.ALTO, Ventana.ANCHO));

        this.addKeyListener(control);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        dibujar(g);
    }

    public void dibujar(Graphics g) {
        dibujarFondo(g);
        mapa.dibujar(g);
        jugador.dibujar(g);
        mostrarPuntaje(g);
        mostrarVidas(g);

        if (estadoJuego == ESTADO_JUEGO_PERDER) {
            mostrarMensajeGameOver(g);
        } else if (estadoJuego == ESTADO_JUEGO_GANAR) {
            mostrarMensajeGanar(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (estadoJuego == ESTADO_JUEGO_JUGANDO) {
            this.actualizar();
            this.repaint();
        }
    }

    private void dibujarFondo(Graphics g) {
        int nivel = Nivel.getNivel();

        switch (nivel) {
            case 0 -> g.setColor(Color.black);
            case 1 -> g.setColor(new Color(47, 53, 66));
            case 2 -> g.setColor(new Color(245, 245, 220));
            case 3 -> g.setColor(Color.GRAY);
            default -> g.setColor(Color.BLACK);
        }

        g.fillRect(0, 0, Ventana.ANCHO, Ventana.ALTO);
    }

    public void actualizar() {
        String direccion = this.control.getDireccion();
        this.jugador.actualizarDireccion(direccion, this.paredes);
        this.jugador.mover(this.paredes);
        this.jugador.capturarMoneda(monedas);
        this.mapa.actualizar();
        this.sonido.setVolumen("juego", 0.7f);
        this.sonido.reproducirSonido("juego");
        this.sonido.loopSonido("juego");

        for (Caballero caballero : mapa.getCaballeros()) {
            if (this.jugador.detectarColisionCaballero(caballero)) {
                this.jugador.perderVida(this.mapa.getMapa());
            }
        }

        if (this.jugador.getVidas() <= 0) {
            this.estadoJuego = ESTADO_JUEGO_PERDER;
            this.timer.stop();
            reproducirSonidoGameOver();
        }

        if (this.monedas.isEmpty() && Nivel.getNivel() < Nivel.NIVEL_MAXIMO) {
            this.estadoJuego = ESTADO_JUEGO_GANAR;
            this.timer.stop();
            reproducirSonidoGanar();
        }

        if (this.monedas.isEmpty() && Nivel.getNivel() == Nivel.NIVEL_MAXIMO) {
            System.exit(0);
        }
    }

    public static void incrementarPuntaje() {
        puntaje++;
    }

    public void mostrarPuntaje(Graphics g) {
        ImageIcon imagenPuntaje = Imagen.cargar("moneda.png", 16, 16, 3);
        g.fillRect(0, 0, 120, 30);
        g.drawImage(imagenPuntaje.getImage(), -10, -12, null);
        g.setColor(Color.WHITE);
        g.drawString(String.format("x %d", puntaje), 30, 20);
    }

    public void mostrarVidas(Graphics g) {
        int vidas = this.jugador.getVidas();
        ImageIcon imagenVida = Imagen.cargar("vida.png", 16, 16, 3);
        g.drawImage(imagenVida.getImage(), 50, -12, null);
        g.setColor(Color.WHITE);
        g.drawString(String.format("x %d", vidas), 90, 20);
    }

    private void mostrarMensajeGameOver(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, Ventana.ANCHO, Ventana.ALTO);
        g.setFont(g.getFont().deriveFont(80f));
        g.setColor(Color.RED);
        g.drawString("GAME OVER", Ventana.ANCHO / 2 - 250, Ventana.ALTO / 2);
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString("Presiona R para reiniciar o Q para salir", Ventana.ANCHO / 2 - 250, Ventana.ALTO / 2 + 50);;
        reproducirSonidoGameOver();
    }

    private void reproducirSonidoGameOver() {
        this.sonido.detenerSonido("juego");
        this.sonido.reproducirSonido("perder_juego");
        this.sonido.setVolumen("perder_juego", 0.7f);
    }

    private void mostrarMensajeGanar(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, Ventana.ANCHO, Ventana.ALTO);
        g.setFont(g.getFont().deriveFont(80f));
        g.setColor(Color.green);
        g.drawString("¡GANASTE!", Ventana.ANCHO / 2 - 200, Ventana.ALTO / 2);
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString("Presiona Enter para continuar o Q para salir", Ventana.ANCHO / 2 - 280,
                Ventana.ALTO / 2 + 50);
        reproducirSonidoGanar();
    }

    private void reproducirSonidoGanar() {
        this.sonido.detenerSonido("juego");
        this.sonido.reproducirSonido("ganar_juego");
        this.sonido.setVolumen("ganar_juego", 0.7f);
    }

    public void reiniciarNivel() {
        puntaje = 0;
        this.mapa = new Mapa();
        this.mapa.cargarMapa();
        this.jugador = mapa.getJugador();
        this.jugador.setVidas(3);
        this.paredes = mapa.getParedes();
        this.monedas = mapa.getMonedas();
        this.estadoJuego = ESTADO_JUEGO_JUGANDO;
        this.timer.restart();
        this.repaint();
    }

    public void pasarAlSiguienteNivel() {
        puntaje = 0;
        Nivel.pasarNivel();
        this.mapa = new Mapa();
        this.jugador = mapa.getJugador();
        this.paredes = mapa.getParedes();
        this.monedas = mapa.getMonedas();
        this.estadoJuego = ESTADO_JUEGO_JUGANDO;
        this.timer.start();
        this.repaint();
    }

}