/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;

public class Medico extends Trabajador {

    int IdMedico;
    String expecialidad;

    public Medico(boolean inicializar) {
        
        super(inicializar);
        
        if (inicializar) {
            
            this.setCuentaBancaria(pideYValida.pidoCbuYValido());
            this.setSalario(pideYValida.pidoSalarioYValido());
            this.setDiasDeTrabajo(pideYValida.pidoDiasDeTrabajoYValido());
            this.setExpecialidad(pideYValida.pidoEspecialidadYValido());
        }
        
        
    }

    public int getIdMedico() {
        return IdMedico;
    }

    public void setIdMedico(int IdMedico) {
        this.IdMedico = IdMedico;
    }
    public String getExpecialidad() {
        return expecialidad;
    }

    public void setExpecialidad(String expecialidad) {
        this.expecialidad = expecialidad;
    }
    
}
