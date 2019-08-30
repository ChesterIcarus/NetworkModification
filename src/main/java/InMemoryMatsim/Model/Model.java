package InMemoryMatsim.Model;

import InMemoryMatsim.IMConfigUtils;
import InMemoryMatsim.Model.Specification.Specification;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.config.Config;
import org.matsim.core.network.NetworkChangeEvent;
import org.matsim.core.scenario.MutableScenario;

import java.util.List;

public class Model {
    private Specification specification;
    private Config config = null;
    private Network network = null;
    private List<NetworkChangeEvent> events = null;
    private MutableScenario scenario = null;

    public Model(){};

    public void loadSpecification(String filepath){
    }

    private void loadConfig(Specification specification){
        this.config = IMConfigUtils.createCleanConfigFromFile(specification.config);

    }

}
