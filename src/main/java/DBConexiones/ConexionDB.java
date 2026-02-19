package DBConexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Ángel
 */
public class ConexionDB {
    private static final ConexionDB conexionDB = null;
    private String strDriver = null;
    private String strJDBC = null;
    public Connection conex = null;
    
    public ConexionDB() throws SQLException {
        System.out.println("Entra en el constructor de ConexionDB");
        
        strJDBC = "org.sqlite.JDBC"; //Driver de conexion
        strDriver = "jdbc:sqlite:./baseDatos/Construccion.db";
        System.out.println("Se han iniciado las variables del constructor");
        System.out.println("Llamo al metodo setConection");
        try {
            conex = DriverManager.getConnection(strDriver, "","");
            System.out.println("Exito al conectar la base de datos con la cadena" + strDriver);
           
        } catch (SQLException e) {
            System.out.println("ERROR CONEXION DB" + strDriver + " " + e);
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void closeConnection() {
        if (this.conex != null) {
            try {
                this.conex.close();
                this.conex = null; // Opcional: poner a null para indicar que está cerrada
                System.out.println("Conexión a la DB cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
