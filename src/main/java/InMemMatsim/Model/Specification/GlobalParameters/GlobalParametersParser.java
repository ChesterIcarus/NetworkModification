package InMemMatsim.Model.Specification.GlobalParameters;

import InMemMatsim.Model.Specification.Core.MetaParser;
import InMemMatsim.Model.Specification.Core.Parser;

import org.w3c.dom.Element;


public class GlobalParametersParser extends Parser {
    private static final Class parseClass = GlobalParameters.class;

    public static GlobalParameters getGlobalParameters(Element element) {
        GlobalParameters globalParams = new GlobalParameters(getParameters(element, parseClass));
        MetaParser.parseDescendant(globalParams, element);
        return globalParams;
    }
}
