package tpintegrador;

import java.time.DayOfWeek;
import java.time.LocalDate;
                
public class TpIntegrador {

    public static void main(String[] args) {

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
        
       // Validador val = new Validador();
        //val.pidoFechaYValido();
        
        
        /*
        
        
        */
        
        Menu menus = new Menu();
        menus.menuGestionClinica();
        System.out.println("awdfasfafGAGAHEJAHFA");
    }
}
