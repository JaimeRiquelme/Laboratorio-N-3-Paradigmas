package CodigoFuente_20964708_RiquelmeOlguin;

import java.util.Date;

public class TextFile_20964708_RiquelmeOlguin extends File_20964708_RiquelmeOlguin {
    public TextFile_20964708_RiquelmeOlguin(String nombre, Date fechaCreacion, Date fechaModificacion, String usuarioCreador, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad, String contenido, String formato, long tamano, char[] contrasena) {
        super(nombre, fechaCreacion, fechaModificacion, usuarioCreador, atributoSeguridad, contenido, formato, tamano, contrasena);
    }

    /**
     * Metodo que podria contener un tipo de file tipo texto
     */
    public void LeerTexto(){
        System.out.println("---Leyendo Texto---");
    }


}
