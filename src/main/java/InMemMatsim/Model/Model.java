package InMemMatsim.Model;

import InMemMatsim.InMemScenarioUtils;
import InMemMatsim.Model.Specification.Specification;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.config.Config;
import org.matsim.core.controler.Controler;
import org.matsim.core.network.NetworkChangeEvent;
import org.matsim.core.scenario.MutableScenario;

import java.util.List;

import static InMemMatsim.InMemConfigUtils.*;
import static InMemMatsim.InMemNetworkEvents.*;
import static InMemMatsim.InMemNetworkUtils.*;
import static InMemMatsim.InMemScenarioUtils.loadPopulationFromPlans;
import static InMemMatsim.Model.Specification.SpecificationParser.*;

public class Model {
    public static void main(String[] args){
        Model model = new Model();
        String path = "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/modelSpecifications.xml";
        model.createScenario(path);
        model.simulateModel();
    }

    private Config config = null;
    private Network network = null;
    private List<NetworkChangeEvent> events = null;
    private MutableScenario scenario = null;

    public Model(){}

    public void createScenario(String filepath){
        loadScenarioFromSpec(loadSpecification(filepath));
    }

    public void simulateModel(){
        Controler controler = new Controler(this.scenario);
        controler.run();
    }

    private void loadScenarioFromSpec(Specification specification){
        initConfig(specification);
        initGlobalParams(specification);
        initPlanParams(specification);
        initNetwork(specification);
        initNetworkEvents(specification);
        initScenario(specification);
    }

    private void initConfig(Specification specification){
        this.config = createCleanConfig(specification.config);
    }

    private void initGlobalParams(Specification specification){
        setGlobal(this.config, specification.globalParameters);
    }

    private void initPlanParams(Specification specification){
        setPlanParams(this.config, specification.planParameters);
    }


    private void initNetwork(Specification specification){
        this.network = loadNetwork(config, network, specification.network);
    }

    private void initNetworkEvents(Specification specification){
        if (this.network == null)
            throw new NullPointerException("Unable to load Network events for an undefined Network.");
        this.events = (List<NetworkChangeEvent>) readNetworkChangeEvents(this.network, specification.events);
    }

    private void initScenario(Specification specification){
        this.scenario = InMemScenarioUtils.createScenario(this.config);
        this.scenario.setNetwork(this.network);
        loadPopulationFromPlans(this.scenario, specification.plans);
    }

}
