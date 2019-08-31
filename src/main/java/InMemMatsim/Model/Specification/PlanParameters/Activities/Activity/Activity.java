package InMemMatsim.Model.Specification.PlanParameters.Activities.Activity;

import InMemMatsim.Model.Specification.Core.Parameters;
import org.matsim.core.config.Config;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        PlanCalcScoreConfigGroup.ActivityParams params;
        params = config.planCalcScore().getActivityParams(activity.type);
        Method[] methods = params.getClass().getMethods();
        HashMap<String, Method> setters = new HashMap<>();
        for (Method method : methods) {
            if (method.getName().startsWith("set"))
                setters.put(method.getName().toLowerCase(), method);
        }
        for (Field field : activity.getClass().getDeclaredFields()) {
            try {
                setters.get(("set" + field.getName()).toLowerCase()).invoke(params, (String) field.get(activity));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
//        params.setScoringThisActivityAtAll(activity.scoringThisActivityAtAll);
//        params.setClosingTime(activity.closingTime);
//        params.setEarliestEndTime(activity.earliestEndTime);
//        params.setLatestStartTime(activity.latestStartTime);
//        params.setMinimalDuration(activity.minimalDuration);
//        params.setOpeningTime(activity.openingTime);
//        params.setPriority(activity.priority);
//        params.setTypicalDuration(activity.typicalDuration);
    }
}
