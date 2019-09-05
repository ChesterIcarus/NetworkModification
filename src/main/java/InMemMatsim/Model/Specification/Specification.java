package InMemMatsim.Model.Specification;

import InMemMatsim.Model.Specification.PlanParameters.PlanParameters;
import InMemMatsim.Model.Specification.GlobalParameters.GlobalParameters;
import org.w3c.dom.Element;

import java.io.IOException;

public class Specification {
    public String path;
    public Element element;
    public String plans;
    public String network;
    public String config;
    public String events;
    public GlobalParameters globalParameters;
    public PlanParameters planParameters;

    @Deprecated
    public static void main(String[] args){
        String path = "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/modelSpecifications.xml";
        try {
            Specification specification = SpecificationParser.loadSpecification(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

    }
}
