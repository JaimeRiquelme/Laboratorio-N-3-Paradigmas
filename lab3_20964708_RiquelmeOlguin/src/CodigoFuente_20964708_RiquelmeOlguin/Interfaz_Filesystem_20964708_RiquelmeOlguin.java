package CodigoFuente_20964708_RiquelmeOlguin;

import java.util.List;
import java.util.SimpleTimeZone;

public interface Interfaz_Filesystem_20964708_RiquelmeOlguin {

    /**
     * Método para agregar un nuevo Drive.
     *
     * @param letra Representa la letra que identifica al Drive.
     * @param nombre Nombre del Drive a crear.
     * @param capacidad Representa la capacidad del Drive.
     */

    void addDrive(String letra, String nombre, int capacidad);

    /**
     * Método para registrar un nuevo usuario en el sistema.
     *
     * @param usuario Nombre del usuario a registrar.
     */

    void register(String usuario);

    /**
     * Método para iniciar sesión de un usuario registrado.
     *
     * @param usuario Nombre del usuario que inicia sesión.
     */

    void login(String usuario);

    /**
     * Método para cerrar la sesión del usuario actual.
     */

    void logout();

    /**
     * Método para cambiar al Drive especificado por la letra dada.
     *
     * @param Letra Letra que identifica al Drive a cambiar.
     */

    void swithDrive(String Letra);

    /**
     * Método para crear un nuevo directorio.
     *
     * @param Nombre Nombre del directorio a crear.
     */

    void mkdir(String Nombre);

    /**
     * Método para cambiar al directorio indicado.
     *
     * @param Nombre Nombre del directorio al cual cambiar.
     */

    void cd(String Nombre);

    /**
     * Método para agregar un archivo al sistema de archivos.
     *
     * @param file El objeto de archivo a agregar.
     */

    void addFile(File_20964708_RiquelmeOlguin file);

    /**
     * Método para eliminar un elemento (archivo o directorio) del sistema de archivos.
     *
     * @param Nombre Nombre del elemento a eliminar.
     */

    void del(String Nombre);

    /**
     * Método para copiar un elemento a la ubicación indicada.
     *
     * @param NombreCopiar Nombre del elemento a copiar.
     * @param path Ruta donde se copiará el elemento.
     */

    void copy(String NombreCopiar, String path);

    /**
     * Método para mover un elemento a la ubicación indicada.
     *
     * @param NombreCopiar Nombre del elemento a mover.
     * @param path Ruta donde se moverá el elemento.
     */
    void move(String NombreCopiar,String path);

    /**
     * Método para renombrar un elemento.
     *
     * @param Nombre Nombre actual del elemento.
     * @param NuevoNombre Nuevo nombre para el elemento.
     */

    void ren(String Nombre,String NuevoNombre);

    /**
     * Método para listar el contenido de un directorio.
     *
     * @param Parametros Lista de parámetros de listado.
     */

    void dir(List<String> Parametros);
    void format(String Letra,String Nombre);
    void encrypt(String Contrasena, String NombreFolder);

    /**
     * Método para buscar contenido dentro del sistema de archivos.
     *
     * @param RutaSplit Ruta especificada en forma de array.
     * @param DriveActual Drive en el que se realizará la búsqueda.
     * @return Un objeto de tipo Folder_20964708_RiquelmeOlguin si se encuentra contenido en la ruta especificada, null en caso contrario.
     */

    Folder_20964708_RiquelmeOlguin buscarContenido(String[] RutaSplit, Drive_20964708_RiquelmeOlguin DriveActual);

    /**
     * Método para buscar el Drive actualmente en uso.
     *
     * @return Un objeto de tipo Drive_20964708_RiquelmeOlguin representando el Drive actualmente en uso.
     */
    Drive_20964708_RiquelmeOlguin buscarDriveActual();

    /**
     * Método para buscar un Drive específico según su identificador de letra.
     *
     * @param letraDrive La letra que identifica al Drive.
     * @return Un objeto de tipo Drive_20964708_RiquelmeOlguin que representa al Drive buscado. Retorna null si el Drive no se encuentra.
     */

    Drive_20964708_RiquelmeOlguin buscarDrivePorLetra(String letraDrive);

    /**
     * Método para obtener los nombres de todos los contenidos en una lista de contenido.
     *
     * @param contenido La lista de contenido de donde obtener los nombres.
     * @return Una lista de String que representa los nombres de todo el contenido.
     */
    List<String> getContenidoNombres(List<Contenido_20964708_RiquelmeOlguin> contenido);

    /**
     * Método para crear un nuevo archivo.
     *
     * @param Nombre El nombre del nuevo archivo a crear.
     */

    void crearFile(String Nombre);

    /**
     * Método para crear un nuevo objeto de seguridad.
     *
     * @return Un nuevo objeto de tipo AtributosSeguridad_20964708_RiquelmeOlguin.
     */
    AtributosSeguridad_20964708_RiquelmeOlguin crearSeguridad();

    /**
     * Método para filtrar una lista de contenido por su formato.
     *
     * @param lista La lista de contenido a filtrar.
     * @param formato El formato a buscar dentro de la lista.
     * @return Una lista de contenido que coincide con el formato especificado.
     */

    List<Contenido_20964708_RiquelmeOlguin> filtrarPorFormato(List<Contenido_20964708_RiquelmeOlguin> lista, String formato);

    /**
     * Método para verificar si una ruta existe dentro de una lista de contenido.
     *
     * @param Contenido La lista de contenido en donde verificar la ruta.
     * @param RutaSplit La ruta a verificar en forma de lista de String.
     * @return Un boolean que es true si la ruta existe en la lista de contenido y false en caso contrario.
     */

    boolean verificarRuta(List<Contenido_20964708_RiquelmeOlguin> Contenido, List<String> RutaSplit);

    /**
     * Método para recuperar todos los nombres de contenido NO oculto.
     * @param contenido
     * @return
     */
    List<String> getContenidoNombresSeguridad(List<Contenido_20964708_RiquelmeOlguin> contenido);

    /**
     * Método que elimina el contenido de una lista de contenido. (Similar a del, pero sin mover a papelera.)
     * @param Nombre
     */
    void delAux(String Nombre);


    /**
     * Método para obtener los nombres de los contenidos de una lista y sus sublistas. (Solamente visibles)
     * @param contenido
     * @return
     */

     List<String> getNombresTotalesSeguridad(List<Contenido_20964708_RiquelmeOlguin> contenido);

    /**
     * Método para obtener todos los nombres de una lista y sublistas
     * @param contenido
     * @return
     */

     List<String> getNombresTotales(List<Contenido_20964708_RiquelmeOlguin> contenido);

    /**
     * Método que busca todos los objetos no ocultos de una lista de contenido.
     * @param contenido
     * @return
     */

    List<Contenido_20964708_RiquelmeOlguin> recolectarObjetosNoOcultos(List<Contenido_20964708_RiquelmeOlguin> contenido);

    /**
     * Método que obtiene ordena por el atributo fecha una lista y luego obtiene los nombres de cada objeto.
     * @param contenido
     * @return
     */

    List<String> getNombresPorFecha(List<Contenido_20964708_RiquelmeOlguin> contenido);


    /**
     * Método para crear una contraseña de un objeto contenido.
     * @return
     */

    char[] CrearContrasena();

    /**
     * Método para calcular el modulo de una contraseña.
     * @param contrasena
     * @return
     */

    int calcularModulo5(char[] contrasena);

    /**
     * Método para sumar el modulo calculado a un Nombre (String)
     * @param nombre
     * @param modValue
     * @return
     */
    String sumarValorAModulo(String nombre, int modValue);

    /**
     * Método que aplica el modulo calculado a una lista de carpetas y subcarpetas.
     * @param carpeta
     * @param modValue
     */

     void aplicarModuloAFolder(Folder_20964708_RiquelmeOlguin carpeta, int modValue);

    /**
     * Método para restar el modulo calculado a un Nombre (String)
     * @param nombre
     * @param modValue
     * @return
     */
     String restarValorAModulo(String nombre, int modValue);

    /**
     * Método que aplica la inversa del modulo calculado a una lista de carpetas y subcarpetas.
     * @param carpeta
     * @param modValue
     */
     void aplicarInversoModuloAFolder(Folder_20964708_RiquelmeOlguin carpeta, int modValue);

    /**
     * Metódo que dado una lista de contenido convierte el nombre y verifica si es igual al nombrer ingresado, dado que el nombre está encryptado.
     * @param contenido
     * @param NombreFolder
     * @param Contrasena
     * @return
     */

     Folder_20964708_RiquelmeOlguin buscarFolderDecrypt(List<Contenido_20964708_RiquelmeOlguin> contenido, String NombreFolder, String Contrasena);
}


