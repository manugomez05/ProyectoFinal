    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;
import com.google.gson.Gson;
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
    
    
    
    //DUDAS
    /*
        (consultar los ids que existen, guardarlos en un array y 
            seguir pidiendo el id mientras que el ingresado no exista)
    
        hacer select de un campo que no existe no tira error
        tengo que verificar que exista o no
    
    
        
    */
    
    
    Scanner input = new Scanner(System.in);
    Connection db = null;
    String limpiarBuffer;
    Statement stmt;
    ResultSet rs;
    String sql;
    Validador pideYValida = new Validador();
    int idIn;
    
    //conecta a la base de datos
    public void conectarDB() {
    
        try {
    
            Class.forName("org.sqlite.JDBC");
            db = DriverManager.getConnection("jdbc:sqlite:ClinicaVitalityDB.sqlite");
            db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Pacientes(idPaciente INTEGER NOT NULL, Nombre TEXT, Apellido TEXT, DNI INTEGER(8), fechaNacimiento TEXT, PRIMARY KEY(idPaciente AUTOINCREMENT) ) ").execute();
            db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Medicos(idMedico INTEGER NOT NULL, Nombre TEXT, Apellido TEXT, DNI INTEGER(8), fechaNacimiento TEXT, cuentaBancaria INTEGER(10), salario DOUBLE, diasDeTrabajo TEXT, especialidad TEXT, PRIMARY KEY(idMedico AUTOINCREMENT) ) ").execute();
            //db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Turnos(idTurno INTEGER NOT NULL, dniPaciente INTERGER(8), diaTurno TEXT, formaDePago TEXT, obraSocial TEXT, especialidad TEXT, asistenciaPaciente INTEGER,PRIMARY KEY(idTurno AUTOINCREMENT)) ").execute();
            db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Turnos(idTurno INTEGER NOT NULL, dniPaciente INTERGER(8), fechaTurno TEXT, formaDePago TEXT, obraSocial TEXT, especialidad TEXT, asistenciaPaciente INTEGER,PRIMARY KEY(idTurno AUTOINCREMENT)) ").execute();
            
            
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
        
        //Agrego el registro a la tabla
        try {

            switch(tablaIn) {
                case "tb_Pacientes":
                    
                    Paciente newPaciente = new Paciente();

                    ps = db.prepareStatement("INSERT INTO tb_Pacientes(Nombre,Apellido,DNI,fechaNacimiento) VALUES (?,?,?,?)");
                    ps.setString(1, newPaciente.getNombre());
                    ps.setString(2, newPaciente.getApellido());
                    ps.setInt(3, newPaciente.getDni());
                    ps.setString(4, newPaciente.getFechaDeNacimiento());
                    
                    
                    System.out.println(newPaciente.getNombre() + " ha sido registrado correctamente");
                    break;
                case "tb_Medicos":
                    
                    Medico newMedico = new Medico();
                                        
                    ps = db.prepareStatement("INSERT INTO tb_Medicos(Nombre,Apellido,DNI,fechaNacimiento,cuentaBancaria,salario,diasDeTrabajo,especialidad) VALUES (?,?,?,?,?,?,?,?)");
                    ps.setString(1, newMedico.getNombre());
                    ps.setString(2, newMedico.getApellido());
                    ps.setInt(3, newMedico.getDni());
                    ps.setString(4, newMedico.getFechaDeNacimiento());
                    
                    ps.setInt(5, newMedico.getCuentaBancaria());
                    ps.setFloat(6, newMedico.getSalario());
                    ps.setString(7, newMedico.getDiasDeTrabajo());
                    ps.setString(8, newMedico.getExpecialidad());
                    
                    System.out.println(newMedico.getNombre() + " ha sido registrado correctamente");
                    
                    break;
                case "tb_Turnos":
                    
                    Turno newTurno = new Turno();
                    
                    ps = db.prepareStatement("INSERT INTO tb_Turnos(dniPaciente,fechaTurno,formaDePago,obraSocial,especialidad,asistenciaPaciente) VALUES (?,?,?,?,?,?)");
                    
                    ps.setInt(1, newTurno.getDniPaciente());
                    ps.setString(2, newTurno.getDia());
                    ps.setString(3, newTurno.getFormaDePago());
                    
                    Gson gson = new Gson();
                    // Convierte el arreglo a JSON
                    String jsonObraSocialPaciente = gson.toJson(newTurno.getObraSocial());
                    
                    ps.setString(4, jsonObraSocialPaciente);
                    ps.setString(5, newTurno.getEspecialidad());
                    ps.setInt(6,0);
                    
                    
                    
                    System.out.println("Se ha registrado correctamente un turno para " + newTurno.getDniPaciente());
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
            
            //puedo crear la funcion pidoEnteroYValido()
            //consulto los ids de la tabla
            //los agrego a un arraylist 
            //lo paso como argumento 
            
            
            
            /*
            if (tablaIn == "tb_Turnos" || tablaIn == "tb_Medicos" || tablaIn == "tb_Pacientes" ) {
                enteroIn = pideYValida.pidoEnteroYValido("Ingrese el ID a modificar");
            }
            */
            
            ArrayList<Integer> idsDeTabla = new ArrayList<>();
            stmt = db.createStatement();
            
            switch (tablaIn) {
                //dependiendo de la tabla consulto el valor de la id
                case "tb_Medicos":
                        rs = stmt.executeQuery("SELECT idMedico FROM " + tablaIn + ";"); 
                        break;
                    case "tb_Pacientes":
                        rs = stmt.executeQuery("SELECT idPaciente FROM tb_Pacientes;"); 
                        break;
                    case "tb_Turnos":
                        rs = stmt.executeQuery("SELECT idTurno FROM tb_Turnos;"); 
                        break;

            }
            
            
            while (rs.next()) {
                //dependiendo de la tabla tomo el valor de id
                switch (tablaIn) {
                    case "tb_Medicos":
                        idsDeTabla.add(rs.getInt("idMedico"));
                        break;
                    case "tb_Pacientes":
                        idsDeTabla.add(rs.getInt("idPaciente"));
                        break;
                    case "tb_Turnos":
                        idsDeTabla.add(rs.getInt("idTurno"));
                        break;
                        
                }
            }
            
            
            
            
                    
            
            
            //consulta las columnas de la tabla 
            stmt = db.createStatement();
            rs = stmt.executeQuery("PRAGMA table_info(" + tablaIn + ");"); 
            ArrayList<String> columnasDeTabla = new ArrayList<>();
            ArrayList<String> columnasACambiar = new ArrayList<>();

            
            //agrego las columnas de la tabla a un array
            while (rs.next()) {
                columnasDeTabla.add(rs.getString("name")); 
            }
            
            System.out.println(columnasDeTabla);
            System.out.println(idsDeTabla);
            
            
            
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
            
            //recorro el array caolumnasACambiar
            /*
            
            
            
            
            
            if (tablaIn == "tb_Pacientes" || tablaIn == "tb_Medicos" || tablaIn == "tb_Turnos" && columnasACambiar.contains("DNI") || columnasACambiar.contains("dniPaciente")) {
                
            }
            
            switch(tablaIn) {
                case "tb_Pacientes":
                    
                    for (String i : columnasACambiar) {
                        switch (i) {
                            case "":
                                break;
                            case "":
                                break;
                            case "":
                                break;
                            case "":
                                break;
                        }
                    }
                    
                    break;
                case "tb_Medicos":
                    
                    break;
                case "tb_Turnos":
                    
                    break;
            }
            */
            
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
        
        
        /*
        DELETE FROM tablaIn WHERE condicion;
        
        el usuario elige  eliminar ID
        
        */
        
        mostrarTabla(tablaIn);
        
        idIn = pideYValida.pidoEnteroYValido(tablaIn);
        
        
        
        
    }
    
    
    public void vaciarTabla(String tablaIn) {
        /*
        Para eliminar todos los registros
        DELETE FROM tablaIn;
        */
        
        sql = "DELETE FROM " + tablaIn;
        
        try {
            stmt = db.createStatement();
            rs = stmt.executeQuery(sql);
            
        } catch (SQLException ex) {
            System.out.println("-SQLException: " +ex);
            System.out.println("--La tabla " + tablaIn + " no existe");
        }
        
        System.out.println("Se ha vaciado la tabla " + tablaIn);
        
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
                        
                        System.out.println("- ID: " + rs.getString(1) +
                                            "\n\t -- DNI de Paciente: " + rs.getString(2) + 
                                            "\n\t -- Fecha del turno: " + rs.getString(3) + 
                                            "\n\t -- Forma de pago: " + rs.getString(4) +
                                            "\n\t -- Obra social: " + rs.getString(5) +
                                            "\n\t -- Especialidad: " + rs.getString(6) +
                                            "\n\t -- Asistencia del paciente: " + rs.getString(7));
                        break;
                }
                
            }
            System.out.println("---------------------------------------");
            
        } catch (SQLException ex) {
            System.out.println("-SQLException: " +ex);
            System.out.println("--La tabla " + tablaIn + " no existe");
        }
        
        
        
    }
    
    
    
}


