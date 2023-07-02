package org.example;

import java.util.List;

public interface Interfaz_Filesystem_20964708_RiquelmeOlguin {

    /**
     *  Descripción: añade un Drive al systema
     * @author Jaime Riquelme
     */

    void addDrive(String letra, String nombre, int capacidad);

    void register(String usuario);

    void login(String usuario);

    void logout();

    void swithDrive(String Letra);

    void mkdir(String Nombre);

    void cd(String Nombre);

    void addFile(File_20964708_RiquelmeOlguin file);

    void del(String Nombre);

    void copy(String NombreCopiar, String path);
    void move(String NombreCopiar,String path);

    void ren(String Nombre,String NuevoNombre);
    Folder_20964708_RiquelmeOlguin buscarContenido(String[] RutaSplit, Drive_20964708_RiquelmeOlguin DriveActual);
    Drive_20964708_RiquelmeOlguin buscarDriveActual();

    Drive_20964708_RiquelmeOlguin buscarDrivePorLetra(String letraDrive);
    List<String> getContenidoNombres(List<Contenido_20964708_RiquelmeOlguin> contenido);

    void crearFile(String Nombre);
    AtributosSeguridad_20964708_RiquelmeOlguin crearSeguridad();

    List<Contenido_20964708_RiquelmeOlguin> filtrarPorFormato(List<Contenido_20964708_RiquelmeOlguin> lista, String formato);

    boolean verificarRuta(List<Contenido_20964708_RiquelmeOlguin> Contenido, List<String> RutaSplit);


}

