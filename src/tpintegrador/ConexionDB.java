/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;
import java.sql.*;
import java.util.Scanner;
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
    
    public void desconectarDB() {
        
        try {
            db.close();
        }catch(SQLException ex) {
            System.out.println("SQLException: " + ex);
        }
        
    }
    
    public void agregarRegistro(String tablaIn) {
        PreparedStatement ps = null;
        
        String nombreIn = "", apellidoIn = "", fechaNacimientoIn = "", limpiarBuffer;
        int dniIn = 0, diaNacimientoIn = 0, mesNacimientoIn = 0, anioNacimientoIn = 0;
        
        
        //pedi los campos que se repiten
        if (tablaIn == "tb_Pacientes" || tablaIn == "tb_Medicos") {
            
            //pide nombre y apellido
            //  necesita validacion
            System.out.println("Ingrese el nombre:");
            nombreIn = input.nextLine();
            System.out.println("Ingrese el apellido:");
            apellidoIn = input.nextLine();
            
            
            
            
            //  necesita validacion
            System.out.println("Ingrese el DNI:");
            dniIn = input.nextInt();
            
            limpiarBuffer = input.nextLine();
            
            //  necesita validacion
            System.out.println("Ingrese el dia de nacimiento:");
            diaNacimientoIn = input.nextInt();
            System.out.println("Ingrese el mes de nacimiento:");
            mesNacimientoIn = input.nextInt();
            System.out.println("Ingrese el anio de nacimiento:");
            anioNacimientoIn = input.nextInt();
            
            fechaNacimientoIn = diaNacimientoIn + "/" + mesNacimientoIn + "/" + anioNacimientoIn;
        }
        
        try {
            
            ps.setString(1, nombreIn);
            ps.setString(2, apellidoIn);
            ps.setInt(3, dniIn);
            ps.setString(4, fechaNacimientoIn);
            
            switch(tablaIn) {
                case "tb_Pacientes":
                    ps = db.prepareStatement("INSERT INTO tb_Pacientes(Nombre,Apellido,DNI,fechaNacimiento) VALUES (?,?,?,?)");
                    break;
                case "tb_Medicos":
                    ps = db.prepareStatement("INSERT INTO tb_Medicos(Nombre,Apellido,DNI,fechaNacimiento,cuentaBancaria,salario,diasDeTrabajo,especialidad) VALUES (?,?,?,?,?,?,?,?)");
                    
                    break;
                case "tb_Turnos":
                    break;
            }
            ps.execute();
            
        }catch(SQLException ex) {
            System.out.println("SQLException: " +ex);
        }
        
        
        
        
    }
    
    
}
