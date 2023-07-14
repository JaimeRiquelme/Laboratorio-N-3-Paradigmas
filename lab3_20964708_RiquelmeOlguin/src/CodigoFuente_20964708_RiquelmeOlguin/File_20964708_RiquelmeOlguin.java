package CodigoFuente_20964708_RiquelmeOlguin;
import java.util.Date;

public abstract class File_20964708_RiquelmeOlguin extends Contenido_20964708_RiquelmeOlguin implements Interfaz_File_20964708_RiquelmeOlguin {
    private String contenido;
    private String formato;
    private long tamano;  // Tamaño en bytes

    /**
     * Contructor de File
     * @param nombre
     * @param fechaCreacion
     * @param fechaModificacion
     * @param usuarioCreador
     * @param atributoSeguridad
     * @param contenido
     * @param formato
     * @param tamano
     */

    public File_20964708_RiquelmeOlguin(String nombre, Date fechaCreacion, Date fechaModificacion, String usuarioCreador, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad, String contenido, String formato, long tamano, char[] contrasena) {
        super(nombre, fechaCreacion, fechaModificacion, usuarioCreador, atributoSeguridad, contrasena);
        this.contenido = contenido;
        this.formato = formato;
        this.tamano = tamano;
    }



    /**
     * Devuelve el contenido del archivo.
     *
     * @return El contenido del archivo como cadena de texto.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el contenido del archivo.
     *
     * @param contenido El nuevo contenido del archivo.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Devuelve el formato del archivo.
     *
     * @return El formato del archivo como cadena de texto.
     */
    public String getFormato() {
        return formato;
    }

    /**
     * Establece el formato del archivo.
     *
     * @param formato El nuevo formato del archivo.
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    /**
     * Devuelve el tamaño del archivo.
     *
     * @return El tamaño del archivo en bytes.
     */
    public long getTamano() {
        return tamano;
    }

    /**
     * Establece el tamaño del archivo.
     *
     * @param tamano El nuevo tamaño del archivo en bytes.
     */
    public void setTamano(long tamano) {
        this.tamano = tamano;
    }

    /**
     * Devuelve una representación en cadena de caracteres del objeto.
     *
     * @return Una cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return "FileFolderSystem{" +
                "nombre='" + super.getNombre() + '\'' +
                ", fechaCreacion=" + super.getFechaCreacion() +
                ", fechaModificacion=" + super.getFechaModificacion() +
                ", creadorUser='" + super.getCreadorUser() + '\'' +
                ", atributoSeguridad=" + super.getAtributoSeguridad() +
                ", contenido='" + contenido + '\'' +
                ", formato='" + formato + '\'' +
                ", tamano=" + tamano +
                ", contrasena =" + getContrasenaString() +
                '}';
    }

}
