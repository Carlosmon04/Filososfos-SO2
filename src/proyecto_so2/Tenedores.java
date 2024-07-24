/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_so2;

/**
 *
 * @author Carlosmon04
 */
public class Tenedores {
    private int tenedor;
    private boolean libre = true;
    
    
    
  public Tenedores (int tenedor){
  this.tenedor=tenedor;
  }
  
  public synchronized void qTenedores (int i)
    throws InterruptedException{
  while(!libre)
      wait();
      System.out.println("Filosofo "+i+" Agarra Tenedor "+tenedor);
      libre=false;
  }  
  
  public synchronized void STenedores(int i){
  libre=true;
      System.out.println("Filosofo "+i+" Suelta el Tenedor "+tenedor);
      notify();
  }
  
}
