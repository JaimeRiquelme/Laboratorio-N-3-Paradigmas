package CodigoFuente_20964708_RiquelmeOlguin;

public class Papelera_20964708_RiquelmeOlguin {
    private Contenido_20964708_RiquelmeOlguin Contenido;
    private String Path;

    public Papelera_20964708_RiquelmeOlguin(Contenido_20964708_RiquelmeOlguin contenido, String path) {
        this.Contenido = contenido;
        this.Path = path;
    }

    @Override
    public String toString() {
        return "Papelera_20964708_RiquelmeOlguin{" +
                "Contenido=" + Contenido +
                ", Path='" + Path + '\'' +
                '}';
    }
}
