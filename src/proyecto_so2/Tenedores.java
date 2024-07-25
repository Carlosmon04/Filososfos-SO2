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
    private String f;
    private boolean libre = true;
    
    
    
  public Tenedores (int tenedor, String f){
  this.tenedor=tenedor;
  this.f=f;
  }
  
  public synchronized void qTenedores (int i)
    throws InterruptedException{
  while(!libre)
      wait();
  
      System.out.println("Filosofo "+i+" Agarra Tenedor "+ f+"("+tenedor+")");
  
      libre=false;
  }  
  
  public synchronized void STenedores(int i){
  libre=true;
      System.out.println("Filosofo "+i+" Suelta el Tenedor "+ f+"("+tenedor+")");
      notify();
  }
  
}
