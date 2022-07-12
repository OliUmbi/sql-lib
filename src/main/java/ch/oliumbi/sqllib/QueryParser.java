package ch.oliumbi.sqllib;

import java.util.ArrayList;
import java.util.List;

public class QueryParser {

    public List<String> findBinds(String query) {
        List<String> binds = new ArrayList<>();

        String[] splitQuery = query.split("[ \\t\\n(),]");
        for (String queryPart : splitQuery) {
            if (queryPart.startsWith(":")) {

                binds.add(queryPart.replace(",", "")
                        .replace(":", ""));
            }
        }

        return binds;
    }

    public List<String> findInputs(String query) {
        List<String> binds = new ArrayList<>();

        String[] splitQuery = query.split("[ \\t\\n(),]");
        for (String queryPart : splitQuery) {
            if (queryPart.startsWith("-")) {

                binds.add(queryPart.replace(",", "")
                        .replace("-", ""));
            }
        }

        return binds;
    }

    public String replaceInputs(String query, List<String> inputs) {
        for (String input : inputs) {
            query = query.replace("-" + input, "?");
        }

        return query;
    }

    public String removeInto(String query) {
        int intoIndex = query.toLowerCase().indexOf("into");

        return query.substring(0, intoIndex);
    }
}
