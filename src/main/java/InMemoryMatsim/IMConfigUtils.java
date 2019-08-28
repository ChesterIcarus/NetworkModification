package InMemoryMatsim;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.OutputDirectoryHierarchy;

public class IMConfigUtils {
    private IMConfigUtils(){};

    /**
     * This is my current default runner, that doesn't change between simulation executions
     * Note that this is subject to change, and that this might not be right for all scenario types
     * @param config
     * @param filepath
     */
    public static void austinWrapperRunner(Config config, String filepath){
        getCleanConfigFromFile(config, filepath);
        defineTripCharacteristics(config);
        // TODO: See if it's actually okay to remove activity scoring at this point
        ConfigScoringUtils.removeActivityScoring(config);
        setOverwriteFileSetting(config, OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
    }

    public static void getCleanConfigFromFile(Config config, String filepath){
        config = ConfigUtils.loadConfig(filepath);
        config.network().setInputFile("null");
        config.network().setTimeVariantNetwork(true);
        config.network().setChangeEventsInputFile("null");
        config.plans().setInputFile("null");
    }

    public static void defineTripCharacteristics(Config config, String[] modes, String[] activityTypes){
        for (String mode : modes) {
            ConfigScoringUtils.addMode(config, mode);
        }
        for (String type : activityTypes){
            ConfigScoringUtils.addActivityType(config, type);
        }
    }

    public static void defineTripCharacteristics(Config config){
        final String[] modes = {"car", "bike", "pt"};
        final String[] activityTypes = {"w", "h", "s", "work", "home", "shopping"};
        defineTripCharacteristics(config, modes, activityTypes);
    }

    public static void setOverwriteFileSetting(Config config, OutputDirectoryHierarchy.OverwriteFileSetting setting){
        config.controler().setOverwriteFileSetting(setting);
    }
}