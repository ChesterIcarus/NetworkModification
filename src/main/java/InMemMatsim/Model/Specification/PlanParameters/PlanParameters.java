package InMemMatsim.Model.Specification.PlanParameters;

import InMemMatsim.Model.Specification.PlanParameters.Activities.Activities;
import InMemMatsim.Model.Specification.PlanParameters.Modes.Modes;
import InMemMatsim.Model.Specification.Core.Parameters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlanParameters extends Parameters {
    public Modes modes;
    public Activities activities;
    // TODO: Add PlanScoring module to parser
//    public PlanScoring planScoring;

    public PlanParameters(){
        super();
        super.subclasses = Arrays.asList(Modes.class, Activities.class);
        this.modes = new Modes();
        this.activities = new Activities();
    };

    public PlanParameters(HashMap<String, ?> params){
        this();
        createParams(this, params);
    }
}
