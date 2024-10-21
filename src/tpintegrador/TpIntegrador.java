package tpintegrador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TpIntegrador {

    public static void main(String[] args) {

        /*
        
                        Dias de turno segun la especialidad
        arreglar modificar 
        Pagar
        
        
        */
        
        // Conecto a la base de datos 
        ConexionDB conDB = new ConexionDB();
        
        conDB.conectarDB();
        //conDB.mostrarTabla("tb_dfsurnos");
        //conDB.modificarRegistro("tb_Pacientes");
        //conDB.agregarRegistro("tb_Pacientes");
        //conDB.agregarRegistro("tb_Turnos");
        //conDB.agregarRegistro("tb_Turnos");
        //conDB.mostrarHistoriaClinicaPorPaciente();
        //conDB.agregarRegistro("tb_Medicos");
        //conDB.eliminarRegistro("tb_Medicos");
        
        
        
        
        /*
        
        Los medicos trabajan todos los dias
        
        hacer una clase menu que tenga metodos por cada menu
        menuGestionClinica()
        menuGestionMedicos()
        ...
        */
        
        Menu menus = new Menu();
        menus.menuGestionClinica();
    }
}
