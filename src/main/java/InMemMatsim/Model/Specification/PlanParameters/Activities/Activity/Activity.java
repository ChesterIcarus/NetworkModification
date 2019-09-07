package InMemMatsim.Model.Specification.PlanParameters.Activities.Activity;

import InMemMatsim.Model.Specification.Core.Parameter;
import InMemMatsim.Model.Specification.Core.Parser;
import org.matsim.core.config.Config;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
import org.w3c.dom.Element;

public class Activity extends Parameter<Activity> {
    private static final Class MATSIM_CLASS = PlanCalcScoreConfigGroup.ActivityParams.class;

    public String type = "null";
    public boolean scoringThisActivityAtAll = true;
    public double closingTime = 86400.0D;
    public double earliestEndTime = 0.0D;
    public double latestStartTime = 86400.0D;
    public double minimalDuration = 0.0D;
    public double openingTime = 0.0D;
    public double priority = 1.0D;
    public double typicalDuration = 3600.0D;

    public Activity(Element element) {
        super();
        populate(this, Parser.getParameters(element, this.getClass()));
    }

    public void toMatsim(Config config, Activity activity) {
        PlanCalcScoreConfigGroup.ActivityParams params = new PlanCalcScoreConfigGroup.ActivityParams(activity.type);
        setMatsimParams(params, activity);
        config.planCalcScore().addActivityParams(params);
    }
}
