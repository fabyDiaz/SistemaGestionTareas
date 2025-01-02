package cl.diazfabiola.servicio;

import cl.diazfabiola.dominio.Estado;
import cl.diazfabiola.dominio.Prioridad;
import cl.diazfabiola.dominio.Tarea;

import java.util.List;

public interface IGestorDeTareas {
    public List<Tarea> listarTareas();
    public Tarea agregarTarea(Tarea tarea);
    public Tarea buscarTarea(int idTarea);
    public Tarea buscarTarea(String texto);
    public Tarea editarTarea(int idTarea, String nuevoTitulo, String nuevaDescripcion, Prioridad nuevaPrioridad, Estado nuevoEstado) ;
    public Tarea eliminarTarea(int idTarea);
    public List<Tarea> filtrarTarea(Estado estado);
    public List<Tarea> ordenarTarea();
}
