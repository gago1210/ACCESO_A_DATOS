package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Empleado;
import model.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class EmpleadoDAO {
    /*para insertar cosas en mi base de datos lo que tengo que insertar aqui en esta parte antes del public void insertarEmpleado es la conexion,
        la marco privada porque va para cada uno de ellos*/
    private Connection connection;
    private PreparedStatement preparedStatement;
    // statement si lo vemos necesario pero si podemos utilizar preparedstatement mejor
    private ResultSet resultSet;

    /*constructor por defecto para que cuando cada vez que yo cree el objeto de tipo empleadoDAO,llame al DBconnection y establezca la conexion*/
    public EmpleadoDAO(){
        connection = new DBConnection().getConnection();
    }

    //o lo que a mi me interese, si quiero un array, un public void.. lo que necesite
    //el objeto que utilizo para hacer la insercion es prepared statement

    //aqui lo que hago es
    public void insertarEmpleado(Empleado empleado) throws SQLException{
        preparedStatement = connection.prepareStatement(String.format("INSER INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
                            SchemaDB.TAB_EMP, SchemaDB.COL_EMP_NAME, SchemaDB.COL_EMP_SURNAME, SchemaDB.COL_EMP_PHO, SchemaDB.COL_EMP_MAIL,
                            SchemaDB.TAB_KIN));
        preparedStatement.setString(1, empleado.getNombre());
        preparedStatement.setString(2, empleado.getApellido());
        preparedStatement.setInt(3, empleado.getTelefono());
        preparedStatement.setString(4, empleado.getCorreo());
        preparedStatement.setInt(5, empleado.getTipo().getId());
        preparedStatement.execute();
    }

    /*a continuacion lo que voy a hacer es tratar la query con el objeto que extraigo del metodo de abajo get empleado donde preparo la query
        con sus tipos */
    public Empleado getEmpleado(int id) throws SQLException {

        preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s=?", SchemaDB.TAB_EMP, SchemaDB.COL_ID));
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery(); //aqui quiero sacar la query por eso el executequery
        while (resultSet.next()) { //lo quiero guardar en un resultsset.next
            String nombre = resultSet.getString(SchemaDB.COL_EMP_NAME);
            String apellido = resultSet.getString(SchemaDB.COL_EMP_SURNAME);
            String correo = resultSet.getString(SchemaDB.COL_EMP_MAIL);
            int telefono = resultSet.getInt(SchemaDB.COL_EMP_PHO);
            int tipo = resultSet.getInt(SchemaDB.COL_EMP_KIN);
            return getEmpleado(nombre, apellido, correo, telefono, tipo);
        }

        return null;
    }
 //este metodo lo hago para que luego puedas retornar los datos de aqui abajo a get empleado arriba cuando tratamos la query
    public Empleado getEmpleado(String nombre, String apellido, String correo, int telefono, int tipo) {
        Tipo tipo1 = null;
        switch (tipo) {
            case 1:
                tipo1 = Tipo.EXTERNO;
                break;
            case 2:
                tipo1 = Tipo.INDEFINIDO;
                break;
            case 3:
                tipo1 = Tipo.BECARIO;
                break;
        }
        return new Empleado(nombre, apellido, correo, telefono, tipo1);
    }

    //la query que yo quiero ejecutar es:
    public void realizarVenta(int id) throws SQLException {
        /*cuando yo hago una venta quiero que a ese empelado se le sume una venta, de ahi el +1*/
        String query = "UPDATE %s SET %s = %s+1 WHERE %s = ?"; /*lo que quiero modificar es el atributo ventas, incrementandole uno al
        apartado de ventas con el usuario que la realice, le meto los parametros a la query de los datos que yo quiera obtener*/
        preparedStatement = connection.prepareStatement(String.format(query,SchemaDB.TAB_EMP, SchemaDB.COL_EMP_SALE,SchemaDB.COL_EMP_SALE,
                SchemaDB.COL_ID));
        preparedStatement.setInt(1,id); //preparamos la query y le indicamos que en el primer hueco de la query que me de, sea el id
        preparedStatement.execute(); //que se ejecute como sea
    }

    public void obtenerEmpleadoMes(int numero) throws SQLException {
        String query = "SELECT * FROM %s ORDER BY %s DESC LIMIT ?"; /*order by es para que me de lo que necesito, sin preguntar y
        desc limit es para quedarme con el primero con mayor puntuacion*/
        preparedStatement = connection.prepareStatement(String.format(query,SchemaDB.COL_EMP_SALE));
        preparedStatement.setInt(1, numero);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String nombre = resultSet.getString(SchemaDB.COL_EMP_NAME);
            String apellido = resultSet.getString(SchemaDB.COL_EMP_SURNAME);
            Empleado empleado = new Empleado(nombre,apellido);
            empleado.mostrarDatos();
        }
    }






}
