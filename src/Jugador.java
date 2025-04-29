import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;

public class Jugador extends JPanel {
    private int x;
    private int y;
    private final int ancho;
    private final int alto;
    private final int velocidad;

    Jugador(int x, int y) {
        this.x = x;
        this.y = y;
        this.ancho = 100;
        this.alto = 100;
        this.velocidad = 15;
    }

    public void moverDerecha() {
        this.x += this.velocidad;
    }

    public void moverIzquierda() {
        this.x -= this.velocidad;
    }

    public void moverArriba() {
        this.y -= this.velocidad;
    }

    public void moverAbajo() {
        this.y += this.velocidad;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
 
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, this.ancho, this.alto);
    }

    

    

}
