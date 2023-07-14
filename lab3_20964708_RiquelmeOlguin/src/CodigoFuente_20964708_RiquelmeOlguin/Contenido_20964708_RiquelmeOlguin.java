package CodigoFuente_20964708_RiquelmeOlguin;
import java.util.Date;

public abstract class Contenido_20964708_RiquelmeOlguin implements Interfaz_Contenido_20964708_RiquelmeOlguin {
    protected String nombre;
    protected Date fechaCreacion;
    protected Date fechaModificacion;
    protected String creadorUser;
    protected AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad;
    protected char[] Contrasena;

    /**
     * Contructor de Contenido del sistema
     * @param nombre
     * @param fechaCreacion
     * @param fechaModificacion
     * @param creadorUser
     * @param atributoSeguridad
     */

    public Contenido_20964708_RiquelmeOlguin(String nombre, Date fechaCreacion, Date fechaModificacion, String creadorUser, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad, char[] Contrasena) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.creadorUser = creadorUser;
        this.atributoSeguridad = atributoSeguridad;
        this.Contrasena = Contrasena;
    }


    /**
     * Devuelve el nombre del objeto.
     *
     * @return El nombre del objeto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la fecha de creación del objeto.
     *
     * @return La fecha de creación del objeto.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Devuelve la fecha de modificación del objeto.
     *
     * @return La fecha de modificación del objeto.
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * Devuelve el usuario creador del objeto.
     *
     * @return El usuario creador del objeto.
     */
    public String getCreadorUser() {
        return creadorUser;
    }

    /**
     * Devuelve los atributos de seguridad del objeto.
     *
     * @return Los atributos de seguridad del objeto.
     */
    public AtributosSeguridad_20964708_RiquelmeOlguin getAtributoSeguridad() {
        return atributoSeguridad;
    }

    /**
     * Devuelve una representación en cadena de caracteres del objeto.
     *
     * @return Una cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return "FileFolderSystem{" +
                "nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaModificacion=" + fechaModificacion +
                ", creadorUser='" + creadorUser + '\'' +
                ", atributoSeguridad=" + atributoSeguridad +
                ", Contraseña= " + Contrasena +
                '}';
    }

    /**
     * Modifica la fecha de modificación del objeto.
     *
     * @param fechaModificacion La nueva fecha de modificación.
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Establece el nombre del objeto.
     *
     * @param nombre El nuevo nombre del objeto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char[] getContrasena() {
        return Contrasena;
    }
    public String getContrasenaString() {
        return new String(Contrasena);
    }
}
