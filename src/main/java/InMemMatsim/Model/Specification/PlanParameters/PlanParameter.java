package InMemMatsim.Model.Specification.PlanParameters;

import InMemMatsim.Model.Specification.Core.Parser;
import InMemMatsim.Model.Specification.PlanParameters.Activities.Activities;
import InMemMatsim.Model.Specification.PlanParameters.Modes.Modes;
import InMemMatsim.Model.Specification.Core.Parameter;
import org.matsim.core.config.Config;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
import org.w3c.dom.Element;

import java.util.HashMap;

public class PlanParameter extends Parameter<PlanParameter> {
    private static final Class MATSIM_CLASS = PlanCalcScoreConfigGroup.class;
    public Modes modes;
    public Activities activities;
    // TODO: Add PlanScoring module to parser

    public PlanParameter(){
        super();
        this.modes = new Modes();
        this.activities = new Activities();
    };

    public PlanParameter(Element element){
        this();
        populate(this, Parser.getParameters(element, this.getClass()));
        this.modes = new Modes(element);
        this.activities = new Activities(element);
    }

    public void toMatsim(Config config, PlanParameter planParameter){ }
}
