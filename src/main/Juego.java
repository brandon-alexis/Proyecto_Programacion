package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import control.Control;
import enemigos.Caballero;
import graficos.Ventana;
import jugadores.Duende;
import mapa.Mapa;
import objetos.Pared;



public class Juego extends JPanel implements ActionListener {
    private Mapa mapa;
    private Duende jugador;
    private Timer timer;
    private Control control;
 

    public Juego() {

        this.mapa = new Mapa();
        this.jugador = mapa.getJugador();
        this.control = new Control();
        this.timer = new Timer(30, this);
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
        this.mapa.actualizar();
        this.jugador.actualizar(this.control.getDireccion());
        
      
        
     
    
    



        // estrellar cuando el jugador toca pared
        for (Pared pared: mapa.getParedes()) {
            if (this.jugador.detectarColisionPared(pared)) {
                System.out.println("colision con bloque");
                break;               
                
            }

        }
        
      
    


        // extrellar cuando el jugador toca caballero
        for (Caballero caballero: mapa.getCaballeros()) {
            if (this.jugador.detectarColisionCaballero(caballero)) {
                timer.stop();
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

    


// dame la linea para detectar cuando el jugador toca la pared tenga que cambiar de direccion
}
// dame la linea para detectar cuando el jugador toca la pared tenga q  