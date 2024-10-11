/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;
import java.sql.*;
import java.util.Scanner;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import com.google.gson.Gson;
/**
 *
 * @author USER
 */
public class ConexionDB {
    
    Scanner input = new Scanner(System.in);
    Connection db = null;
    
    
    
    /*
            TABLA PACIENTES
            CREATE TABLE IF NOT EXISTS tb_Pacientes(idPaciente INTEGER NOT NULL, Nombre TEXT, Apellido TEXT, DNI INTEGER(8), fechaNacimiento TEXT, PRIMARY KEY(idPaciente AUTOINCREMENT) ) 
    
            TABLA MEDICOS
            CREATE TABLE IF NOT EXISTS tb_Medicos(idMedico INTEGER NOT NULL, Nombre TEXT, Apellido TEXT, DNI INTEGER(8), fechaNacimiento TEXT, cuentaBancaria INTEGER(10), salario DOUBLE, diasDeTrabajo TEXT, especialidad TEXT, PRIMARY KEY(idMedico AUTOINCREMENT) ) 
    
            TABLA TURNOS 
            CREATE TABLE IF NOT EXISTS tb_Turnos(dniPaciente INTERGER(8), diaTurno TEXT, formaDePago TEXT, obraSocial TEXT, especialidad TEXT, asisten) 
            
    */
    
    //conecta a la base de datos
    public void conectarDB() {
    
        try {
    
            Class.forName("org.sqlite.JDBC");
            db = DriverManager.getConnection("jdbc:sqlite:ClinicaVitalityDB.sqlite");
            db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Pacientes(idPaciente INTEGER NOT NULL, Nombre TEXT, Apellido TEXT, DNI INTEGER(8), fechaNacimiento TEXT, PRIMARY KEY(idPaciente AUTOINCREMENT) ) ").execute();
            db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Medicos(idMedico INTEGER NOT NULL, Nombre TEXT, Apellido TEXT, DNI INTEGER(8), fechaNacimiento TEXT, cuentaBancaria INTEGER(10), salario DOUBLE, diasDeTrabajo TEXT, especialidad TEXT, PRIMARY KEY(idMedico AUTOINCREMENT) ) ").execute();
            db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Turnos(dniPaciente INTERGER(8), diaTurno TEXT, formaDePago TEXT, obraSocial TEXT, especialidad TEXT, asistenciaPaciente INTEGER) ").execute();
            

            if (db != null) {
                System.out.println("Conexion Exitosa!");
            } else {
                System.out.println("Ha fallado la conexion");
            }
            
        }catch(SQLException ex) {
            System.out.println("SQLException: " + ex);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ClassNotFoundException: " + cnfe);
        }
    }
    
    //desconecta de la base de datos
    public void desconectarDB() {
        
        try {
            db.close();
        }catch(SQLException ex) {
            System.out.println("SQLException: " + ex);
        }
        
    }
    
    
    //agrega un registro a alguna tabla
    public void agregarRegistro(String tablaIn) {
        PreparedStatement ps = null;
        
        String nombreIn = "", apellidoIn = "", fechaNacimientoIn = "", especialidadIn = "", diaIn = "", limpiarBuffer;
        int dniIn = 0, cbuIn = 0 , diaNacimientoIn = 0, mesNacimientoIn = 0, anioNacimientoIn = 0, indiceIn = 0;
        float salarioIn = 0;
        boolean fechaInvalida = true, dniInvalido = true, stringInvalido = true, especialidadInvalida = true, indiceInvalido = true, cbuInvalido = true, salarioInvalido = true, diaInvalido = true;
        
        //pedi los campos que se repiten
        
        if (tablaIn == "tb_Pacientes" || tablaIn == "tb_Medicos") {
            
            //Pido nombre y apellido
            while (stringInvalido) {
                try {
                
                    do  {
                        System.out.println("Ingrese el nombre:");
                        nombreIn = input.nextLine();    

                        System.out.println("Ingrese el apellido:");
                        apellidoIn = input.nextLine(); 

                        if (nombreIn.matches("\\d+") || apellidoIn.matches("\\d+")) {
                            throw new Exception();
                        }

                    //comprueba si los campos contienen solo numeros
                    } while(nombreIn.matches("\\d+") || apellidoIn.matches("\\d+"));

                    stringInvalido = false;

                }  catch(Exception e) {
                    System.out.println("-Exception: " + e);
                    System.out.println("--El nombre y apellido debe contener SOLO letras");
                }
            }
            
            
            //Pido la fecha de Nacimiento y la valido
            while (fechaInvalida) {
                try {
                    
                    limpiarBuffer = input.nextLine();
                    
                    while (diaNacimientoIn > 31 || diaNacimientoIn < 1) {
                        System.out.println("Ingrese el dia de nacimiento:");
                        diaNacimientoIn = input.nextInt();
                        
                        //si el numero ingresado esta fuera de rango, se emite una exception
                        if (diaNacimientoIn > 31 || diaNacimientoIn < 1) {
                            throw new Exception();
                        }
                    }

                    while (mesNacimientoIn > 12 || mesNacimientoIn < 1) {
                        System.out.println("Ingrese el mes de nacimiento:");
                        mesNacimientoIn = input.nextInt();
                        
                        //si el numero ingresado esta fuera de rango, se emite una exception
                        if (mesNacimientoIn > 12 || mesNacimientoIn < 1) {
                            throw new Exception();
                        }
                    }

                    while (anioNacimientoIn < 1) {
                        System.out.println("Ingrese el anio de nacimiento:");
                        anioNacimientoIn = input.nextInt();
                        
                        //si el numero ingresado esta fuera de rango, se emite una exception
                        if (anioNacimientoIn < 1) {
                            throw new Exception();
                        }
                    }
                                    
                    fechaInvalida = false;
                    fechaNacimientoIn = diaNacimientoIn + "/" + mesNacimientoIn + "/" + anioNacimientoIn;
                    
                } catch(InputMismatchException ime) {
                    System.out.println("-InputMismatchException: " + ime);
                    System.out.println("--Error: Ingrese un numero");
                } catch(Exception e) {
                    System.out.println("-Exception: " + e);
                    System.out.println("--Rango invalido");
                }
                
            }
            

        }  
        if (tablaIn == "tb_Pacientes" || tablaIn == "tb_Medicos" || tablaIn == "tb_Turnos") {
            
                //Pido el DNI y lo valido
                while (dniInvalido) {

                    try {

                        limpiarBuffer = input.nextLine();

                        System.out.println("Ingrese el DNI:");
                        dniIn = input.nextInt();

                        //String.valueOf(dniIn).length() es la longitud del DNI ingresado
                        if (String.valueOf(dniIn).length() != 8) {
                            throw new Exception();
                        }

                        dniInvalido = false;

                    } catch(InputMismatchException ime) {
                        System.out.println("-InputMismatchException: " + ime);
                        System.out.println("--Error: Ingrese un DNI valido");
                    } catch(Exception e) {
                        System.out.println("-Exception: " + e);
                        System.out.println("--El DNI ingresado NO tiene 8 digitos");
                    }

                }
            
        } 
        
        if (tablaIn == "tb_Turnos" || tablaIn == "tb_Medicos") {
            //Pido especialidad y la valido
            String[] especialidades = {"Pediatria", "Dermatologia", "Odontologia"};
            
            while (indiceInvalido) {
            
                try {
                
                    limpiarBuffer = input.nextLine();
                    
                    System.out.println("Elija una especialidad");
                    for (String i: especialidades) {
                        System.out.println( (Arrays.asList(especialidades).indexOf(i)) + 1 +"- " + i);
                    }
                    indiceIn = input.nextInt() - 1;
                    especialidadIn = especialidades[indiceIn];
                    
                    indiceInvalido = false;
                    
                    
                } catch(InputMismatchException ime) {
                    System.out.println("-InputMismatchException: " + ime);
                    System.out.println("--Error: Ingrese un numero valido");
                } catch(Exception e) {
                    System.out.println("-Exception: " + e);
                    System.out.println("--El indice ingresado esta fuera de rango");
                }
                
            }
            
        }

        //Agrego el registro a la tabla
        try {

            switch(tablaIn) {
                case "tb_Pacientes":
                    ps = db.prepareStatement("INSERT INTO tb_Pacientes(Nombre,Apellido,DNI,fechaNacimiento) VALUES (?,?,?,?)");
                    ps.setString(1, nombreIn);
                    ps.setString(2, apellidoIn);
                    ps.setInt(3, dniIn);
                    ps.setString(4, fechaNacimientoIn);
                    break;
                case "tb_Medicos":
                    ps = db.prepareStatement("INSERT INTO tb_Medicos(Nombre,Apellido,DNI,fechaNacimiento,cuentaBancaria,salario,diasDeTrabajo,especialidad) VALUES (?,?,?,?,?,?,?,?)");
                    ps.setString(1, nombreIn);
                    ps.setString(2, apellidoIn);
                    ps.setInt(3, dniIn);
                    ps.setString(4, fechaNacimientoIn);
                    
                    //Pido CBU y lo valido
                    while (cbuInvalido) {

                        try {

                            limpiarBuffer = input.nextLine();

                            System.out.println("Ingrese su cuenta Bancaria (CBU):");
                            cbuIn = input.nextInt();

                            //String.valueOf(dniIn).length() es la longitud del CBU ingresado
                            if (String.valueOf(cbuIn).length() != 10) {
                                throw new Exception();
                            }

                            cbuInvalido = false;

                        } catch(InputMismatchException ime) {
                            System.out.println("-InputMismatchException: " + ime);
                            System.out.println("--Error: Ingrese un CBU valido");
                        } catch(Exception e) {
                            System.out.println("-Exception: " + e);
                            System.out.println("--El CBU ingresado NO tiene 10 digitos");
                        }

                    }
                    ps.setInt(5, cbuIn);
                    //Pido salario y lo valido
                    while (salarioInvalido) {
                        
                        try {
                            
                            limpiarBuffer = input.nextLine();
                        
                            System.out.println("Ingrese el salario");
                            salarioIn = input.nextFloat();
                            
                            if (salarioIn < 0) {
                                throw new Exception();
                            }
                            
                            salarioInvalido = false;
                            
                        } catch(InputMismatchException ime) {
                            System.out.println("-InputMismatchException: " + ime);
                            System.out.println("--Error: Ingrese un salario valido");
                        } catch(Exception e) {
                            System.out.println("-Exception: " + e);
                            System.out.println("--El salario NO puede ser negativo");
                        }
                    }
                    
                    ps.setFloat(6, salarioIn);
                    
                    //pedir dias de trabajo, se meten en un arreglo y se transforma a json con libreria Gson
                    ArrayList<String> diasDeSemana = new ArrayList<>(5);
                    diasDeSemana.add("Lunes");
                    diasDeSemana.add("Martes");
                    diasDeSemana.add("Miercoles");
                    diasDeSemana.add("Jueves");
                    diasDeSemana.add("Viernes");
                    ArrayList<String> diasDeTrabajoIn = new ArrayList<>();
                    
                    
                    while (diaInvalido) {
                        
                        try {
                            
                            limpiarBuffer = input.nextLine();
                        
                            while (indiceIn != 6) {
                                System.out.println("Ingrese los dias de trabajo");
                                for (String i: diasDeSemana) {
                                    System.out.println( (diasDeSemana.indexOf(i)) + 1 +"- " + i);
                                }
                                System.out.println("6- Salir");
                                
                                indiceIn = input.nextInt();
                                diaIn = diasDeSemana.get(indiceIn-1);
                                diasDeTrabajoIn.add(diaIn);
                                diasDeSemana.remove(indiceIn-1);
                                
                            }
                            
                            if (indiceIn < 0 || indiceIn > 6 && indiceIn != 6) {
                                throw new Exception();
                            }
                            
                            diaInvalido = false;
                            
                        } catch(InputMismatchException ime) {
                            System.out.println("-InputMismatchException: " + ime);
                            System.out.println("--Error: Ingrese un indice valido");
                        } catch(Exception e) {
                            System.out.println("-Exception: " + e);
                            System.out.println("--Indice fuera de rango");
                        }
                    }
                    
                    Gson gson = new Gson();
                    // Convierte el arreglo a JSON
                    String jsondiasDeTrabajoIn = gson.toJson(diasDeTrabajoIn);
                    
                    ps.setString(7, jsondiasDeTrabajoIn);

                    ps.setString(8, especialidadIn);
                    
                    
                    
                    
                    
                    break;
                case "tb_Turnos":
                    ps = db.prepareStatement("INSERT INTO tb_Medicos(dniPaciente,diaTurno,formaDePago,obraSocial,especialidad,asistenciaPaciente) VALUES (?,?,?,?,?,?)");
                    
                    //pedir dia del turno
                    //pedir forma de pago 
                    //pedir obra social
                    //inicializo la asistencia en 0(false)
                    
                    break;
            }
            ps.execute();
            
        }catch(SQLException ex) {
            System.out.println("SQLException: " +ex);
        }
        

    }
    
    
    
    
    
}
