package InMemoryMatsim.Model.Specification;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModesParser extends Parser {
    private static final Class defaultDestinationClass = Modes.class;
    private static final String[] defaultParamsTagNames =
            Parameters.getStringFieldNames(defaultDestinationClass.getDeclaredFields());

    public static Modes getModes(Element element){
        HashMap<String, String> params = new HashMap<>();
        for (String field : defaultParamsTagNames)
            params.put(field, getChild(element, field).getAttribute("value"));
        return new Modes(params, getModeList(element));
    }

    private static List<Mode> getModeList(Element element){
        List<Mode> modeList = new ArrayList<>();
        for (Element modeElement : getChildren(element, "mode")){
            modeList.add(ModeParser.getMode(modeElement));
        }
        return modeList;
    }
}
