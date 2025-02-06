package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//para poder trabajar con la bb es la conexion.
// Clase destinada a la gestion de los coches contra la BD -> querys
public class CochesDAO {
    //creo la conexion, la inicializo dentro del constructor, es decir aqui, creamos el objeto conexion

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CochesDAO() {
        connection = new DBConnection().getConnection();
    }
//empiezo a crear tantos metodos como yo necesite:
    // que se pueda añadir un coche a la base de datos
    public void addCoche(Coche coche) throws SQLException {
        String query = String.format("INSERT into %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_CH, SchemaDB.COL_CH_MAR, SchemaDB.COL_CH_MOD, SchemaDB.COL_CH_CV, SchemaDB.COL_CH_PRE);
        //aqui para añadir dentro de la bbdd con preparedstatement
        preparedStatement = connection.prepareStatement(query); //inicializo la bbdd
        preparedStatement.setString(1, coche.getMarca());
        preparedStatement.setString(2, coche.getModelo());
        preparedStatement.setInt(3, coche.getCv());
        preparedStatement.setInt(4, coche.getPrecio());
        preparedStatement.execute();
    }
    public ArrayList<Coche> getModeloCochesMarca(String marcaParam) throws SQLException {
        ArrayList<Coche> listaResultado = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s=?", SchemaDB.TAB_CH, SchemaDB.COL_CH_MAR);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,marcaParam);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);
    }

    private ArrayList<Coche> getResultados(ResultSet datosResultantes) throws SQLException {
        ArrayList<Coche> listaResultado = new ArrayList<>();
        while (datosResultantes.next()){
            String marca = resultSet.getString(SchemaDB.COL_CH_MAR);
            String modelo = resultSet.getString(SchemaDB.COL_CH_MOD);
            int cv = resultSet.getInt(SchemaDB.COL_CH_CV);
            int precio = resultSet.getInt(SchemaDB.COL_CH_PRE);
            listaResultado.add(mapearCoche(marca,modelo,cv,precio));
        }
        return listaResultado;
    }
    /*creo un metodo privado de consulta para que me devuelva los parametros marca, modelo, cv y rpecio y me retorna el coche con esas
    caracteristicas que yo le he pedido*/
    private Coche mapearCoche(String marca, String modelo, int cv, int precio){
        return new Coche(marca,modelo,cv,precio); //me lo da construido, mapeado y lo meto en la lista arriba en getResultados
        //este metodo de repetir las cosas es para, si repito una misma accion varias veces, sea mas sencillo
    }

    public void realizarVenta(int id){
        // DELETE -> WHERE id= id
        // UPDATE -> estado = false WHERE id= id
    }

    /*dame los coches que tengan x precio, esto es como hacer filtro en una app. Hago un metodo con un arraylist para que me devuelva una lista
    de los coches que haya con ese filtro de precio que yo le meta*/
    public ArrayList<Coche> getCochePrecio(int precioParam) throws SQLException {
        //esto va a ser una lista de resultados de la query donde le pregunto por las columnas que me interesan
        ArrayList<Coche> listaResultado = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s <= ?",SchemaDB.TAB_CH,SchemaDB.COL_CH_PRE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,precioParam);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);
    }


}