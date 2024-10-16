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
    
    private static int idPaciente; 
    //ArrayList<Turno> historialMedico;

    
    
    public int getIdPaciente() {
        return idPaciente;
    }
        
    public void consultarTurnos() {
        
    }
    
    public void verificarObraSocial(){
        
    }
    
    public Paciente( String nombre, String apellido, int dni, String fechaDeNacimiento) {
        super(nombre, apellido, dni, fechaDeNacimiento);
    }
    
    public Paciente() {
        super();
    }
    


  
    
    
}
