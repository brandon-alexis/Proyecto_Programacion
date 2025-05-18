package util;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonido {
    private Map<String, Clip> sonidos;
    private String[][] rutas;

    // Método constructor
    public Sonido() {
        sonidos = new HashMap<>();
        // array nombre del sonido, ruta del sonido(de tener la extension .wav)
        rutas = new String[][] {
                { "juego", "juego_fondo_sonido.wav" },
                { "moneda", "moneda_sonido.wav" },
                { "ganar_juego", "juego_victoria.wav" },
                { "perder_juego", "juego_gameover.wav" }
        };

        cargarSonidos();
    }

    // Método para cargar todos los sonidos
    private void cargarSonidos() {
        for (int i = 0; i < rutas.length; i++) {
            try {
                URL soundUrl = Sonido.class.getResource(String.format("../recursos/sonido/%s", rutas[i][1]));
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundUrl);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                sonidos.put(rutas[i][0], clip);

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println("Error al cargar el sonido de la ruta '" + rutas[i][1] + "': " + e.getMessage());
                sonidos.put(rutas[i][0], null);

            }
        }
    }

    // Método para reproducir un sonido
    public void reproducirSonido(String pista) {
        Clip clip = sonidos.get(pista);
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0); // Reinicia el sonido desde el inicio
            clip.start();
        } else {
            // System.out.println("Sonido no encontrado: " + pista);
        }
    }

    // Método para reproducir un sonido en bucle
    public void loopSonido(String name) {
        Clip clip = sonidos.get(name);
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            System.out.println("Sonido no encontrado: " + name);
        }
    }

    // Método para detener un sonido
    public void detenerSonido(String name) {
        Clip clip = sonidos.get(name);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // Método para liberar los recursos de todos los sonidos
    public void closeAll() {
        for (Clip clip : sonidos.values()) {
            clip.close();
        }
    }

    /**
     * Ajusta el volumen de un sonido específico.
     * 
     * @param name    Nombre del sonido.
     * @param volumen Valor de volumen entre 0.0 (silencio) y 1.0 (máximo).
     */
    public void setVolumen(String name, float volumen) {
        Clip clip = sonidos.get(name);
        if (clip != null) {
            try {
                FloatControl control = (FloatControl) clip
                        .getControl(javax.sound.sampled.FloatControl.Type.MASTER_GAIN);
                float min = control.getMinimum();
                float max = control.getMaximum();
                float gain = min + (max - min) * volumen;
                control.setValue(gain);
            } catch (IllegalArgumentException e) {
                System.out.println("Control de volumen no soportado para: " + name);
            }
        }
    }

    // Método para reproducir un sonido múltiples veces simultáneamente con volumen
    public void reproducirSonidoMultiple(String name, float volumen) {
        String ruta = null;

        // Buscar la ruta original del sonido
        for (String[] par : rutas) {
            if (par[0].equals(name)) {
                ruta = par[1];
                break;
            }
        }

        if (ruta == null) {
            System.out.println("Ruta no encontrada para: " + name);
            return;
        }

        try {
            

            URL soundUrl = Sonido.class.getResource(String.format("../recursos/sonido/%s", ruta));


            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundUrl);
            Clip nuevoClip = AudioSystem.getClip();
            nuevoClip.open(audioInput);

            // Ajustar volumen
            try {
                FloatControl control = (FloatControl) nuevoClip.getControl(FloatControl.Type.MASTER_GAIN);
                float min = control.getMinimum();
                float max = control.getMaximum();
                float gain = min + (max - min) * volumen;
                control.setValue(gain);
            } catch (IllegalArgumentException e) {
                System.out.println("Control de volumen no soportado para: " + name);
            }

            nuevoClip.start();

            // Liberar recursos al terminar
            nuevoClip.addLineListener(event -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    nuevoClip.close();
                }
            });

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error al reproducir sonido múltiple de " + name + ": " + e.getMessage());
        }
    }

}
