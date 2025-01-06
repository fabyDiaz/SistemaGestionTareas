package cl.diazfabiola.servicio;

import cl.diazfabiola.dominio.Estado;
import cl.diazfabiola.dominio.Prioridad;
import cl.diazfabiola.dominio.Tarea;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GestorDeTareas implements IGestorDeTareas{

    private final List<Tarea> tareas;

    public GestorDeTareas(){
        this.tareas= new ArrayList<>();
    }

    /**
     *Muestra la lista de tareas, si no hay elementos retorne null
     * @return List<Tarea>
     */
    @Override
    public List<Tarea> listarTareas() {
        return this.tareas;
    }

    /**
     * Recibe una tarea como parámetro. En caso de que alguno de los datos de la tarea sea nulo o vacío retrora null.
     * Si la tarea ya existen devuelve la tarea existente.
     * Si la tarea no existe entonces la agrega a la lista y retorna la nueva tarea.
     * @param tarea
     * @return Tarea
     */
    @Override
    public Tarea agregarTarea(Tarea tarea) {
        if (tarea == null ||
                tarea.getTitulo() == null || tarea.getTitulo().trim().isEmpty() ||
                tarea.getDescripcion() == null || tarea.getDescripcion().trim().isEmpty()) {
           // System.out.println("Error: La tarea tiene datos nulos o vacíos.");
            return null; // Retorna null si hay datos inválidos
        }
        // Buscar si ya existe una tarea con el mismo ID
        Tarea tareaExistente = buscarTarea(tarea.getIdTarea());
        // Si la tarea ya existe, retornarla directamente
        if (tareaExistente != null) {
            return tareaExistente;
        }
        // Si no existe, agregarla a la lista
        this.tareas.add(tarea);
        return tarea;
    }

    /**
     * Busca la tarea por ID
     * @param idTarea
     * @return Tarea
     */
    @Override
    public Tarea buscarTarea(int idTarea){
        Tarea tareaBuscadaPorId = this.tareas.stream()
                .filter(tareaTemporal -> tareaTemporal.getIdTarea() == idTarea)
                .findFirst()
                .orElse(null);
        return tareaBuscadaPorId;
    }

    /**
     * Busca la tarea por texto ingresado por parámetro. El método compara el texto tanto con el título como con la descripción.
     * De ecnontrar coincidencias, devuelve la primera tarea encontrada.
     * @param texto
     * @return Tarea
     */
    @Override
    public Tarea buscarTarea(String texto) {
        Tarea tareaBuscadaPorTexto= this.tareas.stream()
                .filter(tareaTemporal -> tareaTemporal.getTitulo().equalsIgnoreCase(texto)||tareaTemporal.getDescripcion().equalsIgnoreCase(texto))
                .findFirst()//Devuelve a primera coincidencia
                .orElse(null); // Devuelve null si no se encuentra ninguna tarea
        return tareaBuscadaPorTexto;
    }

    /**
     * Permite editar cada uno de los atributos siempre que este no venga nulo.
     * @param idTarea
     * @param nuevoTitulo
     * @param nuevaDescripcion
     * @param nuevaPrioridad
     * @param nuevoEstado
     * @return Tarea
     */
    @Override
    public Tarea editarTarea(int idTarea, String nuevoTitulo, String nuevaDescripcion, Prioridad nuevaPrioridad, Estado nuevoEstado) {
        Tarea tareaEditar = buscarTarea(idTarea);

        if (tareaEditar != null) {
            tareaEditar.setTitulo(nuevoTitulo != null && !nuevoTitulo.trim().isEmpty()? nuevoTitulo : tareaEditar.getTitulo());
            tareaEditar.setDescripcion(nuevaDescripcion != null && !nuevaDescripcion.trim().isEmpty()? nuevaDescripcion : tareaEditar.getDescripcion());
            tareaEditar.setPrioridad(nuevaPrioridad != null ? nuevaPrioridad : tareaEditar.getPrioridad());
            tareaEditar.setEstado(nuevoEstado != null ? nuevoEstado : tareaEditar.getEstado());

            return tareaEditar;
        }
        return null;
    }

    /**
     * Busca la tarea a través de su ID y la elimina. Si no la encuentra devuelve null
     * @param idTarea
     * @return Tarea
     */
    @Override
    public Tarea eliminarTarea(int idTarea) {
        Tarea tareaEliminar = buscarTarea(idTarea);

        if (tareaEliminar != null) {
            this.tareas.remove(tareaEliminar);
            return tareaEliminar;
        }

        return null;
    }

    /**
     * Permite flitrar la lista según el estado ingresado por parámetro. Si no encuentra ninguna tarea con ese estado
     * devuelve una lista vacía.
     * @param estado
     * @return List<Tarea>
     */
    @Override
    public List<Tarea> filtrarTarea(Estado estado) {
        List<Tarea> tareasPorEstado = tareas.stream()
                .filter(tarea -> estado.equals(tarea.getEstado()))
                .collect(Collectors.toList());
        return tareasPorEstado;
    }

    /**
     * Ordena las tareas por Prioridad (ALTA, MEDIA, BAJA)
     * Devuelve una lista vacía si no hay tareas
     * @return List<Tarea>
     */
    @Override
    public List<Tarea> ordenarTarea() {
        List<Tarea> tareasPorPrioridad = tareas.stream()
                .sorted(Comparator.comparing(Tarea::getPrioridad))
                .collect(Collectors.toList());
        return tareasPorPrioridad;
    }


}
