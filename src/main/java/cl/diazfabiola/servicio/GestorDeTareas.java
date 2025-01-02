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

    @Override
    public List<Tarea> listarTareas() {
        //muestro la lista, si no hay elementos retorna null
        return this.tareas;
    }

    @Override
    public Tarea agregarTarea(Tarea tarea) {
        if (tarea == null ||
                tarea.getTitulo() == null || tarea.getTitulo().trim().isEmpty() ||
                tarea.getDescripcion() == null || tarea.getDescripcion().trim().isEmpty()) {
            System.out.println("Error: La tarea tiene datos nulos o vacíos.");
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
    @Override
    public Tarea buscarTarea(int idTarea){
        Tarea tareaBuscadaPorId = this.tareas.stream()
                .filter(tareaTemporal -> tareaTemporal.getIdTarea() == idTarea)
                .findFirst()
                .orElse(null);
        return tareaBuscadaPorId;
    }

    @Override
    public Tarea buscarTarea(String texto) {
        Tarea tareaBuscadaPorTexto= this.tareas.stream()
                .filter(tareaTemporal -> tareaTemporal.getTitulo().equalsIgnoreCase(texto)||tareaTemporal.getDescripcion().equalsIgnoreCase(texto))
                .findFirst()//Devuelve a primera coincidencia
                .orElse(null); // Devuelve null si no se encuentra ninguna tarea
        return tareaBuscadaPorTexto;
    }

    @Override
    public Tarea editarTarea(int idTarea, String nuevoTitulo, String nuevaDescripcion, Prioridad nuevaPrioridad, Estado nuevoEstado) {
        Tarea tareaEditar = buscarTarea(idTarea);

        //Cambiar el atributo que no sea nulo
        if (tareaEditar != null) {
            tareaEditar.setTitulo(nuevoTitulo != null ? nuevoTitulo : tareaEditar.getTitulo());
            tareaEditar.setDescripcion(nuevaDescripcion != null ? nuevaDescripcion : tareaEditar.getDescripcion());
            tareaEditar.setPrioridad(nuevaPrioridad != null ? nuevaPrioridad : tareaEditar.getPrioridad());
            tareaEditar.setEstado(nuevoEstado != null ? nuevoEstado : tareaEditar.getEstado());

            return tareaEditar;
        }
        //Si no encuentra la tarea retorna null
        return null;
    }


    @Override
    public Tarea eliminarTarea(int idTarea) {
        Tarea tareaEliminar = buscarTarea(idTarea);

        if (tareaEliminar != null) {
            this.tareas.remove(tareaEliminar);
            return tareaEliminar;
        }

        return null;
    }

    @Override
    public List<Tarea> filtrarTarea(Estado estado) {
        List<Tarea> tareasPorEstado = tareas.stream()
                .filter(tarea -> estado.equals(tarea.getEstado()))
                .collect(Collectors.toList());
        return tareasPorEstado;
    }

    @Override
    public List<Tarea> ordenarTarea() {
        List<Tarea> tareasPorPrioridad = tareas.stream()
                .sorted(Comparator.comparing(Tarea::getPrioridad))
                .collect(Collectors.toList());
        return tareasPorPrioridad;
    }


}
