package InMemMatsim.Model.Specification.PlanParameters.Modes;

import InMemMatsim.Model.Specification.PlanParameters.Modes.Mode.Mode;
import InMemMatsim.Model.Specification.PlanParameters.Modes.Mode.ModeParser;
import InMemMatsim.Model.Specification.Core.Parameters;
import InMemMatsim.Model.Specification.Core.Parser;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModesParser extends Parser {
    private static final Class parseClass = Modes.class;
    private static final Class parseSubclass = Modes.subclass;

    private static final String[] classFields =
            Parameters.getPrimitiveFieldNames(parseClass.getDeclaredFields());

    public static Modes getModes(Element element){
        HashMap<String, String> params = new HashMap<>();
        for (String field : classFields)
            params.put(field, getChild(element, field).getAttribute("value"));
        return new Modes(params, getModeList(element));
    }

    private static List<Mode> getModeList(Element element){
        List<Mode> modeList = new ArrayList<>();
        for (Element modeElement : getChildren(element, getClassName(parseSubclass).toLowerCase())){
            modeList.add(ModeParser.getMode(modeElement));
        }
        return modeList;
    }
}
