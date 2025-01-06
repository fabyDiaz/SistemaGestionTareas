# Sistema de Gestión de Tareas

## Descripción General
Este proyecto implementa un sistema básico de gestión de tareas en **Java** que permite a los usuarios crear, organizar y priorizar tareas. Está diseñado utilizando **estructuras de datos**, **programación funcional**, **algoritmos**, y principios de **diseño orientado a objetos (SOLID)**.

📥 **[Descarga la aplicación desde aquí](https://github.com/fabyDiaz/SistemaGestionTareas/releases/tag/v1.0.0)** - Versión más reciente de la aplicación por consola.

📄 **[Ver el informe completo del proyecto](https://github.com/fabyDiaz/SistemaGestionTareas/blob/v1.0.0/Ejercicio%20Final%20de%20M%C3%B3dulo%202.pdf)** - Documentación detallada sobre el diseño e implementación del sistema.


## Características del Sistema
### 1. Estructuras de Datos
- Uso de **listas enlazadas** para almacenar las tareas.
- Cada tarea incluye:
    - Identificador único.
    - Título.
    - Descripción breve.
    - Prioridad: **ALTA, MEDIA, BAJA**.
    - Estado: **PENDIENTE, EN PROGRESO, COMPLETADA**.

### 2. Operaciones Principales
- **Agregar tarea**: Permite crear y añadir una nueva tarea.
- **Editar tarea**: Modifica los detalles de una tarea existente.
- **Eliminar tarea**: Elimina una tarea usando su identificador único.
- **Listar tareas**: Muestra las tareas organizadas por prioridad.
- **Buscar tarea**: Busca tareas por título o descripción utilizando algoritmos de búsqueda.

### 3. Programación Funcional
- Uso de **expresiones lambda** para filtrar tareas por estado.
- Uso de **Streams** para ordenar tareas por prioridad.

### 4. Diseño Orientado a Objetos
- Principios **SOLID**:
    - Clase `Tarea`: Representa las tareas individuales.
    - Clase `GestorDeTareas`: Gestiona las tareas (agregar, editar, eliminar, buscar, listar).
    - Implementación flexible para que la estructura de datos pueda cambiar sin afectar la funcionalidad principal.
    - Posibilidad de añadir nuevas características sin romper el sistema.

### 5. Pruebas Unitarias
- Pruebas con **JUnit** para validar:
    - Creación de tareas.
    - Operaciones de gestión (agregar, editar, eliminar, buscar y listar).

## Instrucciones de Uso
### Requisitos Previos
- **Java 17** o superior.
- **JUnit 5** para pruebas unitarias.

### Instalación y Ejecución
1. Clona este repositorio o descarga el código fuente.
2. Abre el proyecto en tu IDE preferido (por ejemplo, IntelliJ IDEA o Eclipse).
3. Ejecuta el archivo principal `App.java` para iniciar el sistema.
4. Usa el menú en consola para interactuar con el sistema:
   ```text
   a) Agregar tarea
   b) Editar tarea
   c) Eliminar tarea
   d) Listar tareas
   e) Buscar tarea
   f) Salir
