package InMemMatsim.Model.Specification.PlanParameters.Activities.Activity;

import InMemMatsim.Model.Specification.Core.Parameters;
import InMemMatsim.Model.Specification.Core.Parser;
import org.w3c.dom.Element;

import java.util.HashMap;

public class ActivityParser extends Parser {
    private static final Class parseClass = Activity.class;
    private static final String[] stringFieldNames =
            Parameters.getPrimitiveFieldNames(parseClass.getDeclaredFields());

    public static Activity getActivity(Element element) {
        HashMap<String, String> params = new HashMap<>();
        for (String field : stringFieldNames) {
            Element child = getChild(element, field);
            params.put(field, child == null ? null : child.getAttribute("value"));
        }
        return new Activity(params);
    }
}
