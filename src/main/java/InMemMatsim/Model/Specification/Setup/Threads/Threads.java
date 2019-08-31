package InMemMatsim.Model.Specification.Setup.Threads;

import InMemMatsim.Model.Specification.Core.Parameters;

import java.util.HashMap;

public class Threads extends Parameters {
    public static final Class parser = ThreadsParser.class;
    public int simulation;
    public int planning;

    public Threads(){
        super();
    };

    public Threads(HashMap<String, ?> params){
        super();
        createParams(this, params);
    }
}
