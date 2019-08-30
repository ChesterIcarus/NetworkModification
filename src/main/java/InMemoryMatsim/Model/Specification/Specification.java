package InMemoryMatsim.Model.Specification;

import org.w3c.dom.Element;

import java.util.List;

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
        System.out.println("Testing");
    }
}
