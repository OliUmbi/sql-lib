package ch.oliumbi.sqllib;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.List;

public class Sql {

    private final Connection connection;
    private final QueryParser queryParser = new QueryParser();

    public Sql(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public void select(String query, Object output) {
        try {
            List<String> binds = queryParser.findBinds(query);
            query = queryParser.removeInto(query);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return;
            }

            SQLHelper.mapResultSet(resultSet, output, binds);

        } catch (Exception e) {
            throw new RuntimeException("Failed to executed SQL: " + e.getMessage());
        }
    }

    public void select(String query, Object output, Object input) {
        try {
            List<String> binds = queryParser.findBinds(query);
            List<String> inputs = queryParser.findInputs(query);
            query = queryParser.replaceInputs(query, inputs);
            query = queryParser.removeInto(query);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            SQLHelper.mapPreparedStatement(preparedStatement, input, inputs);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return;
            }

            SQLHelper.mapResultSet(resultSet, output, binds);

        } catch (Exception e) {
            throw new RuntimeException("Failed to executed SQL: " + e.getMessage());
        }
    }

    public void select(String query, List output, Class<?> clazz) {
        try {
            List<String> binds = queryParser.findBinds(query);
            query = queryParser.removeInto(query);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return;
            }

            do {
                Constructor<?> constructor = clazz.getConstructor();
                Object object = constructor.newInstance();

                SQLHelper.mapResultSet(resultSet, object, binds);

                output.add(object);
            } while (resultSet.next());
        } catch (Exception e) {
            throw new RuntimeException("Failed to executed SQL: " + e.getMessage());
        }
    }

    public void select(String query, List output, Class<?> clazz, Object input) {
        try {
            List<String> binds = queryParser.findBinds(query);
            List<String> inputs = queryParser.findInputs(query);
            query = queryParser.replaceInputs(query, inputs);
            query = queryParser.removeInto(query);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            SQLHelper.mapPreparedStatement(preparedStatement, input, inputs);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return;
            }

            do {
                Constructor<?> constructor = clazz.getConstructor();
                Object object = constructor.newInstance();

                SQLHelper.mapResultSet(resultSet, object, binds);

                output.add(object);
            } while (resultSet.next());
        } catch (Exception e) {
            throw new RuntimeException("Failed to executed SQL: " + e.getMessage());
        }
    }

    public int insert(String query, Object input) {
        try {
            List<String> inputs = queryParser.findInputs(query);
            query = queryParser.replaceInputs(query, inputs);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            SQLHelper.mapPreparedStatement(preparedStatement, input, inputs);

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to executed SQL: " + e.getMessage());
        }
    }

    public int update(String query, Object input) {
        try {
            List<String> inputs = queryParser.findInputs(query);
            query = queryParser.replaceInputs(query, inputs);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            SQLHelper.mapPreparedStatement(preparedStatement, input, inputs);

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to executed SQL: " + e.getMessage());
        }
    }

    public int delete(String query, Object input) {
        try {
            List<String> inputs = queryParser.findInputs(query);
            query = queryParser.replaceInputs(query, inputs);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            SQLHelper.mapPreparedStatement(preparedStatement, input, inputs);

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to executed SQL: " + e.getMessage());
        }
    }
}
