package InMemoryMatsim.Model.Specification;

import java.util.HashMap;
import java.util.List;

public class PlanParameters extends Parameters {
    public Modes modes;
    public Activities activities;

    public PlanParameters(){
        super();
    };

    public PlanParameters(HashMap<String, ?> params){
        super();
//        createParams(this, params);
    }
}
