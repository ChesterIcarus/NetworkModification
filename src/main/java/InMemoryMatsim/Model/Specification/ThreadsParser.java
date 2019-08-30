package InMemoryMatsim.Model.Specification;

import org.w3c.dom.Element;

import java.util.*;

public class ThreadsParser extends Parser {
    private static final Class parseClass = Threads.class;
    private static final String[] classFields =
            Parameters.getStringFieldNames(parseClass.getDeclaredFields());
    private static final HashMap<String, String> defaultParamValues = new HashMap<String, String>() {{
        put(classFields[0], "6");
        put(classFields[1], "2");
    }};

    public static Threads getThreads() {
        return new Threads(defaultParamValues);
    }

    public static Threads getThreads(Element element) {
        HashMap<String, String> params = new HashMap<>();
        Element threadsElement = getChild(
                element, parseClass.getName().toLowerCase());
        for (String field : classFields)
            params.put(field, getThreadField(threadsElement, field));
        return new Threads(params);
    }

    private static String getThreadField(Element classElement, String field) {
        return getChild(classElement, field).getAttribute("value");
    }

    ;
}

