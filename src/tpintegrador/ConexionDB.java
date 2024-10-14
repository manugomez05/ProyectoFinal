/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;
import java.sql.*;
import java.util.Scanner;
import java.lang.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 *
 * @author USER
 * 
 * 
 */
public class ConexionDB {
    
    /*
    dudas
        se instancian objetos o el constructor solo llama a la funcion de agregar registro a la tabla
        definir muchas variables con la misma funcion esta bien 
    
    */
    
    Scanner input = new Scanner(System.in);
    Connection db = null;
    String limpiarBuffer;
    Statement stmt;
    ResultSet rs;
    String sql;
    
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
        
        String nombreIn = "", apellidoIn = "", fechaNacimientoIn = "", especialidadIn = "", diasDeTrabajoIn = "", obraSocialIn = "", formaDePagoIn = "", limpiarBuffer;
        int dniIn = 0, cbuIn = 0, indiceIn = 0;
        float salarioIn = 0;
        boolean especialidadInvalida = true, indiceInvalido = true, cbuInvalido = true, salarioInvalido = true, diaInvalido = true;
        
        Validador pideYValida = new Validador();
        
        //pedi los campos que se repiten
        if (tablaIn == "tb_Pacientes" || tablaIn == "tb_Medicos") {
            //Pido nombre y apellido y los valido
            nombreIn = pideYValida.pidoStringYValida("nombre");
            apellidoIn = pideYValida.pidoStringYValida("apellido");
            //Pido fecha de nacimiento y la valido
            fechaNacimientoIn = pideYValida.pidoFechaNacimientoYValido();
        }  
        if (tablaIn == "tb_Pacientes" || tablaIn == "tb_Medicos" || tablaIn == "tb_Turnos") {
            //Pido el DNI y lo valido
            dniIn = pideYValida.pidoDniYValido();
        } 
        if (tablaIn == "tb_Turnos" || tablaIn == "tb_Medicos") {
            //Pido especialidad y la valido
            especialidadIn = pideYValida.pidoEspecialidadYValido();
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
                    cbuIn = pideYValida.pidoCbuYValido();
                    ps.setInt(5, cbuIn);
                    
                    //Pido salario y lo valido
                    salarioIn = pideYValida.pidoSalarioYValido();
                    ps.setFloat(6, salarioIn);
                    
                    //Pido los dias de trabajo y lo valido, se meten en un arreglo y se transforma a json con libreria Gson
                    diasDeTrabajoIn = pideYValida.pidoDiasDeTrabajoYValido();
                    ps.setString(7, diasDeTrabajoIn);

                    ps.setString(8, especialidadIn);

                    break;
                case "tb_Turnos":
                    ps = db.prepareStatement("INSERT INTO tb_Turnos(dniPaciente,diaTurno,formaDePago,obraSocial,especialidad,asistenciaPaciente) VALUES (?,?,?,?,?,?)");
                    ps.setInt(1, dniIn);
                    
                    //pedir dia del turno, depende de la especialidad y de los dias de trabajo de los medicos de esa especialidad
                    
                    
                    //pedir forma de pago 
                    formaDePagoIn = pideYValida.pidoFormaDePagoYValido();
                    ps.setString(3, formaDePagoIn);
                    //pedir obra social
                    obraSocialIn = pideYValida.pidoObraSocialYValido();
                    ps.setString(4, obraSocialIn);
                    
                    ps.setString(5, especialidadIn);
                    
                    //inicializo la asistencia en 0(false)
                    ps.setInt(6, 0);
                    break;
            }
            ps.execute();
            
        }catch(SQLException ex) {
            System.out.println("SQLException: " +ex);
        }
       
    }
    
    
    public void modificarRegistro(String tablaIn) {
        //dependiendo de la tabla 
        //  pregunto que campos va a querer modificar
        //      pregunto por los nuevos valores
        
        /*
        Para obtener solo los nombres de las columnas 
        
        PRAGMA table_info(tb_Medicos);
        SELECT name FROM pragma_table_info('tb_Medicos');
        
        
        UPDATE nombre_tabla
        SET columna1 = valor1, columna2 = valor2, ...
        WHERE condición;
        
        */
        int indiceIn = 0, indiceSalir = 0, enteroIn = 0;
        
        
        //Muestro la tabla, si se ingresa una tabla incorrecta, no pasa de aca
        mostrarTabla(tablaIn); 

        
        try {
            
            //pido Id
            
            //puedo crear la funcion pidoEnteroYValido() con varios argumentos y un if que 
            //verifica si se muestran dos mensajes o uno
            
            if (tablaIn == "tb_Turnos") {
                System.out.println("Ingrese el DNI del paciente a modificar");
            } else {
                System.out.println("Ingrese el ID a modificar");
            }
            
            enteroIn = input.nextInt();
            
            
            //consulta las columnas de la tabla 
            stmt = db.createStatement();
            rs = stmt.executeQuery("PRAGMA table_info(" + tablaIn + ");"); 
            ArrayList<String> columnasDeTabla = new ArrayList<>();
            ArrayList<String> columnasACambiar = new ArrayList<>();
            
            //muestro las columnas de la tabla
            while (rs.next()) {
                columnasDeTabla.add(rs.getString("name"));
            }
            
            //pido las columnas a modificar y las agrego a un array
            do {
                limpiarBuffer = input.nextLine();
                
                indiceSalir = columnasDeTabla.size() + 1;
                
                System.out.println("Elija la columna/s a modificar");
                for (String i: columnasDeTabla) {
                    System.out.println((columnasDeTabla.indexOf(i) + 1) + "- " + i);
                }
                System.out.println(indiceSalir + "- Salir");
                
                try {
                    indiceIn = input.nextInt();
                    
                    if (indiceIn != indiceSalir) {
                        columnasACambiar.add(columnasDeTabla.get(indiceIn-1));
                        columnasDeTabla.remove(indiceIn-1);
                    }
                    
                } catch(IndexOutOfBoundsException iob) {
                    System.out.println("-IndexOutOfBoundsException: " + iob);
                    System.out.println("--Indice ingresado fuera de rango");
                } catch (InputMismatchException ime) {
                    System.out.println("-InputMismatchException: " + ime);
                    System.out.println("--Ingrese un numero");
                }
                                
            } while (indiceIn != indiceSalir);
            
            
            if (!columnasACambiar.isEmpty()) {
                String columnasACambiarString = "";
                for (String i: columnasACambiar) {
                    if (columnasACambiar.indexOf(i) == 0) {
                        columnasACambiarString = columnasACambiarString + "(" + i + ", ";
                        
                    } else if (columnasACambiar.indexOf(i) == columnasACambiar.size()-1) {
                        columnasACambiarString = columnasACambiarString +  i + ")";
                        
                    } else {
                        columnasACambiarString = columnasACambiarString +  i + ", ";
                        
                    }
                }
                System.out.println(columnasACambiarString);
            }
            
            
            
                       
            /*
            
            switch(tablaIn) {
                case "tb_Pacientes":
                    break;
                case "tb_Medicos":
                    
                    break;
                case "tb_Turnos":
                    
                    break;
            }
            */
        } catch(SQLException ex) {
            System.out.println("SQLException: " +ex);
        } catch(Exception e) {
            System.out.println("-Exception: " + e);
            System.out.println("--Indice fuera de rango");
        }
        
    }
    
    
    public void eliminarRegistro(String tablaIn) {
        
    }
    
    public void mostrarTabla(String tablaIn) {
        sql = "SELECT * FROM " + tablaIn;
        
        try {
            stmt = db.createStatement();
            rs = stmt.executeQuery(sql);
            
            System.out.println("-------------- " + tablaIn + " --------------");
            while (rs.next()) {
                
                switch (tablaIn) {
                    case "tb_Medicos":
                        
                        System.out.println("- ID: " + rs.getString(1) + 
                                            "\n\t -- Nombre: " + rs.getString(2) + 
                                            "\n\t -- Apellido: " + rs.getString(3) +
                                            "\n\t -- DNI: " + rs.getString(4) +
                                            "\n\t -- Fecha de Nacimiento: " + rs.getString(5) + 
                                            "\n\t -- Cuenta Bancaria: " + rs.getString(6) + 
                                            "\n\t -- Salario: " + rs.getString(7) + 
                                            "\n\t -- Dias de trabajo: " + rs.getString(8) + 
                                            "\n\t -- Especialidad: " + rs.getString(9));
                        break;
                    case "tb_Pacientes":
                        
                        System.out.println("- ID: " + rs.getString(1) + 
                                            "\n\t -- Nombre: " + rs.getString(2) + 
                                            "\n\t -- Apellido: " + rs.getString(3) +
                                            "\n\t -- DNI: " + rs.getString(4) +
                                            "\n\t -- Fecha de Nacimiento: " + rs.getString(5));
                        break;
                    case "tb_Turnos":
                        
                        System.out.println("- DNI de Paciente: " + rs.getString(1) + 
                                            "\n\t -- Dia del turno: " + rs.getString(2) + 
                                            "\n\t -- Forma de pago: " + rs.getString(3) +
                                            "\n\t -- Obra social: " + rs.getString(4) +
                                            "\n\t -- Especialidad: " + rs.getString(5) +
                                            "\n\t -- Asistencia del paciente: " + rs.getString(6));
                        break;
                }
                
            }
            System.out.println("---------------------------------------");
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " +ex);
        }
        
        
        
    }
}


