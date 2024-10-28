
package tpintegrador;

import java.util.Random;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;


public class Factura {
    private static String tipoFactura;
    private double montoTotal;
    ResultSet rs;
    Validador pideYValida = new Validador();
    ConexionDB conDB = new ConexionDB();
    
    public void mostrarFactura(){
        
        
        
        int dniIn = pideYValida.pidoDniYValido();
        
        /*
        mostramos para pagar el turno mas proximo a la fecha actual
        
        */
        String sql = "SELECT * FROM tb_Turnos WHERE fechaTurno>=date() AND dniPaciente=" + dniIn + " LIMIT 1;";
        
        rs = conDB.realizaConsulta(sql);
        
        
        
        
        try {
            
            String obraSocialJson = rs.getString(5);
            JSONObject obraSocialObj = new JSONObject(obraSocialJson);
            
            float montoSinDescuento = (float) calcularMonto();
            float montoConDescuento = montoSinDescuento * (1 - (float)obraSocialObj.getInt("descuento")/100);
            
            System.out.println("------------ FACTURA ------------");
            System.out.println("Tipo de Factura: " + this.getTipoFactura());
            System.out.println("Fecha: " + rs.getString(3));
            System.out.println("Cliente: ");
            System.out.println("DNI: " + rs.getInt(2));
            System.out.println("Especialidad: " + rs.getString(6));
            System.out.println("Obra Social: " + obraSocialObj.getString("nombre"));
            System.out.println("Monto a Pagar CON descuento: $" + montoConDescuento);
            System.out.println("Monto a Pagar sin descuento: $" + montoSinDescuento);
            System.out.println("---------------------------------");
        }catch(SQLException ex) {
            System.out.println("SQLException: " + ex);
        }
        
        
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
