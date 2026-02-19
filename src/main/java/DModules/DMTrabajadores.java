package DModules;

import MapeoTablas.DBTrabajadores;
import DBConexiones.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author miare
 */
public class DMTrabajadores {

    public ConexionDB ConexBDQT;
    public Connection conTrabajadores;
    //public boolean insertado;

    public ArrayList<DBTrabajadores> jcbCargaComboTrabajadores() throws SQLException {
        ArrayList<DBTrabajadores> arrayTrabajadores = new ArrayList<>();
        try {
            ConexBDQT = new ConexionDB();
            conTrabajadores = ConexBDQT.conex;
            System.out.println("Conexion a base de datos correcta para trabajadores");
        } catch (SQLException e) {

        }
        String query = "SELECT NIF, NOMBRE, POBLACION, TIPO, PVP_HORA, NOMBRE ||\" \"|| substr(NIF, 6, 3) AS DESCRIPCION,"
                + " CASE TIPO WHEN 0 THEN 'Administrativo' WHEN 1 THEN 'Trabajador' ELSE 'Error' END AS DESC_TIPO"
                + " FROM TRABAJADORES ORDER BY NOMBRE ASC";
        System.out.println("SQL: " + query);
        PreparedStatement statement;
        System.out.println("Declaracion statement");
        statement = conTrabajadores.prepareStatement(query);
        System.out.println("Preparado el statement");
        ResultSet resulSet = statement.executeQuery();
        System.out.println("Ejecutada la Query de TRABAJADORES");
        while (resulSet.next()) {
            String Nif = resulSet.getString("NIF");
            String nombre = resulSet.getString("NOMBRE");
            String poblacion = resulSet.getString("POBLACION");
            String Descripcion = resulSet.getString("DESCRIPCION");
            int tipo = resulSet.getInt("TIPO");
            int pvpHora = resulSet.getInt("PVP_HORA");
            arrayTrabajadores.add(new DBTrabajadores(Nif, nombre, poblacion, Descripcion, tipo, pvpHora));
            System.out.println("Valores: " + Nif + " " + Descripcion + " " + tipo);
        }
        conTrabajadores.close();
        System.out.println("CIERRE DE LA CONEXION de Trabajadores realizada con exito");
        return arrayTrabajadores;
    }

    public void insertarTrabajador(DBTrabajadores trabajador) throws SQLException {
        String query = "INSERT INTO TRABAJADORES (NIF, NOMBRE, POBLACION, TIPO, PVP_HORA) VALUES (?, ?, ?, ?, ?)";

        ConexBDQT = new ConexionDB();
        try (Connection conexion = ConexBDQT.conex; PreparedStatement sentencia = conexion.prepareStatement(query)) {
            ConexBDQT = new ConexionDB();

            sentencia.setString(1, trabajador.getNif());
            sentencia.setString(2, trabajador.getNombre());
            sentencia.setString(3, trabajador.getPoblacion());
            sentencia.setInt(4, trabajador.getTipo());
            sentencia.setInt(5, trabajador.getPvpHora());

            int filasAfectadas = sentencia.executeUpdate();

        }
    }

    private Boolean verificarServiciosAsociados(Connection conexion, String nif) throws SQLException {
        String query = "SELECT COUNT(*) FROM SERVICIOS WHERE NIF_TRAB = ?";

        try (PreparedStatement checkStatement = conexion.prepareStatement(query)) {
            checkStatement.setString(1, nif);
            try (ResultSet rs = checkStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean eliminarTrabajadores(String nifAEliminar) throws SQLException {
        String query = "DELETE FROM TRABAJADORES WHERE NIF = ?";

        ConexBDQT = new ConexionDB();
        try (Connection conexion = ConexBDQT.conex;
                PreparedStatement sentencia = conexion.prepareStatement(query)) {

            if (verificarServiciosAsociados(conexion, nifAEliminar)) {
                return false;
            }

            sentencia.setString(1, nifAEliminar);

            int filasAfectadas = sentencia.executeUpdate();

            return filasAfectadas > 0;

        }
    }

    public void modificarTrabajador(DBTrabajadores trabajador) throws SQLException {
        String query = "UPDATE TRABAJADORES SET NOMBRE = ?, POBLACION = ?, TIPO = ?, PVP_HORA = ? WHERE NIF = ?";

        ConexBDQT = new ConexionDB();

        try (Connection conexion = ConexBDQT.conex; PreparedStatement sentencia = conexion.prepareStatement(query)) {

            sentencia.setString(1, trabajador.getNombre());
            sentencia.setString(2, trabajador.getPoblacion());
            sentencia.setInt(3, trabajador.getTipo());
            sentencia.setInt(4, trabajador.getPvpHora());

            sentencia.setString(5, trabajador.getNif());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Trabajador con NIF " + trabajador.getNif() + " modificado correctamente.");
            } else {
                System.out.println("No se encontró ningún trabajador con NIF " + trabajador.getNif() + " para modificar.");
            }
        }
    }
}
