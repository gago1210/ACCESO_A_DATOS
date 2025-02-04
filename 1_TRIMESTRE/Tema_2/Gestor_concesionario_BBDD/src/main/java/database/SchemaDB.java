package database;


/*Una interfaz sirve para conectar dos clases que no tienen nada que ver a traves de su implementacion. En la clase 1 implemento y me obliga a
traer con los metodos abstractos que tiene dicha interfaz.

Hay otra funcion de las interfaces y es que tambien puedo crearlo como un almacen de constantes variables >> finales. IMPORTANTE: NO DE METÓDOS.
Constantes que tienen valor, variables con esto consigo que toda variable definida, por lo tanto final, dentro de una interfaz es constante
y por lo tanto publica porque no tengo que crear una instancia del objeto. Con esta constante lo que hago es poder llamarlo desde otra clase
y tener acceso a ese atributo temporalmente, esto sirve para tener todas las variables de entorno, todas las configuraciones, todo lo que quiera
en un mismo sitio y no volverme loca buscando donde cuando ya tengo un programa de grandes extensiones, acordarme tambien de los nombres
es una ardua tarea que con esto ya lo tenemos definido en un solo sitio mas accesible y ordenado, asi no me equivoco nunca si esta todo bien
configurado.

 */
public interface SchemaDB {
    String DB_NAME = "concesionario"; //esto es para enlazar en el DBConnection y evitar errores con el nombre de la bbdd
    String TAB_EMP = "empleados"; //indicamos el nombre de la tabla empleados
    String TAB_KIN = "tipos";

    //damos nombre a cada una de las columnas que conforman la tabla de mi bbdd
    String COL_ID = "id"; //se llama identificador porque muchas tablas van a tener esta columna y lo reutilizamos
    String COL_EMP_NAME = "nombre";
    String COL_EMP_SURNAME = "apellido";
    String COL_EMP_MAIL = "correo";
    String COL_EMP_PHO = "telefono";
    String COL_KIN_PHO = "descripcion";
    String COL_KIN_SIG = "siglas";
}
