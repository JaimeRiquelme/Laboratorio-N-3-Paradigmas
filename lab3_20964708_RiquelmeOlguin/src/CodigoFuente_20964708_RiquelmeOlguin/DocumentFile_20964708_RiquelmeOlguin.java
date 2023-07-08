package CodigoFuente_20964708_RiquelmeOlguin;

import java.util.Date;

public class DocumentFile_20964708_RiquelmeOlguin extends File_20964708_RiquelmeOlguin {
    public DocumentFile_20964708_RiquelmeOlguin(String nombre, Date fechaCreacion, Date fechaModificacion, String usuarioCreador, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad, String contenido, String formato, long tamano) {
        super(nombre, fechaCreacion, fechaModificacion, usuarioCreador, atributoSeguridad, contenido, formato, tamano);
    }

    /**
     * Metodo que podria tener un archivo tipo Documento.
     */
    public void MostrarDocumento(){
        System.out.printf("---Mostrando Documento");
    }
}
