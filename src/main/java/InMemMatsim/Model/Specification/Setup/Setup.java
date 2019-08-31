package InMemMatsim.Model.Specification.Setup;

import InMemMatsim.Model.Specification.Core.Parameters;

import java.util.HashMap;

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
