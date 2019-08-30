package InMemoryMatsim.Model.Specification;

import org.w3c.dom.Element;

import java.util.HashMap;

public class ModeParser extends Parser {
    private static final Class defaultDestinationClass = Mode.class;
    private static final String[] defaultParamsTagNames =
            Parameters.getStringFieldNames(defaultDestinationClass.getDeclaredFields());

    private static final HashMap<String, String> defaultParamValues = new HashMap<String, String>() {{
        put("mode", "car");
    }};

    public static Mode getMode() {
        return new Mode(defaultParamValues);
    }

    public static Mode getMode(Element element) {
        HashMap<String, String> params = new HashMap<>();
        for (String field : defaultParamsTagNames)
            params.put(field, getChild(element, field).getAttribute("value"));
        return new Mode(params);
    }
}
