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
    
    private int slibre=(Proyecto_SO2.maestros);
    
    public synchronized void qSilla(int i,String Fn) 
            throws InterruptedException{
    
    while(slibre==0)
        wait();
     if(i==0) Fn="Socrates";
  if(i==1) Fn="Aristoteles";
  if(i==2) Fn="Valencio";
  if(i==3) Fn="Serapio";
  if(i==4) Fn="Serafin";
  if(i==5) Fn="Ruperegildo";
  if(i==6) Fn="Eusebio";
  if(i==7) Fn="Joaques";
  if(i==8) Fn="Lexor";
  if(i==9) Fn="Hanibal";
        System.out.println("Filosofo "+Fn+"("+i+")"+" Toma Silla "+slibre);
        System.out.println("");
        slibre--;
    }
    
    public synchronized void sSilla(int i,String Fn){
   
     if(i==0) Fn="Socrates";
  if(i==1) Fn="Aristoteles";
  if(i==2) Fn="Valencio";
  if(i==3) Fn="Serapio";
  if(i==4) Fn="Serafin";
  if(i==5) Fn="Ruperegildo";
  if(i==6) Fn="Eusebio";
  if(i==7) Fn="Joaques";
  if(i==8) Fn="Lexor";
  if(i==9) Fn="Hanibal";
        System.out.println("Filosofo "+Fn+"("+i+")"+" Suelta Silla"+slibre);
        System.out.println("");
         slibre++;
        notify();
    }
}
