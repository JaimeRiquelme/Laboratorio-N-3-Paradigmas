package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;

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
        if (getUsuarioActual() != null) {
            Date Fecha_creacion;
            Date Fecha_modif;
            String Creador;
            Drive_20964708_RiquelmeOlguin DriveActual;
            AtributosSeguridad_20964708_RiquelmeOlguin Atributos;
            Fecha_modif = new Date();
            Fecha_creacion = new Date();
            Creador = getUsuarioActual();
            Atributos = crearSeguridad();
            Folder_20964708_RiquelmeOlguin NewFolder = new Folder_20964708_RiquelmeOlguin(Nombre, Fecha_creacion, Fecha_modif, Creador, Atributos);
            String Ruta = getRutaActual();
            String[] RutaSplit = Ruta.split("/");
            DriveActual = buscarDriveActual();
            List<String> NombresUsados;
            if (RutaSplit.length == 1) { // C:/Folder1/Folder2/folder3/folder4
                NombresUsados = getContenidoNombres(DriveActual.Contenido);
                if (!NombresUsados.contains(Nombre)){
                    DriveActual.Contenido.add(NewFolder);
                }else{
                    System.out.println("Este nombre ya existe!, Prueba otro.");
                }
            } else {
                Folder_20964708_RiquelmeOlguin actual = buscarContenido(RutaSplit, DriveActual);
                NombresUsados = getContenidoNombres(actual.Contenido);
                if (!NombresUsados.contains(Nombre)) {
                        actual.Contenido.add(NewFolder);
                        actual.setFechaModificacion(Fecha_modif); //Cada ves que se inserta algo en el folder, se actualiza la fecha modif
                }else {
                    System.out.println("Este nombre ya existe!, Prueba otro.");
                }
            }
        } else {
            System.out.println("No hay ningun usuario logeado, porfavor realize logeo.");
        }
    }


    @Override
    public void cd(String Nombre) {
        String RutaActual = getRutaActual();
        String[] RutaSplit = RutaActual.split("/");

        Drive_20964708_RiquelmeOlguin Driveactual = buscarDriveActual();

        if (Driveactual == null) {
            System.out.println("Drive actual no encontrado.");
            return;
        }

        if ("..".equals(Nombre)) {
            if(RutaSplit.length <= 2){
                setRutaActual(getDriveActual().concat(":/"));
            }
            else {
                String NuevaRuta = RutaActual.substring(0, RutaActual.lastIndexOf("/"));
                setRutaActual(NuevaRuta);
            }
        } else if ("/".equals(Nombre)) {
            setRutaActual(getDriveActual().concat(":/"));
        }else if (Character.toString(Nombre.charAt(0)).equals(".") && (Nombre != "..")) {
            return;
        } else {
            List<String> NombresContenido;
            if (getDriveActual().concat(":/").equals(getRutaActual())) {
                NombresContenido = getContenidoNombres(Driveactual.Contenido);
            } else {
                Folder_20964708_RiquelmeOlguin Folderactual = buscarContenido(RutaSplit, Driveactual);
                NombresContenido = getContenidoNombres(Folderactual.Contenido);
            }

            if(NombresContenido.contains(Nombre)) {
                if (RutaActual.equals(getDriveActual().concat(":/"))) {
                    setRutaActual(RutaActual.concat(Nombre));
                } else {
                    setRutaActual(RutaActual.concat("/").concat(Nombre));
                }
            } else {
                System.out.println("El nombre del archivo no existe en la ruta actual.");
            }
        }
    }

    @Override
    public void addFile(File_20964708_RiquelmeOlguin file) {
        String Ruta = getRutaActual();
        String[] RutaSplit = Ruta.split("/");
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        if (RutaSplit.length == 1){
            DriveActual.Contenido.add(file);
        }else {
            Folder_20964708_RiquelmeOlguin actual = buscarContenido(RutaSplit,DriveActual);
            actual.Contenido.add(file);
        }
    }

    @Override
    public void del(String Nombre) {
        String RutaActual = getRutaActual();
        String[] RutaSplit = RutaActual.split("/");
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        Folder_20964708_RiquelmeOlguin FolderActual;

        List<String> NombresContenido;
        if (Nombre.equals("*")){
            if (RutaSplit.length == 1){
                DriveActual.Contenido.clear();
            }else{
                FolderActual = buscarContenido(RutaSplit,DriveActual);
                FolderActual.Contenido.clear();
            }
        }else if(Nombre.contains("*") && Nombre.contains(".")){
            System.out.println("IngresaEaaa");
            String[] NombreSplit = Nombre.split("\\.");
            String Extension = ".".concat(NombreSplit[1].toUpperCase());
            DriveActual.elimiarContenidoExt(DriveActual.Contenido,Extension);
        }else{
            if (RutaSplit.length == 1) {
                NombresContenido = getContenidoNombres(DriveActual.Contenido);
                if (!Nombre.contains(".")) { //Si no contiene un punto, es un folder
                    if (NombresContenido.contains(Nombre)) {
                        DriveActual.eliminarcontenidoDrive(DriveActual.Contenido, Nombre);
                    } else {
                        System.out.println("El nombre ingresado no existe dentro del contenido.");
                    }
                }
                if (Nombre.contains(".")) { //Si contiene un punto, es un file
                    String NombreEliminar = Nombre.substring(0, Nombre.lastIndexOf('.'));
                    if (NombresContenido.contains(NombreEliminar)) {
                        DriveActual.eliminarcontenidoDrive(DriveActual.Contenido, NombreEliminar);
                    } else {
                        System.out.println("El nombre ingresado no existe dentro del contenido.");
                    }
                }
            } else {
                FolderActual = buscarContenido(RutaSplit, DriveActual);
                NombresContenido = getContenidoNombres(FolderActual.Contenido);
                if (!Nombre.contains(".")) { //Si no contiene un punto, es un folder
                    if (NombresContenido.contains(Nombre)) {
                        FolderActual.eliminarcontenido(FolderActual.Contenido, Nombre);
                    } else {
                        System.out.println("El nombre ingresado no existe dentro del contenido.");
                    }
                }
                if (Nombre.contains(".")) { //Si contiene un punto, es un file
                    String NombreEliminar = Nombre.substring(0, Nombre.lastIndexOf('.'));
                    if (NombresContenido.contains(NombreEliminar)) {
                        FolderActual.eliminarcontenido(FolderActual.Contenido, NombreEliminar);
                    }
                }
            }
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

    public Drive_20964708_RiquelmeOlguin buscarDriveActual() {
        Drive_20964708_RiquelmeOlguin DriveActual = null;
        for (Drive_20964708_RiquelmeOlguin Drive : drives) {
            if (Drive.getLetra().equals(getDriveActual())) {
                DriveActual = Drive;

                break;
            }
        }
        return DriveActual;
    }


    public Folder_20964708_RiquelmeOlguin buscarContenido(String[] RutaSplit, Drive_20964708_RiquelmeOlguin DriveActual) {
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
        return actual;
    }

    public List<String> getContenidoNombres(List<Contenido_20964708_RiquelmeOlguin> contenido) {
        List<String> nombres = new ArrayList<>();

        for (Contenido_20964708_RiquelmeOlguin objeto : contenido) {
            nombres.add(objeto.getNombre());
        }
        return nombres;
    }

    @Override
    public void crearFile(String Nombre) {
        Random random = new Random();
        Scanner entrada = new Scanner(System.in);
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        String RutaActual = getRutaActual();
        String[] RutaActualSplit = RutaActual.split("/");
        List<String> NombresUsados;

        //SI ESTE IF SE CUMPLE SIGNIFICA QUE YA EXISTE EL NOMBRE EN LA RUTA ACTUAL, sale del metodo.
        if (RutaActualSplit.length == 1){
            NombresUsados = getContenidoNombres(DriveActual.Contenido);
            if (NombresUsados.contains(Nombre)){
                System.out.println("Ese nombre ya existe!, Prueba otro.");
                return;
            }

        }else{
            Folder_20964708_RiquelmeOlguin nf = buscarContenido(RutaActualSplit,DriveActual);
            NombresUsados = getContenidoNombres(nf.Contenido);
            if (NombresUsados.contains(Nombre)){
                System.out.println("Ese nombre ya existe!, Prueba otro.");
                return;
            }
        }

        System.out.println("CREANDO UN FILE");
        System.out.println("INGRESE EL TIPO DE ARCHIVO A CREAR:");
        System.out.println("1.- Tipo Text.(.txt / .md)");
        System.out.println("2.- Tipo Documento. (.docx / .pdf)");
        System.out.println("3.- Tipo Codigo. (.java / .py / .rkt)");
        int eleccion = entrada.nextInt();
        entrada.nextLine();
        Date fechaCreacion = new Date();
        Date fechaModif = new Date();
        String UserActual = getUsuarioActual();
        AtributosSeguridad_20964708_RiquelmeOlguin seguridad;
        String contenido;
        String formato;
        long tamano = random.nextInt(10000); //genera un numero random del 0 al 9999 para el tamaño

        switch(eleccion) {
            case 1:
                System.out.println("TIPO DE Extensión");
                System.out.println("1.- .txt");
                System.out.println("2.- .md");
                int eleccion2 = entrada.nextInt();
                entrada.nextLine();

                seguridad = crearSeguridad();
                switch (eleccion2){
                    case 1:
                        formato = ".TXT";
                        System.out.println("Ingrese el Conteido texto del archivo.");
                        contenido = entrada.nextLine();
                        TextFile_20964708_RiquelmeOlguin newfile = new TextFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano);
                        addFile(newfile);
                        break;

                    case 2:
                        formato = ".MD";
                        System.out.println("Ingrese el Contenido texto del archivo.");
                        contenido = entrada.nextLine();
                        TextFile_20964708_RiquelmeOlguin newfile2 = new TextFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano);
                        addFile(newfile2);
                        break;
                }
                break;
            case 2:

                System.out.println("TIPO DE Extensión");
                System.out.println("1.- .docx");
                System.out.println("2.- .pdf");
                int eleccion3 = entrada.nextInt();
                entrada.nextLine();

                seguridad = crearSeguridad();
                switch (eleccion3){
                    case 1:
                        formato = ".DOCX";
                        System.out.println("Ingrese el contenido del DOCUMENTO");
                        contenido = entrada.nextLine();
                        DocumentFile_20964708_RiquelmeOlguin newfile3 = new DocumentFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano);
                        addFile(newfile3);
                        break;

                    case 2:
                        formato = ".PDF";
                        System.out.println("Ingrese el contenido del DOCUMENTO");
                        contenido = entrada.nextLine();
                        DocumentFile_20964708_RiquelmeOlguin newfile4 = new DocumentFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano);
                        addFile(newfile4);
                        break;
                }
                break;
            case 3:
                System.out.println("TIPO DE Extensión");
                System.out.println("1.- .java");
                System.out.println("2.- .py");
                System.out.println("3.- .rkt");
                int eleccion4 = entrada.nextInt();
                entrada.nextLine();

                seguridad = crearSeguridad();
                switch (eleccion4){
                    case 1:
                        formato = ".JAVA";
                        System.out.println("Ingrese CODIGO del archivo");
                        contenido = entrada.nextLine();
                        CodeFile_20964708_RiquelmeOlguin newfile5 = new CodeFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano);
                        addFile(newfile5);
                        break;

                    case 2:
                        formato = ".PY";
                        System.out.println("Ingrese CODIGO del archivo");
                        contenido = entrada.nextLine();
                        CodeFile_20964708_RiquelmeOlguin newfile6 = new CodeFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano);
                        addFile(newfile6);
                        break;
                    case 3:
                        formato = ".RKT";
                        System.out.println("Ingrese CODIGO del archivo");
                        contenido = entrada.nextLine();
                        CodeFile_20964708_RiquelmeOlguin newfile7 = new CodeFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano);
                        addFile(newfile7);
                        break;
                }
                break;
            default:
                System.out.println("La elección no es válida.");
                break;
        }


        return;
    }

    public AtributosSeguridad_20964708_RiquelmeOlguin crearSeguridad(){
        Scanner entrada = new Scanner(System.in);
        int Valor1;
        int Valor2;
        boolean Lectura = false;
        boolean Oculto = false;
        System.out.println("Ingrese Los atributos de seguridad");
        System.out.println("###ATRIBUTO SOLO LECTURA### \n");
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
        AtributosSeguridad_20964708_RiquelmeOlguin seguridad = new AtributosSeguridad_20964708_RiquelmeOlguin(Lectura,Oculto);

        return seguridad;

    }
}



