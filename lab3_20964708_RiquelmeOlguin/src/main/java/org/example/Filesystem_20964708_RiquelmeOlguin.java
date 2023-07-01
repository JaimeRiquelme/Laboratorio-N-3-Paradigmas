package org.example;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;

public class Filesystem_20964708_RiquelmeOlguin implements Interfaz_Filesystem_20964708_RiquelmeOlguin {

    private String nombre;

    private Date fechaCreacion;

    private List<Drive_20964708_RiquelmeOlguin> drives;

    private List<String> usuarios;

    private String usuarioActual;

    private String DriveActual;

    private String RutaActual;

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
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        if (DriveActual == null){
            System.out.println("Ningun drive seleccionado. Porfavor seleccione uno!");
            return;
        }
        if (getUsuarioActual() != null) {
            Date Fecha_creacion = new Date();
            Date Fecha_modif = new Date();
            String Creador = getUsuarioActual();
            String Ruta = getRutaActual();
            String[] RutaSplit = Ruta.split("/");
            AtributosSeguridad_20964708_RiquelmeOlguin Atributos = crearSeguridad();
            Folder_20964708_RiquelmeOlguin NewFolder = new Folder_20964708_RiquelmeOlguin(Nombre, Fecha_creacion, Fecha_modif, Creador, Atributos);
            List<String> NombresUsados;
            if (RutaSplit.length == 1) { // C:/Folder1/Folder2/folder3/folder4
                NombresUsados = getContenidoNombres(DriveActual.getContenido());
                if (!NombresUsados.contains(Nombre)){
                    DriveActual.getContenido().add(NewFolder);
                }else{
                    System.out.println("Este nombre ya existe!, Prueba otro.");
                }
            } else {
                Folder_20964708_RiquelmeOlguin actual = buscarContenido(RutaSplit, DriveActual);
                NombresUsados = getContenidoNombres(actual.getContenido());
                if (!NombresUsados.contains(Nombre)) {
                        actual.getContenido().add(NewFolder);
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
                NombresContenido = getContenidoNombres(Driveactual.getContenido());
            } else {
                Folder_20964708_RiquelmeOlguin Folderactual = buscarContenido(RutaSplit, Driveactual);
                NombresContenido = getContenidoNombres(Folderactual.getContenido());
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
        List<String> NombresUsados;
        String NombreFile = file.getNombre();


        if(RutaSplit.length == 1){
            NombresUsados = getContenidoNombres(DriveActual.getContenido());
            if (NombresUsados.contains(NombreFile)){
                DriveActual.getContenido().removeIf(f -> f.getNombre().equals(NombreFile));
                DriveActual.getContenido().add(file);
            }else {
                DriveActual.getContenido().add(file);
            }
        }else{
            Folder_20964708_RiquelmeOlguin folderActual = buscarContenido(RutaSplit,DriveActual);
            NombresUsados = getContenidoNombres(folderActual.getContenido());
            if (NombresUsados.contains(NombreFile)){
                folderActual.getContenido().removeIf(f -> f.getNombre().equals(NombreFile));
                folderActual.getContenido().add(file);
            }else{
                folderActual.getContenido().add(file);
            }
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
                DriveActual.getContenido().clear();
            }else{
                FolderActual = buscarContenido(RutaSplit,DriveActual);
                FolderActual.getContenido().clear();
            }
        }else if(Nombre.contains("*") && Nombre.contains(".")){
            String[] NombreSplit = Nombre.split("\\.");
            String Extension = ".".concat(NombreSplit[1].toUpperCase());
            DriveActual.elimiarContenidoExt(DriveActual.getContenido(),Extension);
        }else{
            if (RutaSplit.length == 1) {
                NombresContenido = getContenidoNombres(DriveActual.getContenido());
                if (!Nombre.contains(".")) { //Si no contiene un punto, es un folder
                    if (NombresContenido.contains(Nombre)) {
                        DriveActual.eliminarcontenidoDrive(DriveActual.getContenido(), Nombre);
                    } else {
                        System.out.println("El nombre ingresado no existe dentro del contenido.");
                    }
                }
                if (Nombre.contains(".")) { //Si contiene un punto, es un file
                    String NombreEliminar = Nombre.substring(0, Nombre.lastIndexOf('.'));
                    if (NombresContenido.contains(NombreEliminar)) {
                        DriveActual.eliminarcontenidoDrive(DriveActual.getContenido(), NombreEliminar);
                    } else {
                        System.out.println("El nombre ingresado no existe dentro del contenido.");
                    }
                }
            } else {
                FolderActual = buscarContenido(RutaSplit, DriveActual);
                NombresContenido = getContenidoNombres(FolderActual.getContenido());
                if (!Nombre.contains(".")) { //Si no contiene un punto, es un folder
                    if (NombresContenido.contains(Nombre)) {
                        FolderActual.eliminarcontenido(FolderActual.getContenido(), Nombre);
                    } else {
                        System.out.println("El nombre ingresado no existe dentro del contenido.");
                    }
                }
                if (Nombre.contains(".")) { //Si contiene un punto, es un file
                    String NombreEliminar = Nombre.substring(0, Nombre.lastIndexOf('.'));
                    if (NombresContenido.contains(NombreEliminar)) {
                        FolderActual.eliminarcontenido(FolderActual.getContenido(), NombreEliminar);
                    }
                }
            }
        }
    }

    @Override
    public void copy(String NombreCopiar, String path) { //arreglar cuando intenta copiar en un folder que no existe..........
        //Aquí obtengo el drive y folder que voy a copiar
        String RutaActual = getRutaActual();
        String[] RutaSplitActual = RutaActual.split("/");
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        Folder_20964708_RiquelmeOlguin Factual = null; // aqui tengo el folder actual donde se está en la ruta
        if (RutaSplitActual.length != 1){
            Factual = buscarContenido(RutaSplitActual,DriveActual);
        }

        //Aqui obtengo el drive y folder donde copiar.
        String DriveCopiar = path.substring(0,1);
        String[] RutaCopiarSplit = path.split("/");
        Drive_20964708_RiquelmeOlguin DriveActualCopiar = buscarDrivePorLetra(DriveCopiar);

        if (NombreCopiar.contains("*") && NombreCopiar.contains(".")){ //Significa que estoy usando la opcion *.extension
            String[] NombreSplit = NombreCopiar.split("\\.");
            String Extension = ".".concat(NombreSplit[1].toUpperCase());
            List<Contenido_20964708_RiquelmeOlguin> ContenidoCopy;
            if (RutaSplitActual.length == 1){
                ContenidoCopy = filtrarPorFormato(DriveActual.getContenido(),Extension);
            }else{
                ContenidoCopy = filtrarPorFormato(Factual.getContenido(),Extension);
            }
            if (RutaCopiarSplit.length == 1){ //Tengo que copiar en la raiz
                DriveActualCopiar.getContenido().addAll(ContenidoCopy);
            }else { // sino tengo que copiar en el folder actual
                Folder_20964708_RiquelmeOlguin FolderCopiar = buscarContenido(RutaCopiarSplit,DriveActualCopiar);
                FolderCopiar.getContenido().addAll(ContenidoCopy);
            }
        }else{
            if(RutaCopiarSplit.length == 1) { //Si es 1 significa que tengo que copiar en la raiz
                //Aqui verifico si tengo que copiar un folder o un file
                if (NombreCopiar.split("\\.").length == 1) {
                    Folder_20964708_RiquelmeOlguin FolderCopiar = null;
                    if (RutaSplitActual.length == 1){
                        FolderCopiar = DriveActual.buscarFolder(NombreCopiar);
                    }else{
                        FolderCopiar = Factual.buscarFolder(NombreCopiar);
                    }

                    if (FolderCopiar != null) {
                        DriveActualCopiar.getContenido().add(FolderCopiar);
                    } else {
                        System.out.println("El nombre ingresado no coincide con ningun Folder existente.");
                    }
                } else {
                    String NombreSplit[] = NombreCopiar.split("\\.");
                    File_20964708_RiquelmeOlguin FileCopia = null;
                    if (RutaSplitActual.length == 1){
                        FileCopia = DriveActual.buscarFile(NombreSplit[0]);
                    }else{
                        FileCopia = Factual.buscarFile(NombreSplit[0]);
                    }

                    if (FileCopia != null) {
                        DriveActualCopiar.getContenido().add(FileCopia);
                    } else {
                        System.out.println("El nombre ingresado no conincide con ningun File existente.");
                    }
                }
            }else{
                Folder_20964708_RiquelmeOlguin Fcopiar = buscarContenido(RutaCopiarSplit,DriveActualCopiar); //este sera el folder donde se copiará
                if (NombreCopiar.split("\\.").length == 1) {
                    Folder_20964708_RiquelmeOlguin FolderCopiar = Factual.buscarFolder(NombreCopiar);
                    if (FolderCopiar != null) {
                        Fcopiar.getContenido().add(FolderCopiar);
                    } else {
                        System.out.println("El nombre ingresado no coincide con ningun Folder existente.");
                    }
                } else {
                    String NombreSplit[] = NombreCopiar.split("\\.");
                    File_20964708_RiquelmeOlguin FileCopia = Factual.buscarFile(NombreSplit[0]);

                    if (FileCopia != null) {
                        Fcopiar.getContenido().add(FileCopia);
                    } else {
                        System.out.println("El nombre ingresado no conincide con ningun File existente.");
                    }
                }
            }
        }
    }

    @Override
    public void move(String nombreMover, String path) {
        // Primero intentamos copiar el contenido a la nueva ubicación
        copy(nombreMover, path);

        // Luego intentamos eliminar el contenido original
        del(nombreMover);
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
        Drive_20964708_RiquelmeOlguin BuscarDriveActual = null;
        for (Drive_20964708_RiquelmeOlguin Drive : drives) {
            if (Drive.getLetra().equals(getDriveActual())) {
                BuscarDriveActual = Drive;

                break;
            }
        }
        return BuscarDriveActual;
    }

    public Drive_20964708_RiquelmeOlguin buscarDrivePorLetra(String letraDrive) {
        Drive_20964708_RiquelmeOlguin BuscarDrive = null;
        for (Drive_20964708_RiquelmeOlguin Drive : drives) {
            if (Drive.getLetra().equals(letraDrive)) {
                BuscarDrive = Drive;
                break;
            }
        }
        return BuscarDrive;
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
        if (DriveActual == null){
            System.out.println("Ningun drive seleccionado. Porfavor seleccione uno!");
            return;
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

    public List<Contenido_20964708_RiquelmeOlguin> filtrarPorFormato(List<Contenido_20964708_RiquelmeOlguin> lista, String formato) {
        return lista.stream()
                .filter(contenido -> contenido instanceof File_20964708_RiquelmeOlguin && ((File_20964708_RiquelmeOlguin) contenido).getFormato().equals(formato))
                .collect(Collectors.toList());
    }
}



