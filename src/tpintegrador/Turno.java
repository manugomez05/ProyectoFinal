/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        
    public Turno(boolean inicializar) {
        
        if (inicializar) {
            
            conDB.conectarDB();
            
            try {
                
                do {
                    this.setDniPaciente(pideYValida.pidoDniYValido());
                    rs = conDB.realizaConsulta("SELECT Nombre FROM tb_Pacientes WHERE DNI = " + this.getDniPaciente());
                    conDB.desconectarDB();
                    if (rs.getString(1) == null) {
                        
                        System.out.println("-Error: No existe ningun paciente con el DNI: " + this.getDniPaciente() + ", tiene que crear un nuevo paciente para poder asignarle un turno");
                        
                        conDB.agregarRegistro("tb_Pacientes");
                    }
                    
                } while (rs.getString(1) == null);
                
            } catch(SQLException ex) {
                System.out.println("-SQLException: " +ex);
            }
            

            this.setEspecialidad(pideYValida.pidoEspecialidadYValido());
            this.setDia(pideYValida.pidoFechaYValido(this.especialidad));
            this.setFormaDePago(pideYValida.pidoFormaDePagoYValido());
            this.setObraSocial(pideYValida.pidoObraSocialYValido());
            this.setAsistenciaPaciente(0);
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
