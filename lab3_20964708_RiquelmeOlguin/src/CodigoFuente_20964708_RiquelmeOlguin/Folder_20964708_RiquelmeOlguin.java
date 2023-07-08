package CodigoFuente_20964708_RiquelmeOlguin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Folder_20964708_RiquelmeOlguin extends Contenido_20964708_RiquelmeOlguin implements Interfaz_Folder_20964708_RiquelmeOlguin {
    private List<Contenido_20964708_RiquelmeOlguin> Contenido;

    public Folder_20964708_RiquelmeOlguin(String nombre, Date fechaCreacion, Date fechaModificacion, String creadorUser, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad) {
        super(nombre, fechaCreacion, fechaModificacion, creadorUser, atributoSeguridad);
        Contenido = new ArrayList<>();
    }
    public Folder_20964708_RiquelmeOlguin buscarFolder(String nombre) {
        for (Contenido_20964708_RiquelmeOlguin objeto : this.Contenido) {
            if (objeto instanceof Folder_20964708_RiquelmeOlguin && objeto.getNombre().equals(nombre)) {
                return (Folder_20964708_RiquelmeOlguin)objeto;
            }
        }
        return null;
    }

    public File_20964708_RiquelmeOlguin buscarFile(String nombre) {
        for (Contenido_20964708_RiquelmeOlguin objeto : this.Contenido) {
            if (objeto instanceof File_20964708_RiquelmeOlguin   && objeto.getNombre().equals(nombre)) {
                return (File_20964708_RiquelmeOlguin) objeto;
            }
        }
        return null;
    }


    public void eliminarcontenido(List<Contenido_20964708_RiquelmeOlguin> Contenido, String NombreEliminar){
        Contenido.removeIf(p -> p.getNombre().equals(NombreEliminar));
    }
    @Override
    public String toString() {
        return "Folder_20964708_RiquelmeOlguin{" +
                "Nombre='" + getNombre() + '\'' +
                ", Fecha_creacion=" + getFechaCreacion() +
                ", Fecha_modificacion=" + getFechaModificacion() +
                ", CreadorUser='" + getCreadorUser() + '\'' +
                ", AtributoSeguridad=" + getAtributoSeguridad() +
                ",Contenido="+ Contenido +
                '}';
    }
    public List<Contenido_20964708_RiquelmeOlguin> getContenido() {
        return Contenido;
    }
}