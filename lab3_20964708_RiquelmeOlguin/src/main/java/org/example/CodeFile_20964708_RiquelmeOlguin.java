package org.example;

import java.util.Date;

public class CodeFile_20964708_RiquelmeOlguin extends File_20964708_RiquelmeOlguin {
    public CodeFile_20964708_RiquelmeOlguin(String nombre, Date fechaCreacion, Date fechaModificacion, String usuarioCreador, AtributosSeguridad_20964708_RiquelmeOlguin atributoSeguridad, String contenido, String formato, long tamano) {
        super(nombre, fechaCreacion, fechaModificacion, usuarioCreador, atributoSeguridad, contenido, formato, tamano);
    }

    void Compilar(){
        System.out.println("Compilando CODIGO");
    }
}
