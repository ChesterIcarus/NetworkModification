package InMemoryMatsim.Model.Specification;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.util.*;

public class PlanParametersParser extends Parser {
    private static final Class parseClass = PlanParameters.class;
    private static final String[] classFields = Parameters.getStringFieldNames(
            parseClass.getDeclaredFields());

    public static PlanParameters getPlanParameters(){
        return new PlanParameters();
    }

    public static PlanParameters getPlanParameters(Element element){
        for (String field : classFields)
            continue;
        return new PlanParameters();
    }
}
