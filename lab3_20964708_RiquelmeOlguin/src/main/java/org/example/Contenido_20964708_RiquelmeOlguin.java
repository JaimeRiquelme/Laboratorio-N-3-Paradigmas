package org.example;
import java.util.Date;

public abstract class Contenido_20964708_RiquelmeOlguin {
    private String nombre;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String creadorUser;
    private AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad;

    public Contenido_20964708_RiquelmeOlguin(String nombre, Date fechaCreacion, Date fechaModificacion, String creadorUser, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.creadorUser = creadorUser;
        this.atributoSeguridad = atributoSeguridad;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public String getCreadorUser() {
        return creadorUser;
    }

    public AtributosSeguridad_20964708_RiquelmeOlguin getAtributoSeguridad() {
        return atributoSeguridad;
    }

    @Override
    public String toString() {
        return "FileFolderSystem{" +
                "nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaModificacion=" + fechaModificacion +
                ", creadorUser='" + creadorUser + '\'' +
                ", atributoSeguridad=" + atributoSeguridad +
                '}';
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
