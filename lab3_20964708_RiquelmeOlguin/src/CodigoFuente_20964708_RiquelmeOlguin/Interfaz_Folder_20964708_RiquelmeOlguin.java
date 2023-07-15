package CodigoFuente_20964708_RiquelmeOlguin;

import java.util.List;

public interface Interfaz_Folder_20964708_RiquelmeOlguin {

    /**
     * Busca un Folder por nombre en el contenido del Folder actual.
     *
     * @param nombre El nombre del Folder a buscar.
     * @return El objeto Folder_20964708_RiquelmeOlguin si se encuentra. Null en caso contrario.
     */
    Folder_20964708_RiquelmeOlguin buscarFolder(String nombre);

    /**
     * Busca un File por nombre en el contenido del Folder actual.
     *
     * @param nombre El nombre del File a buscar.
     * @return El objeto File_20964708_RiquelmeOlguin si se encuentra. Null en caso contrario.
     */
    File_20964708_RiquelmeOlguin buscarFile(String nombre);

    /**
     * Elimina un elemento del contenido del Folder actual por nombre.
     *
     * @param Contenido La lista de contenido del Folder actual.
     * @param NombreEliminar El nombre del elemento a eliminar del contenido.
     */
    void eliminarcontenido(List<Contenido_20964708_RiquelmeOlguin> Contenido, String NombreEliminar);

    /**
     * Metodo para eliminar el contenido dado una extension.
     * @param Contenido
     * @param Formato
     */

     void elimiarContenidoExt(List<Contenido_20964708_RiquelmeOlguin> Contenido, String Formato);

    /**
     * Obtiene la lista de contenido del Folder actual.
     *
     * @return La lista de contenido del Folder actual.
     */
    List<Contenido_20964708_RiquelmeOlguin> getContenido();
}
