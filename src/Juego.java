import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Juego extends JPanel implements KeyListener, ActionListener {
    private Jugador jugador;
    private static final int ANCHO = 700;
    private static final int ALTO = 700;

    Juego() {
        this.setPreferredSize(new Dimension(ANCHO, ALTO));
        this.setBackground(Color.BLACK);
        this.jugador = new Jugador(300, 300);
        this.setFocusable(true);
        this.addKeyListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[][] mapa = {
            {0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0}
        };

        int tamañoPared = 700 / mapa.length;

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                if (mapa[i][j] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillRect(tamañoPared * i, tamañoPared * j, tamañoPared, tamañoPared);
                }
            }
        }

        this.jugador.paintComponent(g);

        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.repaint();
        System.out.println("algo");
    }

    @Override
    public void keyPressed(KeyEvent e) {

       

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                this.jugador.moverArriba();

                break;
            case KeyEvent.VK_A:
                this.jugador.moverIzquierda();

                break;
            case KeyEvent.VK_S:
                this.jugador.moverAbajo();
                break;
            case KeyEvent.VK_D:
                this.jugador.moverDerecha();
                break;
            default:
                break;
        }

        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}