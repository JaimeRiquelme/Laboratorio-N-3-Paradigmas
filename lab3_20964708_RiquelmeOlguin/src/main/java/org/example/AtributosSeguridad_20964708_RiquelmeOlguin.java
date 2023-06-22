package org.example;

public class AtributosSeguridad_20964708_RiquelmeOlguin {
    private boolean esSoloLectura;
    private boolean esOculto;

    @Override
    public String toString() {
        return "AtributosSeguridad{" +
                "Solo Lectura=" + esSoloLectura +
                ", es Oculto=" + esOculto +
                '}';
    }

    // constructor, getters y setters
    public AtributosSeguridad_20964708_RiquelmeOlguin(boolean esSoloLectura, boolean esOculto) {
        this.esSoloLectura = esSoloLectura;
        this.esOculto = esOculto;
    }

    public boolean esSoloLectura() {
        return esSoloLectura;
    }

    public void setSoloLectura(boolean esSoloLectura) {
        this.esSoloLectura = esSoloLectura;
    }

    public boolean esOculto() {
        return esOculto;
    }

    public void setOculto(boolean esOculto) {
        this.esOculto = esOculto;
    }
}

