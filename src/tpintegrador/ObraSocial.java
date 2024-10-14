/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;

/**
 *
 * @author Fernanda
 */
public class ObraSocial {
    private String nombre;
    private int descuento;
    
    public void consultarCobertura(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public ObraSocial(String nombre, int descuento) {
        this.nombre = nombre;
        this.descuento = descuento;
    }
    
}
