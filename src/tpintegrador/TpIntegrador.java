package tpintegrador;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.nio.charset.StandardCharsets;                
public class TpIntegrador {

    public static void main(String[] args) throws UnsupportedEncodingException {

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        /*
        
        Dias de turno segun la especialidad
        Pagar
       
                
        Creo que hay que conectar y desconectar la base de datos  en cada funcion
        
        */
        
        // Conecto a la base de datos 
        ConexionDB conDB = new ConexionDB();
        
        conDB.conectarDB();
        
        
        
        /*
        Get day of week devuelve el dia (Lunes, Martes, miercoles) de una fecha
        se puede usar para lo del turno
        
        
        LocalDate hora = LocalDate.now();
        DayOfWeek dia = hora.getDayOfWeek();
        System.out.println(dia);
        
        */
        
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
