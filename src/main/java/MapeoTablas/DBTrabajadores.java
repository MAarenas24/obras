package MapeoTablas;

/**
 *
 * @author miare
 */
public class DBTrabajadores {
    private String Nif;
    private String nombre;
    private String poblacion;
    private String Descripcion;
    private int Tipo;
    private int pvpHora;

    public DBTrabajadores(String Nif, String nombre, String poblacion, String Descripcion, int Tipo, int pvpHora) {
        this.Nif = Nif;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.Descripcion = Descripcion;
        this.Tipo = Tipo;
        this.pvpHora = pvpHora;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }
    
    public int getPvpHora() {
        return pvpHora;
    }
    
    public String getNif() {
        return Nif;
    }

    public void setNif(String Nif) {
        this.Nif = Nif;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
    }

    @Override
    public String toString() {
        return Descripcion;
    }
    
    
}
