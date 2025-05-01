package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {
    private String direccion;

    public Control() {
        this.direccion = "derecha";
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //control.setDireccion()

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        switch (tecla) {
            case KeyEvent.VK_LEFT:
                this.direccion = "izquierda";
                break;
            case KeyEvent.VK_RIGHT:
                this.direccion = "derecha";
                break;
            case KeyEvent.VK_UP:
                this.direccion = "arriba";
                break;
            case KeyEvent.VK_DOWN:
                this.direccion = "abajo";
                break;
            default:
                this.direccion = "cero";
                break;
        }
    }
   

}