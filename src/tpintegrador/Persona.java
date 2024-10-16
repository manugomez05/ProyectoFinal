/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;

/**
 *
 * @author Fernanda
 */
public abstract class Persona {
    private String nombre;
    private String apellido;
    private int dni;
    private String fechaDeNacimiento;
    
    Validador pideYValida = new Validador();
    

    public Persona(String nombre, String apellido, int dni, String fechaDeNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    
    public Persona() {
        this.setNombre(pideYValida.pidoStringYValida("nombre"));
        this.setApellido(pideYValida.pidoStringYValida("apellido"));
        this.setDni(pideYValida.pidoDniYValido());
        this.setFechaDeNacimiento(pideYValida.pidoFechaNacimientoYValido());
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    
    
    /*
    //public void mostrarInformacion(){
        //System.out.println("Nombre: "+getNombre()+", Apellido: "+getApellido()+", DNI: "+getDni()+", Fecha de Nacimiento: "+getFechaDeNacimiento());
        
}*/
    
   
}
