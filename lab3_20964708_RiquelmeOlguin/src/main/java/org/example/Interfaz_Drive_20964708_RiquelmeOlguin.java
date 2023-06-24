package org.example;

import java.util.List;

public interface Interfaz_Drive_20964708_RiquelmeOlguin {

    String getLetra();

    String getNombre();

    int getCapacidad();

    List<FileFolderSystem_20964708_RiquelmeOlguin> getContenido();

    Folder_20964708_RiquelmeOlguin buscarFolder(String nombre);

    @Override
    String toString();
}
