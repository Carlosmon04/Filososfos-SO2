package proyecto_so2;

import javax.swing.JTextArea;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofos extends Thread {
    private int id;
    private Tenedores izquierdo, derecho;
    private Silla silla;
    private String nombre;
    private Semaphore semaforo;
    private int maestros;
    private JTextArea outputArea;
    private boolean corriendo = true;

    public Filosofos(int id, Tenedores izquierdo, Tenedores derecho, Silla silla, String nombre, Semaphore semaforo, int maestros, JTextArea outputArea) {
        this.id = id;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.silla = silla;
        this.nombre = nombre;
        this.semaforo = semaforo;
        this.maestros = maestros;
        this.outputArea = outputArea;
    }

    public void detener() {
        corriendo = false;
    }

    @Override
    public void run() {
        while (corriendo) {
            try {
                pensar();
                semaforo.acquire();
                tomarTenedores();
                comer();
                dejarTenedores();
                semaforo.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pensar() throws InterruptedException {
        log("está pensando.");
        Thread.sleep(Proyecto_SO2.segundos);
    }

    private void tomarTenedores() throws InterruptedException {
        synchronized (izquierdo) {
            log("agarra tenedor " + izquierdo.getNombre() + "(" + izquierdo.getId() + ")");
            synchronized (derecho) {
                log("agarra tenedor " + derecho.getNombre() + "(" + derecho.getId() + ")");
            }
        }
    }

    private void comer() throws InterruptedException {
        Random rand = new Random();
        int bocados = rand.nextInt(10) + 1;
        log("está comiendo. Tiene " + bocados + " bocados.");
        while (bocados > 0) {
            log("da un bocado. Le quedan " + bocados + " bocados.");
            bocados--;
            Thread.sleep(Proyecto_SO2.segundos / 10);
        }
        log("terminó de comer.");
    }

    private void dejarTenedores() {
        log("deja los tenedores.");
    }

    private void log(String mensaje) {
        outputArea.append(nombre + " " + mensaje + "\n");
    }
}
