package CodigoFuente_20964708_RiquelmeOlguin;

public interface Interfaz_AtributosSeguridad_20964708_RiquelmeOlguin {

    /**
     * Verifica si el atributo "Solo lectura" está habilitado.
     *
     * @return true si el atributo "Solo lectura" está habilitado, de lo contrario, false.
     */
    boolean esSoloLectura();
    /**
     * Establece el estado del atributo "Solo lectura".
     *
     * @param esSoloLectura El nuevo estado del atributo "Solo lectura".
     */
    void setSoloLectura(boolean esSoloLectura);

    /**
     * Verifica si el atributo "Oculto" está habilitado.
     *
     * @return true si el atributo "Oculto" está habilitado, de lo contrario, false.
     */
    boolean esOculto();

    /**
     * Establece el estado del atributo "Oculto".
     *
     * @param esOculto El nuevo estado del atributo "Oculto".
     */
    void setOculto(boolean esOculto);
}

