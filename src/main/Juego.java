package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.Timer;

import control.ControlJuego;
import enemigos.Caballero;
import graficos.Ventana;
import jugadores.Duende;
import mapa.Mapa;
import objetos.Pared;



public class Juego extends JPanel implements ActionListener {
    private Mapa mapa;
    private Duende jugador;
    private Timer timer;
    private ControlJuego control;
    private HashSet<Pared> paredes;
 

    public Juego() {

        this.mapa = new Mapa();
        this.jugador = mapa.getJugador();
        this.paredes = mapa.getParedes();
        this.control = new ControlJuego();
        this.timer = new Timer(Ventana.FRAME, this);
        timer.start();
        
        this.setPreferredSize(new Dimension(Ventana.ALTO, Ventana.ANCHO));

        this.addKeyListener(control);
    }

    public void dibujar(Graphics g) {
        this.setBackground(Color.BLACK);
        this.mapa.dibujar(g);
        this.jugador.dibujar(g);
    }

    public void actualizar() {
        String direccion = this.control.getDireccion();
        System.out.println(direccion);
        this.jugador.actualizarDireccion(direccion, this.paredes);
        this.jugador.mover(this.paredes);
        this.mapa.actualizar();
        System.out.printf("dx: %d | dy: %d %n", this.jugador.getVelocidadX(), this.jugador.getVelocidadY());
        //this.jugador.actualizarDireccion(this.paredes);

    
        for (Caballero caballero: mapa.getCaballeros()) {
            if (this.jugador.detectarColisionCaballero(caballero)) {
                //timer.stop();
            }
        }

        this.repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        dibujar(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.actualizar();
        this.repaint();
    }
}
