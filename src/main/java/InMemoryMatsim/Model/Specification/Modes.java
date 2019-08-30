package InMemoryMatsim.Model.Specification;

import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.List;

public class Modes extends Parameters {
    public static final Class subclass = Mode.class;

    public List<Mode> modes;

    public Modes(){};

    public Modes(HashMap<String, ?> params, List<Mode> modeList){
        super();
        createParams(this, params);
        this.modes = modeList;
    }

    public static Modes parse(Element element){
        return ModesParser.getModes(element);
    }
}
