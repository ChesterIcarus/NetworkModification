package InMemoryMatsim.Model.Specification;

import java.util.HashMap;
import java.util.List;

public class Modes extends Parameters {
    public List<Mode> modes;

    public Modes(HashMap<String, ?> params, List<Mode> modeList){
        super();
        createParams(this, params);
        this.modes = modeList;
    }
}
