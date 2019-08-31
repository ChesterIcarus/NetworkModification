package InMemMatsim.Model.Specification;

import InMemMatsim.Model.Specification.PlanParameters.PlanParameters;
import InMemMatsim.Model.Specification.Setup.Setup;
import InMemMatsim.Model.Specification.Setup.Threads.Threads;
import org.w3c.dom.Element;

public class Specification {
    public String path;
    public Element element;
    public String plans;
    public String network;
    public String config;
    public String events;
    public Setup setup;
    public Threads threads;
    public PlanParameters planParameters;

    public static void main(String[] args){
        SpecificationParser.createSpecification(
                "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/modelSpecifications.xml");
        System.out.println(SpecificationParser.createSpecification(
                "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/modelSpecifications.xml"));

        System.out.println("Testing");
    }
}
