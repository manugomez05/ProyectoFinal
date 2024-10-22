package tpintegrador;

import java.util.*;

public class Menu {

    Scanner input = new Scanner(System.in);
    int opcionElegida;
    String menu;
    Validador validador1 = new Validador();
    ArrayList<Integer> indices = new ArrayList<>();
    ConexionDB conDB = new ConexionDB();
    boolean salir = false;
    public void menuGestionClinica() {

        conDB.conectarDB();
      if (salir == false) {  
        for (int i = 1; i < 6; i++) {
            indices.add(i);
        }
        //System.out.println(indices);
        
        
        menu = "\n"
                + "*******************************\n"
                + "     GESTIÓN DE CLÍNICA\n"
                + "*******************************\n"
                + "1. Gestionar Médicos\n"
                + "2. Gestionar Pacientes\n"
                + "3. Gestionar Citas\n"
                + "4. Consultar Historia Clínica\n"
                + "5. Salir\n"
                + "*******************************\n"
                + "Seleccione una opción:";
        opcionElegida = validador1.pidoEnteroYValido(menu, indices);
        indices.clear();
       
        switch (opcionElegida) {
            case 1:
                menuGestionMedico();
                break;
            case 2:
                menuGestionPacientes();
                break;
            case 3:
                menuGestionCitas();
                break;
            case 4:
                menuHistoriaClinica();
                break;
            case 5:
                salir = true;
                System.out.println("Sesion Cerrada"); 
                break;
            
        }
        
      }else {
          System.out.println("adios");}
    }

    public void menuGestionMedico() {
        for (int i = 1; i < 6; i++) {
            indices.add(i);
        }

        menu
                = "*******************************\n"
                + "     GESTIONAR MÉDICOS\n"
                + "*******************************\n"
                + "1. Agregar Nuevo Médico\n"
                + "2. Modificar Información de Médico\n"
                + "3. Eliminar Médico\n"
                + "4. Listar Todos los Médicos\n"
                + "5. Regresar al Menú Principal\n"
                + "*******************************\n"
                + " Seleccione una opción:";

        while (opcionElegida != 5) {
            opcionElegida = validador1.pidoEnteroYValido(menu, indices);
            switch (opcionElegida) {
                case 1:
                    conDB.agregarRegistro("tb_Medicos");
                    break;
                case 2:
                    conDB.modificarRegistro("tb_Medicos");
                    break;
                case 3:
                    conDB.eliminarRegistro("tb_Medicos");
                    break;
                case 4:
                    conDB.mostrarTabla("tb_Medicos");
                    break;
                case 5:
                    indices.clear();
                    menuGestionClinica();
                    break;
            }
        }

    }

    public void menuGestionPacientes() {
        for (int i = 1; i < 6; i++) {
            indices.add(i);
        }

        menu
                = "*******************************\n"
                + "     GESTIONAR PACIENTES\n"
                + "*******************************\n"
                + "1. Agregar Nuevo Paciente\n"
                + "2. Modificar Información de Paciente\n"
                + "3. Eliminar Paciente\n"
                + "4. Listar Todos los Pacientes\n"
                + "5. Regresar al Menú Principal\n"
                + "*******************************\n"
                + "Seleccione una opción:";
        while (opcionElegida != 5) {
            opcionElegida = validador1.pidoEnteroYValido(menu, indices);
            switch (opcionElegida) {
                case 1:
                    conDB.agregarRegistro("tb_Pacientes");
                    break;
                case 2:
                    conDB.modificarRegistro("tb_Pacientes");
                    break;
                case 3:
                    conDB.eliminarRegistro("tb_Pacientes");
                    break;
                case 4:
                    conDB.mostrarTabla("tb_Pacientes");
                    break;
                case 5:
                    indices.clear();
                    menuGestionClinica();
                    break;
            }
        }

    }

    public void menuGestionCitas() {
        for (int i = 1; i < 6; i++) {
            indices.add(i);
        }

        menu
                = "*******************************\n"
                + "     GESTIONAR CITAS\n"
                + "*******************************\n"
                + "1. Agendar Nueva Cita\n"
                + "2. Modificar Cita\n"
                + "3. Cancelar Cita\n"
                + "4. Listar Todas las Citas\n"
                + "5. Regresar al Menú Principal\n"
                + "*******************************\n"
                + "Seleccione una opción:";

        while (opcionElegida != 5) {
            opcionElegida = validador1.pidoEnteroYValido(menu, indices);
            switch (opcionElegida) {
                case 1:
                    conDB.agregarRegistro("tb_Turnos");
                    break;
                case 2:
                    conDB.modificarRegistro("tb_Turnos");
                    break;
                case 3:
                    conDB.eliminarRegistro("tb_Turnos");
                    break;
                case 4:
                    conDB.mostrarTabla("tb_Turnos");
                    break;
                case 5:
                    indices.clear();
                    menuGestionClinica();
                    break;
            }
        }

    }

    
    public void menuHistoriaClinica() {
        opcionElegida = 0;
        for (int i = 1; i < 4; i++) {
            indices.add(i);}
         menu
                = "************************************\n"
                + "     CONSULTAR HISTORIA CLÍNICA\n"
                + "************************************\n"
                + "1. Buscar Historia Clínica por Paciente\n"
                + "2. Listar Todas las Historias Clínicas\n"
                + "3. Regresar al Menú Principal\n"
                + "*******************************\n"
                + "Seleccione una opción:";
         
         opcionElegida = validador1.pidoEnteroYValido(menu, indices);
         switch (opcionElegida){
             case 1:
                    conDB.mostrarHistoriaClinicaPorPaciente();
                 break;
                 
             case 2:
                    conDB.mostrarTabla("tb_Turnos");
                    break;
             case 3:
                    indices.clear();
                    menuGestionClinica();
                
                
        }
    }
}