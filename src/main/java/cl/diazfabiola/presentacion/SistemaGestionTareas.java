package cl.diazfabiola.presentacion;

import cl.diazfabiola.dominio.Estado;
import cl.diazfabiola.dominio.Prioridad;
import cl.diazfabiola.dominio.Tarea;
import cl.diazfabiola.servicio.GestorDeTareas;

import java.util.List;
import java.util.Scanner;
/**
 * Esta clase contiene los menús en donde el usuario interactúa con la aplicación
 * @author Fabiola Díaz <a href="https://github.com/fabyDiaz/SistemaGestionTareas">Github Fabiola Díaz</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class SistemaGestionTareas {

    public Scanner scanner = new Scanner(System.in);
    int opcion=0;
    GestorDeTareas gestorDeTareas= new GestorDeTareas();

    /**
     * Este método princiapl de la aplicación que se ejecuata desde el main
     */
    public void mostrarPrograma(){
        System.out.println("-------------------------------------------");
        System.out.println("BIENVENIDO AL SISTEMA DE GESTIÓN DE TAREAS");
        System.out.println("-------------------------------------------");
        do{
            try{
                mostrarMenu();
                //System.out.println("-------------------------------------------\n");
            }catch (NumberFormatException e){
                System.out.println("Ocurrió un error. Debe ingresar un número");
            }catch (Exception e){
                System.out.println("Ocurrió un erro. "+e.getMessage());;
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }while(opcion != 6);
    }


    /**
     * Este método muestra el menú prinicpal de la aplicación
     */
    public void mostrarMenu(){
        System.out.println("Ingrese una opción");
        System.out.println("1.- Agregar Tarea");
        System.out.println("2.- Editar Tarea");
        System.out.println("3.- Eliminar Tarea");
        System.out.println("4.- Listar Tarea");
        System.out.println("5.- Buscar Tarea");
        System.out.println("6.- Salir");
        opcion = Integer.parseInt(scanner.nextLine());


        switch (opcion){
            case 1->{
                System.out.println("Ingresa los siguientes datos:");
                Tarea tareaAgregar = gestorDeTareas.agregarTarea(nuevaTarea());
                if(tareaAgregar==null){
                    System.out.println("Error al agregar Tarea. Debe ingresar todos los campos");
                }else{
                    System.out.println("La tarea con ID: "+ tareaAgregar.getIdTarea()+" fue agregada con exito");
                }

            }
            case 2->{
                System.out.println("Ingrese ID de la Tarea que quiere editar: ");
                int id = Integer.parseInt(scanner.nextLine());
                Tarea tareaEditar = gestorDeTareas.buscarTarea(id);
                mostrarTarea(tareaEditar);
                editarTarea(tareaEditar.getIdTarea());
            }
            case 3 ->{
                System.out.println("Ingrese ID de la Tarea: ");
                int id = Integer.parseInt(scanner.nextLine());
                if(gestorDeTareas.eliminarTarea(id)==null){
                    System.out.println("La tarea con ID "+id+ " no existe");
                }else{
                    System.out.println("La tarea con ID "+id+ " eliminada");
                }

            }
            case 4 ->{
                if(gestorDeTareas.listarTareas()==null){
                    System.out.println("Aún no se ha ingresado tareas");
                }else{
                   ordenarTareas();
                }
            }
            case 5 ->{
                System.out.println("Ingrese título o descripción de la tarea: ");
                String texto = scanner.nextLine();
                if(gestorDeTareas.buscarTarea(texto)==null){
                    System.out.println("Tarea no encontrada");
                }else{
                    mostrarTarea(gestorDeTareas.buscarTarea(texto));
                }
            }
            case 6-> System.out.println("Programa terminado");
            default -> System.out.println("opción ingresada no es válida");
        }

    }

    /**
     *Este método solicita los datos necesarios para crear una nueva tarea
     * @return Nueva Tarea
     */
    public Tarea nuevaTarea() {
        System.out.println("TÍTULO:");
        String titulo = scanner.nextLine();
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El titulo de la tarea no puede ser nulo o vacío.");
        }

        System.out.println("DESCRIPCIÓN:");
        String descripcion = scanner.nextLine();
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la tarea no puede ser nulo o vacío.");
        }

        System.out.println("PRIORIDAD:");
        for (Prioridad p : Prioridad.values()) {
            System.out.println(p.ordinal() + 1 + ". " + p);
        }
        int prioridadSeleccionada = Integer.parseInt(scanner.nextLine()); // Leer el número de prioridad
        Prioridad prioridad =  validarPrioridad(prioridadSeleccionada);

        System.out.println("ESTADO:");
        for (Estado e : Estado.values()) {
            System.out.println(e.ordinal() + 1 + ". " + e);
        }
        int estadoSeleccionado = Integer.parseInt(scanner.nextLine()); // Leer el número de prioridad
        Estado estado =  validarEstado(estadoSeleccionado);

        Tarea tarea = new Tarea(titulo,descripcion, prioridad, estado);

        return gestorDeTareas.agregarTarea(tarea);
    }

    /**
     * Este método muestra todos los datos (atributos) asociado a una tarea
     * @param tarea
     */
    public void mostrarTarea(Tarea tarea){
        System.out.println("ID: "+tarea.getIdTarea() +
                "\nTITULO: "+ tarea.getTitulo()+
                "\nDESCRIPCIÓN: "+ tarea.getDescripcion()+
                "\nPRIORIDAD: "+ tarea.getPrioridad()+
                "\nESTADO: "+ tarea.getEstado()+"\n" +
                "---------------------------------------------------------------------------"+
                "\n"
        );
    }

    /**
     * Este método muestra la interfaz para la opción 4 "Listar tareas".
     * Se puede seleccionar la forma en que quiero que liste las tareas (por ID, Filtrar por Estado o Ordenar por Prioridad)
     */
    public void ordenarTareas(){
        int opcion;
        Estado estado=null;
        System.out.println("1.- Mostrar Tareas por ID");
        System.out.println("2.- Filtrar por Estado");
        System.out.println("3.- Ordenar por Prioridad");
        opcion = Integer.parseInt(scanner.nextLine());
        switch (opcion){
            case 1-> {
                for(Tarea tareaPorID: gestorDeTareas.listarTareas()){
                    mostrarTarea(tareaPorID);
                }
            }
            case 2-> {
                System.out.println("Ingresa el estado");
                for (Estado e : Estado.values()) {
                    System.out.println(e.ordinal() + 1 + ". " + e);
                }
                int estadoSeleccionado = Integer.parseInt(scanner.nextLine());
                estado = validarEstado(estadoSeleccionado);

                List<Tarea> tareasFiltradas = gestorDeTareas.filtrarTarea(estado);

                if (gestorDeTareas.filtrarTarea(estado).isEmpty()) {
                    System.out.println("No hay tareas en estado: " + estado);
                } else {
                    for (Tarea tareaPorPrioridad : tareasFiltradas) {
                        mostrarTarea(tareaPorPrioridad);
                    }
                }
            }
            case 3 ->{
                for(Tarea tareaPorPrioridad: gestorDeTareas.ordenarTarea()){
                    mostrarTarea(tareaPorPrioridad);
                }
            }
            default -> System.out.println("Opción  no válida.");
        }
    }

    /**
     * Este método muestra la interfaz relacionado con la opción 2 "Editar Tarea".
     * Muestra un submenú que permite selccionar qué atributo de la Tarea deseo editar.
     * Se puede seleccionar todo los atribbutos que quiera de una tarea específica hastq que decida terminar la edición
     * presionando la opción 5.
     * @param idTarea
     */
    public void editarTarea(int idTarea){
        System.out.println("¿Que quiere editar?");
        int opcion;
        String titulo= null, descripcion= null;
        Prioridad prioridad= null;
        Estado estado=null;
        do{
            System.out.println("1.- Título");
            System.out.println("2.- Descripción");
            System.out.println("3.- Prioridad");
            System.out.println("4. Estado");
            System.out.println("5. Terminar Edición");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion){
                case 1-> {
                    System.out.println("Ingresa nuevo título:");
                    titulo = scanner.nextLine();
                }
                case 2-> {
                    System.out.println("ingresa nueva descipción");
                    descripcion = scanner.nextLine();
                }
                case 3 ->{
                    System.out.println("Ingresa nueva prioridad");
                    for (Prioridad p : Prioridad.values()) {
                        System.out.println(p.ordinal() + 1 + ". " + p);
                    }
                    int prioridadSeleccionada = Integer.parseInt(scanner.nextLine()); // Leer el número de prioridad
                    prioridad =  validarPrioridad(prioridadSeleccionada);

                }
                case 4 -> {
                    System.out.println("Ingresa nuevo estado");
                    for (Estado e : Estado.values()) {
                        System.out.println(e.ordinal() + 1 + ". " + e);
                    }
                    int estadoSeleccionado = Integer.parseInt(scanner.nextLine()); // Leer el número de prioridad
                    estado =  validarEstado(estadoSeleccionado);
                }
                case 5 -> System.out.println("Edición terminada.");
                default -> System.out.println("Opción  no válida.");
            }

        }while(opcion!=5);

        gestorDeTareas.editarTarea(idTarea, titulo, descripcion, prioridad, estado);

    }

    /**
     * Este método se utiliza para valida que se seleccione un Prioridad válida cuando se crea o edita una Tarea al
     * seleccionar la opción 1 o 2 del menú.
     * @param prioridadSeleccionada
     * @return Prioridad
     */
    private Prioridad validarPrioridad(int prioridadSeleccionada){
        Prioridad prioridad = null;
        if (prioridadSeleccionada >= 1 && prioridadSeleccionada <= Prioridad.values().length) {
            prioridad = Prioridad.values()[prioridadSeleccionada - 1];
        } else {
            System.out.println("Opción de prioridad no válida, seleccionando por defecto: MEDIA");
            prioridad = Prioridad.MEDIA;
        }
        return prioridad;
    }

    /**
     * Este método se utiliza para valida que se seleccione un Estado válido cuando se crea o edita una Tarea al
     * seleccionar la opción 1 o 2 del menú
     * @param estadoSeleccionado
     * @return Estado
     */
    private Estado validarEstado(int estadoSeleccionado){
        Estado estado = null;
        if (estadoSeleccionado >= 1 && estadoSeleccionado <= Estado.values().length) {
            estado = Estado.values()[estadoSeleccionado - 1];
        } else {
            System.out.println("Opción de estado no válida, seleccionando por defecto: PENDIENTE");
            estado = Estado.PENDIENTE;
        }
        return estado;
    }


}
