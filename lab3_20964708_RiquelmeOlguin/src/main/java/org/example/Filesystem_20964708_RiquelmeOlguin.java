package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Filesystem_20964708_RiquelmeOlguin implements  Interfaz_Filesystem_20964708_RiquelmeOlguin{

    String nombre;

    Date fechaCreacion;

    List<Drive_20964708_RiquelmeOlguin> drives;

    List<String> usuarios;

    String usuarioActual;

    String DriveActual;

    public Filesystem_20964708_RiquelmeOlguin(String nombre) {
        this.nombre = nombre;
        this.fechaCreacion = new Date();
        this.drives = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.usuarioActual = null;
        this.DriveActual = null;
    }

    public void addDrive(String letra, String nombre, int capacidad){
        var nd = new Drive_20964708_RiquelmeOlguin(letra,nombre,capacidad);
        var listaLetras = drives.stream().map(Drive_20964708_RiquelmeOlguin::getLetra)
                .collect(Collectors.toList());
        if (!listaLetras.contains(letra)){
            drives.add(nd);
        }else{
            System.out.println("Letra de drive ya existe.");
        }
    }

    public void register(String NombreUsuario){
        if (!usuarios.contains(NombreUsuario.toUpperCase())){
            usuarios.add(NombreUsuario.toUpperCase());
        }else{
            System.out.println("El usuario " + NombreUsuario +" ya se encuentra registrado");
        }

    }

    @Override
    public void login(String usuario) {
        if(getUsuarioActual() != null){
            System.out.println("Ya se encuentra un usuario registrado.");
        }else{
            if (usuarios.contains(usuario.toUpperCase())){
                setUsuarioActual(usuario.toUpperCase());
            }else{
                System.out.println("Usuario no registrado.");
            }

        }
    }

    @Override
    public void logout() {
        if (getUsuarioActual() == null){
            System.out.println("No se encuentra ningún usuario logeado.");
        }else{
            setUsuarioActual(null);
        }
    }

    @Override
    public void swithDrive(String Letra) {
        var listaLetras = drives.stream().map(Drive_20964708_RiquelmeOlguin::getLetra)
                .collect(Collectors.toList());

        if(getUsuarioActual() != null){
            if (listaLetras.contains(Letra.toUpperCase())){
                setDriveActual(Letra.toUpperCase());
            }else{
                System.out.println("Drive no existente");
            }
        }else {
            System.out.println("No hay ningun usuario logeado, porfavor realize logeo.");
        }

    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }




    public List<Drive_20964708_RiquelmeOlguin> getDrives() {
        return drives;
    }



    public List<String> getUsuarios() {
        return usuarios;
    }

    public void setUsuarioActual(String usuarioActual) {
        this.usuarioActual = usuarioActual;
    }



    public String getUsuarioActual() {
        return usuarioActual;
    }

    @Override
    public String toString() {
        return "Filesystem_20964708_RiquelmeOlguin{" +
                "nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", drives=" + drives +
                ", usuarios=" + usuarios +
                ", usuarioActual='" + usuarioActual + '\'' +
                ", DriveActual='" + DriveActual + '\'' +
                '}';
    }

    public String getDriveActual() {
        return DriveActual;
    }

    public void setDriveActual(String driveActual) {
        this.DriveActual = driveActual;
    }
}
