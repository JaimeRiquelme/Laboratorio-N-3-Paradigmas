package CodigoFuente_20964708_RiquelmeOlguin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Folder_20964708_RiquelmeOlguin extends Contenido_20964708_RiquelmeOlguin implements Interfaz_Folder_20964708_RiquelmeOlguin {
    private List<Contenido_20964708_RiquelmeOlguin> Contenido;

    /**
     * Constructor de la clase Folder_20964708_RiquelmeOlguin.
     *
     * @param nombre El nombre de la carpeta.
     * @param fechaCreacion La fecha de creación de la carpeta.
     * @param fechaModificacion La fecha de la última modificación de la carpeta.
     * @param creadorUser El usuario que creó la carpeta.
     * @param atributoSeguridad Los atributos de seguridad de la carpeta.
     */
    public Folder_20964708_RiquelmeOlguin(String nombre, Date fechaCreacion, Date fechaModificacion, String creadorUser, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad) {
        super(nombre, fechaCreacion, fechaModificacion, creadorUser, atributoSeguridad);
        Contenido = new ArrayList<>();
    }

    /**
     * Busca un Folder con un nombre específico en el contenido.
     *
     * @param nombre El nombre del Folder que se está buscando.
     * @return El Folder con el nombre especificado, o null si no se encuentra.
     */
    public Folder_20964708_RiquelmeOlguin buscarFolder(String nombre) {
        for (Contenido_20964708_RiquelmeOlguin objeto : this.Contenido) {
            if (objeto instanceof Folder_20964708_RiquelmeOlguin && objeto.getNombre().equals(nombre)) {
                return (Folder_20964708_RiquelmeOlguin)objeto;
            }
        }
        return null;
    }

    /**
     * Busca un File con un nombre específico en el contenido.
     *
     * @param nombre El nombre del File que se está buscando.
     * @return El File con el nombre especificado, o null si no se encuentra.
     */

    public File_20964708_RiquelmeOlguin buscarFile(String nombre) {
        for (Contenido_20964708_RiquelmeOlguin objeto : this.Contenido) {
            if (objeto instanceof File_20964708_RiquelmeOlguin   && objeto.getNombre().equals(nombre)) {
                return (File_20964708_RiquelmeOlguin) objeto;
            }
        }
        return null;
    }

    /**
     * Elimina un contenido especificado por su nombre de la carpeta.
     *
     * @param Contenido La lista de contenido desde donde se eliminará el objeto.
     * @param NombreEliminar El nombre del contenido a eliminar.
     */

    public void eliminarcontenido(List<Contenido_20964708_RiquelmeOlguin> Contenido, String NombreEliminar){
        Contenido.removeIf(p -> p.getNombre().equals(NombreEliminar));
    }

    public void elimiarContenidoExt(List<Contenido_20964708_RiquelmeOlguin> Contenido, String Formato){
        System.out.println("EntroFolder");
        Contenido.removeIf(contenido -> contenido instanceof File_20964708_RiquelmeOlguin && ((File_20964708_RiquelmeOlguin) contenido).getFormato()
                .equals(Formato));
    }

    /**
     * Devuelve una representación en cadena de caracteres del objeto Folder_20964708_RiquelmeOlguin.
     *
     * @return Una cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return "Folder_20964708_RiquelmeOlguin{" +
                "Nombre='" + getNombre() + '\'' +
                ", Fecha_creacion=" + getFechaCreacion() +
                ", Fecha_modificacion=" + getFechaModificacion() +
                ", CreadorUser='" + getCreadorUser() + '\'' +
                ", AtributoSeguridad=" + getAtributoSeguridad() +
                ",Contenido="+ Contenido +
                '}';
    }

    /**
     * Devuelve el contenido de la carpeta.
     *
     * @return Una lista con el contenido de la carpeta.
     */
    public List<Contenido_20964708_RiquelmeOlguin> getContenido() {
        return Contenido;
    }
}
