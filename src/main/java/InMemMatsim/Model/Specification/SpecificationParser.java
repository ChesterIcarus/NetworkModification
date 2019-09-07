package InMemMatsim.Model.Specification;

import InMemMatsim.Model.Specification.Core.Parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class SpecificationParser extends Parser {

    public static Specification loadSpecification(String filepath) throws IOError, IOException {
        Specification spec = new Specification();
        spec.path = filepath;
        try {
            spec.element = readSpecification(filepath);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new IOError(e);
        }
        getBaseElements(spec);
        getSetupElements(spec);
//        validateAtomicPaths(spec);
        return spec;
    }

    public static void validateAtomicPaths(Specification specification) throws IOException {
        // TODO : Debug this method
        String specDir = specification.path.substring(0, specification.path.lastIndexOf("/"));
        String configDir = specification.config.substring(0, specification.config.lastIndexOf("/"));
        String eventsDir = null;
        if (!specDir.equalsIgnoreCase(configDir))
            throw new IOException("Specification file and Config file are located in distinct directories");
        if (specification.events != null)
            eventsDir = specification.events.substring(0, specification.events.lastIndexOf("/"));
            if (!specDir.equalsIgnoreCase(eventsDir))
                throw new IOException("Specification file and Networks Events file are located in distinct directories");
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
    private static Element readSpecification(String filepath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        Document document = factory.newDocumentBuilder().parse(new File(filepath));
        document.normalizeDocument();
        return document.getDocumentElement();
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
//        specification.globalParameters = GlobalParametersParser.getGlobalParameters(specification.element);
//        specification.threads = ThreadsParser.getThreads(specification.element);
//        specification.planParameters = PlanParametersParser.getPlanParameters(specification.element);
    }
}
