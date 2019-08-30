package InMemoryMatsim.Model.Specification;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class SpecificationParser extends Parser {

    public static Specification createSpecification(String filepath){
        Specification spec = new Specification();
        spec.path = filepath;
        try {
            spec.element = readSpecification(filepath);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        getBaseElements(spec);
        return spec;
    }

    /**
     * Reads the specifications for the model in the same directory as the filepath passed.
     * Used in the creation of a model, informs the plan source, network source, and other information.
     * @param filepath
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static Element readSpecification(String filepath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        return factory.newDocumentBuilder().parse(new File(filepath)).getDocumentElement();
    }

    private static void getBaseElements(Specification specification){
        specification.config = getChild(
                specification.element, "config").getAttribute("path");
        specification.plans = getChild(
                specification.element, "plans").getAttribute("path");
        specification.network = getChild(
                specification.element, "network").getAttribute("path");
        specification.events = getChild(
                specification.element, "events").getAttribute("path");


    }

    private static void getSetupElements(Specification specification){
        specification.setup = SetupParser.getSetupParams();
        specification.threads = ThreadsParser.getThreads(specification.element);
        specification.planParameters = PlanParametersParser.getPlanParameters(specification.element);
    }
}
