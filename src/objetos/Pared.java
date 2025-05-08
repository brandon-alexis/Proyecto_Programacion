package objetos;


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
        g.setColor(Color.GRAY);
        g.fillRect(this.x, this.y, this.ancho, this.alto);
        g.setColor(Color.BLACK);
        g.drawRect(this.x, this.y, this.ancho, this.alto);

    }

    public void actualizar() {

    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
