/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpintegrador;
import java.sql.*;
/**
 *
 * @author Fernanda
 */
public interface AtencionMedica {

    public void agregarRegistro(String tablaIn, Connection conDB);
    public void modificarRegistro(String tablaIn);
    public void eliminarRegistro(String tablaIn);
    public void mostrarTabla(String tablaIn);
    
}
