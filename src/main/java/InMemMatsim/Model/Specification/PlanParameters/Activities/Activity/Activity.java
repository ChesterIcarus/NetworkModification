package InMemMatsim.Model.Specification.PlanParameters.Activities.Activity;

import InMemMatsim.Model.Specification.Core.Parameters;
import org.matsim.core.config.Config;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;

import java.util.HashMap;

public class Activity extends Parameters {
    public static final Class parser = ActivityParser.class;

    public String type = "null";
    public boolean scoringThisActivityAtAll = true;
    public double closingTime = 86400.0D;
    public double earliestEndTime = 0.0D;
    public double latestStartTime = 86400.0D;
    public double minimalDuration = 0.0D;
    public double openingTime = 0.0D;
    public double priority = 1.0D;
    public double typicalDuration = 3600.0D;

    public Activity(HashMap<String, ?> params) {
        super();
        createParams(this, params);
    }

    public static void addActivityToActivityParams(Config config, Activity activity) {
        PlanCalcScoreConfigGroup.ActivityParams params = new PlanCalcScoreConfigGroup.ActivityParams(activity.type);
        setMatsimParams(params, activity);
    }
}
