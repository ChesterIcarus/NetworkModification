package InMemMatsim.Model.Specification.GlobalParameters;

import InMemMatsim.Model.Specification.Core.Parameters;
import InMemMatsim.Model.Specification.GlobalParameters.Threads.Threads;
import InMemMatsim.Model.Specification.PlanParameters.Activities.Activities;
import InMemMatsim.Model.Specification.PlanParameters.Modes.Modes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GlobalParameters extends Parameters {
    public boolean timeVariant;
    public Threads threads;

    public GlobalParameters(){
        super();
        super.subclasses = Arrays.asList(Threads.class);
        this.timeVariant = false;
        this.threads = new Threads();
    };

    public GlobalParameters(HashMap<String, ?> params){
        this();
        createParams(this, params);
    }
}
