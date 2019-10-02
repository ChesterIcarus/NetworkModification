package InMemMatsim.Model;

import InMemMatsim.InMemScenarioUtils;
import InMemMatsim.Model.Specification.Specification;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.config.Config;
import org.matsim.core.controler.Controler;
import org.matsim.core.network.NetworkChangeEvent;
import org.matsim.core.scenario.MutableScenario;

import java.util.List;


import static InMemMatsim.InMemNetworkUtils.*;

public class Model {
    public static void main(String[] args){
//        String path = "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/modelSpecifications.xml";
//        Model model = new Model(path);
//        model.simulate();
    }

    private Config config;
    private Network network;
    private List<NetworkChangeEvent> events;
    private MutableScenario scenario;

    /**
     * Creates a model from a specification XML file
     * @param specification - Specification object to create a scenario from
     */
    public Model(Specification specification)  {

    }


    public void simulate(){
        Controler controler = new Controler(this.scenario);
        controler.run();
    }

    private void loadScenarioFromSpec(Specification specification){
//        initConfig(specification);

        initNetwork(specification);
//        initNetworkEvents(specification);
        initScenario(specification);
    }



    private void initScenario(Specification specification){
        this.scenario = InMemScenarioUtils.createScenario(this.config);
        this.scenario.setNetwork(this.network);
//        loadPopulationFromPlans(this.scenario, specification.planPath);
    }

    private void initNetwork(Specification specification){
//        network = loadNetwork(config, network, specification.);
    }

    private void loadNetworkEvents(Specification specification){
        if (this.network == null)
            throw new NullPointerException("Unable to load Network events for an undefined Network.");
//        this.events = (List<NetworkChangeEvent>) readNetworkChangeEvents(this.network, specification.eventPath);
    }

    public Config getConfig(){
        return config;
    }
}
