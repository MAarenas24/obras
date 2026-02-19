package MapeoTablas;

/**
 *
 * @author miare
 */
public class DBServicios {
    private String nifTrab;
    private String codObra;
    private String fecha;
    private String horaInicio;
    private String horaFin;

    public DBServicios(String nifTrab, String codObra, String fecha, String horaInicio, String horaFin) {
        this.nifTrab = nifTrab;
        this.codObra = codObra;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getNifTrab() {
        return nifTrab;
    }

    public void setNifTrab(String nifTrab) {
        this.nifTrab = nifTrab;
    }

    public String getCodObra() {
        return codObra;
    }

    public void setCodObra(String codObra) {
        this.codObra = codObra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "DBServicios{" + "nifTrab=" + nifTrab + ", codObra=" + codObra + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + '}';
    }
    
    
}
