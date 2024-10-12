/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;
import java.sql.*;
import java.util.Scanner;
import java.lang.*;
/**
 *
 * @author USER
 */
public class ConexionDB {
    
    Scanner input = new Scanner(System.in);
    Connection db = null;
    String limpiarBuffer;
    
    
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
    
}


