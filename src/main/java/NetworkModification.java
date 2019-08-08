import ArgParser.ArgParser;
import ArgParser.CommandLineArgs;
import ChangeEventParser.ChangeEventHandler;
import com.beust.jcommander.JCommander;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.NetworkChangeEvent;

import java.util.Collection;

public class NetworkModification {
    public static void main(String[] args) {
        ArgParser argParser = handleArgs(args);
        Config config = argParser.getConfig();
        createTimeVariantNetwork(config, argParser.getCsvEventInput());
        writeConfig(config, argParser.getConfigOutput());
    }

    private static ArgParser handleArgs(String[] args){
        CommandLineArgs commandLineArgs = new CommandLineArgs();
        JCommander.newBuilder().addObject(commandLineArgs).build().parse(args);
        return (new ArgParser(commandLineArgs));
    }

    /**
     * @param config
     * @param string - Path to valid CSV file to create MATsim NetworkChangeEvent's from
     */
    private static void createTimeVariantNetwork(Config config, String string) {
        Collection<NetworkChangeEvent> events = ChangeEventHandler.parseInputCsv(config, string);
        ChangeEventHandler.writeChangeEvents(config, events);
    }

    /**
     * @param config
     * @param string - Path to write the new TimeVariantConfig to
     */
    private static void writeConfig(Config config, String string){
//        ConfigUtils.writeMinimalConfig(ConfigCleaner.clean(config), string);
        ConfigCleaner.clean(config);
        ConfigUtils.writeConfig(config, string);
    }
}
