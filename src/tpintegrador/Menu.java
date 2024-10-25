package tpintegrador;

import java.util.*;

public class Menu {

    int opcionElegida;
    String menu;
    Validador validador1 = new Validador();
    ArrayList<Integer> indices = new ArrayList<>();
    ConexionDB conDB = new ConexionDB();
    
    public void menuGestionClinica() {

        indices.clear();
        for (int i = 1; i < 6; i++) {
            indices.add(i);
        }
        System.out.println(indices);  
        
        menu = """
               *******************************
                    GESTION DE CLINICA
               *******************************
               1. Gestionar Medicos
               2. Gestionar Pacientes
               3. Gestionar Citas
               4. Consultar Historia Clinica
               5. Salir
               *******************************
               Seleccione una opcion:""";
        
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
                System.out.println("Sesion Cerrada");
                break;
        }
    }

    public void menuGestionMedico() {
        indices.clear();  
        for (int i = 1; i < 6; i++) {
            indices.add(i);
        }

        menu = """
               *******************************
                    GESTIONAR MEDICOS
               *******************************
               1. Agregar Nuevo Medico
               2. Modificar Informacion de Medico
               3. Eliminar Medico
               4. Listar Todos los Medicos
               5. Regresar al Menu Principal
               *******************************
               Seleccione una opcion:""";

        conDB.conectarDB();
        
        while (true) { 
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
                    return;  
            }
        }
    }

    public void menuGestionPacientes() {
        indices.clear();
        for (int i = 1; i < 6; i++) {
            indices.add(i);
        }

        menu = """
               *******************************
                    GESTIONAR PACIENTES
               *******************************
               1. Agregar Nuevo Paciente
               2. Modificar Informacion de Paciente
               3. Eliminar Paciente
               4. Listar Todos los Pacientes
               5. Regresar al Menu Principal
               *******************************
               Seleccione una opcion:""";
        
        conDB.conectarDB();
        
        while (true) {  
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
                    return;  
            }
        }
    }

    public void menuGestionCitas() {
        indices.clear();
        for (int i = 1; i < 7; i++) {
            indices.add(i);
        }

        menu = """
               *******************************
                    GESTIONAR CITAS
               *******************************
               1. Agendar Nueva Cita
               2. Modificar Cita
               3. Cancelar Cita
               4. Pagar Cita
               5. Listar Todas las Citas
               6. Regresar al Menu Principal
               *******************************
               Seleccione una opcion:""";

        conDB.conectarDB();
        
        while (true) {  
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
                    // Mostrar citas disponibles para pagar
                    // Mostrar factura con los datos
                    break;
                case 5:
                    conDB.mostrarTabla("tb_Turnos");
                    break;
                case 6:
                    indices.clear();
                    menuGestionClinica();  
                    return;  
            }
        }
    }

    public void menuHistoriaClinica() {
        indices.clear();
        for (int i = 1; i < 4; i++) {
            indices.add(i);
        }

        menu = """
               ************************************
                    CONSULTAR HISTORIA CLINICA
               ************************************
               1. Buscar Historia Clinica por Paciente
               2. Listar Todas las Historias Clinicas
               3. Regresar al Menu Principal
               *******************************
               Seleccione una opcion:""";
        
        conDB.conectarDB();
        
        while (true) {  
            opcionElegida = validador1.pidoEnteroYValido(menu, indices);
            switch (opcionElegida) {
                case 1:
                    conDB.mostrarHistoriaClinicaPorPaciente();
                    break;
                case 2:
                    conDB.mostrarTabla("tb_Turnos");
                    break;
                case 3:
                    indices.clear();
                    menuGestionClinica();  
                    return;  
            }
        }
    }
}
