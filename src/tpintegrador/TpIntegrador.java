package tpintegrador;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;                

public class TpIntegrador {

    public static void main(String[] args) throws UnsupportedEncodingException {

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        /*
        
        CAMBIAR A TIPO DATE LAS FECHAS, TURNOS DATE TIME
        
        Dias de turno segun la especialidad, cuando se intenta sacar un turno que tiene que 
        verificar si el DNI existe en la base de datos Pacientes
        
        si no hay medico con la especialidad disponible, se queda preguntando el dia eternamente
        
        VOLVIO EL BUCLE EN EL MISMO LUGAR DE ANTES
        
        Pagar
       
        Preguntar si vamos bien al profe
        
        mejorar los souts de agregar registros
        
        Creo que hay que conectar y desconectar la base de datos  en cada funcion
        
        */
        
        // Conecto a la base de datos 
        ConexionDB conDB = new ConexionDB();
        
        //conDB.conectarDB();
        //conDB.pidoFechaYValido("Pediatria");
        
        //Validador val = new Validador();
        //val.pidoFechaYValido("Pediatria");
        
        
       
        /*
       
        Menu menus = new Menu();
        menus.menuGestionClinica();
        System.out.println("awdfasfafGAGAHEJAHFA");
        
        */
        Menu menus = new Menu();
        menus.menuGestionClinica();
        
    }
}
