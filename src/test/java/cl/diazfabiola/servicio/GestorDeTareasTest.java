package cl.diazfabiola.servicio;

import cl.diazfabiola.dominio.Estado;
import cl.diazfabiola.dominio.Prioridad;
import cl.diazfabiola.dominio.Tarea;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GestorDeTareasTest extends TestCase {

    private GestorDeTareas gestorDeTareas;

    @BeforeEach
    public void setUp() {
        // Inicializamos el gestor de tareas antes de cada prueba
        gestorDeTareas = new GestorDeTareas();
        Tarea.reiniciarId();
    }

    @Test
    public void testAgregarTarea() {
        Tarea tarea = new Tarea("Título 1", "Descripción 1", Prioridad.ALTA, Estado.PENDIENTE);
        Tarea tareaAgregada = gestorDeTareas.agregarTarea(tarea);

        // Validamos que la tarea se haya agregado correctamente
        assertNotNull(tareaAgregada);
        assertEquals(1, gestorDeTareas.listarTareas().size());
        assertEquals(tarea, gestorDeTareas.listarTareas().get(0));
    }

    @Test
    public void testBuscarTareaPorId() {
        Tarea tarea = new Tarea("Título 1", "Descripción 1", Prioridad.ALTA, Estado.PENDIENTE);
        gestorDeTareas.agregarTarea(tarea);

        // Buscamos la tarea por su ID
        Tarea tareaEncontrada = gestorDeTareas.buscarTarea(1);

        assertNotNull(tareaEncontrada);
        assertEquals(tarea, tareaEncontrada);
    }

    @Test
    public void testBuscarTareaPortexto() {
        Tarea tarea = new Tarea("Título 1", "Descripción importante", Prioridad.MEDIA, Estado.EN_PROGRESO);
        gestorDeTareas.agregarTarea(tarea);

        // Buscamos por título
        Tarea tareaEncontradaPorTitulo = gestorDeTareas.buscarTarea("Título 1");
        assertNotNull(tareaEncontradaPorTitulo);

        // Buscamos por descripción
        Tarea tareaEncontradaPorDescripcion = gestorDeTareas.buscarTarea("Descripción importante");
        assertNotNull(tareaEncontradaPorDescripcion);
    }

    @Test
    public void testEditarTarea() {
        Tarea tarea = new Tarea("Título 1", "Descripción 1", Prioridad.BAJA, Estado.PENDIENTE);
        gestorDeTareas.agregarTarea(tarea);

        // Editamos los valores de la tarea
        Tarea tareaEditada = gestorDeTareas.editarTarea(1,"Nuevo Título", "Nueva Descripción", Prioridad.ALTA, Estado.COMPLETADA);

        assertNotNull(tareaEditada);
        assertEquals("Nuevo Título", tareaEditada.getTitulo());
        assertEquals("Nueva Descripción", tareaEditada.getDescripcion());
        assertEquals(Prioridad.ALTA, tareaEditada.getPrioridad());
        assertEquals(Estado.COMPLETADA, tareaEditada.getEstado());
    }

    @Test
    public void testEliminarTarea() {
        Tarea tarea = new Tarea("Título 1", "Descripción 1", Prioridad.BAJA, Estado.PENDIENTE);
        gestorDeTareas.agregarTarea(tarea);

        // Eliminamos la tarea
        Tarea tareaEliminada = gestorDeTareas.eliminarTarea(1);

        assertNotNull(tareaEliminada);
        assertEquals(0, gestorDeTareas.listarTareas().size());
    }

    @Test
    public void testFiltrarTarea() {
        // Agregamos varias tareas
        gestorDeTareas.agregarTarea(new Tarea("Título 1", "Descripción 1", Prioridad.ALTA, Estado.PENDIENTE));
        gestorDeTareas.agregarTarea(new Tarea("Título 2", "Descripción 2", Prioridad.MEDIA, Estado.EN_PROGRESO));
        gestorDeTareas.agregarTarea(new Tarea("Título 3", "Descripción 3", Prioridad.BAJA, Estado.COMPLETADA));

        // Filtramos por estado PENDIENTE
        List<Tarea> tareasPendientes = gestorDeTareas.filtrarTarea(Estado.PENDIENTE);

        assertEquals(1, tareasPendientes.size());
        assertTrue(tareasPendientes.stream().allMatch(t -> t.getEstado() == Estado.PENDIENTE));
    }

    @Test
    public void testOrdenarTarea() {
        // Agregamos varias tareas
        gestorDeTareas.agregarTarea(new Tarea("Título 1", "Descripción 1", Prioridad.ALTA, Estado.PENDIENTE));
        gestorDeTareas.agregarTarea(new Tarea("Título 2", "Descripción 2", Prioridad.MEDIA, Estado.EN_PROGRESO));
        gestorDeTareas.agregarTarea(new Tarea("Título 3", "Descripción 3", Prioridad.BAJA, Estado.COMPLETADA));

        // Ordenamos por prioridad
        List<Tarea> tareasOrdenadas = gestorDeTareas.ordenarTarea();

        assertEquals(Prioridad.ALTA, tareasOrdenadas.get(0).getPrioridad());
        assertEquals(Prioridad.MEDIA, tareasOrdenadas.get(1).getPrioridad());
        assertEquals(Prioridad.BAJA, tareasOrdenadas.get(2).getPrioridad());
    }
}