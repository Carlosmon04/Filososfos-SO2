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
    private Tenedores  left,right;
    private Silla s;
    public Filosofos (int Filosofo, Tenedores left, Tenedores right, Silla s){
    this.Filosofo= Filosofo;
    this.left=left;
    this.right=right;  
    this.s=s;
    }
    
    public void run(){
    while(true){
        try{
            s.qSilla(Filosofo);
    left.qTenedores(Filosofo);
    right.qTenedores(Filosofo);
    //comer
    right.STenedores(Filosofo);
    left.STenedores(Filosofo);
    s.sSilla(Filosofo);
        }catch(InterruptedException e){
        e.printStackTrace();
        }
        //Fin Try-Catch
    }
    //fin While
    } 
}
