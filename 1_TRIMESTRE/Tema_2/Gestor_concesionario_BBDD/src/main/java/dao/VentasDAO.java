package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentasDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public VentasDAO() {
        connection = new DBConnection().getConnection();
    }

    public void realizarVenta(int idEmpleado, int idCoche) throws SQLException {
        String query = "INSERT INTO ventas (id_empleado, id_coche) VALUES (?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,idEmpleado);
        preparedStatement.setInt(2,idCoche);
        preparedStatement.executeUpdate();
    }

}
