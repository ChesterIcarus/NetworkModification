package InMemMatsim;

import InMemMatsim.Model.Specification.PlanParameters.Activities.Activity.Activity;
import InMemMatsim.Model.Specification.PlanParameters.Modes.Mode.Mode;
import InMemMatsim.Model.Specification.PlanParameters.PlanParameters;
import InMemMatsim.Model.Specification.GlobalParameters.GlobalParameters;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;

public class InMemConfigUtils {
    private InMemConfigUtils() { }


    public static Config createCleanConfig(Config config, String filepath) {
        config = ConfigUtils.loadConfig(filepath);
        config.network().setTimeVariantNetwork(true);
        config.network().setInputFile(null);
        config.network().setChangeEventsInputFile(null);
        config.plans().setInputFile(null);
        setOutputParams(config, OverwriteFileSetting.deleteDirectoryIfExists);
        return config;
    }

    public static Config createCleanConfig(String filepath) {
        return createCleanConfig(ConfigUtils.createConfig(), filepath);
    }

    public static void setPlanParams(Config config, PlanParameters planParameters) {
        for (Mode mode : planParameters.modes.modes)
            Mode.addModeToModeParams(config, mode);
        for (Activity activity : planParameters.activities.activities)
            Activity.addActivityToActivityParams(config, activity);
    }

    @Deprecated
    public static void setGlobal(Config config, GlobalParameters globalParameters){
        // TODO: Fix the Threads class - it shouldn't even exist
        // TODO: Create a network class as well
        config.global().setNumberOfThreads(6);
        config.qsim().setNumberOfThreads(8);
    }

    @Deprecated
    public static void setOutputParams(Config config, OverwriteFileSetting setting) {
        // TODO: This is a hhuge mess
        config.controler().setOverwriteFileSetting(setting);
        String outDir = config.controler().getOutputDirectory().substring(2);
        config.controler().setOutputDirectory("./output/" + outDir);
    }
}
