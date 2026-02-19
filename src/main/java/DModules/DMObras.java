/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DModules;

import DBConexiones.ConexionDB;
import MapeoTablas.DBObras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author miare
 */
public class DMObras {
    
    public ArrayList<DBObras> obtenerTodasObras() throws SQLException {
        ArrayList<DBObras> listaObras = new ArrayList<>();
        
        String query = "SELECT COD_OBRA, DESCRIPCION, FEC_TERMINA FROM OBRAS ORDER BY COD_OBRA ASC";
        
        ConexionDB conexDB = new ConexionDB();
        
        try (Connection conexion = conexDB.conex;
                PreparedStatement sentencia = conexion.prepareStatement(query)) {
            
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {
                String codObra = rs.getString("COD_OBRA");
                String descripcion = rs.getString("DESCRIPCION");
                String fechaFin = rs.getString("FEC_TERMINA");
                
                DBObras obra = new DBObras(codObra, descripcion, fechaFin);
                
                listaObras.add(obra);
            }
        }
        return listaObras;
    }

    public void modificarObra(DBObras obra) throws SQLException {
        String query = "UPDATE OBRAS SET DESCRIPCION = ?, FEC_TERMINA = ? WHERE COD_OBRA = ?";

        ConexionDB conexBDQT = new ConexionDB();

        try (Connection conexion = conexBDQT.conex; PreparedStatement sentencia = conexion.prepareStatement(query)) {

            sentencia.setString(1, obra.getDescripcion());
            sentencia.setString(2, obra.getFechaFin());
            sentencia.setString(3, obra.getCodObra());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas == 0) {
                System.out.println("Advertencia: No se encontró la obra con código " + obra.getCodObra() + " para modificar.");
            }
        }
    }

    public void insertarObra(DBObras obra) throws SQLException {
        String query = "INSERT INTO OBRAS (COD_OBRA, DESCRIPCION, FEC_TERMINA) VALUES (?, ?, ?)";

        ConexionDB conexBDQT = new ConexionDB();
        try (Connection conexion = conexBDQT.conex; PreparedStatement sentencia = conexion.prepareStatement(query)) {

            sentencia.setString(1, obra.getCodObra());
            sentencia.setString(2, obra.getDescripcion());
            sentencia.setString(3, obra.getFechaFin());

            sentencia.executeUpdate();

        }
    }
    
    public void insertarObraSinFecha(DBObras obra) throws SQLException {
        String query = "INSERT INTO OBRAS (COD_OBRA, DESCRIPCION) VALUES (?, ?)";

        ConexionDB conexBDQT = new ConexionDB();
        try (Connection conexion = conexBDQT.conex; PreparedStatement sentencia = conexion.prepareStatement(query)) {

            sentencia.setString(1, obra.getCodObra());
            sentencia.setString(2, obra.getDescripcion());

            sentencia.executeUpdate();

        }
    }

    public Boolean eliminarObra(String codObra) throws SQLException {
        if (comprobarDependenciasServicio(codObra)) {
            return false;
        }
        
        String query = "DELETE FROM OBRAS WHERE COD_OBRA = ?";

        ConexionDB conexBDQT = new ConexionDB();

        try (Connection conexion = conexBDQT.conex; PreparedStatement sentencia = conexion.prepareStatement(query)) {

            sentencia.setString(1, codObra);

            int filasAfectadas = sentencia.executeUpdate();

            return filasAfectadas > 0;
        }
    }
    
    public boolean comprobarDependenciasServicio(String codObra) throws SQLException {
        String query = "SELECT COUNT(*) FROM SERVICIOS WHERE COD_OBRAS = ?";
        
        ConexionDB conexDB = new ConexionDB();
        
        try (Connection conexion = conexDB.conex;
             PreparedStatement sentencia = conexion.prepareStatement(query)) {
            
            sentencia.setString(1, codObra);
            
            try (ResultSet rs = sentencia.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}
