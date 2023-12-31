package CodigoFuente_20964708_RiquelmeOlguin;

import java.util.*;


public class Menu_20964708_RiquelmeOlguin {
    private boolean salirMenu;
    private boolean SistemaCreado = false;
    private Filesystem_20964708_RiquelmeOlguin Sistema;

    /**
     * Constructor por defecto de la clase, inicializa el menú.
     */
    public Menu_20964708_RiquelmeOlguin() {
        this.salirMenu = false;
    }

    /**
     * Método que ejecuta el menú principal y espera la entrada del usuario.
     */
    public void ejecutarMenu(){
        MenuPrincipal();
        scannerOpcion();
    }

    /**
     * Método que imprime el menú principal.
     */
    public void MenuPrincipal(){
        System.out.println("### Manipulador de Sistema ###");
        System.out.println("Escoja su opcion: ");
        System.out.println("1. Crear un Sistema");
        System.out.println("2. Modificar un Sistema");
        System.out.println("3. Visualizar Sistema");
        System.out.println("4. Salir");
        System.out.println();
        System.out.println();

    }

    /**
     * Método que maneja la entrada del usuario para el menú principal.
     */
    private void scannerOpcion(){
        Scanner entrada = new Scanner(System.in);
        try {

            System.out.print("Ingrese su opcion: ");
            int eleccion = entrada.nextInt();
            switch(eleccion){
                case 1:
                    if (!SistemaCreado) {
                        scannerOpcion1();
                        SistemaCreado = true;
                        break;
                    }else{
                        System.out.println("Ya existe un sistema!");
                        break;
                    }
                case 2:
                    if (SistemaCreado) {
                        mensajeopcional2();
                        scannerOpcion2();
                        break;
                    }else{
                        System.out.println("Debe crear un sistema antes de modificarlo.");
                        break;
                    }
                case 3:

                    if (SistemaCreado) {
                        System.out.printf("Filesystem_20964708_RiquelmeOlguin{%n" +
                                        "  nombre='%s',%n" +
                                        "  fechaCreacion=%s,%n" +
                                        "  drives=%s,%n" +
                                        "  usuarios=%s,%n" +
                                        "  usuarioActual='%s',%n" +
                                        "  DriveActual='%s',%n" +
                                        "  RutaActual='%s',%n" +
                                        "  Papelera='%s',%n" +
                                        "}%n",
                                Sistema.getNombreSistema(),
                                Sistema.getFechaCreacion(),
                                Sistema.getDrives(),
                                Sistema.getUsuarios(),
                                Sistema.getUsuarioActual(),
                                Sistema.getDriveActual(),
                                Sistema.getRutaActual(),
                                Sistema.getPapelera());

                        break;
                    }else {
                        System.out.println("Debe crear un sistema antes de visualizarlo.");
                        break;
                    }

                case 4:
                    System.out.println("\nPrograma finalizado.");
                    salirMenu = true;
                    break;


                default:
                    System.out.println("\nEsa opción no existe");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nSolo se admite de entrada enteros positivos");
            entrada.next();
        }
    }

    /**
     * Método que maneja la entrada del usuario para crear un nuevo sistema de archivos.
     */
    private void scannerOpcion1(){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese el nombre del SISTEMA a crear: ");
        String nombre = entrada.nextLine();
        this.Sistema = new Filesystem_20964708_RiquelmeOlguin(nombre);


    }

    /**
     * Método que imprime las opciones disponibles para modificar el sistema de archivos.
     */
    public void mensajeopcional2(){

        System.out.println("### Mofificador de un sistema ###");
        System.out.println("Escoja su opcion: ");
        System.out.println("1. Crear un drive");
        System.out.println("2. Registrar un usuario");
        System.out.println("3. Logear un usuario");
        System.out.println("4. Deslogeo de un usuario");
        System.out.println("5. Switch Drive");
        System.out.println("6. Crear un FOLDER");
        System.out.println("7. Crear FILE");
        System.out.println("8. Acceder a carpeta");
        System.out.println("9. Eliminar por nombre");
        System.out.println("10. Copiar Contenido");
        System.out.println("11. Mover Contenido");
        System.out.println("12. Renombrar Contenido");
        System.out.println("13. listar contenido");
        System.out.println("14. Formatear una unidad.");
        System.out.println("15. Encryptar un Folder.");
        System.out.println("16. Desencryptar un Folder.");
        System.out.println("17. Salir");
        System.out.println();
        System.out.println();

    }

    /**
     * Método que maneja la entrada del usuario para modificar el sistema de archivos.
     */

    public void scannerOpcion2(){
        Scanner entrada = new Scanner(System.in);
        try {
            System.out.print("Ingrese su opcion: ");
            int eleccion = entrada.nextInt();
            entrada.nextLine();

            switch(eleccion){
                case 1:
                    String letra = "RELLENO";
                    String nombre;
                    int capacidad;

                    while (!(letra.length() == 1)){
                        System.out.println("Ingrese la LETRA del drive. Ej: C");
                        letra = entrada.nextLine();
                    }
                    System.out.println("Ingrese el NOMBRE del drive a crear");
                    nombre = entrada.nextLine();
                    System.out.println("Ingrese la CAPACIDAD del drive a crear");
                    capacidad = entrada.nextInt();
                    entrada.nextLine();  // Consumir el salto de línea residual

                    Sistema.addDrive(letra.toUpperCase(),nombre,capacidad);

                    break;
                case 2:
                    String usuarioRegister;
                    System.out.println("Ingrese el usuario a registrar");
                    usuarioRegister = entrada.nextLine();
                    Sistema.register(usuarioRegister);
                    break;
                case 3:
                    String usuarioLogear;
                    System.out.println("Ingrese el usuario a logear");
                    usuarioLogear = entrada.nextLine();
                    Sistema.login(usuarioLogear.toUpperCase());
                    break;
                case 4:
                    Sistema.logout();
                    break;
                case 5:
                    String DriveSwitch;
                    System.out.println("Ingrese LETRA del drive a cambiar.");
                    DriveSwitch = entrada.nextLine();
                    Sistema.swithDrive(DriveSwitch.toUpperCase());
                    break;
                case 6:
                    System.out.println("----CREANDO UN FOLDER------ \n");
                    String Nombre;
                    System.out.println("Ingrese el NOMBRE del nuevo folder. \n");
                    Nombre = entrada.nextLine();
                    Sistema.mkdir(Nombre.toUpperCase());
                    break;
                case 7:
                    String NombreFile;
                    System.out.println("Ingrese el nombre del file a crear");
                    NombreFile = entrada.nextLine();
                    Sistema.crearFile(NombreFile.toUpperCase());
                    break;
                case 8:
                    String nombreRuta;
                    System.out.println("OPCIONES DISPONIBLES DE USO:");
                    System.out.println("- Volver al directorio padre : .. ");
                    System.out.println("- Volver a la raiz : / ");
                    System.out.println("- Referirse al directorio actual : . (Todo aquel que inicie con '.') ");
                    System.out.println("- Ingresar con ruta relativa: Ejemplo: Folder1/Folder2 ");
                    System.out.println("Ingrese el nombre de la carpeta");
                    nombreRuta = entrada.nextLine();
                    if (nombreRuta.equals("..")){
                        Sistema.cd(nombreRuta.toUpperCase());
                    }else if (!nombreRuta.contains(".")) { // esto es para que no entre en un file y no de error posterior.
                        Sistema.cd(nombreRuta.toUpperCase());
                    }else{
                        System.out.println("Ingrese un nombre de Carpeta valido");
                    }
                    break;
                case 9:
                    System.out.println("Ingrese el NOMBRE a eliminar");
                    System.out.println("Si es Folder -> nombre");
                    System.out.println("Si es File -> nombre.extension");
                    System.out.println("---Comandos especiales---");
                    System.out.println("Eliminar todo el contenido: *");
                    System.out.println("Eliminar dado extension : '*.extension'");
                    String NombreEliminar = entrada.nextLine();
                    Sistema.del(NombreEliminar.toUpperCase());
                    break;
                case 10:
                    System.out.println("Ingrese el nombre a copiar. Ej:");
                    System.out.println("Si es Folder -> nombre");
                    System.out.println("Si es File -> nombre.extension");
                    System.out.println("---Comandos especiales---");
                    System.out.println("Copiar dado una extension: '*.extension'");
                    String NombreCopiar = entrada.nextLine();
                    System.out.println("Ingrese la ruta destino");
                    String RutaDestino = entrada.nextLine();
                    Sistema.copy(NombreCopiar.toUpperCase(),RutaDestino.toUpperCase());
                    break;
                case 11:
                    System.out.println("Ingrese el nombre a mover");
                    System.out.println("Si es Folder -> nombre (Ej: Folder1)");
                    System.out.println("Si es File -> nombre.extension (Ej: File1.txt)");
                    String NombreMover = entrada.nextLine();
                    System.out.println("Ingrese la ruta destino (Ej: C:/folder3/folder6)");
                    String RutaDestinoMover = entrada.nextLine();
                    Sistema.move(NombreMover.toUpperCase(),RutaDestinoMover.toUpperCase());
                    break;
                case 12:
                    System.out.println("Ingrese el nombre del Folder/File a renombrar");
                    System.out.println("Si es Folder -> nombre (Ej: Folder1)");
                    System.out.println("Si es File -> nombre.extension (Ej: File1.txt)");
                    String NombreRenombrar = entrada.nextLine();
                    System.out.println("Ingrese el NUEVO nombre");
                    System.out.println("Si es Folder -> nombre (Ej: Folder1)");
                    System.out.println("Si es File -> nombre.extension (Ej: File1.txt)");
                    String NuevoNombre = entrada.nextLine();
                    Sistema.ren(NombreRenombrar.toUpperCase(),NuevoNombre.toUpperCase());
                    break;
                case 13:
                    System.out.println("Ingrese los elementos para listar en formato [\"elemento1\", \"elemento2\", ...]:");
                    System.out.println("--Presione ENTER SIN NINGUN CARACTER PARA LISTAR ACTUAL--");
                    String linea = entrada.nextLine();
                    List<String> ListParametros = new ArrayList<>(Arrays.asList(linea.split(",")));
                    Sistema.dir(ListParametros);

                    break;
                case 14:
                    System.out.println("Ingrese la LETRA del drive a formatear");
                    String Letra = entrada.nextLine();
                    System.out.println("Ingrese el nuevo nombre del drive");
                    String NuevoNombreDrive = entrada.nextLine();
                    if (Letra.length() > 1){
                        System.out.println("Ingrese una letra correctamente.");
                        break;
                    }else {
                        Sistema.format(Letra.toUpperCase(),NuevoNombreDrive.toUpperCase());
                        break;
                    }
                case 15:
                    System.out.println("Ingrese el nombre del folder a encryptar");
                    String NombreFolderEncrypt = entrada.nextLine();
                    System.out.println("Ingrese la contraseña del folder a encryptar");
                    String ContrasenaFolder = entrada.nextLine();
                    Sistema.encrypt(ContrasenaFolder,NombreFolderEncrypt.toUpperCase());
                    break;
                case 16:
                    System.out.println("Ingrese el nombre del folder a desencryptar");
                    String NombreFolderdesencrypt = entrada.nextLine();
                    System.out.println("Ingrese la contraseña del folder a desencryptar");
                    String ContrasenaFolderdesencrypt = entrada.nextLine();
                    Sistema.decrypt(ContrasenaFolderdesencrypt,NombreFolderdesencrypt.toUpperCase());
                    break;
                case 17:
                    System.out.println("Volver al menú principal.");
                    MenuPrincipal();
                    scannerOpcion();
                    break;
                default:
                    System.out.println("\nEsa opción no existe");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nSolo se admite de entrada enteros positivos");
            entrada.next();
        }
    }



    /**
     * Getter para la variable salirMenu.
     * @return boolean - el estado actual del menú (si debe salir o no).
     */

    public boolean getSalirMenu(){
        return salirMenu;
    }
}

