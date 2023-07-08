package CodigoFuente_20964708_RiquelmeOlguin;

import java.util.List;

public interface Interfaz_Drive_20964708_RiquelmeOlguin {

    /**
     * Busca un folder en el drive por su nombre.
     *
     * @param nombre El nombre del folder a buscar.
     * @return El folder buscado, o null si no se encuentra.
     */
    Folder_20964708_RiquelmeOlguin buscarFolder(String nombre);

    /**
     * Busca un archivo en el drive por su nombre.
     *
     * @param nombre El nombre del archivo a buscar.
     * @return El archivo buscado, o null si no se encuentra.
     */
    File_20964708_RiquelmeOlguin buscarFile(String nombre);

    /**
     * Elimina un contenido del drive por su nombre.
     *
     * @param Contenido La lista de contenidos del drive.
     * @param NombreEliminar El nombre del contenido a eliminar.
     */
    void eliminarcontenidoDrive(List<Contenido_20964708_RiquelmeOlguin> Contenido, String NombreEliminar);

    /**
     * Elimina un contenido del drive por su formato.
     *
     * @param Contenido La lista de contenidos del drive.
     * @param Formato El formato del contenido a eliminar.
     */
    void elimiarContenidoExt(List<Contenido_20964708_RiquelmeOlguin> Contenido, String Formato);

    /**
     * Obtiene la letra asignada al drive.
     *
     * @return La letra asignada al drive.
     */
    String getLetra();

    /**
     * Obtiene el nombre del drive.
     *
     * @return El nombre del drive.
     */
    String getNombre();

    /**
     * Obtiene la capacidad del drive.
     *
     * @return La capacidad del drive.
     */
    int getCapacidad();

    /**
     * Obtiene la lista de contenidos del drive.
     *
     * @return La lista de contenidos del drive.
     */
    List<Contenido_20964708_RiquelmeOlguin> getContenido();
}
