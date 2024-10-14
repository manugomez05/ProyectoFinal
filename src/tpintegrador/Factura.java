/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;

/**
 *
 * @author Fernanda
 */
public class Factura {
    private String tipoFactura;
    private double montoTotal;
    
    
    
    
    public void mostrarFactura(){
        
    }
    
    public void calcularMonto(){
        
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Factura(String tipoFactura, double montoTotal) {
        this.tipoFactura = tipoFactura;
        this.montoTotal = montoTotal;
    }
    
}
