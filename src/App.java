import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame ventana = new JFrame("Juego");
        Juego juego = new Juego();

        ventana.add(juego);
        ventana.setSize(400, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);

        ventana.pack();
        ventana.setVisible(true);
    }

    
}
