package InMemoryMatsim.Model.Specification;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.lang.reflect.Field;
import java.util.*;

public class SetupParser extends Parser {
    public static Setup getSetupParams(){
        return new Setup();
    }
}
