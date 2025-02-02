import database.DBConnection;

import java.sql.Connection;
import java.sql.Connection;

public class Entrada {
    //voy a pedir la conexion dos veces//
    /*voy con la primera conexion*/
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        //trabajas con la conexion y despues lo cierras con
        dbConnection.closeConnection();

        /*si creo otra conexion y la pido, cuando ejecuto, solo me aparece un mensaje de conexion creada a pesar de haber vuelto a crear el
        objeto y pedir la conexion pero no creo un objeto nuevo sino que reutilizo el objeto que ya se creó con la primera conexión.

        ¿Cuando se volveria a ejecutar esa conexion? cuando voy a la clase dbconecction.java, en vez de llamar a la url connection que
        tenemos establecida, llamamos a private void closeConnection()---- y su correspondiente try catch etc, y le meto el finally
        connection = null, si yo cierro la conexion, he igualado a nulo por lo que ya no esta el objeto que podemos seguir utilizando por lo que
        vamos a crear la nueva conexion2 de manera duplicada.

        una vez cerrada la conexion 1, cuando alguien, un objeto de tipo getconnection, llame al metodo getconnection, se va a encontrar que
        estoy llamando al metodo getconnection, al ser nulo, esta cerrada  por lo que ejecuto el metodo getconnection y coge la url, carga el
        driver y con este llama a la url, al root y la contraseña. Si alguien vuelve a hacer una llamada a la conexion aunque sea un objeto
        diferente, estaria llamando al mismo metodo, se reutiliza, para no tener que estar haciendo miles de objetos.*/

        DBConnection dbConnection2 = new DBConnection();
        Connection connection2 = dbConnection.getConnection();

        DBConnection dbConnection3 = new DBConnection();
        Connection connection3 = dbConnection.getConnection();


    }
}
