
package tpintegrador;
import com.google.gson.Gson;
import java.sql.*;
import java.util.Scanner;
import java.lang.*;
import java.util.ArrayList;


public class ConexionDB implements AtencionMedica {
    
   
    
    
    Scanner input = new Scanner(System.in);
    Connection db = null;
    String limpiarBuffer;
    PreparedStatement ps = null;
    Statement stmt;
    ResultSet rs;
    String sql;
    Validador pideYValida = new Validador();
    ArrayList<Integer> idsDeTabla = new ArrayList<>();
    Gson gson = new Gson();
    int idIn, dniIn;
    
    //conecta a la base de datos
    public void conectarDB() {
    
        try {
    
            Class.forName("org.sqlite.JDBC");
            db = DriverManager.getConnection("jdbc:sqlite:ClinicaVitalityDB.sqlite");
            db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Pacientes(idPaciente INTEGER NOT NULL, Nombre TEXT, Apellido TEXT, DNI INTEGER(8), fechaNacimiento TEXT, PRIMARY KEY(idPaciente AUTOINCREMENT) ) ").execute();
            db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Medicos(idMedico INTEGER NOT NULL, Nombre TEXT, Apellido TEXT, DNI INTEGER(8), fechaNacimiento TEXT, cuentaBancaria INTEGER(10), salario DOUBLE, diasDeTrabajo TEXT, especialidad TEXT, PRIMARY KEY(idMedico AUTOINCREMENT) ) ").execute();
            //db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Turnos(idTurno INTEGER NOT NULL, dniPaciente INTERGER(8), diaTurno TEXT, formaDePago TEXT, obraSocial TEXT, especialidad TEXT, asistenciaPaciente INTEGER,PRIMARY KEY(idTurno AUTOINCREMENT)) ").execute();
            db.prepareStatement("CREATE TABLE IF NOT EXISTS tb_Turnos(idTurno INTEGER NOT NULL, dniPaciente INTERGER(8), fechaTurno TEXT, formaDePago TEXT, obraSocial TEXT, especialidad TEXT, asistenciaPaciente INTEGER,PRIMARY KEY(idTurno AUTOINCREMENT)) ").execute();
            
            
            if (db != null) {
                System.out.println("Conexion Exitosa!");
            } else {
                System.out.println("Ha fallado la conexion");
            }
            
        }catch(SQLException ex) {
            System.out.println("SQLException: " + ex);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ClassNotFoundException: " + cnfe);
        }
    }
    
    //desconecta de la base de datos
    public void desconectarDB() {
        
        try {
            db.close();
        }catch(SQLException ex) {
            System.out.println("SQLException: " + ex);
        }
        
    }
    

    //agrega un registro a alguna tabla     
    public void agregarRegistro(String tablaIn) {
        
        //conectarDB();
        
        //Agrego el registro a la tabla
        try {

            switch(tablaIn) {
                case "tb_Pacientes":
                    
                    Paciente newPaciente = new Paciente(true);

                    ps = db.prepareStatement("INSERT INTO tb_Pacientes(Nombre,Apellido,DNI,fechaNacimiento) VALUES (?,?,?,?)");
                    ps.setString(1, newPaciente.getNombre());
                    ps.setString(2, newPaciente.getApellido());
                    ps.setInt(3, newPaciente.getDni());
                    ps.setString(4, newPaciente.getFechaDeNacimiento());
                    
                    
                    System.out.println(newPaciente.getNombre() + " ha sido registrado correctamente");
                    break;
                case "tb_Medicos":
                    
                    Medico newMedico = new Medico(true);
                                        
                    ps = db.prepareStatement("INSERT INTO tb_Medicos(Nombre,Apellido,DNI,fechaNacimiento,cuentaBancaria,salario,diasDeTrabajo,especialidad) VALUES (?,?,?,?,?,?,?,?)");
                    ps.setString(1, newMedico.getNombre());
                    ps.setString(2, newMedico.getApellido());
                    ps.setInt(3, newMedico.getDni());
                    ps.setString(4, newMedico.getFechaDeNacimiento());
                    
                    ps.setInt(5, newMedico.getCuentaBancaria());
                    ps.setFloat(6, newMedico.getSalario());
                    ps.setString(7, newMedico.getDiasDeTrabajo());
                    ps.setString(8, newMedico.getExpecialidad());
                    
                    System.out.println(newMedico.getNombre() + " ha sido registrado correctamente");
                    
                    break;
                case "tb_Turnos":
                    
                    Turno newTurno = new Turno(true);
                    
                    ps = db.prepareStatement("INSERT INTO tb_Turnos(dniPaciente,fechaTurno,formaDePago,obraSocial,especialidad,asistenciaPaciente) VALUES (?,?,?,?,?,?)");
                    
                    ps.setInt(1, newTurno.getDniPaciente());
                    ps.setString(2, newTurno.getDia());
                    ps.setString(3, newTurno.getFormaDePago());
                    
                    Gson gson = new Gson();
                    // Convierte el arreglo a JSON
                    String jsonObraSocialPaciente = gson.toJson(newTurno.getObraSocial());
                    
                    ps.setString(4, jsonObraSocialPaciente);
                    ps.setString(5, newTurno.getEspecialidad());
                    ps.setInt(6,0);
                    
                    
                    
                    System.out.println("Se ha registrado correctamente un turno para " + newTurno.getDniPaciente());
                    break;
            }
            ps.execute();
            
        }catch(SQLException ex) {
            System.out.println("SQLException: " +ex);
        }
       
        //desconectarDB();
        
    }
        
        
    
    public ArrayList consultaIdsDeTabla(String tablaIn) {
        
        
        //conectarDB();
        
        ArrayList<Integer> arrayDeIds = new ArrayList<>();
        
        try {
        
            stmt = db.createStatement();
        
        
            switch (tablaIn) {
                //dependiendo de la tabla consulto el valor de la id
                case "tb_Medicos":
                        rs = stmt.executeQuery("SELECT idMedico FROM tb_Medicos;"); 
                        break;
                    case "tb_Pacientes":
                        rs = stmt.executeQuery("SELECT idPaciente FROM tb_Pacientes;"); 
                        break;
                    case "tb_Turnos":
                        rs = stmt.executeQuery("SELECT idTurno FROM tb_Turnos;"); 
                        break;

            }

            while (rs.next()) {
                //dependiendo de la tabla tomo el valor de id
                switch (tablaIn) {
                    case "tb_Medicos":
                        arrayDeIds.add(rs.getInt("idMedico"));
                        break;
                    case "tb_Pacientes":
                        arrayDeIds.add(rs.getInt("idPaciente"));
                        break;
                    case "tb_Turnos":
                        arrayDeIds.add(rs.getInt("idTurno"));
                        break;

                }
            }
            
        } catch(SQLException ex) {
            System.out.println("SQLException: " +ex);
        }
        
        //desconectarDB();
        
        return arrayDeIds;
        
    }
    
    public ArrayList consultoColumnasDeTabla (String tablaIn) {
    
        //conectarDB();
        
        ArrayList<String> columnasDeTabla = new ArrayList<>();
        String columna = "";
        try {
        
            stmt = db.createStatement();
            rs = stmt.executeQuery("PRAGMA table_info(" + tablaIn + ");"); 

            //agrego las columnas de la tabla a un array
            
            while (rs.next()) {
                columna = rs.getString("name");
                
                if (!columna.equals("dniPaciente") && !columna.equals("especialidad")) {
                    columnasDeTabla.add(rs.getString("name")); 
                }
                
                
            }
            
        } catch(SQLException ex) {
            System.out.println("SQLException: " +ex);
        }
        
        //desconectarDB();
        return columnasDeTabla;
        
    }
    
    
    public String armaStringConsultaSql(ArrayList columnasACambiar, String columna, String nuevoValor) {
        
        //conectarDB();
        
        
        String consultaSql = "";
        
        //System.out.println(columna);
        //System.out.println(nuevoValor);
        
        if (columnasACambiar.indexOf(columna) == 0) {
            
            if (columnasACambiar.size() == 1) {
                consultaSql = consultaSql + " " + columna + "='" + nuevoValor + "'";
            } else {
                consultaSql = consultaSql + " " + columna + "='" + nuevoValor + "', ";
            }
            
            

        } else if (columnasACambiar.indexOf(columna) == columnasACambiar.size()-1) {
            consultaSql = consultaSql + columna + "='" + nuevoValor + "'";

        } else {
            consultaSql = consultaSql + columna + "='" + nuevoValor + "', ";

        }
        
        
        //desconectarDB();
        return consultaSql;
    }
    
    
    public ResultSet realizaConsulta(String consulta) {
        
        try {
                
            stmt = db.createStatement();
            rs = stmt.executeQuery(consulta);
            
            //System.out.println(rs);

        } catch(SQLException ex) {
            System.out.println("SQLException: " +ex);
        }
        
        return rs;
    }
    
    public void modificarRegistro(String tablaIn) {
        //dependiendo de la tabla 
        //  pregunto que campos va a querer modificar
        //      pregunto por los nuevos valores
        
        /*
        Para obtener solo los nombres de las columnas 
        
        PRAGMA table_info(tb_Medicos);
        SELECT name FROM pragma_table_info('tb_Medicos');
        
        
        UPDATE nombre_tabla
        SET columna1 = valor1, columna2 = valor2, ...
        WHERE condición;
        
        */
        
        //conectarDB();
        
        int indiceIn = 0, indiceSalir = 0;
        ArrayList<Integer> idsDeTabla = new ArrayList<>();
        String columnasACambiarString = "";
        String dniString = "", cbuString = "", salarioString = "", enteroString = "";
        
        
        //Muestro la tabla, si se ingresa una tabla incorrecta, no pasa de aca
        mostrarTabla(tablaIn); 

        idsDeTabla = consultaIdsDeTabla(tablaIn);
        System.out.println(idsDeTabla);
        
        if (!idsDeTabla.isEmpty()) {
            
            try {
            
                //pido Id
                idIn = pideYValida.pidoEnteroYValido("Ingrese el ID a modificar", idsDeTabla);

                //consulta las columnas de la tabla 
                ArrayList<String> columnasDeTabla = consultoColumnasDeTabla(tablaIn);
                
                //pido las columnas a modificar y las agrego a un array
                ArrayList<String> columnasACambiar = new ArrayList<>();
                columnasACambiar = pideYValida.pidoColumnasAModificarYValido(columnasDeTabla);

                //recorro el array columnasACambiar y pido los nuevos valores
                if (!(columnasACambiar.isEmpty())) {
                    
                    switch(tablaIn) {
                        case "tb_Pacientes":

                            Paciente pacienteExistente = new Paciente(false);

                            for (String i : columnasACambiar) {

                                //limpiarBuffer = input.nextLine();

                                //dependiendo del campo a modificar, lo pido y voy armando la consulta SQL

                                switch (i) {
                                    case "Nombre":
                                        pacienteExistente.setNombre(pideYValida.pidoStringYValida("nombre"));
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, pacienteExistente.getNombre());

                                        break;
                                    case "Apellido":
                                        pacienteExistente.setApellido(pideYValida.pidoStringYValida("apellido"));
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, pacienteExistente.getApellido());

                                        break;
                                    case "DNI":
                                        pacienteExistente.setDni(pideYValida.pidoDniYValido());
                                        dniString = String.valueOf(pacienteExistente.getDni());
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, dniString);

                                        break;
                                    case "fechaNacimiento":
                                        pacienteExistente.setFechaDeNacimiento(pideYValida.pidoFechaNacimientoYValido());
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, pacienteExistente.getFechaDeNacimiento());

                                        break;
                                }
                            }

                            System.out.println("UPDATE " + tablaIn + " SET " + columnasACambiarString + " WHERE idPaciente=" + idIn);
                            ps = db.prepareStatement("UPDATE " + tablaIn + " SET " + columnasACambiarString + " WHERE idPaciente=" + idIn + ";");
                            ps.execute();



                            break;
                        case "tb_Medicos":

                            Medico medicoExistente = new Medico(false);

                            for (String i : columnasACambiar) {

                                //limpiarBuffer = input.nextLine();

                                //dependiendo del campo a modificar, lo pido y voy armando la consulta SQL

                                switch (i) {
                                    case "Nombre":
                                        medicoExistente.setNombre(pideYValida.pidoStringYValida("nombre"));
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, medicoExistente.getNombre());

                                        break;
                                    case "Apellido":
                                        medicoExistente.setApellido(pideYValida.pidoStringYValida("apellido"));
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, medicoExistente.getApellido());

                                        break;
                                    case "DNI":
                                        medicoExistente.setDni(pideYValida.pidoDniYValido());
                                        dniString = String.valueOf(medicoExistente.getDni());
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, dniString);

                                        break;
                                    case "fechaNacimiento":
                                        medicoExistente.setFechaDeNacimiento(pideYValida.pidoFechaNacimientoYValido());
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, medicoExistente.getFechaDeNacimiento());

                                        break;
                                    case "cuentaBancaria":
                                        medicoExistente.setCuentaBancaria(pideYValida.pidoCbuYValido());
                                        cbuString = String.valueOf(medicoExistente.getCuentaBancaria());

                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, cbuString);

                                        break;
                                    case "salario":
                                        medicoExistente.setSalario(pideYValida.pidoSalarioYValido());
                                        salarioString = String.valueOf(medicoExistente.getCuentaBancaria());

                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, salarioString);

                                        break;
                                    case "diasDeTrabajo":
                                        medicoExistente.setDiasDeTrabajo(pideYValida.pidoDiasDeTrabajoYValido());
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, medicoExistente.getDiasDeTrabajo());

                                        break;
                                    case "especialidad":
                                        medicoExistente.setExpecialidad(pideYValida.pidoEspecialidadYValido());
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, medicoExistente.getExpecialidad());

                                        break;
                                }
                            }

                            System.out.println("UPDATE " + tablaIn + " SET " + columnasACambiarString + " WHERE idTurno=" + idIn);
                            ps = db.prepareStatement("UPDATE " + tablaIn + " SET " + columnasACambiarString + " WHERE idMedico=" + idIn + ";");
                            ps.execute();

                            break;
                        case "tb_Turnos":

                            Turno turnoExistente = new Turno(false);

                            for (String i : columnasACambiar) {

                                //limpiarBuffer = input.nextLine();

                                //dependiendo del campo a modificar, lo pido y voy armando la consulta SQL

                                switch (i) {
                                    /*
                                    case "dniPaciente":
                                        
                                        turnoExistente.setDniPaciente(pideYValida.pidoDniYValido());
                                        dniString = String.valueOf(turnoExistente.getDniPaciente());
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, dniString);
                                        
                                        System.out.println("No se puede modificar el DNI del paciente");
                                        
                                        break;
                                    */
                                    case "fechaTurno":
                                        
                                        ResultSet especialidadDelTurno = realizaConsulta("SELECT especialidad FROM tb_Turnos WHERE idTurno=" + idIn);
                                        
                                        turnoExistente.setDia(pideYValida.pidoFechaYValido(especialidadDelTurno.getString(1)));
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, turnoExistente.getDia());

                                        break;
                                    case "formaDePago":
                                        turnoExistente.setFormaDePago(pideYValida.pidoFormaDePagoYValido());
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, turnoExistente.getFormaDePago());
                                        break;
                                    case "obraSocial":
                                        turnoExistente.setObraSocial(pideYValida.pidoObraSocialYValido());


                                        // Convierte el objeto a JSON
                                        String jsonObraSocial = gson.toJson(turnoExistente.getObraSocial());

                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, jsonObraSocial);

                                        break;
                                    /*
                                    case "especialidad":
                                        
                                        
                                        turnoExistente.setEspecialidad(pideYValida.pidoEspecialidadYValido());
                                        columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, turnoExistente.getEspecialidad());
                                        
                                        System.out.println("No se puede modificar la especialidad del turno");
                                        
                                        break;
                                    */
                                    case "asistenciaPaciente":

                                        if (turnoExistente.getAsistenciaPaciente() == 0) {
                                            turnoExistente.setAsistenciaPaciente(1);
                                            enteroString = String.valueOf(turnoExistente.getAsistenciaPaciente());
                                            columnasACambiarString = columnasACambiarString + armaStringConsultaSql(columnasACambiar, i, enteroString);
                                        } else {
                                            System.out.println("La asistencia ya esta confirmada");
                                        }

                                        break;    

                                }
                            }

                            System.out.println("UPDATE " + tablaIn + " SET " + columnasACambiarString + " WHERE idPaciente=" + idIn);
                            ps = db.prepareStatement("UPDATE " + tablaIn + " SET " + columnasACambiarString + " WHERE idTurno=" + idIn + ";");
                            ps.execute();

                            break;
                    }
                }
                

            } catch(Exception e) {
                System.out.println("-Exception: " + e);
                System.out.println("--Indice fuera de rango");
            }
        }
        
        
        
        
        //desconectarDB();
        
    }
    
    
    public void eliminarRegistro(String tablaIn) {
        
        /*
        DELETE FROM tablaIn WHERE condicion;
        
        el usuario elige que ID eliminar de la lista
            dependiendo de la tabla se ejecuta una linea Sql
        
        */
        
        //conectarDB();
        
        idsDeTabla = consultaIdsDeTabla(tablaIn);
        
        if (!idsDeTabla.isEmpty()) {
        
            mostrarTabla(tablaIn);
            idIn = pideYValida.pidoEnteroYValido("Ingrese el ID a eliminar", idsDeTabla);
            
            try {
        
                switch (tablaIn) {
                    case "tb_Pacientes":
                        db.prepareStatement("DELETE FROM tb_Pacientes WHERE idPaciente=" + idIn ).execute();
                        break;
                    case "tb_Medicos":
                        db.prepareStatement("DELETE FROM tb_Medicos WHERE idMedico=" + idIn ).execute();
                        break;
                    case "tb_Turnos":
                        db.prepareStatement("DELETE FROM tb_Turnos WHERE idTurno=" + idIn ).execute();
                        break;

                }

            } catch(SQLException ex) {
                System.out.println("SQLException: " +ex);
            }
            
            System.out.println("Se ha eliminado el registro con Id = " + idIn);
            
        } else {
        
            System.out.println("La tabla esta vacia");
        }
        
        //desconectarDB();
    }
    
    
    public void vaciarTabla(String tablaIn) {
        /*
        DELETE FROM tablaIn;
        
        Se vacia la tabla 
        */
        
        //conectarDB();
        
        sql = "DELETE FROM " + tablaIn;
        
        try {
            stmt = db.createStatement();
            rs = stmt.executeQuery(sql);
            
        } catch (SQLException ex) {
            System.out.println("-SQLException: " +ex);
            System.out.println("--La tabla " + tablaIn + " no existe");
        }
        
        System.out.println("Se ha vaciado la tabla " + tablaIn);
        
        //desconectarDB();
    }
    
    public void mostrarTabla(String tablaIn) {
        
        /*
        Muestra la tabla 
        */
        
        //conectarDB();
        
        sql = "SELECT * FROM " + tablaIn;
        
        try {
            stmt = db.createStatement();
            rs = stmt.executeQuery(sql);
            
            System.out.println("-------------- " + tablaIn + " --------------");
            while (rs.next()) {
                
                switch (tablaIn) {
                    case "tb_Medicos":
                        
                        System.out.println("- ID: " + rs.getString(1) + 
                                            "\n\t -- Nombre: " + rs.getString(2) + 
                                            "\n\t -- Apellido: " + rs.getString(3) +
                                            "\n\t -- DNI: " + rs.getString(4) +
                                            "\n\t -- Fecha de Nacimiento: " + rs.getString(5) + 
                                            "\n\t -- Cuenta Bancaria: " + rs.getString(6) + 
                                            "\n\t -- Salario: " + rs.getString(7) + 
                                            "\n\t -- Dias de trabajo: " + rs.getString(8) + 
                                            "\n\t -- Especialidad: " + rs.getString(9));
                        break;
                    case "tb_Pacientes":
                        
                        System.out.println("- ID: " + rs.getString(1) + 
                                            "\n\t -- Nombre: " + rs.getString(2) + 
                                            "\n\t -- Apellido: " + rs.getString(3) +
                                            "\n\t -- DNI: " + rs.getString(4) +
                                            "\n\t -- Fecha de Nacimiento: " + rs.getString(5));
                        break;
                    case "tb_Turnos":
                        
                        System.out.println("- ID: " + rs.getString(1) +
                                            "\n\t -- DNI de Paciente: " + rs.getString(2) + 
                                            "\n\t -- Fecha del turno: " + rs.getString(3) + 
                                            "\n\t -- Forma de pago: " + rs.getString(4) +
                                            "\n\t -- Obra social: " + rs.getString(5) +
                                            "\n\t -- Especialidad: " + rs.getString(6) +
                                            "\n\t -- Asistencia del paciente: " + rs.getString(7));
                        break;
                }
                
            }
            System.out.println("---------------------------------------");
            
        } catch (SQLException ex) {
            System.out.println("-SQLException: " +ex);
            System.out.println("--La tabla " + tablaIn + " no existe");
        }
        
        
        //desconectarDB();
    }
    
    public void mostrarHistoriaClinicaPorPaciente() {
        dniIn = pideYValida.pidoDniYValido();
        boolean entro = false;
        sql = "SELECT * FROM tb_turnos WHERE dniPaciente=" + dniIn;

        //conectarDB();
        
        try {
            stmt = db.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                entro = true;
                System.out.println("- ID: " + rs.getString(1)
                        + "\n\t -- DNI de Paciente: " + rs.getString(2)
                        + "\n\t -- Fecha del turno: " + rs.getString(3)
                        + "\n\t -- Forma de pago: " + rs.getString(4)
                        + "\n\t -- Obra social: " + rs.getString(5)
                        + "\n\t -- Especialidad: " + rs.getString(6)
                        + "\n\t -- Asistencia del paciente: " + rs.getString(7));
            }
        } catch (SQLException ex) {
            System.out.println("-SQLException: " + ex);
            System.out.println("--La tabla tb_turnos no existe");
        }
        if (entro == false) {
            System.out.println("El dni que ingresó no posee Historia Clinica");
        }
        
        //desconectarDB();
        
    }

}


