package InMemMatsim.Model.Specification.PlanParameters.Modes.Mode;

import InMemMatsim.Model.Specification.Core.Parameters;
import org.matsim.core.config.Config;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;

import java.util.HashMap;

public class Mode extends Parameters {
    public String type;
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
        PlanCalcScoreConfigGroup.ModeParams params;
        params = config.planCalcScore().getOrCreateModeParams(mode.type);
        params.setConstant(mode.constant);
        params.setDailyMonetaryConstant(mode.dailyMonetaryConstant);
        params.setDailyUtilityConstant(mode.dailyUtilityConstant);
        params.setMarginalUtilityOfDistance(mode.marginalUtilityOfDistance);
        params.setMarginalUtilityOfTraveling(mode.marginalUtilityOfTraveling);
        params.setMonetaryDistanceRate(mode.monetaryDistanceRate);
    }
}
