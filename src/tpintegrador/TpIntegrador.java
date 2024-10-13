/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tpintegrador;

import java.util.InputMismatchException;
import java.util.Scanner;

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
        conDB.mostrarTabla("tb_dfsurnos");
        //conDB.modificarRegistro("tb_Turnos");
        //conDB.agregarRegistro("tb_Turnos");
        //-------------
        
          // variables que se utilizaran en el menu de opciones utilizando scanner:
        int opcion = 0;
        boolean opcionValida = true;
        String limpiarBuffer;
        
        int opcionUsuario;
        int opcionAdmin;
        boolean opcionValidaUsuar = true;
        boolean opcionValidaAdmin=true;
        
        Scanner input = new Scanner(System.in);

        System.out.println("---------------------------------------");

        //Eleccion con un menu de opciones utilizando scanner
        do {
            System.out.println("1. PACIENTE");
            System.out.println("2. ADMINISTRADOR");
            System.out.println("3. SALIR");
            System.out.println("");
            System.out.print("Elija una opcion: ");

            // validaciones de las entradas con try -> catch
            try {
                opcion = input.nextInt();
                if (opcion < 1 || opcion > 3) {
                    throw new Exception();
                }else{
                    opcionValida = false;
                }
                
            } catch (InputMismatchException exc) {
                System.out.println("ERROR: " + exc);
                System.out.println("Ingrese numeros unicamente");
                limpiarBuffer = input.nextLine();
                
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
                System.out.println("Ingrese un indice valido");
            }

        } while (opcionValida);
        
        System.out.println(" ");
        
        //switch donde se encuentra el modo usuario, modo admin o la salida del programa 
        
          switch (opcion){
              
              case 1: 
                  do{
                  System.out.println("----------Usted esta en modo paciente-----------");
                  System.out.println("1. Agregar turno");
                  System.out.println("2. Modificar turno");
                  System.out.println("3. Cancelar turno");
                  System.out.println("4. Pagar ");
                  System.out.println("5. Salir");
                  System.out.println("");
                  System.out.print("Elija una opcion: ");
                  
                  // validaciones de las entradas con try -> catch
                    try{
                        opcionUsuario = input.nextInt();
                        if (opcionUsuario<1 || opcionUsuario>5){
                            throw new Exception();
                        }else{
                            opcionValidaUsuar = false;
                        }
                    }catch(InputMismatchException exc){
                        System.out.println("ERROR:" + exc);
                        System.out.println("Ingrese unicamente numeros");
                        limpiarBuffer = input.nextLine();
                    }catch (Exception e){
                        System.out.println("ERROR: " + e);
                        System.out.println("Ingrese un indice valido");
                    }
                        
                  }while(opcionValidaUsuar);
                  
                            break;
              case 2: 
                  
                  do{
                      
                      System.out.println("------Usted esta en modo admin-------");
                       System.out.println("1. Confirmar asistencia");
                       System.out.println("2. Realizar pago");
                       System.out.println("3. Salir");
                      
                        // validaciones de las entradas con try -> catch
                      try{
                        opcionAdmin = input.nextInt();
                        if (opcionAdmin<1 || opcionAdmin>3){
                            throw new Exception();
                        }else{
                            opcionValidaAdmin = false;
                        }
                    }catch(InputMismatchException exc){
                        System.out.println("ERROR:" + exc);
                        System.out.println("Ingrese unicamente numeros");
                        limpiarBuffer = input.nextLine();
                    }catch (Exception e){
                        System.out.println("ERROR: " + e);
                        System.out.println("Ingrese un indice valido");
                    }
                        
                  }while(opcionValidaAdmin);
                            break;
                            
              case 3: 
                  System.out.println("¡Gracias por visitar clinica Vitality!");
                            break;
           }
        
        
        //-------------¡¡¡¡¡¡¡¡¡¡¡¡¡Eleccion con una interfaz!!!!!!!!!!!!!!!!!!----------------------
        /*
        //Llamo a la interfaz inicial
        Interfaz inter=new Interfaz();
        inter.setVisible(true);//Muestra la interfaz en la pnatalla
        inter.setLocationRelativeTo(null);//Hace que su ubicación no sea relativa a nada, es decir, que la pone en el medio de la pantalla
         */
    }

}
        
    
