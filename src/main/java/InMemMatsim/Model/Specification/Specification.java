package InMemMatsim.Model.Specification;

import InMemMatsim.Model.Specification.PlanParameters.PlanParameters;
import InMemMatsim.Model.Specification.GlobalParameters.GlobalParameters;
import org.w3c.dom.Element;

public class Specification {
    public String path;
    public Element element;
    public String plans;
    public String network;
    public String config;
    public String events;
    public GlobalParameters globalParameters;
    public PlanParameters planParameters;

    public static void main(String[] args){
        String path = "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/modelSpecifications.xml";
        Specification specification = SpecificationParser.loadSpecification(path);
        System.out.println();

    }
}
