package InMemoryMatsim.Model.Specification;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

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
