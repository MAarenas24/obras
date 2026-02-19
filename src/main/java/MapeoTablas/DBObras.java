package MapeoTablas;

/**
 *
 * @author miare
 */
public class DBObras {
    private String codObra;
    private String descripcion;
    private String fechaFin;

    public DBObras(String codObra, String descripcion, String fechaFin) {
        this.codObra = codObra;
        this.descripcion = descripcion;
        this.fechaFin = fechaFin;
    }

    public String getCodObra() {
        return codObra;
    }

    public void setCodObra(String codObra) {
        this.codObra = codObra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
