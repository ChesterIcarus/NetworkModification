package InMemMatsim.Model.Specification.PlanParameters;

import InMemMatsim.Model.Specification.Core.MetaParser;
import InMemMatsim.Model.Specification.Core.Parser;

import org.w3c.dom.Element;


public class PlanParametersParser extends Parser {
    private static final Class parseClass = PlanParameters.class;

    public static PlanParameters getPlanParameters(Element element) {
        PlanParameters planParameters = new PlanParameters(getParameters(element, parseClass));
        MetaParser.parseDescendant(planParameters, element);
        return planParameters;
    }
}
