package proyecto_so2;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Filosofos extends Thread {
    private int id;
    private Tenedores tenedorIzquierdo;
    private Tenedores tenedorDerecho;
    private Silla silla;
    private String nombre;
    private Semaphore tenedorSemaforo;
    private int maestros;
    private JTextArea txtOutput;
    private boolean running;
    private Plato plato;

    public Filosofos(int id, Tenedores tenedorIzquierdo, Tenedores tenedorDerecho, Silla silla, String nombre, Semaphore tenedorSemaforo, int maestros, JTextArea txtOutput) {
        this.id = id;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
        this.silla = silla;
        this.nombre = nombre;
        this.tenedorSemaforo = tenedorSemaforo;
        this.maestros = maestros;
        this.txtOutput = txtOutput;
        this.running = true;
        this.plato = new Plato();
    }

    public void detener() {
        running = false;
    }

    @Override
    public void run() {
        try {
            while (running) {
                pensar();
                tenedorSemaforo.acquire();
                tenedorIzquierdo.qTenedores(nombre, txtOutput);
                tenedorDerecho.qTenedores(nombre, txtOutput);
                comer();
                tenedorIzquierdo.sTenedores(nombre, txtOutput);
                tenedorDerecho.sTenedores(nombre, txtOutput);
                tenedorSemaforo.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        appendOutput(nombre + " está pensando...");
        Thread.sleep(Proyecto_SO2.segundos);
    }

    private void comer() throws InterruptedException {
        appendOutput(nombre + " está comiendo...");
        while (plato.comerBocado() && running) {
            appendOutput(nombre + " da un bocado. Comida restante: " + plato.getComida());
            Thread.sleep(Proyecto_SO2.segundos);
        }
        appendOutput(nombre + " ha terminado de comer.");
    }

    private void appendOutput(String message) {
        SwingUtilities.invokeLater(() -> txtOutput.append(message + "\n"));
    }
}
