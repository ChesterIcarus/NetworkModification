package InMemMatsim.Model.Specification.PlanParameters;

import InMemMatsim.Model.Specification.PlanParameters.Activities.Activities;
import InMemMatsim.Model.Specification.PlanParameters.Modes.Modes;
import InMemMatsim.Model.Specification.Core.Parameters;

import java.util.HashMap;

public class PlanParameters extends Parameters {
    public Modes modes;
    public Activities activities;
    // TODO: Add PlanScoring module to parser
//    public PlanScoring planScoring;

    public PlanParameters(){
        super();
    };

    public PlanParameters(HashMap<String, ?> params){
        super();
        createParams(this, params);
        this.modes = new Modes();
        this.activities = new Activities();
    }
}
