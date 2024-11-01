package tpintegrador;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;                

public class TpIntegrador {

    public static void main(String[] args) throws UnsupportedEncodingException {

        //
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        /*

        --------------------------------------------------------------------
        solucion de conectar y desconectar DB
            cree el metodo getConnection() que devuelve el atributo db que tiene la conexion a la DB
                se lo paso como parametro a la funciones que necesitan realizar operaciones con la DB
                    en los casos de Factura y Turno igualo el atributo conDB.db a la conexion que tengo por parametro
        --------------------------------------------------------------------
        
        */

        Menu menus = new Menu();
        menus.menuGestionClinica();
        
    }
}
