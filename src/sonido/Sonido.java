package sonido;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonido {
private Map <String,Clip> sonidos;
private String [] []rutas;

//Método constructor
public Sonido (){
    sonidos= new HashMap<>();
    //array nombre del sonido, ruta del sonido(de tener la extension .wav)
    rutas = new String[][]{{"juego","sonido/1UP-_16-Bit-Arcade-No-Copyright-Music_.wav"},
                
              };

    cargarSonidos();
}
// Método para cargar todos los sonidos 
private void cargarSonidos(){
    for (int i=0;i<rutas.length;i++){
        try {
            File soundFile = new File(rutas[i][1]);
            if (!soundFile.exists()) {
                System.out.println("Archivo no encontrado: " + rutas[i][1]);
                continue;
            }
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            sonidos.put(rutas[i][0],clip);
           
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error al cargar el sonido de la ruta '" +rutas[i][1] + "': " + e.getMessage());
            sonidos.put(rutas[i][0],null);
        }
    }
  

    
}
// Método para reproducir un sonido 
public void reproducirSonido(String pista){
    Clip clip = sonidos.get(pista);
    if (clip != null&& !clip.isRunning()) {
        clip.setFramePosition(0);  // Reinicia el sonido desde el inicio
        clip.start();
    } else {
        //System.out.println("Sonido no encontrado: " + pista);
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

}
 