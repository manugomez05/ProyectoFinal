/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
class Validador {
    
    String limpiarBuffer;
    Scanner input = new Scanner(System.in);
    int indiceIn = 0;
    boolean indiceInvalido = true;
    
    
    public String pidoStringYValida(String campo) {
        
        String stringIn = "";
        boolean stringInvalido = true;
        
        //Pido nombre y apellido
        while (stringInvalido) {
            try {

                do  {
                    System.out.println("Ingrese el" + campo +":");
                    stringIn = input.nextLine();    

                    if (stringIn.matches("\\d+")) {
                        throw new Exception();
                    }

                //comprueba si los campos contienen solo numeros
                } while(stringIn.matches("\\d+"));

                stringInvalido = false;

            }  catch(Exception e) {
                System.out.println("-Exception: " + e);
                System.out.println("--El string debe contener SOLO letras");
            }
        }
        
        return stringIn;
    }
    
    public String pidoFechaNacimientoYValido() {
        
        String fecha = "";
        boolean fechaInvalida = true;
        int diaNacimientoIn = 0, mesNacimientoIn = 0, anioNacimientoIn = 0;
        
        //Pido la fecha de Nacimiento y la valido
        while (fechaInvalida) {
            try {

                limpiarBuffer = input.nextLine();

                while (diaNacimientoIn > 31 || diaNacimientoIn < 1) {
                    System.out.println("Ingrese el dia de nacimiento:");
                    diaNacimientoIn = input.nextInt();

                    //si el numero ingresado esta fuera de rango, se emite una exception
                    if (diaNacimientoIn > 31 || diaNacimientoIn < 1) {
                        throw new Exception();
                    }
                }

                while (mesNacimientoIn > 12 || mesNacimientoIn < 1) {
                    System.out.println("Ingrese el mes de nacimiento:");
                    mesNacimientoIn = input.nextInt();

                    //si el numero ingresado esta fuera de rango, se emite una exception
                    if (mesNacimientoIn > 12 || mesNacimientoIn < 1) {
                        throw new Exception();
                    }
                }

                while (anioNacimientoIn < 1) {
                    System.out.println("Ingrese el anio de nacimiento:");
                    anioNacimientoIn = input.nextInt();

                    //si el numero ingresado esta fuera de rango, se emite una exception
                    if (anioNacimientoIn < 1) {
                        throw new Exception();
                    }
                }

                fechaInvalida = false;
                fecha = diaNacimientoIn + "/" + mesNacimientoIn + "/" + anioNacimientoIn;

            } catch(InputMismatchException ime) {
                System.out.println("-InputMismatchException: " + ime);
                System.out.println("--Error: Ingrese un numero");
            } catch(Exception e) {
                System.out.println("-Exception: " + e);
                System.out.println("--Rango invalido");
            }

        }
        
        return fecha;
    }
    
    public int pidoDniYValido() {
        
        int dni = 0;
        boolean dniInvalido = true;
        
        while (dniInvalido) {

            try {

                limpiarBuffer = input.nextLine();

                System.out.println("Ingrese el DNI:");
                dni = input.nextInt();

                //String.valueOf(dniIn).length() es la longitud del DNI ingresado
                if (String.valueOf(dni).length() != 8) {
                    throw new Exception();
                }

                dniInvalido = false;

            } catch(InputMismatchException ime) {
                System.out.println("-InputMismatchException: " + ime);
                System.out.println("--Error: Ingrese un DNI valido");
            } catch(Exception e) {
                System.out.println("-Exception: " + e);
                System.out.println("--El DNI ingresado NO tiene 8 digitos");
            }

        }
        
        return dni;
    }
    
    public String pidoEspecialidadYValido() {
        
        String especialidad = "";
        
        ArrayList<String> especialidades = new ArrayList<>(3);
        especialidades.add("Pediatria");
        especialidades.add("Dermatologia");
        especialidades.add("Odontologia");

        while (indiceInvalido) {

            try {

                limpiarBuffer = input.nextLine();

                System.out.println("Elija una especialidad");
                for (String i: especialidades) {
                    System.out.println( (especialidades.indexOf(i)) + 1 +"- " + i);
                }
                indiceIn = input.nextInt() - 1;
                especialidad = especialidades.get(indiceIn);

                indiceInvalido = false;


            } catch(InputMismatchException ime) {
                System.out.println("-InputMismatchException: " + ime);
                System.out.println("--Error: Ingrese un numero valido");
            } catch(Exception e) {
                System.out.println("-Exception: " + e);
                System.out.println("--El indice ingresado esta fuera de rango");
            }

        }
        
        return especialidad;
    }
    
    public int pidoCbuYValido() {
        
        int cbu = 0;
        boolean cbuInvalido = true;
        
        while (cbuInvalido) {

            try {

                limpiarBuffer = input.nextLine();

                System.out.println("Ingrese su cuenta Bancaria (CBU):");
                cbu = input.nextInt();

                //String.valueOf(dniIn).length() es la longitud del CBU ingresado
                if (String.valueOf(cbu).length() != 10) {
                    throw new Exception();
                }

                cbuInvalido = false;

            } catch(InputMismatchException ime) {
                System.out.println("-InputMismatchException: " + ime);
                System.out.println("--Error: Ingrese un CBU valido");
            } catch(Exception e) {
                System.out.println("-Exception: " + e);
                System.out.println("--El CBU ingresado NO tiene 10 digitos");
            }

        }
        
        return cbu;
    }
    
    public float pidoSalarioYValido() {
        
        float salario = 0;
        boolean salarioInvalido = true;
        
        while (salarioInvalido) {
                        
            try {

                limpiarBuffer = input.nextLine();

                System.out.println("Ingrese el salario");
                salario = input.nextFloat();

                if (salario < 0) {
                    throw new Exception();
                }

                salarioInvalido = false;

            } catch(InputMismatchException ime) {
                System.out.println("-InputMismatchException: " + ime);
                System.out.println("--Error: Ingrese un salario valido");
            } catch(Exception e) {
                System.out.println("-Exception: " + e);
                System.out.println("--El salario NO puede ser negativo");
            }
        }
        
        return salario;
    }
    
    public String pidoDiasDeTrabajoYValido() {
        ArrayList<String> diasDeSemana = new ArrayList<>(5);
        diasDeSemana.add("Lunes");
        diasDeSemana.add("Martes");
        diasDeSemana.add("Miercoles");
        diasDeSemana.add("Jueves");
        diasDeSemana.add("Viernes");
        ArrayList<String> diasDeTrabajoIn = new ArrayList<>();
        
        boolean diaInvalido = true;
        String diaIn = "";

        while (diaInvalido) {

            try {

                limpiarBuffer = input.nextLine();

                while (indiceIn != 6) {
                    System.out.println("Ingrese los dias de trabajo");
                    for (String i: diasDeSemana) {
                        System.out.println( (diasDeSemana.indexOf(i)) + 1 +"- " + i);
                    }
                    System.out.println("6- Salir");

                    indiceIn = input.nextInt();
                    diaIn = diasDeSemana.get(indiceIn-1);
                    diasDeTrabajoIn.add(diaIn);
                    diasDeSemana.remove(indiceIn-1);

                }

                if (indiceIn < 0 || indiceIn > 6 && indiceIn != 6) {
                    throw new Exception();
                }

                diaInvalido = false;

            } catch(InputMismatchException ime) {
                System.out.println("-InputMismatchException: " + ime);
                System.out.println("--Error: Ingrese un indice valido");
            } catch(Exception e) {
                System.out.println("-Exception: " + e);
                System.out.println("--Indice fuera de rango");
            }
        }

        Gson gson = new Gson();
        // Convierte el arreglo a JSON
        String jsondiasDeTrabajoIn = gson.toJson(diasDeTrabajoIn);
        
        return jsondiasDeTrabajoIn;
    }
    
    public String pidoFormaDePagoYValido() {
        String formaDePago = "";
        
        ArrayList<String> formasDePago = new ArrayList<>(2);
        formasDePago.add("Efectivo");
        formasDePago.add("Tarjeta");

        while (indiceInvalido) {

            try {

                limpiarBuffer = input.nextLine();

                System.out.println("Elija la forma de pago");
                for (String i: formasDePago) {
                    System.out.println( (formasDePago.indexOf(i)) + 1 +"- " + i);
                }
                indiceIn = input.nextInt() - 1;
                formaDePago = formasDePago.get(indiceIn);

                indiceInvalido = false;


            } catch(InputMismatchException ime) {
                System.out.println("-InputMismatchException: " + ime);
                System.out.println("--Error: Ingrese una forma de pago valida");
            } catch(Exception e) {
                System.out.println("-Exception: " + e);
                System.out.println("--El indice ingresado esta fuera de rango");
            }

        }
        
        return formaDePago;
    }
    
    public String pidoObraSocialYValido() {
        
        String obraSocial = "";
        
        ArrayList<String> obrasSocialesCubiertas = new ArrayList<>(4);
        obrasSocialesCubiertas.add("OSEP");
        obrasSocialesCubiertas.add("OSDE");
        obrasSocialesCubiertas.add("DAMSU");
        obrasSocialesCubiertas.add("Medife");
        while (indiceInvalido) {

            try {

                limpiarBuffer = input.nextLine();

                System.out.println("Elija su obra social");
                for (String i: obrasSocialesCubiertas) {
                    System.out.println( (obrasSocialesCubiertas.indexOf(i)) + 1 +"- " + i);
                }
                System.out.println("5- No tengo obra social");

                indiceIn = input.nextInt() - 1;
                if (indiceIn == 5) {
                    obraSocial = "Ninguna";
                } else if (indiceIn < 0 || indiceIn > 5) {
                    throw new Exception();
                } else {
                    obraSocial = obrasSocialesCubiertas.get(indiceIn);
                }

                indiceInvalido = false;


            } catch(InputMismatchException ime) {
                System.out.println("-InputMismatchException: " + ime);
                System.out.println("--Error: Ingrese una obra social valida");
            } catch(Exception e) {
                System.out.println("-Exception: " + e);
                System.out.println("--El indice ingresado esta fuera de rango");
            }

        }
        
        return obraSocial;
    }
 
    
    public void Validador() {
        
    }
}
