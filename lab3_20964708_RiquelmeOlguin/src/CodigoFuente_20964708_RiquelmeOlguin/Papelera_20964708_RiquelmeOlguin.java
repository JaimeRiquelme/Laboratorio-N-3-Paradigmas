package CodigoFuente_20964708_RiquelmeOlguin;

public class Papelera_20964708_RiquelmeOlguin implements Interfaz_Papelera_20964708_RiquelmeOlguin{
    private Contenido_20964708_RiquelmeOlguin Contenido;
    private String Path;

    /**
     * Constructor para crear una nueva Papelera con contenido y su ruta.
     *
     * @param contenido El contenido que se almacena en la Papelera.
     * @param path La ruta del contenido almacenado.
     */

    public Papelera_20964708_RiquelmeOlguin(Contenido_20964708_RiquelmeOlguin contenido, String path) {
        this.Contenido = contenido;
        this.Path = path;
    }


    /**
     * Representación en cadena de la Papelera.
     *
     * @return Representación en cadena de la Papelera.
     */
    @Override
    public String toString() {
        return "Papelera_20964708_RiquelmeOlguin{" +
                "Contenido=" + Contenido +
                ", Path='" + Path + '\'' +
                '}';
    }

    /**
     * Obtiene el contenido almacenado en la Papelera.
     *
     * @return El contenido de la Papelera.
     */

    @Override
    public Contenido_20964708_RiquelmeOlguin getContenido() {
        return null;
    }

    /**
     * Obtiene la ruta del contenido almacenado en la Papelera.
     *
     * @return La ruta del contenido.
     */

    @Override
    public String getPath() {
        return null;
    }
}
