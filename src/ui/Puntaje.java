package ui;

import java.awt.Color;
import java.awt.Graphics;

public class Puntaje {
    private int puntaje;

    public Puntaje() {  
        this.puntaje = 0;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Puntaje: " + puntaje, 10, 20);
    }

    public void actualizar() {

    }

    public int getPuntajer() {
        return puntaje;
    }

    public void incrementarPuntaje() {
        puntaje++;
    }
}
