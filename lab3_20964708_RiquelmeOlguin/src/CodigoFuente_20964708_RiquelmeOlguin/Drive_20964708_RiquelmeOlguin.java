package CodigoFuente_20964708_RiquelmeOlguin;

import java.util.ArrayList;
import java.util.List;

public class Drive_20964708_RiquelmeOlguin implements  Interfaz_Drive_20964708_RiquelmeOlguin{

    private String letra;
    private String nombre;
    private int capacidad;

    private List<Contenido_20964708_RiquelmeOlguin> Contenido;

    /**
     * Contructor de Drive
     * @param letra
     * @param nombre
     * @param capacidad
     */

    public Drive_20964708_RiquelmeOlguin(String letra, String nombre, int capacidad) {
        this.letra = letra;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.Contenido = new ArrayList<>();
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
     * Elimina un contenido especificado por su nombre.
     *
     * @param Contenido La lista de contenido desde donde se eliminará el objeto.
     * @param NombreEliminar El nombre del contenido a eliminar.
     */
    public void eliminarcontenidoDrive(List<Contenido_20964708_RiquelmeOlguin> Contenido, String NombreEliminar){
        Contenido.removeIf(p -> p.getNombre().equals(NombreEliminar));
    }

    /**
     * Elimina los archivos con un formato específico del contenido.
     *
     * @param Contenido La lista de contenido desde donde se eliminarán los objetos.
     * @param Formato El formato de los archivos a eliminar.
     */
    public void elimiarContenidoExt(List<Contenido_20964708_RiquelmeOlguin> Contenido, String Formato){
        System.out.println("Entroaca");
        Contenido.removeIf(contenido -> contenido instanceof File_20964708_RiquelmeOlguin && ((File_20964708_RiquelmeOlguin) contenido).getFormato()
                .equals(Formato));
    }





    /**
     * Devuelve la letra asociada al Drive.
     *
     * @return La letra del Drive.
     */
    public String getLetra() {
        return letra;
    }

    /**
     * Devuelve el nombre del Drive.
     *
     * @return El nombre del Drive.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la capacidad del Drive.
     *
     * @return La capacidad del Drive.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Devuelve el contenido del Drive.
     *
     * @return Una lista con el contenido del Drive.
     */

    public List<Contenido_20964708_RiquelmeOlguin> getContenido() {
        return Contenido;
    }

    /**
     * Devuelve una representación en cadena de caracteres del objeto.
     *
     * @return Una cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return "Drive_20964708_RiquelmeOlguin{" +
                "letra='" + letra + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", Contenido=" + Contenido +
                '}';
    }
}
