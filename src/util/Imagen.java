package util;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class Imagen {
    private static ImageIcon imagenOriginal;
    private static ImageIcon imagen;
    private static URL urlImagen;

    // Metodo para crear imagenes pasandole la ruta
    public static ImageIcon cargar(String url, int ancho, int alto, int scala) {
        urlImagen = Imagen.class.getResource(String.format("../%s", url));
        imagenOriginal = new ImageIcon(urlImagen);
        Image imagenEscalada = imagenOriginal
            .getImage()
            .getScaledInstance(ancho * scala, alto * scala, Image.SCALE_SMOOTH);
        imagen = new ImageIcon(imagenEscalada);
        return imagen;
    }

}
