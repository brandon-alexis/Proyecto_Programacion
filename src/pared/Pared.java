package pared;

import java.awt.Color;
import java.awt.Graphics;

public class Pared {
    private int ancho;
    private int alto;
    private int x;
    private int y;
    
    public Pared(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;        
        this.ancho = ancho;
        this.alto = alto;
    }
    
    public void dibujar(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(this.x, this.y, this.ancho, this.alto);
        g.setColor(Color.WHITE);
        g.drawRect(this.x, this.y, this.ancho, this.alto);

    }

    public void actualizar() {

    }




}
