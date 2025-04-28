APIJUANN
1º DAW. Curso 2024-2025. 
Este es mi proyecto CRUD de inventario para una librería de DAW, denominado “libdaw”.

INSTRUCCIONES PARA EJECUTAR EL PROGRAMA:

PÁGINA DE INICIO DE SESIÓN (LOGIN)
Para acceder, introduzca el usuario y contraseña facilitados.

PÁGINA PRINCIPAL O LIBRERÍA DAW
Al iniciar sesión, serás redirigido a la página de inicio o librería DAW. 
El título de la página actual aparece en la esquina superior izquierda, su descripción en el centro y el nombre del usuario conectado a la derecha. Cada página tiene una paginación que la resalta.

Los botones son flotantes y el color de fondo cambia al hacer clic en cualquier botón. Por ejemplo, observa el cambio al hacer clic en "Libros".

PÁGINA LIBROS (CRUD)
Al acceder a "Libros", una sección reservada para gestionar el CRUD de libros, se muestra una lista de libros registrados, con un máximo de cuatro libros por página. 
Hay un contador de registros en la última fila de cada página y otro con el número total de registros. 
Esta sección incluye botones a la izquierda para añadir un libro, ir al inventario, volver a la página de inicio o a librería DAW, o cerrar sesión. 
También incluye botones para editar y eliminar. Las filas se resaltan al pasar el ratón por encima.

El formulario "Nuevo Libro" (arriba) se utiliza para añadir libros. 
Tiene un botón en la esquina superior izquierda para volver al CRUD de Libros y otro en la esquina inferior izquierda para registrar los datos introducidos. 
Al añadir un nuevo registro y guardarlo, se le redirigirá a la primera página del CRUD de Libros y podrá ver cómo cambia el número total de registros; en este caso, de 15 a 16. 
El nuevo registro se coloca en la última fila y en la última página.

El último registro también aparece en la lista de inicio, ver la última página:

EDITAR
Al guardar el cambio XXX Don Quijote de la Mancha, se aplica al CRUD de libros, al inicio y a la base de datos:

Dejo la opción de eliminar para después de insertar un inventario.

PÁGINA INVENTARIO (CRUD)
Mismas funciones que en "Libros".

Voy a añadir el libro Don Quijote de la Mancha al inventario. 
Para ello, como se indica en la nota roja del siguiente formulario, primero debe registrarlo en Libros e introducir el ID del libro en el inventario como clave foránea. 

El registro aparece en la lista de inventario y en la base de datos, así como en las funciones de Libros.

ELIMINAR
Al eliminar un registro, aparece una página de advertencia. 
Para eliminar un registro en Libros que también esté en Inventarios, primero debe eliminarlo del Inventario, debido a la clave foránea ID Libro.

Eliminar primero en inventarios.

Cuando se elimina un registro, se elimina de la lista CRUD, de la página de inicio y de la base de datos al mismo tiempo.


CERRAR SESIÓN
Puedes cerrar sesión en cualquier página haciendo clic en el botón "Cerrar sesión". 
Al cerrar sesión, serás redirigido a la página principal de inicio de sesión, lo que significa que no podrás acceder a ninguna otra página desde la URL.
