package InMemMatsim.Model.Specification.PlanParameters.Activities;

import InMemMatsim.Model.Specification.PlanParameters.Activities.Activity.Activity;
import InMemMatsim.Model.Specification.Core.Parameters;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.List;

public class Activities extends Parameters {
    public static final Class<Activity> subclass = Activity.class;

    public List<Activity> activities;

    public Activities(){};

    public Activities(HashMap<String, ?> params, List<Activity> activityList){
        super();
        createParams(this, params);
        this.activities = activityList;
    }

    public static Activities parse(Element element){
        return ActivitiesParser.getActivities(element);
    }
}
