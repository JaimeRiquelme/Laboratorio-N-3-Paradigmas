package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Filesystem_20964708_RiquelmeOlguin implements Interfaz_Filesystem_20964708_RiquelmeOlguin {

    String nombre;

    Date fechaCreacion;

    List<Drive_20964708_RiquelmeOlguin> drives;

    List<String> usuarios;

    String usuarioActual;

    String DriveActual;

    String RutaActual;

    public Filesystem_20964708_RiquelmeOlguin(String nombre) {
        this.nombre = nombre;
        this.fechaCreacion = new Date();
        this.drives = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.usuarioActual = null;
        this.DriveActual = null;
        this.RutaActual = null;

    }

    public void addDrive(String letra, String nombre, int capacidad) {
        var nd = new Drive_20964708_RiquelmeOlguin(letra, nombre, capacidad);
        var listaLetras = drives.stream().map(Drive_20964708_RiquelmeOlguin::getLetra)
                .collect(Collectors.toList());
        if (!listaLetras.contains(letra)) {
            drives.add(nd);
        } else {
            System.out.println("Letra de drive ya existe.");
        }
    }

    public void register(String NombreUsuario) {
        if (!usuarios.contains(NombreUsuario.toUpperCase())) {
            usuarios.add(NombreUsuario.toUpperCase());
        } else {
            System.out.println("El usuario " + NombreUsuario + " ya se encuentra registrado");
        }

    }

    @Override
    public void login(String usuario) {
        if (getUsuarioActual() != null) {
            System.out.println("Ya se encuentra un usuario registrado.");
        } else {
            if (usuarios.contains(usuario.toUpperCase())) {
                setUsuarioActual(usuario.toUpperCase());
            } else {
                System.out.println("Usuario no registrado.");
            }

        }
    }

    @Override
    public void logout() {
        if (getUsuarioActual() == null) {
            System.out.println("No se encuentra ningún usuario logeado.");
        } else {
            setUsuarioActual(null);
        }
    }

    @Override
    public void swithDrive(String Letra) {
        var listaLetras = drives.stream().map(Drive_20964708_RiquelmeOlguin::getLetra)
                .collect(Collectors.toList());

        if (getUsuarioActual() != null) {
            if (listaLetras.contains(Letra.toUpperCase())) {
                setDriveActual(Letra.toUpperCase());
                setRutaActual(Letra.toUpperCase().concat(":/"));
            } else {
                System.out.println("Drive no existente");
            }
        } else {
            System.out.println("No hay ningun usuario logeado, porfavor realize logeo.");
        }

    }

    @Override
    public void mkdir(String Nombre) {
        Scanner entrada = new Scanner(System.in);

        if (getUsuarioActual() != null) {
            Date Fecha_creacion;
            Date Fecha_modif;
            String Creador;
            boolean Lectura = false;
            boolean Oculto = false;
            int Valor1;
            int Valor2;
            Drive_20964708_RiquelmeOlguin DriveActual = null;
            AtributosSeguridad_20964708_RiquelmeOlguin Atributos;
            System.out.println("Ingrese Los atributos de seguridad");
            System.out.printf("###ATRIBUTO SOLO LECTURA### \n");
            System.out.println("1.SI");
            System.out.println("2.NO");
            Valor1 = entrada.nextInt();
            entrada.nextLine();
            if (Valor1 == 1) {
                Lectura = true;
            }
            System.out.println("###ATRIBUTO OCULTO### \n");
            System.out.println("1.SI");
            System.out.println("2.NO");
            Valor2 = entrada.nextInt();
            entrada.nextLine();
            if (Valor2 == 1) {
                Oculto = true;
            }
            Fecha_modif = new Date();
            Fecha_creacion = new Date();
            Creador = getUsuarioActual();
            Atributos = new AtributosSeguridad_20964708_RiquelmeOlguin(Lectura, Oculto);
            Folder_20964708_RiquelmeOlguin NewFolder = new Folder_20964708_RiquelmeOlguin(Nombre, Fecha_creacion, Fecha_modif, Creador, Atributos);
            String Ruta = getRutaActual();
            String[] RutaSplit = Ruta.split("/");
            for (Drive_20964708_RiquelmeOlguin Drive : drives) {  //ENCUENTRO DRIVE ACTUAL DADO EL CURRENT DRIVE
                if (Drive.getLetra().equals(getDriveActual())) {
                    DriveActual = Drive;
                }
            }
            if (RutaSplit.length == 1) {
                DriveActual.Contenido.add(NewFolder);
            } else {
                Folder_20964708_RiquelmeOlguin actual = null;
                if (DriveActual != null) {
                    actual = DriveActual.buscarFolder(RutaSplit[1]);
                    for (int i = 2; i < RutaSplit.length && actual != null; i++) {
                        Folder_20964708_RiquelmeOlguin Encontrado = actual.buscarFolder(RutaSplit[i]);
                        if (Encontrado != null) {
                            actual = Encontrado;
                        } else {
                            break;
                        }
                    }
                }
                if (actual != null) {
                    actual.Contenido.add(NewFolder);
                }
            }
        } else {
            System.out.println("No hay ningun usuario logeado, porfavor realize logeo.");
        }

    }

    @Override
    public void cd(String Nombre) {
        String RutaActual = getRutaActual();
        if (RutaActual == getDriveActual().concat(":/")) {

            setRutaActual(RutaActual.concat(Nombre));
        }else{
            setRutaActual(RutaActual.concat(Nombre));

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


    public String getDriveActual() {
        return DriveActual;
    }

    public void setDriveActual(String driveActual) {
        this.DriveActual = driveActual;
    }


    public void setRutaActual(String rutaActual) {
        RutaActual = rutaActual;
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
                ", RutaActual='" + RutaActual + '\'' +
                '}';
    }

    public String getRutaActual() {
        return RutaActual;
    }
}
