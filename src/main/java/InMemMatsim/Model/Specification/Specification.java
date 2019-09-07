package InMemMatsim.Model.Specification;

import InMemMatsim.Model.Specification.PlanParameters.PlanParameter;
import org.matsim.core.config.Config;
import org.w3c.dom.Element;

import java.io.IOException;

public class Specification {
    public String path = null;
    public Element element = null;
    public String plans = null;
    public String network = null;
    public String config = null;
    public String events = null;
    public PlanParameter planParameters = new PlanParameter();

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

    public static void toMatsim(Config config, Specification specification){

    }
}
