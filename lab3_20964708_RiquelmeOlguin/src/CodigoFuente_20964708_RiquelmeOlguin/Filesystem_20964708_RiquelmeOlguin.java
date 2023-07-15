package CodigoFuente_20964708_RiquelmeOlguin;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;

public class Filesystem_20964708_RiquelmeOlguin implements Interfaz_Filesystem_20964708_RiquelmeOlguin {


    private String NombreSistema;

    private Date fechaCreacion;

    private List<Drive_20964708_RiquelmeOlguin> drives;

    private List<String> usuarios;

    private String usuarioActual;

    private String DriveActual;

    private String RutaActual;

    private List<Papelera_20964708_RiquelmeOlguin> Papelera;

    /**
     * Contructor del sistema
     * @param NombreSistema
     */

    public Filesystem_20964708_RiquelmeOlguin(String NombreSistema) {
        this.NombreSistema = NombreSistema;
        this.fechaCreacion = new Date();
        this.drives = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.usuarioActual = null;
        this.DriveActual = null;
        this.RutaActual = null;
        this.Papelera = new ArrayList<>();

    }

    /**
     * Añade un nuevo Drive_20964708_RiquelmeOlguin a la lista de drives si la letra proporcionada no existe ya.
     * Si la letra ya existe en la lista de drives, muestra un mensaje de error.
     *
     * @param letra
     * @param nombre
     * @param capacidad
     */
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

    /**
     * Registra a un usuario en un sistema, si el usuario ya existe entrega un mensaje de error.
     * @param NombreUsuario
     */

    public void register(String NombreUsuario) {
        if (!usuarios.contains(NombreUsuario.toUpperCase())) {
            usuarios.add(NombreUsuario.toUpperCase());
        } else {
            System.out.println("El usuario " + NombreUsuario + " ya se encuentra registrado");
        }

    }

    /**
     * Logea a un usuario en un sistema, si ya existe un usuario logeado devuelve un
     * mensaje de error.
     * @param usuario
     */
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

    /**
     * Deslogea un usuario de un sistema, si no existe ningun usuario logeado devuelve mensaje de error.
     */
    @Override
    public void logout() {
        if (getUsuarioActual() == null) {
            System.out.println("No se encuentra ningún usuario logeado.");
        } else {
            setUsuarioActual(null);
        }
    }

    /**
     * Cambia el Drive actual al indicado por el parámetro Letra, si es que se encuentra disponible.
     * Si no hay un usuario actualmente logueado o si la Letra proporcionada no corresponde a un Drive existente,
     * se mostrará un mensaje de error.
     *
     * @param Letra
     */
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


    /**
     * Crea un nuevo directorio con el nombre proporcionado en la ruta actual si el usuario está logueado y el nombre no está en uso.
     * @param Nombre
     */
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
            char[] contrasena = CrearContrasena();
            AtributosSeguridad_20964708_RiquelmeOlguin Atributos = crearSeguridad();
            Folder_20964708_RiquelmeOlguin NewFolder = new Folder_20964708_RiquelmeOlguin(Nombre, Fecha_creacion, Fecha_modif, Creador, Atributos, contrasena);
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

    /**
     * Cambia la ruta actual a la proporcionada en el parámetro Nombre. Esta función maneja comandos estándar como ".." para moverse a la ruta padre,
     * "/" para ir a la raíz del drive y rutas relativas o absolutas. Si la ruta proporcionada no existe, se mostrará un mensaje de error.
     *
     * @param Nombre
     */
    @Override
    public void cd(String Nombre) { //AREGLAR .. EN SOLO UNA RAIZ
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
        } else if (Character.toString(Nombre.charAt(0)).equals(".") && (Nombre != "..")) {
            return;
        } else if (Nombre.contains("/")) { // Añadida esta opción para manejar rutas.
            List<Contenido_20964708_RiquelmeOlguin> contenido;
            if (getDriveActual().concat(":/").equals(getRutaActual())) {
                contenido = Driveactual.getContenido();
            } else {
                Folder_20964708_RiquelmeOlguin Folderactual = buscarContenido(RutaSplit, Driveactual);
                contenido = Folderactual.getContenido();
            }

            // Verificar si la ruta dada en Nombre existe.
            if(verificarRuta(contenido, Arrays.asList(Nombre.split("/")))) {
                if (RutaActual.equals(getDriveActual().concat(":/"))) {
                    setRutaActual(RutaActual.concat(Nombre));
                } else {
                    setRutaActual(RutaActual.concat("/").concat(Nombre));
                }
            } else {
                System.out.println("El nombre del archivo no existe en la ruta actual.");
            }
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


    /**
     * Añade un archivo a la ubicación actual. Si un archivo con el mismo nombre ya existe en la ubicación, se reemplaza.
     *
     * @param file
     */
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

    /**
     * Elimina el archivo o directorio con el nombre proporcionado de la ubicación actual.
     * Soporta comandos de eliminación avanzados como "*" para eliminar todo y "*.<extension>" para eliminar archivos de una cierta extensión.
     * Los archivos y directorios eliminados se mueven a la papelera de reciclaje.
     *
     * @param Nombre
     */
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
            if (RutaSplit.length == 1) {
                DriveActual.elimiarContenidoExt(DriveActual.getContenido(), Extension);
            }else{
                FolderActual = buscarContenido(RutaSplit, DriveActual);
                FolderActual.elimiarContenidoExt(FolderActual.getContenido(),Extension);
            }
        }else{
            if (RutaSplit.length == 1) {
                NombresContenido = getContenidoNombres(DriveActual.getContenido());
                if (!Nombre.contains(".")) { //Si no contiene un punto, es un folder
                    if (NombresContenido.contains(Nombre)) {
                        Folder_20964708_RiquelmeOlguin FolderPapelera = DriveActual.buscarFolder(Nombre);
                        Papelera_20964708_RiquelmeOlguin ObjetoPapelera = new Papelera_20964708_RiquelmeOlguin(FolderPapelera,RutaActual);
                        Papelera.add(ObjetoPapelera);
                        DriveActual.eliminarcontenidoDrive(DriveActual.getContenido(), Nombre);
                    } else {
                        System.out.println("El nombre ingresado no existe dentro del contenido.");
                    }
                }
                if (Nombre.contains(".")) { //Si contiene un punto, es un file
                    String NombreEliminar = Nombre.substring(0, Nombre.lastIndexOf('.'));
                    if (NombresContenido.contains(NombreEliminar)) {
                        File_20964708_RiquelmeOlguin FilePapelera = DriveActual.buscarFile(NombreEliminar);
                        Papelera_20964708_RiquelmeOlguin ObjetoPapelera = new Papelera_20964708_RiquelmeOlguin(FilePapelera,RutaActual);
                        Papelera.add(ObjetoPapelera);
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
                        Folder_20964708_RiquelmeOlguin FolderPapelera = FolderActual.buscarFolder(Nombre);
                        Papelera_20964708_RiquelmeOlguin ObjetoPapelera = new Papelera_20964708_RiquelmeOlguin(FolderPapelera,RutaActual);
                        Papelera.add(ObjetoPapelera);
                        FolderActual.eliminarcontenido(FolderActual.getContenido(), Nombre);
                    } else {
                        System.out.println("El nombre ingresado no existe dentro del contenido.");
                    }
                }
                if (Nombre.contains(".")) { //Si contiene un punto, es un file
                    String NombreEliminar = Nombre.substring(0, Nombre.lastIndexOf('.'));
                    if (NombresContenido.contains(NombreEliminar)) {
                        File_20964708_RiquelmeOlguin FilePapelera = FolderActual.buscarFile(NombreEliminar);
                        Papelera_20964708_RiquelmeOlguin ObjetoPapelera = new Papelera_20964708_RiquelmeOlguin(FilePapelera,RutaActual);
                        Papelera.add(ObjetoPapelera);
                        FolderActual.eliminarcontenido(FolderActual.getContenido(), NombreEliminar);
                    } else {
                        System.out.println("El nombre ingresado no existe dentro del contenido.");
                    }
                }
            }
        }
    }

    /**
     * Copia un archivo o directorio desde la ubicación actual a la ruta especificada.
     * El método también soporta copiar todos los archivos con una cierta extensión usando el formato "*.extension".
     *
     * @param NombreCopiar
     * @param path
     */

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
                if (Fcopiar !=null) {
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
                }else {
                    System.out.println("El folder donde copiar no fue encontrado.");
                }
            }
        }
    }


    /**
     * Mueve un archivo o directorio desde la ubicación actual a la ruta especificada.
     * El método eliminará el archivo o directorio de la ubicación original después de moverlo.
     *
     * @param nombreMover
     * @param path
     */
    @Override
    public void move(String nombreMover, String path) { //error cuando intento copiar dentro mover dentro de una carpeta.
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
        List<String> NombresUsados;

        if(RutaCopiarSplit.length == 1) { //Si es 1 significa que tengo que copiar en la raiz
            //Aqui verifico si tengo que copiar un folder o un file
            if (nombreMover.split("\\.").length == 1) {
                Folder_20964708_RiquelmeOlguin FolderMover = null;
                if (RutaSplitActual.length == 1){
                    FolderMover = DriveActual.buscarFolder(nombreMover);
                    delAux(nombreMover);
                }else{
                    FolderMover = Factual.buscarFolder(nombreMover);
                    delAux(nombreMover);
                }

                if (FolderMover != null) {
                    NombresUsados = getContenidoNombres(DriveActualCopiar.getContenido());
                    if (NombresUsados.contains(nombreMover)){
                        DriveActualCopiar.getContenido().removeIf(f -> f.getNombre().equals(nombreMover));
                        DriveActualCopiar.getContenido().add(FolderMover);
                        delAux(nombreMover);
                       // DriveActual.getContenido().removeIf(f -> f.getNombre().equals(NombreFile));
                    }else {
                        DriveActualCopiar.getContenido().add(FolderMover);
                        delAux(nombreMover);
                    }
                } else {
                    System.out.println("El nombre ingresado no coincide con ningun Folder existente.");
                }
            } else {
                String NombreSplit[] = nombreMover.split("\\.");
                File_20964708_RiquelmeOlguin FileCopia = null;
                if (RutaSplitActual.length == 1){
                    FileCopia = DriveActual.buscarFile(NombreSplit[0]);
                }else{
                    FileCopia = Factual.buscarFile(NombreSplit[0]);
                }

                if (FileCopia != null) {
                    String[] NombreFileSplit = nombreMover.split("\\.");
                    NombresUsados = getContenidoNombres(DriveActualCopiar.getContenido());
                    if (NombresUsados.contains(NombreFileSplit[0])){
                        DriveActualCopiar.getContenido().removeIf(f-> f.getNombre().equals(NombreFileSplit[0]));
                        DriveActualCopiar.getContenido().add(FileCopia);
                        delAux(nombreMover);
                    }else {
                        DriveActualCopiar.getContenido().add(FileCopia);
                        delAux(nombreMover);
                    }
                } else {
                    System.out.println("El nombre ingresado no conincide con ningun File existente.");
                }
            }
        }else{
            Folder_20964708_RiquelmeOlguin Fcopiar = buscarContenido(RutaCopiarSplit,DriveActualCopiar); //este sera el folder donde se copiará
            if (Fcopiar !=null) {
                if (nombreMover.split("\\.").length == 1) {
                    Folder_20964708_RiquelmeOlguin FolderMover = Factual.buscarFolder(nombreMover);
                    if (FolderMover != null) {
                        NombresUsados = getContenidoNombres(Fcopiar.getContenido());
                        if (NombresUsados.contains(nombreMover)){
                            Fcopiar.getContenido().removeIf(f -> f.getNombre().equals(nombreMover));
                            Fcopiar.getContenido().add(FolderMover);
                            delAux(nombreMover);
                            // DriveActual.getContenido().removeIf(f -> f.getNombre().equals(NombreFile));
                        }else {
                            Fcopiar.getContenido().add(FolderMover);
                            delAux(nombreMover);
                        }
                    } else {
                        System.out.println("El nombre ingresado no coincide con ningun Folder existente.");
                    }
                } else {
                    String NombreSplit[] = nombreMover.split("\\.");
                    File_20964708_RiquelmeOlguin FileMover = Factual.buscarFile(NombreSplit[0]);

                    if (FileMover != null) {
                        String[] NombreFileSplit = nombreMover.split("\\.");
                        NombresUsados = getContenidoNombres(Factual.getContenido());
                        if (NombresUsados.contains(NombreFileSplit[0])){
                            Factual.getContenido().removeIf(f-> f.getNombre().equals(NombreFileSplit[0]));
                            Factual.getContenido().add(FileMover);
                            delAux(nombreMover);
                        }else {
                            Factual.getContenido().add(FileMover);
                            delAux(nombreMover);
                        }
                    } else {
                        System.out.println("El nombre ingresado no conincide con ningun File existente.");
                    }
                }
            }else {
                System.out.println("El folder donde copiar no fue encontrado.");
            }
        }
    }


    /**
     * Renombra un archivo o directorio en la ubicación actual con un nuevo nombre.
     *
     * Este método buscará un archivo o directorio con el nombre original especificado
     * y cambiará su nombre al nuevo nombre proporcionado.
     *
     * Este método también verifica si el nuevo nombre ya existe en el sistema.
     * Si el nuevo nombre ya existe, se imprimirá un mensaje en la consola y
     * no se realizará la operación de cambio de nombre.
     * @param Nombre
     * @param NuevoNombre
     */
    @Override
    public void ren(String Nombre, String NuevoNombre) {
        String RutaActual = getRutaActual();
        String[] RutaActualSplit = RutaActual.split("/");
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        List<String> NombresUsados;


        if (RutaActualSplit.length == 1){
            String[] NombreSplit = Nombre.split("\\.");
            NombresUsados = getContenidoNombres(DriveActual.getContenido());
            if (!NombresUsados.contains(NuevoNombre)) {
                if (Nombre.split("\\.").length != 1) { //significa que no es un folder
                    String[] NuevoNombreSplit = NuevoNombre.split("\\.");
                    File_20964708_RiquelmeOlguin FileRenombrar = DriveActual.buscarFile(NombreSplit[0]);
                    if (FileRenombrar != null) {
                        FileRenombrar.setNombre(NuevoNombreSplit[0]);
                        FileRenombrar.setFormato(".".concat(NuevoNombreSplit[1]));
                    } else {
                        System.out.println("Nombre NO encontrado");
                    }
                } else {
                    Folder_20964708_RiquelmeOlguin FolderRenombrar = DriveActual.buscarFolder(Nombre);
                    if (FolderRenombrar != null) {
                        FolderRenombrar.setNombre(NuevoNombre);
                    } else {
                        System.out.println("Nombre NO encontrado.");
                    }
                }
            }else{
                System.out.println("Ese nombre ya existe en el sistema");
            }
        }else{
            Folder_20964708_RiquelmeOlguin Actual = buscarContenido(RutaActualSplit,DriveActual);
            NombresUsados = getContenidoNombres(Actual.getContenido());
            if (!NombresUsados.contains(NuevoNombre)) {
                if (Nombre.split("\\.").length != 1) { //significa que no es un folder
                    String[] NuevoNombreSplit = NuevoNombre.split("\\.");
                    String[] NombreSplit = Nombre.split("\\.");
                    File_20964708_RiquelmeOlguin FileRenombrar = Actual.buscarFile(NombreSplit[0]);
                    if (FileRenombrar != null) {
                        FileRenombrar.setNombre(NuevoNombreSplit[0]);
                        FileRenombrar.setFormato(".".concat(NuevoNombreSplit[1]));
                    } else {
                        System.out.println("Nombre NO encontrado");
                    }
                } else {
                    Folder_20964708_RiquelmeOlguin FolderRenombrar = Actual.buscarFolder(Nombre);
                    if (FolderRenombrar != null) {
                        FolderRenombrar.setNombre(NuevoNombre);
                    } else {
                        System.out.println("Nombre NO encontrado.");
                    }
                }
            }else{
                System.out.println("Ese nombre ya existe en el sistema");
            }
        }
    }

    /**
     * Este método imprime el contenido del directorio actual o dependiendo de los argumentos entregados.
     * @param args
     */
    public void dir(List<String> args) {
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        String RutaActual = getRutaActual();
        String[] RutaSplit = RutaActual.split("/");
        List<String> ListaNombres;

        if (args.get(0).equals("?")){
            System.out.println("---OPCIONES DISPONIBLES---");
            System.out.println(" ' ' -> Listar actual (Presionar enter)");
            System.out.println(" '/s' -> Listar actual y subdirectorios");
            System.out.println(" '/a' -> Listar actual incluyendo contenido oculto.");
            System.out.println(" '/s , /a' -> Listar actual y subdirectorios incluyendo contenido oculto (incluir la coma entre ambos)");
            System.out.println(" '/o N , /o -N -> Listar contenido actual en orden alfabetico ascendente/descendiente'");
            System.out.println(" '/o D , /o -D -> Listar contenido actual segun fecha creación ascendente/descendiente'");
        }
        if (RutaSplit.length != 1) {
            Folder_20964708_RiquelmeOlguin FolderActual = buscarContenido(RutaSplit, DriveActual);
            if (args == null || args.isEmpty()) {
                System.out.println("La lista está vacía.");
            } else if (args.size() == 1 && args.get(0).equals("")) {
                ListaNombres = getContenidoNombresSeguridad(FolderActual.getContenido());
                StringBuilder ListaMostarNombres = new StringBuilder();
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);
            } else if(args.get(0).equals("/s")){
                ListaNombres = getNombresTotalesSeguridad(FolderActual.getContenido());
                StringBuilder ListaMostarNombres = new StringBuilder();
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);
            }else if(args.get(0).equals("/a")){
                ListaNombres = getContenidoNombres(FolderActual.getContenido());
                StringBuilder ListaMostarNombres = new StringBuilder();
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);
            }else if(args.get(0).equals("/s") && args.get(1).equals("/a")){
                ListaNombres = getNombresTotales(FolderActual.getContenido());
                StringBuilder ListaMostarNombres = new StringBuilder();
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);
            }else if(args.get(0).equals("/o N") || args.get(0).equals("/o -N")){
                ListaNombres = getContenidoNombresSeguridad(FolderActual.getContenido());
                Collections.sort(ListaNombres);
                StringBuilder ListaMostarNombres = new StringBuilder();

                if (args.get(0).contains("-")){
                    Collections.reverse(ListaNombres);
                }
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);

            }else if(args.get(0).equals("/o D") || args.get(0).equals("/o -D")){
                ListaNombres = getNombresPorFecha(FolderActual.getContenido());
                StringBuilder ListaMostarNombres = new StringBuilder();

                if (args.get(0).contains("-")){
                    Collections.reverse(ListaNombres);
                }
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);


            }
            else{
                System.out.println("La lista tiene los siguientes elementos:");
                for (String arg : args) {
                    System.out.println(arg);
                }
            }

        } else {
            if (args == null || args.isEmpty()) {
                System.out.println("La lista está vacía.");
            } else if (args.size() == 1 && args.get(0).equals("")) {
                ListaNombres = getContenidoNombresSeguridad(DriveActual.getContenido());
                StringBuilder ListaMostarNombres = new StringBuilder();
                System.out.println("Todos los directorios incluyendo los SubDirectorios.");
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);
            } else if(args.get(0).equals("/s")){
                ListaNombres = getNombresTotalesSeguridad(DriveActual.getContenido());
                StringBuilder ListaMostarNombres = new StringBuilder();
                System.out.println("Todos los directorios incluyendo los SubDirectorios.");
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);
            }else if(args.get(0).equals("/a")){
                ListaNombres = getContenidoNombres(DriveActual.getContenido());
                StringBuilder ListaMostarNombres = new StringBuilder();
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);
            }else if(args.get(0).equals("/s") && args.get(1).equals("/a")){
                ListaNombres = getNombresTotales(DriveActual.getContenido());
                StringBuilder ListaMostarNombres = new StringBuilder();
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);
            }else if(args.get(0).equals("/o N") || args.get(0).equals("/o -N")){
                ListaNombres = getContenidoNombresSeguridad(DriveActual.getContenido());
                Collections.sort(ListaNombres);
                StringBuilder ListaMostarNombres = new StringBuilder();

                if (args.get(0).contains("-")){
                    Collections.reverse(ListaNombres);
                }
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);

            }else if(args.get(0).equals("/o D") || args.get(0).equals("/o -D")){
                ListaNombres = getNombresPorFecha(DriveActual.getContenido());
                StringBuilder ListaMostarNombres = new StringBuilder();

                if (args.get(0).contains("-")){
                    Collections.reverse(ListaNombres);
                }
                for (String Nombre : ListaNombres){
                    ListaMostarNombres.append(Nombre).append("\n");

                }
                System.out.println(ListaMostarNombres);


            }
            else{
                System.out.println("La lista tiene los siguientes elementos:");
                for (String arg : args) {
                    System.out.println(arg);
                }
                System.out.println("Por favor verifique los argumentos ingresados.");
            }
        }
    }

    /**
     * Método para formatear una unidad dado su letra, además se actualiza su nombre una vez formateado.
     *
     * @param Letra
     * @param Nombre
     */
    @Override
    public void format(String Letra, String Nombre) {
        Drive_20964708_RiquelmeOlguin DriveFormat = buscarDrivePorLetra(Letra);

        DriveFormat.getContenido().clear();
        DriveFormat.setNombre(Nombre);
    }

    /**
     * Funcion que permite encryptar una carpeta con todo su contenido dentro, la contraseña debe estar correcta.
     * @param Contrasena
     * @param NombreFolder
     */

    @Override
    public void encrypt(String Contrasena, String NombreFolder) {
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        String RutaActual = getRutaActual();
        String[] RutaActualSplit = RutaActual.split("/");
        Folder_20964708_RiquelmeOlguin FolderEncrypt;
        if (RutaActualSplit.length == 1) {
            FolderEncrypt = buscarFolder(DriveActual.getContenido(), NombreFolder);
        }else{
            Folder_20964708_RiquelmeOlguin FolderActual = buscarContenido(RutaActualSplit,DriveActual);
            FolderEncrypt = buscarFolder(FolderActual.getContenido(),NombreFolder);
        }

        if (FolderEncrypt != null && FolderEncrypt.getContrasenaString().equals(Contrasena)) {
            int modValue = calcularModulo5(FolderEncrypt.getContrasena());
            String NuevoNombre = sumarValorAModulo(FolderEncrypt.getNombre(),modValue);
            FolderEncrypt.setNombre(NuevoNombre);
            aplicarModuloAFolder(FolderEncrypt, modValue);
        } else {
            System.out.println("No se encontró el folder o la contraseña no coincide.");
        }
    }

    /**
     * Método para desencryptar un contenido.
     * @param Contrasena
     * @param NombreFolder
     */


    public void decrypt(String Contrasena, String NombreFolder) {
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        String RutaActual = getRutaActual();
        String[] RutaActualSplit = RutaActual.split("/");
        Folder_20964708_RiquelmeOlguin FolderDecrypt;
        if (RutaActualSplit.length == 1) {
            FolderDecrypt = buscarFolderDecrypt(DriveActual.getContenido(), NombreFolder, Contrasena);
        } else {
            Folder_20964708_RiquelmeOlguin FolderActual = buscarContenido(RutaActualSplit,DriveActual);
            FolderDecrypt = buscarFolderDecrypt(FolderActual.getContenido(), NombreFolder, Contrasena);
        }

        if (FolderDecrypt != null && FolderDecrypt.getContrasenaString().equals(Contrasena)) {
            int modValue = calcularModulo5(FolderDecrypt.getContrasena());
            String NuevoNombre = restarValorAModulo(FolderDecrypt.getNombre(),modValue);
            FolderDecrypt.setNombre(NuevoNombre);
            aplicarInversoModuloAFolder(FolderDecrypt, modValue);
        } else {
            System.out.println("No se encontró el folder o la contraseña no coincide.");
        }
    }




    /**
     * selector de Nombre sistema
     * @return
     */
    public String getNombreSistema() {
        return NombreSistema;
    }

    /**
     * Selector de fecha creacion
     * @return
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * selectro de drives del sistema
     * @return
     */
    public List<Drive_20964708_RiquelmeOlguin> getDrives() {
        return drives;
    }

    /**
     * selector de usuarios del sistema
     * @return
     */
    public List<String> getUsuarios() {
        return usuarios;
    }

    /**
     * modificador del usuario actual del sistema
     * @param usuarioActual
     */
    public void setUsuarioActual(String usuarioActual) {
        this.usuarioActual = usuarioActual;
    }


    /**
     * selector del usuario actual
     * @return
     */
    public String getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * selector del drive actual
     * @return
     */

    public String getDriveActual() {
        return DriveActual;
    }

    /**
     * modificador del drive actual
     * @param driveActual
     */

    public void setDriveActual(String driveActual) {
        this.DriveActual = driveActual;
    }

    /**
     * modificador de la ruta actual
     * @param rutaActual
     */
    public void setRutaActual(String rutaActual) {
        RutaActual = rutaActual;
    }

    /**
     * toString del sistema
     * @return
     */
    @Override
    public String toString() {
        String result = String.format(
                "Filesystem_20964708_RiquelmeOlguin{%n" +
                        "  nombre='%s',%n" +
                        "  fechaCreacion=%s,%n" +
                        "  drives=%s,%n" +
                        "  usuarios=%s,%n" +
                        "  usuarioActual='%s',%n" +
                        "  DriveActual='%s',%n" +
                        "  RutaActual='%s',%n" +
                        "  Papelera='%s',%n" +
                        "}%n",
                NombreSistema,
                fechaCreacion,
                drives,
                usuarios,
                usuarioActual,
                DriveActual,
                RutaActual,
                Papelera
        );
        return result;
    }

    /**
     * selector de la ruta actual del sistema
     * @return
     */

    public String getRutaActual() {
        return RutaActual;
    }

    /**
     * selector de la papelera del sistema
     * @return
     */
    public List<Papelera_20964708_RiquelmeOlguin> getPapelera() {
        return Papelera;
    }

    /**
     * metodo que busca el drive actual y devuelve el objeto drive actual
     * @return
     */

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

    /**
     * metodo que busca un drive dado una letra ingresada. devuelve el drive referente a esa letra
     * @param letraDrive
     * @return
     */
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


    /**
     * metodo que busca un folder dado un nombre en una lista de contenido, esto funciona hasta que la lista de la path se reccorre completa
     * y se llega hasta el ultimo folder, este se retorna como el folder actual posicionado del sistema.
     * @param RutaSplit
     * @param DriveActual
     * @return
     */

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

    /**
     * Metodo que obtiene todos los nombres de el contenido que contiene una lista de contenido.
     * @param contenido
     * @return
     */

    public List<String> getContenidoNombres(List<Contenido_20964708_RiquelmeOlguin> contenido) {
        List<String> nombres = new ArrayList<>();

        for (Contenido_20964708_RiquelmeOlguin objeto : contenido) {
            nombres.add(objeto.getNombre());
        }

        return nombres;
    }

    /**
     * Metodo que crea un file, dado el tipo que file que existen, esto se le piden al usuario.
     * una ves creado el file se hace el llamado a el metodo addFile para ingresarlo al sistema.
     * @param Nombre
     */
    @Override
    public void crearFile(String Nombre) {
        Random random = new Random();
        Scanner entrada = new Scanner(System.in);
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        if (DriveActual == null){
            System.out.println("Ningun drive seleccionado. Porfavor seleccione uno!");
            return;
        }else if (usuarioActual == null){
            System.out.println("Ningun Usuario registrado. Porfavor Registre uno!");
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
        char[] contrasena;

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
                        System.out.println("Ingrese el Contenido texto del archivo.");
                        contenido = entrada.nextLine();
                        contrasena = CrearContrasena();
                        TextFile_20964708_RiquelmeOlguin newfile = new TextFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano,contrasena);
                        addFile(newfile);
                        break;

                    case 2:
                        formato = ".MD";
                        System.out.println("Ingrese el Contenido texto del archivo.");
                        contenido = entrada.nextLine();
                        contrasena = CrearContrasena();
                        TextFile_20964708_RiquelmeOlguin newfile2 = new TextFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano,contrasena);
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
                        contrasena = CrearContrasena();
                        DocumentFile_20964708_RiquelmeOlguin newfile3 = new DocumentFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano,contrasena);
                        addFile(newfile3);
                        break;

                    case 2:
                        formato = ".PDF";
                        System.out.println("Ingrese el contenido del DOCUMENTO");
                        contenido = entrada.nextLine();
                        contrasena = CrearContrasena();
                        DocumentFile_20964708_RiquelmeOlguin newfile4 = new DocumentFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano,contrasena);
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
                        contrasena = CrearContrasena();
                        CodeFile_20964708_RiquelmeOlguin newfile5 = new CodeFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano,contrasena);
                        addFile(newfile5);
                        break;

                    case 2:
                        formato = ".PY";
                        System.out.println("Ingrese CODIGO del archivo");
                        contenido = entrada.nextLine();
                        contrasena = CrearContrasena();
                        CodeFile_20964708_RiquelmeOlguin newfile6 = new CodeFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano,contrasena);
                        addFile(newfile6);
                        break;
                    case 3:
                        formato = ".RKT";
                        System.out.println("Ingrese CODIGO del archivo");
                        contenido = entrada.nextLine();
                        contrasena = CrearContrasena();
                        CodeFile_20964708_RiquelmeOlguin newfile7 = new CodeFile_20964708_RiquelmeOlguin(Nombre,fechaCreacion,fechaModif,UserActual,seguridad,contenido,formato,tamano,contrasena);
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

    /**
     * Metodo que crea un objeto atributos de seguridad para el contenido.
     * @return
     */

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

    /**
     * Metodo que filtra Files dado un formato especifico de estos.
     * @param lista
     * @param formato
     * @return
     */

    public List<Contenido_20964708_RiquelmeOlguin> filtrarPorFormato(List<Contenido_20964708_RiquelmeOlguin> lista, String formato) {
        return lista.stream()
                .filter(contenido -> contenido instanceof File_20964708_RiquelmeOlguin && ((File_20964708_RiquelmeOlguin) contenido).getFormato().equals(formato))
                .collect(Collectors.toList());
    }

    /**
     * Metodo que verifica que dado una ruta, esta existe en el sistema.
     * @param Contenido
     * @param RutaSplit
     * @return
     */

    @Override
    public boolean verificarRuta(List<Contenido_20964708_RiquelmeOlguin> Contenido, List<String> RutaSplit) {
        Folder_20964708_RiquelmeOlguin FolderActual = null;
        for (String Nombre : RutaSplit){
            FolderActual = buscarFolder(Contenido,Nombre);
            if (FolderActual == null){
                return false;
            }else{
                Contenido = FolderActual.getContenido();
            }
        }
        return true;
    }

    /**
     * Metodo que dado una lista de contenido y un nombre devuelve el folder referente a ese folder.
     * @param contenido
     * @param nombre
     * @return
     */

    public Folder_20964708_RiquelmeOlguin buscarFolder(List<Contenido_20964708_RiquelmeOlguin> contenido, String nombre) {
        for (Contenido_20964708_RiquelmeOlguin objeto : contenido) {
            if (objeto instanceof Folder_20964708_RiquelmeOlguin && objeto.getNombre().equals(nombre)) {
                return (Folder_20964708_RiquelmeOlguin) objeto;
            }
        }
        return null;
    }

    /**
     * Método que elimina el contenido de una lista de contenido. (Similar a del, pero sin mover a papelera.)
     * @param Nombre
     */

    public void delAux(String Nombre) {
        String RutaActual = getRutaActual();
        String[] RutaSplit = RutaActual.split("/");
        Drive_20964708_RiquelmeOlguin DriveActual = buscarDriveActual();
        Folder_20964708_RiquelmeOlguin FolderActual;
        List<String> NombresContenido;
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
                    } else {
                        System.out.println("El nombre ingresado no existe dentro del contenido.");
                    }
                }
            }
        }

    /**
     * Metodo para obtener todos los nombres de una lista de contenido. (solamente contenido visible)
     * @param contenido
     * @return
     */

    public List<String> getContenidoNombresSeguridad(List<Contenido_20964708_RiquelmeOlguin> contenido) {
        List<String> nombres = new ArrayList<>();

        for (Contenido_20964708_RiquelmeOlguin objeto : contenido) {
            if (!objeto.getAtributoSeguridad().esOculto()) {
                nombres.add(objeto.getNombre());
            }
        }
        return nombres;
    }

    /**
     * Método para obtener los nombres de los contenidos de una lista y sus sublistas. (Solamente visibles)
     * @param contenido
     * @return
     */



    public List<String> getNombresTotalesSeguridad(List<Contenido_20964708_RiquelmeOlguin> contenido) {
        List<String> nombres = new ArrayList<>();

        for (Contenido_20964708_RiquelmeOlguin objeto : contenido) {
            if (!objeto.getAtributoSeguridad().esOculto()) {
                nombres.add(objeto.getNombre());
            }

            // Verificamos si el objeto es un Folder antes de hacer la llamada recursiva
            if (objeto instanceof Folder_20964708_RiquelmeOlguin) {
                Folder_20964708_RiquelmeOlguin carpeta = (Folder_20964708_RiquelmeOlguin) objeto;
                nombres.addAll(getNombresTotalesSeguridad(carpeta.getContenido()));
            }
        }

        return nombres;
    }

    /**
     * Método para obtener todos los nombres de una lista y sublistas
     * @param contenido
     * @return
     */

    public List<String> getNombresTotales(List<Contenido_20964708_RiquelmeOlguin> contenido) {
        List<String> nombres = new ArrayList<>();

        for (Contenido_20964708_RiquelmeOlguin objeto : contenido) {
                nombres.add(objeto.getNombre());
            // Verificamos si el objeto es un Folder antes de hacer la llamada recursiva
            if (objeto instanceof Folder_20964708_RiquelmeOlguin) {
                Folder_20964708_RiquelmeOlguin carpeta = (Folder_20964708_RiquelmeOlguin) objeto;
                nombres.addAll(getNombresTotales(carpeta.getContenido()));
            }
        }

        return nombres;
    }

    /**
     * Método que busca todos los objetos no ocultos de una lista de contenido.
     * @param contenido
     * @return
     */

    public List<Contenido_20964708_RiquelmeOlguin> recolectarObjetosNoOcultos(List<Contenido_20964708_RiquelmeOlguin> contenido) {
        List<Contenido_20964708_RiquelmeOlguin> objetosNoOcultos = new ArrayList<>();

        for (Contenido_20964708_RiquelmeOlguin objeto : contenido) {
            if (!objeto.getAtributoSeguridad().esOculto()) {
                objetosNoOcultos.add(objeto);
            }

            if (objeto instanceof Folder_20964708_RiquelmeOlguin) {
                Folder_20964708_RiquelmeOlguin carpeta = (Folder_20964708_RiquelmeOlguin) objeto;
                objetosNoOcultos.addAll(recolectarObjetosNoOcultos(carpeta.getContenido()));
            }
        }

        return objetosNoOcultos;
    }

    /**
     * Método que obtiene ordena por el atributo fecha una lista y luego obtiene los nombres de cada objeto.
     * @param contenido
     * @return
     */

    public List<String> getNombresPorFecha(List<Contenido_20964708_RiquelmeOlguin> contenido) {
        List<Contenido_20964708_RiquelmeOlguin> objetosNoOcultos = recolectarObjetosNoOcultos(contenido);

        Collections.sort(objetosNoOcultos, Comparator.comparing(Contenido_20964708_RiquelmeOlguin::getFechaCreacion));

        List<String> nombres = objetosNoOcultos.stream()
                .map(Contenido_20964708_RiquelmeOlguin::getNombre)
                .collect(Collectors.toList());

        return nombres;
    }

    /**
     * Método para crear una contraseña de un objeto contenido.
     * @return
     */

    public char[] CrearContrasena(){
        Scanner entrada = new Scanner(System.in);
        String contrasena;

        while (true) {
            System.out.println("Ingrese la contraseña:");
            contrasena = entrada.nextLine();

            if (contrasena.length() >= 6 && contrasena.matches(".*\\d.*") && contrasena.matches(".*[a-zA-Z].*")) {
                break;
            } else {
                System.out.println("La contraseña debe tener al menos 6 caracteres, al menos una letra y al menos un número.");
            }
        }

        return contrasena.toCharArray();
    }

    /**
     * Método para calcular el modulo de una contraseña.
     * @param contrasena
     * @return
     */

    public int calcularModulo5(char[] contrasena) {
        int suma = 0;
        for (char c : contrasena) {
            suma += (int) c;
        }
        return suma % 5;
    }

    /**
     * Método para sumar el modulo calculado a un Nombre (String)
     * @param nombre
     * @param modValue
     * @return
     */
    public String sumarValorAModulo(String nombre, int modValue) {
        char[] caracteres = nombre.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            caracteres[i] += modValue;
        }
        return new String(caracteres);
    }

    /**
     * Método que aplica el modulo calculado a una lista de carpetas y subcarpetas.
     * @param carpeta
     * @param modValue
     */

    private void aplicarModuloAFolder(Folder_20964708_RiquelmeOlguin carpeta, int modValue) {
        for (Contenido_20964708_RiquelmeOlguin objeto : carpeta.getContenido()) {
            String NuevoNombre = sumarValorAModulo(objeto.getNombre(), modValue);
            objeto.setNombre(NuevoNombre);

            // Verificamos si el objeto es un Folder antes de hacer la llamada recursiva
            if (objeto instanceof Folder_20964708_RiquelmeOlguin) {
                Folder_20964708_RiquelmeOlguin subCarpeta = (Folder_20964708_RiquelmeOlguin) objeto;
                aplicarModuloAFolder(subCarpeta, modValue);
            }
        }
    }

    /**
     * Método para restar el modulo calculado a un Nombre (String)
     * @param nombre
     * @param modValue
     * @return
     */
    public String restarValorAModulo(String nombre, int modValue) {
        char[] caracteres = nombre.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            caracteres[i] -= modValue;
        }
        return new String(caracteres);
    }

    /**
     * Método que aplica la inversa del modulo calculado a una lista de carpetas y subcarpetas.
     * @param carpeta
     * @param modValue
     */
    private void aplicarInversoModuloAFolder(Folder_20964708_RiquelmeOlguin carpeta, int modValue) {
        for (Contenido_20964708_RiquelmeOlguin objeto : carpeta.getContenido()) {
            String nuevoNombre = restarValorAModulo(objeto.getNombre(), modValue);
            objeto.setNombre(nuevoNombre);

            // Verificamos si el objeto es un Folder antes de hacer la llamada recursiva
            if (objeto instanceof Folder_20964708_RiquelmeOlguin) {
                Folder_20964708_RiquelmeOlguin subCarpeta = (Folder_20964708_RiquelmeOlguin) objeto;
                aplicarInversoModuloAFolder(subCarpeta, modValue);
            }
        }
    }

    public Folder_20964708_RiquelmeOlguin buscarFolderDecrypt(List<Contenido_20964708_RiquelmeOlguin> contenido, String NombreFolder, String Contrasena) {
        for (Contenido_20964708_RiquelmeOlguin objeto : contenido) {
            if (objeto instanceof Folder_20964708_RiquelmeOlguin) {
                Folder_20964708_RiquelmeOlguin folder = (Folder_20964708_RiquelmeOlguin) objeto;
                if (folder.getContrasenaString().equals(Contrasena)) {
                    int modValue = calcularModulo5(folder.getContrasena());
                    String nombreDecifrado = restarValorAModulo(folder.getNombre(), modValue);
                    if (nombreDecifrado.equals(NombreFolder)) {
                        return folder;
                    }
                }
            }
        }
        return null;
    }






















}



