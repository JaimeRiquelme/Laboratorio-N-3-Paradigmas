package CodigoFuente_20964708_RiquelmeOlguin;
import java.util.Date;

public interface Interfaz_Contenido_20964708_RiquelmeOlguin {
    /**
     * Obtiene el nombre del contenido.
     *
     * @return El nombre del contenido.
     */
    String getNombre();

    /**
     * Obtiene la fecha de creación del contenido.
     *
     * @return La fecha de creación del contenido.
     */
    Date getFechaCreacion();

    /**
     * Obtiene la fecha de la última modificación del contenido.
     *
     * @return La fecha de la última modificación del contenido.
     */
    Date getFechaModificacion();

    /**
     * Obtiene el usuario creador del contenido.
     *
     * @return El usuario creador del contenido.
     */
    String getCreadorUser();

    /**
     * Obtiene los atributos de seguridad del contenido.
     *
     * @return Los atributos de seguridad del contenido.
     */
    AtributosSeguridad_20964708_RiquelmeOlguin getAtributoSeguridad();

    /**
     * Establece la fecha de la última modificación del contenido.
     *
     * @param fechaModificacion La nueva fecha de la última modificación del contenido.
     */
    void setFechaModificacion(Date fechaModificacion);

    /**
     * Establece el nombre del contenido.
     *
     * @param nombre El nuevo nombre del contenido.
     */
    void setNombre(String nombre);

    /**
     * Devuelve una representación en cadena de caracteres del objeto.
     *
     * @return Una cadena que representa al objeto.
     */
    String toString();
}

