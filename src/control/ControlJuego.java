package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControlJuego extends KeyAdapter {
    public static final String ARRIBA = "arriba";
    public static final String ABAJO = "abajo";
    public static final String IZQUIERDA = "izquierda";
    public static final String DERECHA = "derecha";
    private String direccion;

    public ControlJuego() {
        this.direccion = DERECHA;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        switch (tecla) {
            case KeyEvent.VK_LEFT:
                this.direccion = IZQUIERDA;
                break;
            case KeyEvent.VK_RIGHT:
                this.direccion = DERECHA;
                break;
            case KeyEvent.VK_UP:
                this.direccion = ARRIBA;
                break;
            case KeyEvent.VK_DOWN:
                this.direccion = ABAJO;
                break;
            default:
                this.direccion = "cero";
                break;
        }
    }
   

}