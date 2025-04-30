package app;

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

import caballero.Caballero;
import jugador.Jugador;
import mapa.Mapa;
import pared.Pared;
import ventana.Ventana;

public class Juego extends JPanel implements KeyListener, ActionListener {
    private Mapa mapa;
    private Jugador jugador;
    private Caballero caballero;
    private Timer timer;
    

    public Juego() {
        this.setPreferredSize(new Dimension(Ventana.ANCHO, Ventana.ALTO));
        this.setFocusable(true);
        this.addKeyListener(this);
        
        

  

        this.mapa = new Mapa();
        this.jugador = new Jugador(Ventana.TAMAÑO_BLOQUE * 2, Ventana.TAMAÑO_BLOQUE * 3);
        this.caballero = new Caballero(Ventana.TAMAÑO_BLOQUE * 5, Ventana.TAMAÑO_BLOQUE * 5);
        this.timer = new Timer(16, this);
        timer.start();
    }

    public void dibujar(Graphics g) {
        this.setBackground(Color.BLACK);
        this.mapa.dibujar(g);
        this.jugador.dibujar(g);
        this.caballero.dibujar(g);
        
    }

    public void actualizar() {
       this.mapa.actualizar();
        this.jugador.actualizar();
        

        for (Pared pared: this.mapa.getParedes()){
            if (this.jugador.detectarColisionPared(pared)) {
                System.out.println("toco pared");
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
    public void keyPressed(KeyEvent e) {
        this.jugador.presionalTecla(e.getKeyChar());



    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.jugador.soltarTecla(e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.actualizar();
        this.repaint();
    }

}
