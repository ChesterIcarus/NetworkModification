package InMemMatsim.Model.Specification.GlobalParameters;

import InMemMatsim.Model.Specification.Core.Parameters;

import java.util.HashMap;

public class GlobalParameters extends Parameters {
    public int numberOfThreads;
    public String coordinateSystem;
    public long randomSeed;

    public GlobalParameters(){
        super();
        this.numberOfThreads = 2;
        this.coordinateSystem = "Atlantis";
        this.randomSeed = 4711L;
    };

    public GlobalParameters(HashMap<String, ?> params){
        this();
        createParams(this, params);
    }
}
