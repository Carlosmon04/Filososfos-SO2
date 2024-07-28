/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_so2;
import java.util.Random;
import java.lang.Thread;
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
             if(Filosofo==0) Fn="Socrates";
  if(Filosofo==1) Fn="Aristoteles";
  if(Filosofo==2) Fn="Valencio";
  if(Filosofo==3) Fn="Serapio";
  if(Filosofo==4) Fn="Serafin";
  if(Filosofo==5) Fn="Ruperegildo";
  if(Filosofo==6) Fn="Eusebio";
  if(Filosofo==7) Fn="Joaques";
  if(Filosofo==8) Fn="Lexor";
  if(Filosofo==9) Fn="Hanibal";
             try{
                 //Retraso de 5 segs antes de agarrar sillas
            Thread.sleep(Proyecto_SO2.segundos);
        }catch(Exception e)
        {e.printStackTrace();}
             
            s.qSilla(Filosofo,Fn);
            System.out.println("Filosofo"+Fn+"("+Filosofo+") esta pensando");
            //Retraso de 5 segs antes de agarrar Tenedores
             try{
            Thread.sleep(Proyecto_SO2.segundos);
        }catch(Exception e)
        {e.printStackTrace();}
             
    left.qTenedores(Filosofo,Fn);
    right.qTenedores(Filosofo,Fn);
    //comer
    //Retraso de 5 segs antes de comer
        try{
            Thread.sleep(Proyecto_SO2.segundos);
        }catch(Exception e)
        {e.printStackTrace();}
        
    Random r = new Random();
    int comidas = r.nextInt(10)+1;
 
     System.out.println("Filosofo "+Fn+"("+Filosofo+")"+"va comer "+comidas+" donas"); 
   while(comidas!=0){
        System.out.println("Filosofo "+Fn+"("+Filosofo+")"+" Bocado "+comidas);
         try{
            Thread.sleep(1000);
        }catch(Exception e)
        {e.printStackTrace();}
    comidas--;
   }
   //Retraso de 5 segundos despues de comer
    if(comidas==0){
    right.STenedores(Filosofo,Fn);
    left.STenedores(Filosofo,Fn);
     s.sSilla(Filosofo,Fn);
     try{
            Thread.sleep(Proyecto_SO2.segundos);
        }catch(Exception e)
        {e.printStackTrace();}
     //Retraso antes de soltar silla de 5 segs
    
    }
    
    
    
    //agarra silla
        }catch(InterruptedException e){
        e.printStackTrace();
        }
        //Fin Try-Catch
    }
    //fin While
    } 
}
