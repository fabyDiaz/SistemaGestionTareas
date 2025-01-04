package cl.diazfabiola.dominio;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TareaTest extends TestCase {

    @BeforeEach
    public void reiniciarIds() {
        Tarea.reiniciarId();  // Reinicia el ID antes de cada prueba para asegurar consistencia
    }
    @Test
    public void testConstructorConParametros() {
        Tarea tarea = new Tarea("Titulo 1", "Descripcion 1", Prioridad.ALTA, Estado.EN_PROGRESO);

        assertEquals(1, tarea.getIdTarea()); //El id de la primera tarea debería ser 1
        assertEquals("Titulo 1", tarea.getTitulo());
        assertEquals("Descripcion 1", tarea.getDescripcion());
        assertEquals(Prioridad.ALTA, tarea.getPrioridad());
        assertEquals(Estado.EN_PROGRESO, tarea.getEstado());
    }

    @Test
    public void testConstructorIncrementaId() {
        Tarea tarea1 = new Tarea("Titulo 1", "Descripcion 1", Prioridad.MEDIA, Estado.COMPLETADA);
        Tarea tarea2 = new Tarea("Titulo 2", "Descripcion 2", Prioridad.BAJA, Estado.PENDIENTE);

        assertEquals(1, tarea1.getIdTarea()); //el id de la primera tarea debería ser 1
        assertEquals(2, tarea2.getIdTarea()); //el Id de la segunda tarea debería ser 2
    }

    @Test
    public void testConstructorPorDefecto() {
        Tarea tarea = new Tarea();

        assertNull(tarea.getTitulo());
        assertNull(tarea.getDescripcion());
        assertNull(tarea.getPrioridad());
        assertNull(tarea.getEstado());
    }

    @Test
    public void testSetters() {
        Tarea tarea = new Tarea();
        tarea.setTitulo("Nuevo Titulo");
        tarea.setDescripcion("Nueva Descripcion");
        tarea.setPrioridad(Prioridad.ALTA);
        tarea.setEstado(Estado.EN_PROGRESO);

        assertEquals("Nuevo Titulo", tarea.getTitulo());
        assertEquals("Nueva Descripcion", tarea.getDescripcion());
        assertEquals(Prioridad.ALTA, tarea.getPrioridad());
        assertEquals(Estado.EN_PROGRESO, tarea.getEstado());
    }

    @Test
    public void testReiniciarId() {
        Tarea tarea1 = new Tarea("Titulo 1", "Descripcion 1", Prioridad.MEDIA, Estado.PENDIENTE);
        Tarea.reiniciarId();
        Tarea tarea2 = new Tarea("Titulo 2", "Descripcion 2", Prioridad.BAJA, Estado.COMPLETADA);

        assertEquals(1, tarea2.getIdTarea());
    }

    @Test
    public void testToString() {
        Tarea tarea = new Tarea("Titulo", "Descripcion", Prioridad.ALTA, Estado.EN_PROGRESO);
        String esperado = "Tarea{idTarea=1, titulo='Titulo', descripcion='Descripcion', prioridad=ALTA, Estado=EN_PROGRESO}";

        assertEquals(esperado, tarea.toString());
    }
}
