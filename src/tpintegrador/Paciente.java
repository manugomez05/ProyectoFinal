/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;

/**
 *
 * @author Estudiante
 */
public class Paciente extends Persona{
    
    private int idPaciente; 
    private String sintomas;
    //ArrayList<Turno> historialMedico;

    public Paciente(int idPaciente, String sintomas) {
        setIdPaciente(idPaciente);
        setSintomas(sintomas);
        //this.historialMedico = historialMedico; 
    }

  
    
    
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }
    
    
    public void consultarTurnos() {
        
    }
    
    public void verificarObraSocial(){
        
    }
}
