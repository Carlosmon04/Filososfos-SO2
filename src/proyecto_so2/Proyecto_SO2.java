package proyecto_so2;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import javax.swing.JTextArea;

public class Proyecto_SO2 {
    public static int maestros;
    public static int segundos;
    public static String[] nombresFilosofos = {
        "Socrates", "Aristoteles", "Valencio", "Serapio",
        "Serafin", "Ruperegildo", "Eusebio", "Joaques",
        "Lexor", "Hanibal"
    };

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        Scanner seg = new Scanner(System.in);
        System.out.println("Antes de Empezar indica que tantos segundos quieres que tarden los filosofos en hacer acciones ");
        segundos = (seg.nextInt() * 1000);
        while (segundos < 0) {
            System.out.println("No puede Ingresar Valores negativos, Intente de nuevo");
            segundos = (seg.nextInt() * 1000);
        }
        System.out.println("");
        System.out.println("Ingrese la cantidad de Filósofos que quieren participar ");
        maestros = leer.nextInt();
        while (maestros < 1 || maestros > 10) {
            System.out.println("Solo pueden participar de 1 a 10 Filósofos, Intente de nuevo ");
            maestros = leer.nextInt();
        }

        Silla s = new Silla();
        Semaphore tenedorSemaforo = new Semaphore(maestros - 1);
        Tenedores[] cubiertos = new Tenedores[maestros];
        Filosofos[] f = new Filosofos[maestros];

        for (int i = 0; i < cubiertos.length; i++) {
            String nombreR = "Derecho";
            String nombreL = "Izquierdo";
            String fName = (i % 2 == 0) ? nombreR : nombreL;
            cubiertos[i] = new Tenedores(i, fName);
        }

        JTextArea txtOutput = new JTextArea();

        for (int k = 0; k < f.length; k++) {
            String nombre = nombresFilosofos[k];
            f[k] = new Filosofos(k, cubiertos[k], cubiertos[(k + 1) % maestros], s, nombre, tenedorSemaforo, maestros, txtOutput);
        }

        for (int j = 0; j < f.length; j++) {
            f[j].start();
            if ((j + 1) % 4 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
