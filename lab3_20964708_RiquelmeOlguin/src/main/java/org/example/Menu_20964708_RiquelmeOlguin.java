package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu_20964708_RiquelmeOlguin {
    private boolean salirMenu;
    private Filesystem_20964708_RiquelmeOlguin Sistema;


    public Menu_20964708_RiquelmeOlguin() {
        this.salirMenu = false;
    }

    public void ejecutarMenu(){
        MenuPrincipal();
        scannerOpcion();
    }

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

    private void scannerOpcion(){
        Scanner entrada = new Scanner(System.in);
        try {

            System.out.print("Ingrese su opcion: ");
            int eleccion = entrada.nextInt();
            switch(eleccion){
                case 1:
                    scannerOpcion1();
                    break;
                case 2:
                    mensajeopcional2();
                    scannerOpcion2();
                case 3:
                    System.out.printf("Filesystem_20964708_RiquelmeOlguin{" +
                            "nombre='" + Sistema.getNombre() + '\'' +
                            ", fechaCreacion=" + Sistema.getFechaCreacion() +
                            ", drives=" + Sistema.getDrives() +
                            ", usuarios=" + Sistema.getUsuarios() +
                            ", usuarioActual='" + Sistema.getUsuarioActual() + '\'' +
                            ", DriveActual='" + Sistema.getDriveActual() + '\'' +
                            ", RutaActual='" + Sistema.getRutaActual() + '\'' +
                            '}');
                    break;


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

    private void scannerOpcion1(){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese el nombre del SISTEMA a crear: ");
        String nombre = entrada.nextLine();
        this.Sistema = new Filesystem_20964708_RiquelmeOlguin(nombre);


    }

    public void mensajeopcional2(){

        System.out.println("### Mofificador de un sistema ###");
        System.out.println("Escoja su opcion: ");
        System.out.println("1. Crear un drive");
        System.out.println("2. Registrar un usuario");
        System.out.println("3. Logear un usuario");
        System.out.println("4. Deslogeo de un usuario");
        System.out.println("5. Switch Drive");
        System.out.println("6. Crear un folder");
        System.out.println("7. Acceder a carpeta");
        System.out.println("8. Salir");
        System.out.println();
        System.out.println();

    }

    public void scannerOpcion2(){
        Scanner entrada = new Scanner(System.in);
        try {
            System.out.print("Ingrese su opcion: ");
            int eleccion = entrada.nextInt();
            entrada.nextLine();  // Consumir el salto de línea residual

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
                    String nombreRuta;
                    System.out.println("Ingrese el nombre de la carpeta");
                    nombreRuta = entrada.nextLine();
                    Sistema.cd(nombreRuta.toUpperCase());
                    break;
                case 8:
                    System.out.printf("Volver al menú principal.");
                    MenuPrincipal();
                    scannerOpcion();
                default:
                    System.out.println("\nEsa opción no existe");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nSolo se admite de entrada enteros positivos");
            entrada.next();
        }
    }


    public boolean getSalirMenu(){
        return salirMenu;
    }
}

