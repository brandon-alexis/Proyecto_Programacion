package motor;

import app.Juego;

public class MotorJuego implements Runnable {

    private Juego juego;
    private Thread hilo;
    private boolean enEjecucion;

    public MotorJuego(Juego juego) {
        this.juego = juego;
    }

    public void iniciar() {
        if (enEjecucion) return;

        enEjecucion = true;
        hilo = new Thread(this);
        hilo.start();
    }

    public void detener() {
        enEjecucion = false;
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // Bucle de juego (60 FPS)
        long objetivoTiempo = 1000000000 / 60; // nanosegundos por frame

        while (enEjecucion) {
            long inicio = System.nanoTime();

            juego.actualizar();
            juego.repaint(); // Llama a paintComponent()

            long duracion = System.nanoTime() - inicio;
            long descanso = (objetivoTiempo - duracion) / 1000000; // en milisegundos

            if (descanso < 0) descanso = 2;

            try {
                Thread.sleep(descanso);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

