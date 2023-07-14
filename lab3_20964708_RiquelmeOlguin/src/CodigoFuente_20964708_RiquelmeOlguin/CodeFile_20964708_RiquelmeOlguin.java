package CodigoFuente_20964708_RiquelmeOlguin;

import java.util.Date;

public class CodeFile_20964708_RiquelmeOlguin extends File_20964708_RiquelmeOlguin {
    public CodeFile_20964708_RiquelmeOlguin(String nombre, Date fechaCreacion, Date fechaModificacion, String usuarioCreador, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad, String contenido, String formato, long tamano , char[] contrasena) {
        super(nombre, fechaCreacion, fechaModificacion, usuarioCreador, atributoSeguridad, contenido, formato, tamano, contrasena);
    }

    /**
     * Metodo que tiene solo archivos tipo Codigo
     */
    void Compilar(){
        System.out.println("Compilando CODIGO");
    }
}
