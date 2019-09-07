package InMemMatsim.Model;

import InMemMatsim.InMemScenarioUtils;
import InMemMatsim.Model.Specification.PlanParameters.Activities.Activity.Activity;
import InMemMatsim.Model.Specification.PlanParameters.Modes.Mode.Mode;
import InMemMatsim.Model.Specification.Specification;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.config.Config;
import org.matsim.core.controler.Controler;
import org.matsim.core.network.NetworkChangeEvent;
import org.matsim.core.scenario.MutableScenario;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import static InMemMatsim.InMemConfigUtils.*;
import static InMemMatsim.InMemNetworkEvents.*;
import static InMemMatsim.InMemNetworkUtils.*;
import static InMemMatsim.InMemScenarioUtils.loadPopulationFromPlans;
import static InMemMatsim.Model.Specification.SpecificationParser.*;

public class Model {
    public static void main(String[] args){
        String path = "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/modelSpecifications.xml";
        Model model = new Model(path);
        model.simulate();
    }

    private Config config = null;
    private Network network = null;
    private List<NetworkChangeEvent> events = null;
    private MutableScenario scenario = null;

    /**
     * Creates a model from a specification XML file
     * @param filepath - Absolute path to the specification file
     */
    public Model(String filepath)  {
        try {
            loadScenarioFromSpec(loadSpecification(filepath));
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    public void simulate(){
        Controler controler = new Controler(this.scenario);
        controler.run();
    }

    private void loadScenarioFromSpec(Specification specification){
        initConfig(specification);
        initPlanParams(specification);
        initNetwork(specification);
        initNetworkEvents(specification);
        initScenario(specification);
    }

    private void initConfig(Specification specification){
        this.config = createCleanConfig(specification.config);
    }

    private void initPlanParams(Specification specification){
        for (Mode mode : specification.planParameters.modes.modes)
            Mode.toMatsim(config, mode);
        for (Activity activity : specification.planParameters.activities.activities)
            Activity.toMatsim(config, activity);
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
