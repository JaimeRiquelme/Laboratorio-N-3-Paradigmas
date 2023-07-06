package CodigoFuente_20964708_RiquelmeOlguin;
import java.util.Date;

public abstract class File_20964708_RiquelmeOlguin extends Contenido_20964708_RiquelmeOlguin implements Interfaz_File_20964708_RiquelmeOlguin {
    private String contenido;
    private String formato;
    private long tamano;  // Tamaño en bytes

    public File_20964708_RiquelmeOlguin(String nombre, Date fechaCreacion, Date fechaModificacion, String usuarioCreador, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad, String contenido, String formato, long tamano) {
        super(nombre, fechaCreacion, fechaModificacion, usuarioCreador, atributoSeguridad);
        this.contenido = contenido;
        this.formato = formato;
        this.tamano = tamano;
    }




    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public long getTamano() {
        return tamano;
    }

    public void setTamano(long tamano) {
        this.tamano = tamano;
    }

    @Override
    public String toString() {
        return "FileFolderSystem{" +
                "nombre='" + super.getNombre() + '\'' +
                ", fechaCreacion=" + super.getFechaCreacion() +
                ", fechaModificacion=" + super.getFechaModificacion() +
                ", creadorUser='" + super.getCreadorUser() + '\'' +
                ", atributoSeguridad=" + super.getAtributoSeguridad() +
                ", contenido='" + contenido + '\'' +
                ", formato='" + formato + '\'' +
                ", tamano=" + tamano +
                '}';
    }

}
