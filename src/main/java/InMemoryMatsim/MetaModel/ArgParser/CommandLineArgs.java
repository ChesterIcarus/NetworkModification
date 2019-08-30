package InMemoryMatsim.MetaModel.ArgParser;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class CommandLineArgs {
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = {"--config-input", "-CONIN"},
            description = "Path to input configuration file",
            required = false)
    public String CONFIGINPUT = null;

    @Parameter(names = {"--config-output", "-CONOUT"},
            description = "Path to output configuration file",
            required = true)
    public String CONFIGOUTPUT = null;

    @Parameter(names = {"--network", "-NET"},
            description = "Path to network input file",
            required = false)
    public String NETWORK = null;

    @Parameter(names = {"--global-threads", "-GT"},
            description = "Number of global threads to assign",
            required = false)
    public String GLOBALTHREADS = "70";

    @Parameter(names = {"--qsim-threads", "-QT"},
            description = "Number of Qsim threads to assign",
            required = false)
    public String QSIMTHREADS = "60";

    @Parameter(names = {"--csv-event-input", "-CSV"},
            description = "Path to CSV file with the unformatted change events to be parsed",
            required = true)
    public String CSVEVENTINPUT = null;

    @Parameter(names = {"--xml-event-output", "-XML"},
            description = "Path to XML file to write the parsed, " +
                    "and formatted ChangeEvents for simulation on the network",
            required = true)
    public String XMLEVENTOUTPUT = null;
}
