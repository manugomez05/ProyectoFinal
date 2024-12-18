

package tpintegrador;

import com.google.gson.Gson;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Validador {
    
    String limpiarBuffer;
    Scanner input = new Scanner(System.in);
    int indiceIn = 0;
    boolean indiceInvalido = true;
    ConexionDB conDB;
    ResultSet rs;
    
    
    public String pidoStringYValida(String campo) {
        
        String stringIn = "";
        boolean stringInvalido = true;
        
        //Pido nombre y apellido
        while (stringInvalido) {
            
            limpiarBuffer = input.nextLine();
            
            try {

                do  {
                    System.out.println("Ingrese el " + campo +":");
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

        boolean fechaInvalida = true;
        int diaIn = 0, mesIn = 0, anioIn = 0;
        String fechaNacimiento = "";
        
        
        do {
            try {
                
                limpiarBuffer = input.nextLine();
                
                System.out.println("Ingrese el día de Nacimiento:");
                diaIn = input.nextInt();
                if (diaIn < 1 || diaIn > 31) {
                    throw new Exception("Día fuera de rango");
                }

                System.out.println("Ingrese el mes de Nacimiento:");
                mesIn = input.nextInt();
                if (mesIn < 1 || mesIn > 12) {
                    throw new Exception("Mes fuera de rango");
                }

                
                while (anioIn < 1930 || anioIn > 2005) {
                    System.out.println("Ingrese el año de Nacimiento:");
                    anioIn = input.nextInt();

                    //si el numero ingresado esta fuera de rango, se emite una exception
                    if (anioIn < 1) {
                        throw new Exception();
                    }
                }
                
                //SALIDA CON FORMATO YYYY-MM-DD
                if ((diaIn >= 1 && diaIn <= 9) && (mesIn >= 1 && mesIn <= 9)) {
                    fechaNacimiento = anioIn + "-0" + mesIn + "-0" + diaIn;
                } else if ((diaIn >= 1 && diaIn <= 9)) {
                    fechaNacimiento = anioIn + "-" + mesIn + "-" + "0" + diaIn;
                }
                else if ((mesIn >= 1 && mesIn <= 9)) {
                    fechaNacimiento = anioIn + "-0" + mesIn + "-" + diaIn;
                }
                else {
                    fechaNacimiento = anioIn + "-" + mesIn + "-" + diaIn;
                }
                
                
                fechaInvalida = false;
                
            } catch (InputMismatchException ime) {
                System.out.println("-Error: Ingrese un número válido.");
                input.next();  // Limpiar el buffer para evitar el loop
            } catch (Exception e) {
                System.out.println("-Error: " + e.getMessage());
            }
        } while (fechaInvalida);
        
        
        
        return fechaNacimiento;
        
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
        indiceInvalido = true;
        
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
        diasDeSemana.add("Miércoles");
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
                    
                    if (indiceIn != 6) {
                        diaIn = diasDeSemana.get(indiceIn-1);
                        diasDeTrabajoIn.add(diaIn);
                        diasDeSemana.remove(indiceIn-1);
                    }
                    
                    
                    if (indiceIn < 0 || indiceIn > 6 && indiceIn != 6) {
                        throw new Exception();
                    }

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
    
    public ArrayList pidoColumnasAModificarYValido(ArrayList<String> columnasArr ) {
        
        int indiceSalir = 0;
        ArrayList<String> columnasACambiar = new ArrayList<>();
        
        do {
            limpiarBuffer = input.nextLine();

            indiceSalir = columnasArr.size();

            System.out.println("Elija la columna/s a modificar");
            for (String i: columnasArr) {

                if (!(columnasArr.indexOf(i) == 0)) {
                    System.out.println((columnasArr.indexOf(i)) + "- " + i);
                }

            }
            System.out.println(indiceSalir + "- Salir");

            try {
                indiceIn = input.nextInt();

                if (indiceIn != indiceSalir) {
                    columnasACambiar.add(columnasArr.get(indiceIn));
                    columnasArr.remove(indiceIn);
                }

            } catch(IndexOutOfBoundsException iob) {
                System.out.println("-IndexOutOfBoundsException: " + iob);
                System.out.println("--Indice ingresado fuera de rango");
            } catch (InputMismatchException ime) {
                System.out.println("-InputMismatchException: " + ime);
                System.out.println("--Ingrese un numero");
            }

        } while (indiceIn != indiceSalir);
        
        return columnasACambiar;
        
    }
    
    public String pidoFormaDePagoYValido() {
        String formaDePago = "";
        indiceInvalido = true;
        
        
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
    
    public ObraSocial pidoObraSocialYValido() {
        
        ObraSocial obraSocialPaciente = null;
        
        indiceInvalido = true;
        
        ArrayList<ObraSocial> obrasSocialesCubiertas = new ArrayList<>(4);
        
        ObraSocial obraSocial1 = new ObraSocial("OSEP", 10);
        ObraSocial obraSocial2 = new ObraSocial("OSDE", 5);
        ObraSocial obraSocial3 = new ObraSocial("DAMSU", 5);
        ObraSocial obraSocial4 = new ObraSocial("Medife", 15);
        
        obrasSocialesCubiertas.add(obraSocial1);
        obrasSocialesCubiertas.add(obraSocial2);
        obrasSocialesCubiertas.add(obraSocial3);
        obrasSocialesCubiertas.add(obraSocial4);
        
        
        while (indiceInvalido) {

            try {

                limpiarBuffer = input.nextLine();

                System.out.println("Elija su obra social");
                for (ObraSocial i: obrasSocialesCubiertas) {
                    System.out.println( (obrasSocialesCubiertas.indexOf(i)) + 1 +"- " + i.getNombre());
                }
                System.out.println("5- No tengo obra social");

                indiceIn = input.nextInt() - 1 ;
                if (indiceIn == 4) {
                    obraSocialPaciente = new ObraSocial("Ninguna", 0);
                } else if (indiceIn < 0 || indiceIn > 4) {
                    throw new Exception();
                } else {
                    obraSocialPaciente = obrasSocialesCubiertas.get(indiceIn);
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

        return obraSocialPaciente;
    }
 
    public int pidoEnteroYValido(String mensaje, ArrayList arrayIndice) {
    
        boolean enteroInvalido = true;
        
        int entero = 0;
        
        //System.out.println(arrayIndice);
        
        if (!arrayIndice.isEmpty()) {
            while (enteroInvalido) {
            
                try {
                    
                    
                    limpiarBuffer = input.nextLine();

                    System.out.println(mensaje);
                    entero = input.nextInt();

                    if (arrayIndice.contains(entero)) {
                        enteroInvalido = false;
                        
                    } else {
                        throw new Exception();
                    }
                } catch(InputMismatchException ime) {
                    System.out.println("-InputMismatchException: " + ime);
                    System.out.println("--Error: Ingrese un indice valido");
                } catch(Exception e) {
                    System.out.println("-Exception: " + e);
                    System.out.println("--El indice ingresado no existe");
                }
            
            }
        } else {
            return -00;
        }
        
        return entero;
        
    }
    
    public Validador() {
        
    }
}
