package main;

import graficos.Ventana;

public class App {
    private Ventana ventana;

    public App() {
        this.ventana = new Ventana();
        this.ventana.setVisible(true);
    }
    public static void main(String[] args) throws Exception {
        new App();
    }

    

    
}
