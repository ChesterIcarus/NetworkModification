package InMemoryMatsim.Model.Specification;

import java.util.HashMap;
import java.util.List;

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
