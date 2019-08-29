package Model;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ModelUtils {
    private static final Map<String, List<String>> defaults  = new HashMap<String, List<String>>() {{
        put("modes", Arrays.asList("car", "bike", "pt"));
        put("activities", Arrays.asList("w", "h", "s", "work", "home", "shopping"));
    }};
    private ModelUtils(){};

    public static Model createModel(){
        Model model = new Model();
        model.setActivities(getPlanParamTypes("modes").toArray(new String[0]));
        model.setActivities(getPlanParamTypes("activities").toArray(new String[0]));
        return model;
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
    public static Element readDirectorySpec(String filepath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        return factory.newDocumentBuilder().parse(new File(filepath)).getDocumentElement();
    }


    /**
     * Parses the parameters for either activity or mode types, returns
     * @param element
     * @param tagname Choices are {"activities", "modes"}
     * @return
     * @throws SAXException
     */
    private static List<String> getPlanParamTypes(Element element, String tagname) {
        tagname = tagname.toLowerCase();
        try {
            validateTagname(element, tagname);
            return getTagnameTypes(element, tagname);
        }
        catch (SAXException e) {
            e.printStackTrace();
            System.out.println("Unable to parse activities, using defaults.\nNote that the tagname" +
                    "for each child of `activities` must be `activity`, and have a field `type`.");
            return defaults.get(tagname);
        }
    }
    private static List<String> getPlanParamTypes(String tagname) {
        return getPlanParamTypes(null, tagname);
    };

    private static List<String> getTagnameTypes(Element element, String tagname){
        NodeList tagged = element.getElementsByTagName(tagname);
        List<String> content = new ArrayList<>();
        for (int i = 0; i < tagged.getLength(); i++){
            content.add(tagged.item(i).getAttributes().getNamedItem("type").getNodeValue());
        }
        return content;
    }

    /**
     * Used to validate tagnames of elements when parsing a model's specifications
     * @param element
     * @param tagname
     * @throws SAXException
     */
    private static void validateTagname(Element element, String tagname) throws SAXException {
        if (!element.getTagName().equalsIgnoreCase(tagname)){
            throw new SAXException("Element passed to parseActivities was not of tag " + tagname +
                    ".\nGiven tagname was " + element.getTagName());
        }
    }
}
