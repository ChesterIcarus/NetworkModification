package InMemMatsim.Model.Specification.PlanParameters.Modes.Mode;

import InMemMatsim.Model.Specification.Core.Parameter;
import InMemMatsim.Model.Specification.Core.Parser;
import org.matsim.core.config.Config;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
import org.w3c.dom.Element;

import java.util.HashMap;

public class Mode extends Parameter<Mode> {
    public String type = "null";
    public double constant = 0.0D;
    public double dailyMonetaryConstant = 0.0D;
    public double dailyUtilityConstant = 0.0D;
    public double marginalUtilityOfDistance = 0.0D;
    public double marginalUtilityOfTraveling = -6.0D;
    public double monetaryDistanceRate = 0.0D;

    public Mode(Element element){
        super();
        populate(this, Parser.getParameters(element, this.getClass()));
    }

    public void toMatsim(Config config, Mode mode){
        PlanCalcScoreConfigGroup.ModeParams params = new PlanCalcScoreConfigGroup.ModeParams(mode.type);
        setMatsimParams(params, mode);
        config.planCalcScore().addModeParams(params);
    }
}
