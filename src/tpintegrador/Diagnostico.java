
package tpintegrador;

public class Diagnostico {
    
    private String enfermedad; 
    private String medicacion; 
    private String recomendaciones; 
    private Receta receta; 
    
    public void generarTratamiento(){
        //a completar
        // Receta tratamiento = new Receta();
        
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Diagnostico(String enfermedad, String medicacion, String recomendaciones, Receta receta) {
        this.enfermedad = enfermedad;
        this.medicacion = medicacion;
        this.recomendaciones = recomendaciones;
        this.receta = receta;
    }
    
    
}
