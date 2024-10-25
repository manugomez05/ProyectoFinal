
package tpintegrador;

import java.util.Random;
import java.sql.ResultSet;


public class Factura {
    private static String tipoFactura;
    private double montoTotal;
    ResultSet rs;
    Validador pideYValida = new Validador();
    ConexionDB conDB = new ConexionDB();
    
    public void mostrarFactura(){
        
        
        
        System.out.println (this.getTipoFactura());
        int dniIn = pideYValida.pidoDniYValido();
        
        //String sql = "";
        
        rs = conDB.realizaConsulta("");
        
        
        
        
        
        System.out.println("Obra social: "  );
        System.out.println("DNI: ");
        System.out.println("Monto a pagar: ");
        System.out.println("");
    }
    
    public double calcularMonto(){
        
        double monto=0;
        Random rd = new Random();
        
        //Monto calculado con un numero random
        monto = rd.nextDouble(5000, 20000);
        
        return monto;
        
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura =  "Factura tipo B";
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Factura(String tipoFactura, double montoTotal) {
        this.tipoFactura = tipoFactura;
        this.montoTotal = montoTotal;
    }
    
    public Factura(){
        
    }
    
}
