/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_so2;
import java.util.Scanner;

/**
 *
 * @author Carlosmon04
 */
public class Proyecto_SO2 {
    public static int maestros;
    public static int segundos;
    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner leer = new Scanner(System.in);
        Scanner seg = new Scanner(System.in);
        System.out.println("Antes de Empezar indica que tantos segundos quieres que tarden los filosofos en hacer acciones ");
        segundos=(seg.nextInt() * 1000);
        while(segundos<0){
            System.out.println("No puede Ingresar Valores negativos, Intente de nuevo");
                    segundos=(seg.nextInt() * 1000);

        }
        System.out.println("");
        System.out.println("Ingrese la cantidad de FIlosofos que quieren participar ");
        maestros = leer.nextInt();
       while(maestros <0  || maestros >10 )
       {
           System.out.println("Solo pueden participar de 1 a 10 Filosofos, Intente de nuevo ");
                   maestros = leer.nextInt();

       }
        Silla s= new Silla();
       Tenedores[] cubiertos = new Tenedores[maestros];
       for(int i =0;i<cubiertos.length;i++){
           String nombreR= "Derecho";
           String nombreL="Izquierdo";
           String f;
           if(i%2==0){
          f=nombreR;
                     }
           else{
           f=nombreL;
           }
       cubiertos[i]= new Tenedores(i,f);
       }
       Filosofos[] f=new Filosofos[maestros];
       for(int k=0;k<f.length;k++){
       f[k]=new Filosofos(k,cubiertos[k],cubiertos[(k+1)%maestros],s,"Fn");      
       }
       for(int j=0;j<f.length;j++){
           f[j].start();
       }
    }
    
    
    
}




