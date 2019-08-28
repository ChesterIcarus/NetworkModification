package MetaModel.ArgParser;

import org.matsim.api.core.v01.network.Network;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.utils.geometry.transformations.GeotoolsTransformation;
import org.matsim.core.utils.io.OsmNetworkReader;


public class ArgParser {
    private Config config;
    private String configOutput;
    private String csvEventInput;
    private String xmlEventOutput;

    public Config getConfig() { return this.config; }
    public String getCsvEventInput(){ return this.csvEventInput; }
    public String getConfigOutput() { return this.configOutput; }
    public String getXmlEventOutput(){ return this.xmlEventOutput; }

    /**
     * Parsing the command line arguments correctly.
     * Note: The order of processing arguments DOES matter. Changes w/o
     * consideration to those may result in unintended consequences.
     * @param commandLineArgs
     */
    public ArgParser(CommandLineArgs commandLineArgs){
        if (commandLineArgs.CONFIGINPUT != null)
            this.config = ConfigUtils.loadConfig(commandLineArgs.CONFIGINPUT);
        else
            this.config = ConfigUtils.createConfig();
        Network network = NetworkUtils.createNetwork();
        OsmNetworkReader osmNetworkReader = new OsmNetworkReader(network, new GeotoolsTransformation("WGS84", this.config.global().getCoordinateSystem()));


        if (commandLineArgs.GLOBALTHREADS != null)
            this.config.global().setNumberOfThreads(Integer.parseInt(commandLineArgs.GLOBALTHREADS));

        if (commandLineArgs.QSIMTHREADS != null)
            this.config.qsim().setNumberOfThreads(Integer.parseInt(commandLineArgs.QSIMTHREADS));


        this.configOutput = commandLineArgs.CONFIGOUTPUT;
        this.csvEventInput = commandLineArgs.CSVEVENTINPUT;
        this.xmlEventOutput = commandLineArgs.XMLEVENTOUTPUT;


        if (commandLineArgs.NETWORK != null)
            this.config.network().setInputFile(commandLineArgs.NETWORK);

        this.config.network().setChangeEventsInputFile(
                commandLineArgs.XMLEVENTOUTPUT.split("/")[
                        commandLineArgs.XMLEVENTOUTPUT.split("/").length - 1]);
        this.config.network().setTimeVariantNetwork(true);
    }
}
