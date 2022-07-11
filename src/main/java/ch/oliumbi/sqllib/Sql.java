package ch.oliumbi.sqllib;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.ParameterizedType;

public class Sql {

    private final Connection connection;

    public Sql(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public void select(String query, Object object) {
        try {
            QueryParts queryParts = parseQuery(query);

            ResultSet resultSet = executeQuery(queryParts.getQuery());

            mapResultSet(resultSet, queryParts.getFields(), object);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void select(String query, List list, Class<?> clazz) {
        try {
            QueryParts queryParts = parseQuery(query);

            ResultSet resultSet = executeQuery(queryParts.getQuery());

            do {
                Constructor<?> constructor = clazz.getConstructor();
                Object object = constructor.newInstance();

                mapResultSet(resultSet, queryParts.getFields(), object);

                list.add(object);
            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private QueryParts parseQuery(String query) {
        int intoIndex = query.toLowerCase().indexOf("into");
        String queryBlock = query.substring(0, intoIndex);
        String intoBlock = query.substring(intoIndex);
        List<String> fields = new ArrayList<>();

        while (true) {
            int colonIndex = intoBlock.indexOf(":");

            if (colonIndex == -1) {
                break;
            }

            int spaceIndex = intoBlock.indexOf(" ", colonIndex);

            if (spaceIndex == -1) {
                fields.add(intoBlock.substring((colonIndex + 1)).replace(",", ""));
                break;
            }

            fields.add(intoBlock.substring((colonIndex + 1), spaceIndex).replace(",", ""));

            intoBlock = intoBlock.replaceFirst(":", "");
        }

        return new QueryParts(queryBlock, fields);
    }

    private ResultSet executeQuery(String query) throws RuntimeException, SQLException {
        ResultSet resultSet = connection.prepareStatement(query).executeQuery();

        if (!resultSet.next()) {
            throw new RuntimeException("No columns");
        }

        return resultSet;
    }

    private void mapResultSet(ResultSet resultSet, List<String> fields, Object object) throws NoSuchFieldException, NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);

            String capitalizedField = field.substring(0, 1).toUpperCase() + field.substring(1);
            Method method = object.getClass()
                    .getDeclaredMethod(
                            "set" + capitalizedField,
                            object.getClass().getDeclaredField(field).getType()
                    );

            method.invoke(
                    object,
                    resultSet.getObject(
                            (i + 1),
                            object.getClass().getDeclaredField(field).getType()
                    )
            );
        }
    }
}
