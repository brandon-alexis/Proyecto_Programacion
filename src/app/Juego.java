package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import mapa.Mapa;
import motor.MotorJuego;
import ventana.Ventana;

public class Juego extends JPanel implements KeyListener, ActionListener {
    private MotorJuego motor;
    private Mapa mapa;

    public Juego() {
        this.setPreferredSize(new Dimension(Ventana.ANCHO, Ventana.ALTO));
        this.setFocusable(true);
        this.addKeyListener(this);

        this.motor = new MotorJuego(this);
        motor.iniciar();

        this.mapa = new Mapa();
    }

    public void dibujar(Graphics g) {
        this.setBackground(Color.BLACK);
        this.mapa.dibujar(g);
    }

    public void actualizar() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        dibujar(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actualizar();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("algo");
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
