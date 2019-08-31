import InMemMatsim.InMemScenarioUtils;
import InMemMatsim.InMemConfigUtils;
import InMemMatsim.InMemNetworkUtils;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.scenario.MutableScenario;

public class CustomRunner {
    private CustomRunner(){};

    /**
     *
     * @param args
     * config
     * network
     * plans
     * events
     */
    public static void main(String[] args){
        args = new String[]{
                // Config File
                "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/data/scenarios/scenario_1a/SWUSFINAL_Config_Scenario1a.xml",
                // Network File
                "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/data/networks/SWUSFULLFINAL_NET.xml",
                // Plans File
                "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/data/plans/SWUS_Plans_FINAL.xml",
                // Events File (CSV)
                "/Users/austinmichne/Research/ChesterIcarus/NetworkModification/data/scenarios/scenario_1a/Disruption Inputs_Scenario1a.csv"
        };

        Config config = ConfigUtils.createConfig();
        InMemConfigUtils.austinWrapperRunner(config, args[0]);

        Network network = NetworkUtils.createNetwork();
        InMemNetworkUtils.loadNetworkWithEvents(config, network, args[1], args[3]);

        MutableScenario scenario = InMemScenarioUtils.createScenario(config);

        scenario.setNetwork(network);
        InMemScenarioUtils.loadPopulationFromPlans(scenario, args[2]);

        Controler controler = new Controler(scenario);
        controler.run();
    }

}
