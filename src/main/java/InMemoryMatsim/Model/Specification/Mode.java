package InMemoryMatsim.Model.Specification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mode extends Parameters {
    public String type;
    public float constant = 1.0f;
    public float dailyMonetaryConstant = 1.0f;
    public float dailyUtilityConstant = 1.0f;
    public float marginalUtilityOfDistance = 1.0f;
    public float marginalUtilityOfTraveling = 0.0f;
    public float monetaryDistanceRate = 1.0f;

    public Mode(HashMap<String, ?> params){
        super();
        createParams(this, params);
    }

}
