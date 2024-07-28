package proyecto_so2;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Filosofos extends Thread {
    private int id;
    private Tenedores tenedorIzquierdo;
    private Tenedores tenedorDerecho;
    private Silla silla;
    private String nombre;
    private Semaphore semaforo;
    private int totalFilosofos;
    private Plato plato;
    private JTextArea txtOutput;

    public Filosofos(int id, Tenedores tenedorIzquierdo, Tenedores tenedorDerecho, Silla silla, String nombre, Semaphore semaforo, int totalFilosofos, JTextArea txtOutput) {
        this.id = id;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
        this.silla = silla;
        this.nombre = nombre;
        this.semaforo = semaforo;
        this.totalFilosofos = totalFilosofos;
        this.plato = new Plato();
        this.txtOutput = txtOutput;
    }

    public void run() {
        try {
            while (true) {
                pensar();
                if (totalFilosofos > 1) {
                    semaforo.acquire();
                    tenedorIzquierdo.qTenedores(nombre, txtOutput);
                    tenedorDerecho.qTenedores(nombre, txtOutput);
                    comer();
                    tenedorDerecho.sTenedores(nombre, txtOutput);
                    tenedorIzquierdo.sTenedores(nombre, txtOutput);
                    semaforo.release();
                } else {
                    comer();
                }
                Thread.sleep(Proyecto_SO2.segundos);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pensar() throws InterruptedException {
        appendOutput(nombre + " está pensando.");
        Thread.sleep(Proyecto_SO2.segundos);
    }

    private void comer() throws InterruptedException {
        appendOutput(nombre + " está comiendo. Comida inicial en el plato: " + plato.getComida());
        while (plato.comerBocado()) {
            appendOutput(nombre + " da un bocado. Comida restante: " + plato.getComida());
            Thread.sleep(Proyecto_SO2.segundos / 10);
        }
        appendOutput(nombre + " ha terminado de comer.");
    }

    private void appendOutput(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtOutput.append(message + "\n");
            }
        });
    }
}
