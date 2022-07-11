package ch.oliumbi.sqllib;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            Sql sql = new Sql("jdbc:mysql://localhost:3306/ocontrol", "root", "root");


//            TestDTO testDTO = new TestDTO();
//            sql.select("" +
//                            "SELECT name " +
//                            "FROM   user " +
//                            "INTO   :text,",
//                    testDTO);

            List<TestDTO> testDTOs = new ArrayList<>();
            sql.select("" +
                            "SELECT id, " +
                            "       name," +
                            "       password " +
                            "FROM   user " +
                            "INTO   :id, " +
                            "       :password ",
                    testDTOs,
                    TestDTO.class);

            System.out.println(testDTOs);
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        int count = sql.insert("" +
//                "INSERT INTO user(name) " +
//                "VALUES :text ",
//                testDTO);


        /**
         * Ideas:
         *
         * something like SQL from scout where you define the results (maybe directly using lib from scout)
         * advantage mor control over results
         * disadvantage complicated, string parsing, time intensive
         *
         * sql maps all dto fields directly
         * advantage easy to use, others have done this
         * disadvantage boilerplate, edge cases lists
         *
         * something new from both worlds
         * define sql with fields you want to parse in.
         * list are handled by the amount of results or defined beforehand via function name
         * SQL.select("query with :yeet", Yeet.class)
         *
         * general problems:
         * id (bin): could be resolved in sql query
         * lists
         *
         */
    }
}
