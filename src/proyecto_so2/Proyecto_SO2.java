package proyecto_so2;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Carlosmon04
 */
public class Proyecto_SO2 {
    public static int maestros;
    public static int segundos;
    private static final String[] nombresFilosofos = {
        "Socrates", "Aristoteles", "Valencio", "Serapio", "Serafin",
        "Ruperegildo", "Eusebio", "Joaques", "Lexor", "Hanibal"
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner leer = new Scanner(System.in);
        System.out.println("Antes de Empezar indica que tantos segundos quieres que tarden los filosofos en hacer acciones ");
        segundos = (leer.nextInt() * 1000);
        while (segundos < 0) {
            System.out.println("No puede Ingresar Valores negativos, Intente de nuevo");
            segundos = (leer.nextInt() * 1000);
        }

        System.out.println("Ingrese la cantidad de Filosofos que quieren participar ");
        maestros = leer.nextInt();
        while (maestros < 1 || maestros > 10) {
            System.out.println("Solo pueden participar de 1 a 10 Filosofos, Intente de nuevo ");
            maestros = leer.nextInt();
        }

        Silla s = new Silla();
        Semaphore tenedorSemaforo = new Semaphore(maestros - 1); // Controla acceso a los tenedores

        Tenedores[] cubiertos = new Tenedores[maestros];
        inicializarTenedores(cubiertos);

        Filosofos[] f = new Filosofos[maestros];
        inicializarFilosofos(f, cubiertos, s, tenedorSemaforo);

        for (int j = 0; j < f.length; j++) {
            f[j].start();
            // Agregar delay por cada 4 hilos
            if ((j + 1) % 4 == 0) {
                try {
                    Thread.sleep(1000); // 1000 milisegundos = 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void inicializarTenedores(Tenedores[] cubiertos) {
        for (int i = 0; i < cubiertos.length; i++) {
            String nombreR = "Derecho";
            String nombreL = "Izquierdo";
            String f = (i % 2 == 0) ? nombreR : nombreL;
            cubiertos[i] = new Tenedores(i, f);
        }
    }

    private static void inicializarFilosofos(Filosofos[] f, Tenedores[] cubiertos, Silla s, Semaphore semaforo) {
        for (int k = 0; k < f.length; k++) {
            String nombre = nombresFilosofos[k];
            f[k] = new Filosofos(k, cubiertos[k], cubiertos[(k + 1) % maestros], s, nombre, semaforo, maestros);
        }
    }
}
