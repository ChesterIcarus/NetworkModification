package InMemoryMatsim.Model.Specification;

import java.util.HashMap;

public class Activity extends Parameters {
    public static final Class parser = ActivityParser.class;

    public String type;
    public boolean scoringThisActivityAtAll = true;
    public float closingTime;
    public float earliestEndTime;
    public float latestStartTime;
    public float minimalDuration;
    public float openingTime;
    public float priority;
    public float typicalDuration;

    public Activity(HashMap<String, ?> params){
        super();
        createParams(this, params);
    }
}
