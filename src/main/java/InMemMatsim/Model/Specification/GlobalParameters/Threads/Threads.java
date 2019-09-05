package InMemMatsim.Model.Specification.GlobalParameters.Threads;

import InMemMatsim.Model.Specification.Core.Parameters;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.List;

public class Threads extends Parameters {
    public static final Class parser = ThreadsParser.class;
    public int simulation;
    public int planning;

    public Threads(){
        super();
        this.subclasses = null;
        this.planning = 1;
        this.simulation = 1;
    };

    public Threads(HashMap<String, ?> params){
        this();
        createParams(this, params);
    }

    public static Threads parse(Element element){
        return ThreadsParser.getThreads(element);
    }
}
