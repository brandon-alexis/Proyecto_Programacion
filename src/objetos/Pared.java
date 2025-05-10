package objetos;


import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;

public class Pared {
    private int ancho;
    private int alto;
    private int x;
    private int y;
    private ImageIcon imagenPared;
    private URL urlPared;
    
    public Pared(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;        
        this.ancho = ancho;
        this.alto = alto;
        this.urlPared = Pared.class.getResource("../recursos/imagenes/pared.png");
        this.imagenPared = new ImageIcon(urlPared);
    }
    
    public void dibujar(Graphics g) {
        g.drawImage(imagenPared.getImage(), this.x, this.y, ancho, alto, null);
        g.setColor(Color.BLACK);
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
