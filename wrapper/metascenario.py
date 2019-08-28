from os import walk
from os.path import join

from scenario import Scenario
from jar import Jar, SimulationJar, NetworkModJar

class MetaScenario:
    path = None
    plan = None
    network = None
    convert_events = None
    preserve_configs = None
    config_postfix = None

    simulation_jar = None
    networkmod_jar = None

    @classmethod
    def build_meta(cls,
                   path=None, network=None, plan=None, convert_events=None,
                   simulation_jar=None, simulation_jar_args=None,
                   networkmod_jar=None, networkmod_jar_args=None,
                   preserve_configs=None, config_postfix=None,
                   java=None, shell=None):
        cls.path = path
        cls.convert_events = convert_events
        Jar.java = java
        Jar.shell = shell
        if simulation_jar is not None:
            cls.simulation_jar = SimulationJar(simulation_jar,
                                               simulation_jar_args.split(' '))
        if networkmod_jar is not None:
            cls.networkmod_jar = NetworkModJar(networkmod_jar,
                                               networkmod_jar_args.split(' '))
        cls.preserve_configs = preserve_configs
        cls.config_postfix = config_postfix

    @classmethod
    def run_scenarios(cls):
        if None in [cls.path, cls.convert_events]:
            print([cls.path, cls.convert_events])
            raise ValueError("Must set directory, plan, network, and event " +
                             "conversion boolean before attempting to run " +
                             "scenarios.")
        # We chop off the superdirectory that should hold the subdir's for
        # each scenario
        scenarios = cls.create_scenarios()
        for scenario in scenarios:
            scenario.run(cls.convert_events,
                         cls.simulation_jar, cls.networkmod_jar,
                         cls.preserve_configs, cls.config_postfix)

    @classmethod
    def create_scenarios(cls):
        """
        Called to create a list of scenarios based off of a source directory
        """
        scenarios = list()
        cwd, subdirectories = list(walk(cls.path))[0][0:2]

        for subdir in subdirectories:
            scenarios.append(cls.create_scenario(cwd, subdir))
        return scenarios

    @classmethod
    def create_scenario(cls, cwd, subdir):
        """
        cwd: String of the absolute path of the metascenario directory
        subdir: String of subdirectory name for a given scenario
        Used to create a single instance of a scenario for use in processing
            the set of scenarios to simulate """
        config, events = cls.parse_subdir(next(walk(join(cwd, subdir))))
        return Scenario(subdir, config, events)

    @classmethod
    def parse_subdir(cls, dir_tuple):
        """
        dir_tuple: Tuple containing (given directory absolute path,
                                    sub directories in given directory,
                                    non-directory files in given directory)
        """
        config = None
        events = None

        # Different options based on proposed events file extension
        if cls.convert_events:
            events_fe = '.csv'
        else:
            events_fe = '.xml'

        # We search through  dir_tuple[2] because it holds filenames
        for filename in dir_tuple[2]:
            low_fname = filename.lower()
            # Gives the full path for the config file
            if 'config' in low_fname and '.xml' in low_fname \
                    and cls.config_postfix.lower() not in low_fname:
                if config is None:
                    config = join(dir_tuple[0], filename)
                else:
                    raise ValueError("Subdirectory contains multiple valid" +
                                     "configs. Select one and remove the rest")
            if events_fe in low_fname:
                if (events_fe is '.xml'):
                    if ('config' not in low_fname):
                        events = join(dir_tuple[0], filename)
                if (events_fe is '.csv'):
                    events = join(dir_tuple[0], filename)
        if config is None or events is None:
            raise ValueError("Make sure there is one config file with both" +
                             "\'config\' in it that is XML, and a events " +
                             "file without \'config\' in it either in " +
                             "csv or xml, with the proper conversion "+
                             "setting for events passed as arguments")
        return config, events
