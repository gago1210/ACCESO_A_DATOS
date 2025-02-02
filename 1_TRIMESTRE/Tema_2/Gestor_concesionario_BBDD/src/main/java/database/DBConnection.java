package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    /*vamos a utilizar la estructura:
        si alquien pide una conexion:
            si esta --> se la doy (la que pertenece a la clase)
            si no esta --> la creo (pero creo la de provate static connection, porque pertenece a la clase, la creo una sola vez)*/

    public static Connection getConnection() { //si alguien pide una conexion se la doy, sino entro en el bucle if de no y la creo

        /*si yo te pido esto tu me vas a devolver una conexion, el que sea estatico quiere decir que pertenece a la instancia de la clase, no al
        objeto completo*/
        if (connection == null) {
            //creo la conexion en caso de que no este, cumplimos la estructura atras comentada
            new Connection();
        }
        return connection;
    }
    private void newConnection() {
        //creo la conexion, para ello necesito la uri de la conexion jdbc:mysql://127.0.0.1:3306/conesionario, lo guardo en un String
        String url = "jdbc:mysql://127.0.0.1:3306/conesionario";
        /*necesito inicializar esta variable para que cuando a mi me llamen, esto este inicializado y pueda retornar. Utilizo el driver que me
        // descargué, y le indico la url , el usuario y la contraseña que tengamos en dbbeaver que es de donde estamos cogiendo los datos de
        la bbdd. Como me va a dar error, hacemos una excepcion */
        try {
            connection = DriverManager.getConnection(url, user:"root", password:"");
        } catch (SQLException e) {
            System.out.println("Error en la conexión de la BBDD");
        }
        System.out.println("Conexión creada correctamente");
    } //ya lo tengo conectado

    public void closeConnection () {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión");
        } finally {
            connection = null;
        }
    }



}
