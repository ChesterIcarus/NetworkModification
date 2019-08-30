package InMemoryMatsim.Model.Specification;

import java.util.HashMap;
import java.util.List;

public class Setup extends Parameters {
    public String crs;

    public Setup(){
        super();
    };

    public Setup(HashMap<String, ?> params){
        super();
        createParams(this, params);
    }
}
