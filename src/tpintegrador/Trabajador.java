
package tpintegrador;

public class Trabajador extends Persona {
    int cuentaBancaria;
    float salario;
    String diasDeTrabajo;

 

    public int getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(int cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getDiasDeTrabajo() {
        return diasDeTrabajo;
    }

    public void setDiasDeTrabajo(String diasDeTrabajo) {
        this.diasDeTrabajo = diasDeTrabajo;
    }

    public Trabajador(boolean inicializar) {
        super(inicializar);
    } 
}
