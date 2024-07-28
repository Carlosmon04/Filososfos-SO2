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
  
  public synchronized void qTenedores (int i,String Fn)
    throws InterruptedException{
  while(!libre)
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
      System.out.println("Filosofo "+Fn+"("+i+")"  + " Agarra Tenedor "+ f+"("+tenedor+")");
      System.out.println("");
      libre=false;
  }  
  
  public synchronized void STenedores(int i, String Fn){
  
      libre=true;  
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
     
      System.out.println("Filosofo "+Fn+"(" +i+")"+" Suelta el Tenedor "+ f+"("+tenedor+")");
      System.out.println("");
      notify();
     
  }
  
}
