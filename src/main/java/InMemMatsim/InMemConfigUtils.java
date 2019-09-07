package InMemMatsim;

import InMemMatsim.Model.Specification.GlobalParameters.GlobalParameters;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;

public class InMemConfigUtils {
    private InMemConfigUtils() { }


    public static Config createCleanConfig(Config config, String filepath) {
        config = ConfigUtils.loadConfig(filepath);
        config.network().setTimeVariantNetwork(true);
        config.network().setInputFile(null);
        config.network().setChangeEventsInputFile(null);
        config.plans().setInputFile(null);
        return config;
    }

    public static Config createCleanConfig(String filepath) {
        return createCleanConfig(ConfigUtils.createConfig(), filepath);
    }
}
