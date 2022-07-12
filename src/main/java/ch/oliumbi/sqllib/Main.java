package ch.oliumbi.sqllib;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
            Sql sql = new Sql("jdbc:mysql://localhost:3306/ocontrol", "root", "root");

            QueryParts searchValues = new QueryParts("bachofner", new ArrayList<>());
            TestDTO outputTestDTO = new TestDTO();
            List<TestDTO> outputTestDTOs = new ArrayList<>();

            // select
            sql.select("" +
                            "SELECT id, " +
                            "       name, " +
                            "       password " +
                            "FROM   user " +
                            "INTO   :id, " +
                            "       :name, " +
                            "       :password",
                    outputTestDTO);

            System.out.println(outputTestDTO);
            outputTestDTO = new TestDTO();

            sql.select("" +
                            "SELECT id, " +
                            "       name, " +
                            "       password " +
                            "FROM   user " +
                            "WHERE  name = -query " +
                            "INTO   :id, " +
                            "       :name, " +
                            "       :password ",
                    outputTestDTO,
                    searchValues);

            System.out.println(outputTestDTO);

            sql.select("" +
                            "SELECT id, " +
                            "       name, " +
                            "       password " +
                            "FROM   user " +
                            "INTO   :id, " +
                            "       :name, " +
                            "       :password",
                    outputTestDTOs,
                    TestDTO.class);

            System.out.println(outputTestDTOs);
            outputTestDTOs = new ArrayList<>();

            sql.select("" +
                            "SELECT id, " +
                            "       name, " +
                            "       password " +
                            "FROM   user " +
                            "WHERE  name = -query " +
                            "INTO   :id, " +
                            "       :name, " +
                            "       :password",
                    outputTestDTOs,
                    TestDTO.class,
                    searchValues);

            System.out.println(outputTestDTOs);

            // insert
            TestDTO inputTestDTO = new TestDTO(0, "username3", "password3");
            int count = sql.insert("" +
                            "INSERT INTO user (" +
                            "   name, " +
                            "   password) " +
                            "VALUES (" +
                            "   -name, " +
                            "   -password)",
                    inputTestDTO);
            System.out.println(count);

            // update
            inputTestDTO = new TestDTO(15, "usernameEdit", "passwordEdit");
            count = sql.insert("" +
                            "UPDATE user " +
                            "SET    name = -name, " +
                            "       password = -password " +
                            "WHERE  id = -id ",
                    inputTestDTO);
            System.out.println(count);

            // delete
            searchValues = new QueryParts("eriic", new ArrayList<>());
            count = sql.delete("" +
                    "DELETE FROM user " +
                    "WHERE  name = -query",
                    searchValues);
            System.out.println(count);

            /**
             * Possible improvements:
             * - optimize update so where is not in same dto (not urgent)
             * - refactoring
             * - better exception messages
             */

    }
}
