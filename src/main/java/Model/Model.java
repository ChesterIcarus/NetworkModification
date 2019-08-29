package Model;

import org.matsim.api.core.v01.network.Network;
import org.matsim.core.config.Config;
import org.matsim.core.scenario.MutableScenario;

public class Model {
    private Config config = null;
    private Network network = null;
    private MutableScenario scenario = null;

    private String[] modes = null;
    private String[] activities = null;

    public Model(){};

    public String[] getActivities() {
        return activities;
    }

    public void setActivities(String[] activities) {
        this.activities = activities;
    }

    public String[] getModes() {
        return modes;
    }

    public void setModes(String[] modes) {
        this.modes = modes;
    }
}
