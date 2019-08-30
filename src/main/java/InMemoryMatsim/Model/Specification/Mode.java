package InMemoryMatsim.Model.Specification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mode extends Parameters {
    public String mode;
    public float constant;
    public float dailyMonetaryConstant;
    public float dailyUtilityConstant;
    public float marginalUtilityOfDistance;
    public float marginalUtilityOfTraveling;
    public float monetaryDistanceRate;

    public Mode(HashMap<String, ?> params){
        super();
        createParams(this, params);
    }

}
