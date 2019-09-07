package InMemMatsim.Model.Specification.PlanParameters.Modes;

import InMemMatsim.Model.Specification.Core.Parser;
import InMemMatsim.Model.Specification.PlanParameters.Activities.Activity.Activity;
import InMemMatsim.Model.Specification.PlanParameters.Modes.Mode.Mode;
import InMemMatsim.Model.Specification.Core.Parameter;
import org.matsim.core.config.Config;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static InMemMatsim.Model.Specification.Core.Parser.getChildren;
import static InMemMatsim.Model.Specification.Core.Parser.getClassName;

public class Modes extends Parameter<Modes> {
    public static final Class DESCENDANT = Mode.class;

    public List<Mode> modes;

    public Modes(){
        super();
        this.modes = new ArrayList<>();
    }

    public Modes(Element element){
        this();
        populate(this, Parser.getParameters(element, this.getClass()));
        for (Element childActivity : getChildren(element, getClassName(DESCENDANT)))
            this.modes.add(new Mode(childActivity));
    }

    @Override
    public void toMatsim(Config config, Modes modes) {

    }



}
