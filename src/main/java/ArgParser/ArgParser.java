package ArgParser;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;


public class ArgParser {
    private Config config;
    private String configOutput;
    private String csvEventInput;

    public Config getConfig() { return this.config; }
    public String getCsvEventInput(){ return this.csvEventInput; }
    public String getConfigOutput() { return this.configOutput; }

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

        if (commandLineArgs.GLOBALTHREADS != null)
            this.config.global().setNumberOfThreads(Integer.valueOf(commandLineArgs.GLOBALTHREADS));

        if (commandLineArgs.QSIMTHREADS != null)
            this.config.qsim().setNumberOfThreads(Integer.valueOf(commandLineArgs.QSIMTHREADS));


        this.configOutput = commandLineArgs.CONFIGOUTPUT;
        this.csvEventInput = commandLineArgs.CSVEVENTINPUT;

        this.config.network().setInputFile(commandLineArgs.NETWORK);
        this.config.network().setChangeEventsInputFile(commandLineArgs.XMLEVENTOUTPUT);
        this.config.network().setTimeVariantNetwork(true);
    }
}
