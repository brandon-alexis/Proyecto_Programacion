package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import jugador.Jugador;
import mapa.Mapa;
import ventana.Ventana;

public class Juego extends JPanel implements KeyListener, ActionListener {
    private Mapa mapa;
    private Jugador jugador;
    private Timer timer;
    

    public Juego() {
        this.setPreferredSize(new Dimension(Ventana.ANCHO, Ventana.ALTO));
        this.setFocusable(true);
        this.addKeyListener(this);
        
        

  

        this.mapa = new Mapa();
        this.jugador = new Jugador(Ventana.TAMAÑO_BLOQUE * 2, Ventana.TAMAÑO_BLOQUE * 3);
        this.timer = new Timer(16, this);
        timer.start();
    }

    public void dibujar(Graphics g) {
        this.setBackground(Color.BLACK);
        this.mapa.dibujar(g);
        this.jugador.dibujar(g);
    }

    public void actualizar() {
       this.mapa.actualizar();
        this.jugador.actualizar();
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
