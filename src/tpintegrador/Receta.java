
package tpintegrador;

public class Receta {
    
    private String medicamentos; 
    private String firma;
    private String fecha;
    private String vencimiento; 
    
    public void consultaReceta(){
        //consultar db
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Receta(String medicamentos, String firma, String fecha, String vencimiento) {
        this.medicamentos = medicamentos;
        this.firma = firma;
        this.fecha = fecha;
        this.vencimiento = vencimiento;
    }
    
    
}
