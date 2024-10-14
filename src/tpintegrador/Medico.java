/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;

public class Medico extends Trabajador {

    int IdMedico;
    String expecialidad;

    public Medico(int IdMedico, String expecialidad, int cuentaBancaria, float salario, String diasDeTrabajo, String nombre, String apellido, int dni, String fechaDeNacimiento) {
        super(cuentaBancaria, salario, diasDeTrabajo, nombre, apellido, dni, fechaDeNacimiento);
        this.IdMedico = IdMedico;
        this.expecialidad = expecialidad;
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
