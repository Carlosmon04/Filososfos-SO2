package proyecto_so2;

import javax.swing.SwingUtilities;

public class Proyecto_SO2 {
    public static int maestros;
    public static int segundos;
    public static String[] nombresFilosofos = {
        "Socrates", "Aristoteles", "Valencio", "Serapio", "Serafin",
        "Ruperegildo", "Eusebio", "Joaques", "Lexor", "Hanibal"
    };

    public static void main(String[] args) {
        // Iniciar la interfaz gráfica
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FilosofosGUI frame = new FilosofosGUI();
                frame.setVisible(true);
            }
        });
    }
}
