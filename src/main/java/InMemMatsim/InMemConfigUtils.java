package InMemMatsim;

import InMemMatsim.Model.Specification.PlanParameters.Activities.Activity.Activity;
import InMemMatsim.Model.Specification.PlanParameters.Modes.Mode.Mode;
import InMemMatsim.Model.Specification.PlanParameters.PlanParameters;
import InMemMatsim.Model.Specification.PlanParameters.PlanParametersParser;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.OutputDirectoryHierarchy;

public class InMemConfigUtils {
    private InMemConfigUtils() {};

    public static Config createCleanConfig(Config config, String filepath, boolean timeVariant) {
        config = ConfigUtils.loadConfig(filepath);
        config.network().setInputFile("null");
        config.network().setTimeVariantNetwork(timeVariant);
        config.network().setChangeEventsInputFile("null");
        config.plans().setInputFile("null");
        return config;
    }

    public static Config createCleanConfig(String filepath) {
        return createCleanConfig(ConfigUtils.createConfig(), filepath, false);
    }

    public static void createCleanConfig(Config config, String filepath) {
        createCleanConfig(config, filepath, false);
    }

    public static void setPlanParams(Config config, PlanParameters planParameters) {
        for (Mode mode : planParameters.modes.modes)
            Mode.addModeToModeParams(config, mode);
        for (Activity activity : planParameters.activities.activities)
            Activity.addActivityToActivityParams(config, activity);
    }

    public static void setOverwriteFileSetting(Config config, OutputDirectoryHierarchy.OverwriteFileSetting setting) {
        config.controler().setOverwriteFileSetting(setting);
    }
}
