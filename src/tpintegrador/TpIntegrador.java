package tpintegrador;

public class TpIntegrador {

    public static void main(String[] args) {

        /*
        
                        Dias de turno segun la especialidad
        arreglar modificar 
        Pagar
        
        Bucleo infinito cuando se entra al menu de historial, vuelve al menu principal y se sale
        
        
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
        
        
        */
        
        Menu menus = new Menu();
        menus.menuGestionClinica();
    }
}
