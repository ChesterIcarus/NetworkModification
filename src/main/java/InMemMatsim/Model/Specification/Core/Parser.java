package InMemMatsim.Model.Specification.Core;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static Element getChild(Element element, String childName){
        NodeList nodes = element.getElementsByTagName(childName);
        Element child = null;
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (child == null)
                    child = (Element) node;
                else
                    throw new InstantiationError(
                            childName + " must be unique element specification XML.");
            }
        }
        return child;
    }

    public static List<Element> getChildren(Element element, String childName){
        NodeList nodes = element.getElementsByTagName(childName);
        List<Element> children = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                children.add((Element) node);
            }
        }
        return children;
    }

    public static String getClassName(Class classObj){
        return classObj.getName().toLowerCase().split("\\.")[
                classObj.getName().toLowerCase().split("\\.").length - 1];
    }
}
