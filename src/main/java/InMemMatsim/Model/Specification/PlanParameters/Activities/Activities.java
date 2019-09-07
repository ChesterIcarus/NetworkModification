package InMemMatsim.Model.Specification.PlanParameters.Activities;

import InMemMatsim.Model.Specification.Core.Parser;
import InMemMatsim.Model.Specification.PlanParameters.Activities.Activity.Activity;
import InMemMatsim.Model.Specification.Core.Parameter;
import org.matsim.core.config.Config;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static InMemMatsim.Model.Specification.Core.Parser.*;

public class Activities extends Parameter<Activities> {
    public List<Activity> activities;
    public static final Class DESCENDANT = Activity.class;

    public Activities(){
        super();
        this.activities = new ArrayList<>();
    }

    public Activities(Element element){
        this();
        populate(this, Parser.getParameters(element, this.getClass()));
        for (Element childActivity : getChildren(element, getClassName(DESCENDANT)))
            this.activities.add(new Activity(childActivity));
    }

    private void getDescendantList(Element element){

    }

    @Override
    public void toMatsim(Config config, Activities value) {

    }
}
