package CodigoFuente_20964708_RiquelmeOlguin;

public interface Interfaz_File_20964708_RiquelmeOlguin {

    /**
     * Obtiene el contenido del archivo.
     *
     * @return El contenido del archivo como una cadena de texto.
     */
    String getContenido();

    /**
     * Establece el contenido del archivo.
     *
     * @param contenido El nuevo contenido del archivo como una cadena de texto.
     */
    void setContenido(String contenido);

    /**
     * Obtiene el formato del archivo.
     *
     * @return El formato del archivo como una cadena de texto.
     */
    String getFormato();

    /**
     * Establece el formato del archivo.
     *
     * @param formato El nuevo formato del archivo como una cadena de texto.
     */
    void setFormato(String formato);

    /**
     * Obtiene el tamaño del archivo.
     *
     * @return El tamaño del archivo en bytes.
     */
    long getTamano();

    /**
     * Establece el tamaño del archivo.
     *
     * @param tamano El nuevo tamaño del archivo en bytes.
     */
    void setTamano(long tamano);
}

