package InMemoryMatsim.Model.Specification;

import org.w3c.dom.Element;

import java.util.*;

public class ThreadsParser extends Parser {
    private static final Class parseClass = Threads.class;
    private static final String[] fieldNames =
            Parameters.getPrimitiveFieldNames(parseClass.getDeclaredFields());

    public static Threads getThreads(Element element) {
        HashMap<String, String> params = new HashMap<>();
        Element threadsElement = getChild(
                element, getClassName(parseClass));
        for (String field : fieldNames)
            params.put(field, getThreadField(threadsElement, field));
        return new Threads(params);
    }

    private static String getThreadField(Element classElement, String field) {
        return getChild(classElement, field).getAttribute("value");
    }

    ;
}

