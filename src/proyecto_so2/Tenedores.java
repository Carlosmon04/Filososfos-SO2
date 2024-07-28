package proyecto_so2;

import javax.swing.*;

public class Tenedores {
    private int tenedor;
    private String lado;
    private boolean libre;

    public Tenedores(int tenedor, String lado) {
        this.tenedor = tenedor;
        this.lado = lado;
        this.libre = true;
    }

    public synchronized void qTenedores(String nombreFilosofo, JTextArea txtOutput) throws InterruptedException {
        while (!libre) {
            wait();
        }
        libre = false;
        appendOutput(txtOutput, nombreFilosofo + " agarra tenedor " + lado + " (" + tenedor + ")");
    }

    public synchronized void sTenedores(String nombreFilosofo, JTextArea txtOutput) {
        libre = true;
        appendOutput(txtOutput, nombreFilosofo + " suelta tenedor " + lado + " (" + tenedor + ")");
        notify();
    }

    private void appendOutput(JTextArea txtOutput, String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtOutput.append(message + "\n");
            }
        });
    }
}
