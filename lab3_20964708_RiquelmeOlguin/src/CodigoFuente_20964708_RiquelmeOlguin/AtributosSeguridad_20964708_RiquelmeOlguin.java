package CodigoFuente_20964708_RiquelmeOlguin;

public class AtributosSeguridad_20964708_RiquelmeOlguin implements Interfaz_AtributosSeguridad_20964708_RiquelmeOlguin {
    private boolean esSoloLectura;
    private boolean esOculto;

    /**
     * Metodo tostring de atributo de seguridad
     * @return
     */
    @Override
    public String toString() {
        return "AtributosSeguridad{" +
                "Solo Lectura=" + esSoloLectura +
                ", es Oculto=" + esOculto +
                '}';
    }

    /**
     * Contructor de atributos de seguridad
     * @param esSoloLectura
     * @param esOculto
     */
    // constructor, getters y setters
    public AtributosSeguridad_20964708_RiquelmeOlguin(boolean esSoloLectura, boolean esOculto) {
        this.esSoloLectura = esSoloLectura;
        this.esOculto = esOculto;
    }

    /**
     * selector de es oculto
     * @return
     */
    public boolean esOculto() {
        return esOculto;
    }

}

