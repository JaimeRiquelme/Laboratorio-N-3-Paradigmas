package org.example;

import java.util.ArrayList;
import java.util.List;

public class Drive_20964708_RiquelmeOlguin {

    String letra;
    String nombre;
    int capacidad;

    List<FileFolderSystem_20964708_RiquelmeOlguin> Contenido;

    public Drive_20964708_RiquelmeOlguin(String letra, String nombre, int capacidad) {
        this.letra = letra;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.Contenido = new ArrayList<>();
    }

    public Folder_20964708_RiquelmeOlguin buscarFolder(String nombre) {
        for (FileFolderSystem_20964708_RiquelmeOlguin objeto : this.Contenido) {
            if (objeto instanceof Folder_20964708_RiquelmeOlguin && objeto.getNombre().equals(nombre)) {
                return (Folder_20964708_RiquelmeOlguin)objeto;
            }
        }
        return null;
    }



    public String getLetra() {
        return letra;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public List<FileFolderSystem_20964708_RiquelmeOlguin> getContenido() {
        return Contenido;
    }

    @Override
    public String toString() {
        return "Drive_20964708_RiquelmeOlguin{" +
                "letra='" + letra + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", Contenido=" + Contenido +
                '}';
    }
}
