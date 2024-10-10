/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tpintegrador;


public class TpIntegrador {

    public static void main(String[] args) {
        
        /*
        
        usar camelCase
        nombres de variables descriptivos
        describir con un comentario arriba de cada funcion que es lo que hace la funcion 
        
        
        */
        
        //Conecto a la base de datos 
        ConexionDB conDB = new ConexionDB();
        conDB.conectarDB();
        
        
        //-------------
        
        //Llamo a la interfaz inicial
        Interfaz inter=new Interfaz();
        inter.setVisible(true);//Muestra la interfaz en la pnatalla
        inter.setLocationRelativeTo(null);//Hace que su ubicación no sea relativa a nada, es decir, que la pone en el medio de la pantalla
    }
    
}
