package InMemMatsim.Model.Specification.PlanParameters.Modes.Mode;

import InMemMatsim.Model.Specification.Core.Parameters;
import org.matsim.core.config.Config;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Mode extends Parameters {
    public String type = "null";
    public double constant = 0.0D;
    public double dailyMonetaryConstant = 0.0D;
    public double dailyUtilityConstant = 0.0D;
    public double marginalUtilityOfDistance = 0.0D;
    public double marginalUtilityOfTraveling = -6.0D;
    public double monetaryDistanceRate = 0.0D;

    public Mode(HashMap<String, ?> params){
        super();
        createParams(this, params);
    }

    public static void addModeToModeParams(Config config, Mode mode){
        PlanCalcScoreConfigGroup.ModeParams params = new PlanCalcScoreConfigGroup.ModeParams(mode.type);
        setMatsimParams(params, mode);
    }
}
