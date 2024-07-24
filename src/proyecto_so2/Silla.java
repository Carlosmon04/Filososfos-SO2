/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_so2;

/**
 *
 * @author Carlosmon04
 */

public class Silla {
    private int slibre=(Proyecto_SO2.maestros-1);
    
    public synchronized void qSilla(int i) 
            throws InterruptedException{
    
    while(slibre==0)
        wait();
        System.out.println("Filosofo "+i+" Toma Silla ");
        slibre--;
    }
    
    public synchronized void sSilla(int i){
    slibre++;
        System.out.println("Filosofo "+i+" Suelta Silla");
        notify();
    }
}
