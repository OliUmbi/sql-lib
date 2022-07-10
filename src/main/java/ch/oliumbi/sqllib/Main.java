package ch.oliumbi.sqllib;

import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
//        ResultSet resultSet = SQL.select("Select * from user");
//
//        System.out.println(resultSet.toString());

        ResultHandler resultHandler = new ResultHandler();

        resultHandler.handle(new TestDTO());


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
