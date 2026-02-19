package DModules;

import DBConexiones.ConexionDB;
import MapeoTablas.DBObras;
import MapeoTablas.DBServicios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author miare
 */
public class DMServicios {

    public ArrayList<DBServicios> listarTodos() throws SQLException {
        ArrayList<DBServicios> servicios = new ArrayList<>();
        final String SQL_SELECT_ALL = "SELECT NIF_TRAB, COD_OBRAS, FECHA, HORA_INICIO, HORA_FIN FROM SERVICIOS";

        ConexionDB conexDB = new ConexionDB();

        try (Connection conexion = conexDB.conex; PreparedStatement ps = conexion.prepareStatement(SQL_SELECT_ALL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DBServicios s = new DBServicios(
                        rs.getString("NIF_TRAB"),
                        rs.getString("COD_OBRAS"),
                        rs.getString("FECHA"),
                        rs.getString("HORA_INICIO"),
                        rs.getString("HORA_FIN")
                );
                servicios.add(s);
            }
        }
        return servicios;
    }
    
    public ArrayList<DBServicios> listarPorTrabajador(String nifTrabajador) throws SQLException {
        ArrayList<DBServicios> servicios = new ArrayList<>();
        final String SQL_SELECT_BY_NIF = "SELECT NIF_TRAB, COD_OBRAS, FECHA, HORA_INICIO, HORA_FIN FROM SERVICIOS WHERE NIF_TRAB = ?";

        ConexionDB conexDB = new ConexionDB();

        try (Connection conexion = conexDB.conex; 
             PreparedStatement ps = conexion.prepareStatement(SQL_SELECT_BY_NIF)) {

            ps.setString(1, nifTrabajador); 
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DBServicios s = new DBServicios(
                            rs.getString("NIF_TRAB"),
                            rs.getString("COD_OBRAS"),
                            rs.getString("FECHA"),
                            rs.getString("HORA_INICIO"),
                            rs.getString("HORA_FIN")
                    );
                    servicios.add(s);
                }
            }
        }
        return servicios;
    }

    public boolean insertar(DBServicios servicio) throws SQLException {
        final String SQL_INSERT = "INSERT INTO SERVICIOS (NIF_TRAB, COD_OBRAS, FECHA, HORA_INICIO, HORA_FIN) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = 0;

        ConexionDB conexDB = new ConexionDB();

        try (Connection conexion = conexDB.conex; PreparedStatement ps = conexion.prepareStatement(SQL_INSERT)) {

            ps.setString(1, servicio.getNifTrab());
            ps.setString(2, servicio.getCodObra());
            ps.setString(3, servicio.getFecha());
            ps.setString(4, servicio.getHoraInicio());
            ps.setString(5, servicio.getHoraFin());

            rowsAffected = ps.executeUpdate();
        }
        return rowsAffected > 0;
    }

    public boolean modificar(DBServicios servicio, DBServicios claveAntigua) throws SQLException {
        final String SQL_UPDATE = "UPDATE SERVICIOS SET HORA_FIN = ? WHERE NIF_TRAB = ? AND COD_OBRAS = ? AND FECHA = ? AND HORA_INICIO = ?";
        int rowsAffected = 0;

        ConexionDB conexDB = new ConexionDB();

        try (Connection conexion = conexDB.conex; PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, servicio.getHoraFin());

            ps.setString(2, claveAntigua.getNifTrab());
            ps.setString(3, claveAntigua.getCodObra());
            ps.setString(4, claveAntigua.getFecha());
            ps.setString(5, claveAntigua.getHoraInicio());

            rowsAffected = ps.executeUpdate();
        }
        return rowsAffected > 0;
    }

    public boolean eliminar(DBServicios servicio) throws SQLException {
        final String SQL_DELETE = "DELETE FROM SERVICIOS WHERE NIF_TRAB = ? AND COD_OBRAS = ? AND FECHA = ? AND HORA_INICIO = ?";
        int rowsAffected = 0;

        ConexionDB conexDB = new ConexionDB();

        try (Connection conexion = conexDB.conex; PreparedStatement ps = conexion.prepareStatement(SQL_DELETE)) {

            ps.setString(1, servicio.getNifTrab());
            ps.setString(2, servicio.getCodObra());
            ps.setString(3, servicio.getFecha());
            ps.setString(4, servicio.getHoraInicio());

            rowsAffected = ps.executeUpdate();
        }
        return rowsAffected > 0;
    }
}
