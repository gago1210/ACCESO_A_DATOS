package controller;

import database.SchemaDB;
import database.DBConnection;

import model.Empleado;
import model.Coche;

import dao.CochesDAO;
import dao.EmpleadoDAO;
import dao.VentaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Concesionario {
    /* aqui vamos a hacer nuestras querys correspondientes basandonos en el CRUD  CREATE, READ, UPDATE, DELETE tipicos de la bbdd, pero los podemos juntar en dos grandes partes.
    El create, update y delete por un lado y el read por otro.
    Los separo porque son diferentes, los tres se ejecutan de una forma mientras que read se ejecuta de una manera diferente.
    Ademas de que los tres se basan en que modifican la tabla mientras que read, no la modifica sino que obtiene un vector de valores.

    ¿cuales son los dos grandes objetos que puedo utilizar a la hora de trabajar con base de datos?
        1. statement --> query directa (sin pasar ningun filtro) >> INSERT INTO empleados (nombre, apellidos, correo, telefono) VALUES ('BORJA', 'MARTIN', 'bmartin@correo', 12345678)
            la ejecucion de esto me va a dar si se ha ejecutado bien o no mediante:
                    - true o false >> lo voy a utilizar para el CREATE
                    - numero de filas afectadas >> en caso de actualizacion o borrado, lo utilizo para UPDATE Y CREATE

        2. PrepareStatement (ES EL MÁS RECOMENDABLE) --> "QUERY con template" >> (se hace de manera muy parecida a la anterior pero en formato
            sin comillas simples, sino con interrogaciones) INSERT INTO empleados (nombre, apellidos, correo, telefono) VALUES (?,?,?,?)
            le tenemos que decir a traves de set + tipo de dato ( posicion de la interrogracion , nuevo valor)
                setInt(posicion 4, valor nuevo 12345678)
                setString(1, "Borja")
                setString(2, "Martin")
                setString(3, "bmartin@correo")
            lo bueno que tiene esto es que mas eficiente al no tener que compilar la  query cada vez que hago algun cambio ya que esta
            precompilada; al motor de la bbdd se lo doy todo hecho y, a la hora de construirlo es mucho mas sencillo.

   Aqui entra en juego el tema de las comillas simples o dobles, si yo hago una query directa es comilla simple, pero si lo que creo es un
   String la comilla es doble.
   Las posiciones van de 1 en uno empezando en 1, no es como en un array que se inicializa en 0.

   El formato de creacion de quuerys es ALTAMENTE RECOMENDABLE seguir una interfaz, a creamos dentro de database para que se siga un formato
   predefinido y todo esté más automatizado y en orden, SchemaDB.*/

    //seguiremos el siguiente formato
    //insertar trabajador dentro de mi bbdd, para ello tengo que sacar los datos de model-empleado

    //hacemos el insertarTrabajador a traves del DAO:
    private EmpleadoDAO empleadoDAO;
    private CochesDAO cochesDAO; //creo el objeto cochesDAO
    private VentaDAO ventaDAO;


    //lo inicializo dentro del constructor
    public Concesionario() {
        empleadoDAO = new EmpleadoDAO();
        cochesDAO = new CochesDAO(); //inicializo el objeto cochesDAO
        ventaDAO = new VentaDAO();
    }

    public void insertarTrabajadorDAO(Empleado empleado) {
        // la logica del negocio:
        try {
            empleadoDAO.insertarEmpleado(empleado);
        } catch (SQLException e) {
            System.out.println("Error en la insercion del empleado");
        }
    }

    public void insertarTrabajador(Empleado empleado) {
        //voy a hacer solo el PrepareStatement
        // SchemaDB.nombre
        // Connection -> Statement (query) -> execute
        Connection connection = new DBConnection().getConnection();
        // ya puedo acceder a la base de datos
        try {
            Statement statement = connection.createStatement();
            String Psquery = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)", //s es el nombre de la columna
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME, SchemaDB.COL_EMP_SURNAME, SchemaDB.COL_EMP_MAIL, SchemaDB.COL_EMP_PHO, SchemaDB.COL_EMP_KIN);
            //primero creas la query, la metes en la creacion del objeto por eso no hay que compilarla cada vez que modifico.
            PreparedStatement preparedStatement = connection.prepareStatement(Psquery);
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellido());
            preparedStatement.setString(3, empleado.getCorreo());
            preparedStatement.setInt(4, empleado.getTelefono());
            preparedStatement.setInt(5, 6);
            preparedStatement.executeUpdate();

            //esto que esta comentado es parte de solo statement
            /*String query = "INSERT INTO " + SchemaDB.TAB_EMP +
                    " (" + SchemaDB.COL_EMP_NAME + "," + SchemaDB.COL_EMP_SURNAME + "," + SchemaDB.COL_EMP_MAIL + "," + SchemaDB.COL_EMP_PHO + ") " +
                    "VALUES ('" + empleado.getNombre() + "','" + empleado.getApellido() + "','" + empleado.getCorreo() + "'," + empleado.getTelefono() + ")";*/
            // %s ->string
            // %d ->int
            // %f ->double o float
            // %b -> boolean
            // %c -> char
            String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES ('%s','%s','%s',%d)",
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME, SchemaDB.COL_EMP_SURNAME, SchemaDB.COL_EMP_MAIL, SchemaDB.COL_EMP_PHO,
                    empleado.getNombre(), empleado.getApellido(), empleado.getCorreo(), empleado.getTelefono());

            //esto que esta comentado es parte del modelo statement
            // statement.execute(query); // hay o no fallo
            // statement.executeUpdate() // cuantas filas estan afectadas

        } catch (SQLException e) {
            System.out.println("Error en la conexion de la base de datos");
        }
    }

    public int borrarUsuario(int id) {
        Connection connection = new DBConnection().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + SchemaDB.TAB_EMP + " WHERE id=?");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en la creacion de la query");
        }
        return 0;
    }

    // lectura de los usuarios
    public void leerUsuarios(int tipo) {
        // no se puede mapear de forma directa -> Vector[[nombre, apellido, correo],[nombre, apellido, correo]]
        // Connection -> Statement / PrepareStatement -> executeQuery -> ResultSet
        Connection connection = new DBConnecion().getConnection();
        // SELECT * FROM empleado WHERE ID=7;
        String query = String.format("SELECT * FROM %s WHERE %s=?", SchemaDB.TAB_EMP, SchemaDB.COL_EMP_KIN);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tipo);
            ResultSet resultSet = preparedStatement.executeQuery();
            //  v
            //  R,R,R,R,R
            while (resultSet.next()) {
                String nombre = resultSet.getString(SchemaDB.COL_EMP_NAME);
                String correo = resultSet.getString(SchemaDB.COL_EMP_MAIL);
                int tipo1 = resultSet.getInt(SchemaDB.COL_EMP_KIN);
                System.out.printf("Nombre del empleado %s\n\tCorreo del empleado %s\n\tTipo del empleado %s\n", nombre, correo, tipo1);
            }
        } catch (SQLException e) {
            System.out.println("error en la query");
        }
    }

    //hacemos otro metodo para agregar coche a dao:
    public void agregarCoche() {
        //pido los datos:
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce marca");
        String marca = scanner.next(); //recojo los datos
        //aqui estoy haciendo la logica y recogiendo los datos para tratarlos en el dao
        try {
            if (cochesDAO.getModeloCochesMarca(marca).size() < 2) {
                System.out.println("Introduce modelo");
                String modelo = scanner.next();
                System.out.println("Que caballos tiene");
                int cv = scanner.nextInt();
                System.out.println("Que precio tiene");
                int precio = scanner.nextInt();
                // si me dicen una marca y ya tengo 8 coches de esa marca, no lo quiero comprar
                cochesDAO.addCoche(new Coche(marca, modelo, cv, precio)); /*aqui añado el coche. Esto puede provocar un fallo por diversas
                causas (porque la query esté mal, etc)*/
                System.out.println("Coche agregado con exito");
            } else {
                System.out.println("No interesa el coche al concesionario");
            }
        } catch (SQLException e) {
            System.out.println("La base de datos no esta disponible, quieres guardar el objeto para mas adelante");
            /*despues de esto me voy al dao, para poder irme al dao, necesito un objeto de tipo dao, lo creo arriba antes de los metodos
            tipo private CochesDAO cohesDAO;*/
            // guardar el dato en un fichero -> DAO
        }

    }
    public void filtrarPrecio() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por que precio quieres filtrar");
        int precio = scanner.nextInt();
        try {
            for (Coche coche : cochesDAO.getCochePrecio(precio)) {
                // mostrar los datos de los coches resultantes en la consola
                coche.mostrarDatos();
            }
        } catch (SQLException e) {
            System.out.println("No se puede realizar la consulta, quiere hacer otra cosa");
        }

    }




}

