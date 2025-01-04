package cl.diazfabiola.dominio;

/**
 * Clase Tarea con sus respectivos atributos. En este caso el ID se genera de forma automática cada vez que se crea una nueava tarea
 */
public class Tarea {
    private static int ultimoID = 1;
    private int idTarea;
    private String titulo;
    private String descripcion;
    private Prioridad prioridad;
    private Estado estado;

    public Tarea(String titulo, String descripcion, Prioridad prioridad, Estado estado) {
        this.idTarea = ultimoID++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public Tarea() {
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public static void reiniciarId(){
        ultimoID = 1;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "idTarea=" + idTarea +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", prioridad=" + prioridad +
                ", Estado=" + estado +
                '}';
    }
}
