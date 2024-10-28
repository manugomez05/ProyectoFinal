/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.*;
/**
 *
 * @author Estudiante
 */
public class Turno {
    
    private int dniPaciente;
    private String dia;
    private String formaDePago;
    private ObraSocial obraSocial;
    private String especialidad;
    private int asistenciaPaciente; 
    private Factura factura1;
    
    Validador pideYValida = new Validador();
    ConexionDB conDB = new ConexionDB();
    ResultSet rs;
    boolean dniInexsistente = true;
        
        
    public Turno(boolean inicializar) {
        
        if (inicializar) {
            
            while (dniInexsistente) {
                
                this.setDniPaciente(pideYValida.pidoDniYValido());
                String sql = "SELECT Nombre FROM tb_Pacientes WHERE DNI = " + this.getDniPaciente();
                
                rs = conDB.realizaConsulta(sql);
                try {
                    
                    if (rs.getString(1) != null) {
                        System.out.println("Paciente encontrado : " + rs.getString(1));
                    } else {
                        System.out.println("Error: No existe ningún paciente con el DNI: " + this.getDniPaciente() + ", primero tiene que crear uno nuevo");
                        
                        conDB.conectarDB();
                        conDB.agregarRegistro("tb_Pacientes");
                        conDB.desconectarDB();
                    }
                    
                    dniInexsistente = false;
                } catch (SQLException ex) {
                    System.out.println("SQL Exception: " + ex.getMessage());
                }
            
                this.setEspecialidad(pideYValida.pidoEspecialidadYValido());
                //this.setDia(pideYValida.pidoFechaYValido(this.especialidad));
                conDB.desconectarDB();
                this.setDia(conDB.pidoFechaTurnoYValido(this.especialidad));

                this.setFormaDePago(pideYValida.pidoFormaDePagoYValido());
                this.setObraSocial(pideYValida.pidoObraSocialYValido());
                this.setAsistenciaPaciente(0);
                //conDB.conectarDB();
            }
        
        }
    }

   
    public int getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(int dniPaciente) {
        this.dniPaciente = dniPaciente;
    }
    
    
    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getAsistenciaPaciente() {
        return asistenciaPaciente;
    }

    public void setAsistenciaPaciente(int asistenciaPaciente) {
        this.asistenciaPaciente = asistenciaPaciente;
    }
    
    
    
    public void confirmarTurno(){
    
    }
    
    
    public void asignarMedico(){
    
    }
    
    
    public void editarTurno(){
    
    }
    
    
    public void eliminarTurno(){
    
    }
    
    
    public void realizarPago(){
        
        
           factura1 = new Factura();
           
           factura1.setTipoFactura("Factura tipo B");
           
           double monto = factura1.calcularMonto();
           
           factura1.setMontoTotal(monto);
           
    }

    
    
    
    
    
    
    
    
}
