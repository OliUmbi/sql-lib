package ch.oliumbi.sqllib;

import java.sql.*;

public class Testing<T> {

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teachu", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet select(String query) {
        ResultSet resultSet = null;

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            resultSet = prepareStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }
}
