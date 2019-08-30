package InMemoryMatsim.Model.Specification;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class Threads extends Parameters {
    public String simulation;
    public String planning;

    public Threads(){
        super();
    };

    public Threads(HashMap<String, ?> params){
        super();
        createParams(this, params);
    }
}
