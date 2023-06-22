package org.example;
import java.util.Date;

public class Folder_20964708_RiquelmeOlguin {
    String Nombre;
    Date Fecha_creacion;
    Date Fecha_modificacion;
    String CreadorUser;
    AtributosSeguridad_20964708_RiquelmeOlguin AtributoSeguridad;


    public String getNombre() {
        return Nombre;
    }

    public Date getFecha_creacion() {
        return Fecha_creacion;
    }

    public Date getFecha_modificacion() {
        return Fecha_modificacion;
    }

    public String getCreadorUser() {
        return CreadorUser;
    }

    public AtributosSeguridad_20964708_RiquelmeOlguin getAtributoSeguridad() {
        return AtributoSeguridad;
    }

    public Folder_20964708_RiquelmeOlguin(String nombre, Date fecha_creacion, Date fecha_modificacion, String creadorUser, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad) {
        this.Nombre = nombre;
        this.Fecha_creacion = fecha_creacion;
        this.Fecha_modificacion = fecha_modificacion;
        this.CreadorUser = creadorUser;
        this.AtributoSeguridad = atributoSeguridad;
    }


    @Override
    public String toString() {
        return "Folder_20964708_RiquelmeOlguin{" +
                "Nombre='" + Nombre + '\'' +
                ", Fecha_creacion=" + Fecha_creacion +
                ", Fecha_modificacion=" + Fecha_modificacion +
                ", CreadorUser='" + CreadorUser + '\'' +
                ", AtributoSeguridad=" + AtributoSeguridad +
                '}';
    }
}
