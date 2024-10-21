package tpintegrador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TpIntegrador {

    public static void main(String[] args) {

        // Conecto a la base de datos 
        ConexionDB conDB = new ConexionDB();
        
        
        conDB.conectarDB();
        //conDB.mostrarTabla("tb_dfsurnos");
        conDB.modificarRegistro("tb_Pacientes");
        //conDB.agregarRegistro("tb_Pacientes");
        //conDB.agregarRegistro("tb_Turnos");
        //conDB.agregarRegistro("tb_Medicos");
        //conDB.eliminarRegistro("tb_Medicos");
        Validador pide = new Validador();
        
        
        
               
        
        /*
        
        Los medicos trabajan todos los dias
        
        hacer una clase menu que tenga metodos por cada menu
        menuGestionClinica()
        menuGestionMedicos()
        ...
        
        
        
        MENU 
        
        *******************************
            GESTIÓN DE CLÍNICA
        *******************************
        1. Gestionar Médicos
        2. Gestionar Pacientes
        3. Gestionar Citas
        4. Consultar Historia Clínica
        5. Salir
        *******************************
        Seleccione una opción:
        
        
        *******************************
            GESTIONAR MÉDICOS
        *******************************
        1. Agregar Nuevo Médico
        2. Modificar Información de Médico
        3. Eliminar Médico
        4. Listar Todos los Médicos
        5. Regresar al Menú Principal
        *******************************
        Seleccione una opción:

        
        
        *******************************
            GESTIONAR PACIENTES
        *******************************
        1. Agregar Nuevo Paciente
        2. Modificar Información de Paciente
        3. Eliminar Paciente
        4. Listar Todos los Pacientes
        5. Regresar al Menú Principal
        *******************************
        Seleccione una opción:


        *******************************
            GESTIONAR CITAS
        *******************************
        1. Agendar Nueva Cita
        2. Modificar Cita
        3. Cancelar Cita
        4. Listar Todas las Citas
        5. Regresar al Menú Principal
        *******************************
        Seleccione una opción:

        
        *******************************
        CONSULTAR HISTORIA CLÍNICA
        *******************************
        1. Buscar Historia Clínica por Paciente
        2. Listar Todas las Historias Clínicas
        3. Regresar al Menú Principal
        *******************************
        Seleccione una opción:

        
        
        */
        
        
        
        
        
        // Variables del menú
        int opcion = 0;
        boolean opcionValida = true;
        String limpiarBuffer;

        int opcionUsuario = 0;
        int opcionAdmin = 0;
        boolean opcionValidaUsuar;
        boolean opcionValidaAdmin;
        
        Scanner input = new Scanner(System.in);

        // Menú principal
        do {
            System.out.println("---------------------------------------");
            System.out.println("1. PACIENTE");
            System.out.println("2. ADMINISTRADOR");
            System.out.println("3. SALIR");
            System.out.print("Elija una opción: ");

            // Validaciones de las entradas
            try {
                opcion = input.nextInt();
                if (opcion < 1 || opcion > 3) {
                    throw new Exception();
                } else {
                    opcionValida = false;
                }
                
            } catch (InputMismatchException exc) {
                System.out.println("ERROR: " + exc);
                System.out.println("Ingrese números únicamente");
                limpiarBuffer = input.nextLine(); // Limpiar el buffer
                
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
                System.out.println("Ingrese un índice válido");
            }

        } while (opcionValida);

        System.out.println(" ");
        
        // Switch para el modo Paciente, Administrador o salida
        switch (opcion) {
            case 1: 
                do {
                    System.out.println("----------Usted está en modo paciente-----------");
                    System.out.println("1. Agregar turno");
                    System.out.println("2. Modificar turno");
                    System.out.println("3. Cancelar turno");
                    System.out.println("4. Pagar ");
                    System.out.println("5. Volver al menú principal");
                    System.out.print("Elija una opción: ");

                    opcionValidaUsuar = true;

                    // Validaciones de las entradas
                    try {
                        opcionUsuario = input.nextInt();
                        input.nextLine(); // Limpiar el buffer
                        if (opcionUsuario < 1 || opcionUsuario > 5) {
                            throw new Exception();
                        } else {
                            opcionValidaUsuar = false;
                        }
                    } catch (InputMismatchException exc) {
                        System.out.println("ERROR: " + exc);
                        System.out.println("Ingrese únicamente números");
                        limpiarBuffer = input.nextLine(); // Limpiar el buffer
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e);
                        System.out.println("Ingrese un índice válido");
                    }

                    // Acciones según la opción elegida
                    switch (opcionUsuario) {
                        case 1: 
                            conDB.agregarRegistro("tb_Turnos");
                            break;
                        case 2:
                            conDB.modificarRegistro("tb_Turnos");
                            break;
                        case 3:
                            conDB.eliminarRegistro("tb_Turnos");
                            break;
                        //case 4:
                            
                        // Aquí puedes agregar más acciones para las otras opciones si es necesario.
                    }

                } while (opcionUsuario != 5); // Salir del menú de paciente cuando se elija la opción 5

                opcionValida = true; // Reiniciar para volver al menú principal
                main(args); // Llamar nuevamente a `main` para mostrar el menú principal
                break;

            case 2:
                do {
                    System.out.println("------Usted está en modo administrador-------");
                    System.out.println("1. Confirmar asistencia");
                    System.out.println("2. Realizar pago");
                    System.out.println("3. Volver al menú principal");
                    System.out.print("Elija una opción: ");

                    opcionValidaAdmin = true;

                    // Validaciones de las entradas
                    try {
                        opcionAdmin = input.nextInt();
                        if (opcionAdmin < 1 || opcionAdmin > 3) {
                            throw new Exception();
                        } else {
                            opcionValidaAdmin = false;
                        }
                    } catch (InputMismatchException exc) {
                        System.out.println("ERROR:" + exc);
                        System.out.println("Ingrese únicamente números");
                        limpiarBuffer = input.nextLine(); // Limpiar el buffer
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e);
                        System.out.println("Ingrese un índice válido");
                    }
                    
                    switch (opcionAdmin) {
                        case 1: 
                            break;
                        case 2:
                            // pedir dni y filtrar facturas asociadas al dni        
                           //conDB.mostrarRegistro();
                           
                            break;
                        case 3:
                            break;
                    }

                } while (opcionValidaAdmin || opcionAdmin != 3); // Salir cuando se elija la opción 3
                opcionValida = true; // Reiniciar para volver al menú principal
                main(args); // Llamar nuevamente a `main` para mostrar el menú principal
                break;

            case 3: 
                System.out.println("¡Gracias por visitar clínica Vitality!");
                break;
        }
    }
}
