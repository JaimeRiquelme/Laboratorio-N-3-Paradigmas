package org.example;

public class Drive_20964708_RiquelmeOlguin {

    String letra;
    String nombre;
    int capacidad;

    public Drive_20964708_RiquelmeOlguin(String letra, String nombre, int capacidad) {
        this.letra = letra;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Drive_20964708_RiquelmeOlguin{" +
                "letra='" + letra + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                '}';
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
}
