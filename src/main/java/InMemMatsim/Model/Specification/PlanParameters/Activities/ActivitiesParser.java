package InMemMatsim.Model.Specification.PlanParameters.Activities;

import InMemMatsim.Model.Specification.PlanParameters.Activities.Activity.Activity;
import InMemMatsim.Model.Specification.PlanParameters.Activities.Activity.ActivityParser;
import InMemMatsim.Model.Specification.Core.Parameters;
import InMemMatsim.Model.Specification.Core.Parser;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivitiesParser extends Parser {
    private static final Class parseClass = Activities.class;
    private static final Class parseSubclass = Activities.subclass;
    private static final String[] classFields =
            Parameters.getPrimitiveFieldNames(parseClass.getDeclaredFields());


    public static Activities getActivities(Element element) {
        HashMap<String, String> params = new HashMap<>();
        for (String field : classFields)
            params.put(field, getChild(element, field).getAttribute("value"));
        return new Activities(params, getActivityList(element));
    }

    private static List<Activity> getActivityList(Element element){
        List<Activity> activityList = new ArrayList<>();
        for (Element activityElement : getChildren(element, getClassName(parseSubclass))){
            activityList.add(ActivityParser.getActivity(activityElement));
        }
        return activityList;
    }
}

