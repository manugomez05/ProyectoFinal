
package tpintegrador;

import java.util.Random;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;




public class Factura {
    private String tipoFactura =  "Tipo B";
    private double montoTotal;
    ResultSet rs;
    Validador pideYValida = new Validador();
    ConexionDB conDB = new ConexionDB();
    Random numFactura = new Random();
    
    
    public void mostrarFactura(Connection conn) {
        
        try {
            
            conDB.db = conn;
            
            int dniIn = pideYValida.pidoDniYValido();
            
            /*
            mostramos para pagar el turno mas proximo a la fecha actual
            
            */
            String nombreApellido = "SELECT Nombre, Apellido FROM tb_pacientes WHERE DNI=" + dniIn;
            rs = conDB.realizaConsulta(nombreApellido);
            String nombreYapellido = rs.getString(1) + " " +  rs.getString(2);
            
            String sql = "SELECT * FROM tb_Turnos WHERE fechaTurno>=date() AND dniPaciente=" + dniIn + " LIMIT 1;";
            rs = conDB.realizaConsulta(sql);
            
            if (rs.getString(1) != null) {
                try {
                
                String obraSocialJson = rs.getString(5);
                JSONObject obraSocialObj = new JSONObject(obraSocialJson);
                
                float montoSinDescuento = (float) calcularMonto();
                float montoConDescuento = montoSinDescuento * (1 - (float)obraSocialObj.getInt("descuento")/100);
                
                System.out.println("------------ FACTURA ------------");
                System.out.println("Numero de Factura: " + numFactura.nextInt(10000, 99999));
                System.out.println("Tipo de Factura: " + this.getTipoFactura());
                System.out.println("Fecha de turno: " + rs.getString(3));
                System.out.println("Cliente: " + nombreYapellido);
                System.out.println("DNI: " + rs.getInt(2));
                System.out.println("Especialidad: " + rs.getString(6));
                System.out.println("Obra Social: " + obraSocialObj.getString("nombre"));
                System.out.println("Monto a Pagar CON descuento: $" + montoConDescuento);
                System.out.println("Monto a Pagar sin descuento: $" + montoSinDescuento);
                System.out.println("---------------------------------");
            }catch(SQLException ex) {
                System.out.println("SQLException: " + ex);
            }
            } else {
                System.out.println("No se encontro ninguna factura asociada a su DNI");
            }
            
            
            
        }catch(SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
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
