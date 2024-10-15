/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;

/**
 *
 * @author Estudiante
 */
abstract public class Turno {
    
    private String dia;
    private String formaDePago;
    private ObraSocial obraSocial;
    private String especialidad;
    private boolean asistenciaPaciente; 

    public Turno(String dia, String formaDePago, ObraSocial obraSocial, String especialidad, boolean asistenciaPaciente) {
        this.dia = dia;
        this.formaDePago = formaDePago;
        this.obraSocial = obraSocial;
        this.especialidad = especialidad;
        this.asistenciaPaciente = asistenciaPaciente;
    }
    
    public Turno() {
        
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

    public boolean isAsistenciaPaciente() {
        return asistenciaPaciente;
    }

    public void setAsistenciaPaciente(boolean asistenciaPaciente) {
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
    
    }
    
    
    
    
    
    
    
}
