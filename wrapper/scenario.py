from operator import itemgetter
from pprint import pprint

class Scenario:
    def __init__(self, name, config, change_events):
        self.name = name
        self.config = config
        self.change_events = change_events

    def run(self, convert, simulation_jar, networkmod_jar,
            preserve_configs, config_postfix):
        if convert:
            params = dict()
            params['config_input'] = self.config
            # Handling whether we should write to the source config or not
            if (preserve_configs):
                print(vars(self))
                filename, ext = itemgetter(0, 2)(
                    self.config.rpartition('.'))
                print(filename, ext)
                # Doing quick checking for formatting
                if ext != "xml":
                    raise ValueError("Must pass config with `xml` file "
                                     "extension."
                                     f"\nViolated by {self.config}")
                if filename[-1] == '_':
                    raise ValueError("Config files are not permitted to have "
                                     "a trailing `_` direectly preceding the "
                                     "file extension. "
                                     f"\nViolated by {self.config}.")
                # Done to make sure a single `_` is present
                filename = f'{filename}_{config_postfix}.xml'
                self.config = filename
            params['config_output'] = self.config

            # Important Note: We write the new NetworkChangeEvents to the
            # same filename, simply changing the extension from `csv` to `xml`
            params['csv_event_input'] = self.change_events
            fname = itemgetter(0)(self.change_events.rpartition('.'))
            params['xml_event_output'] = f'{fname}.xml'

            args = networkmod_jar.create_args(**params)
            networkmod_jar.create_dynamic_net(args)

        if simulation_jar is not None:
            params = dict()
            params['name'] = self.name
            params['config'] = self.config
            scenario_dir = itemgetter(0)(self.config.rpartition('/'))
            params['output_dir'] = f'{scenario_dir}/output'
            args = simulation_jar.create_args(**params)
            pprint(args)
            simulation_jar.simulate(args)
