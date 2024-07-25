/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_so2;

/**
 *
 * @author Carlosmon04
 */
public class Filosofos extends Thread {
    private int Filosofo;
    private String Fn;
    private Tenedores  left,right;
    private Silla s;
    public Filosofos (int Filosofo, Tenedores left, Tenedores right, Silla s,String Fn){
    this.Filosofo= Filosofo;
    this.left=left;
    this.right=right;  
    this.s=s;
    this.Fn=Fn;
    }
    
    public void run(){
    while(true){
        try{
            s.qSilla(Filosofo,Fn);
    left.qTenedores(Filosofo,Fn);
    right.qTenedores(Filosofo,Fn);
    //comer
    right.STenedores(Filosofo,Fn);
    left.STenedores(Filosofo,Fn);
    s.sSilla(Filosofo,Fn);
        }catch(InterruptedException e){
        e.printStackTrace();
        }
        //Fin Try-Catch
    }
    //fin While
    } 
}
