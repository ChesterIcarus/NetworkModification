from collections import namedtuple
from pprint import pprint
import argparse

class Arguments:

    def __init__(self):
        pass

    @staticmethod
    def parse(args, silent=True):
        parsed = vars(Arguments.default_parser().parse_args(args))
        Arguments.validate(parsed)
        # Don't know that we want to force autobuild here
        # TODO: Think about this further
        # MetaScenario.build_meta(**parsed)
        if not silent:
            pprint(parsed)
        return parsed

    @staticmethod
    def default_parser():
        parser = argparse.ArgumentParser()
        parser.add_argument('--path', type=str,
                            help='Path to parent directory, containing the'
                            'subdirectories for all scenarios to be ran')

        parser.add_argument('--plan', type=str,
                            help='Path to plan to simulate for given scenarios')

        parser.add_argument('--network', type=str,
                            help='Path to network to use for given scenarios')

        parser.add_argument('--simulation-jar', type=str,
                            help='Path to jar used to simulate. Must follow'
                            'ChesterLab/Icarus flag input convention')

        parser.add_argument('--simulation-jar-args', type=str,
                            help='Arguments to pass when the simulation jar'
                            'is called from the shell.')

        parser.add_argument('--networkmod-jar', type=str,
                            help='Path to jar used to create ChangeEvents. '
                            'Must follow ChesterLab/NetworkModification '
                            'flag input convention')

        parser.add_argument('--networkmod-jar-args', type=str,
                            help='Arguments to pass when the network '
                            'modification jar is called from the shell.')

        parser.add_argument('--convert-events', dest='convert_events',
                            action='store_true')
        parser.add_argument('--no-convert-events', dest='convert_events',
                            action='store_false')
        parser.set_defaults(convert_events=False)

        parser.add_argument('--java', type=str, default='java',
                            help='Allows you to specify the path or alias'
                            'used to call the JAR files. Default value '
                            'is `java`')

        parser.add_argument('--shell', dest='shell', action='store_true')
        parser.add_argument('--no-shell', dest='shell', action='store_false')
        parser.set_defaults(shell=True)

        parser.add_argument('--preserve-configs', dest='preserve_configs',
                            action='store_true',
                            help='Boolean flag informing original configs '
                            'should be preserved. This is done by postfixing '
                            'the orignal config name without `.xml` with '
                            'the value of `--config-postfix`')

        parser.add_argument('--no-preserve-configs', dest='preserve_configs',
                            action='store_false')
        parser.set_defaults(preserve_configs=True)

        parser.add_argument('--config-postfix', type=str, default='timeVariant',
                            help='Allows you to specify the config file '
                            'postfix used if `--preserve-configs` is passed. '
                            'Default value is `timeVariant`. \n'
                            'Note: The postfix will be joined with the '
                            'orignal string using a `_` character.')
        return parser

    @staticmethod
    def validate(args):
        Validator = namedtuple('Validator', list(args))
        valid = Validator(**args)
        print(valid)

        if valid.path is None:
            raise ValueError("Must pass path to a directory containing "
                             "a subdirectory for each simulation via the "
                             "--path flag.")

        if valid.simulation_jar is None:
            if valid.networkmod_jar is None:
                raise ValueError("Must have at least one jar input.")
            if valid.simulation_jar_args is not None:
                raise ValueError("Must pass simulation jar if passing "
                                 "simulation jar arguements.")

        if valid.networkmod_jar is None:
            if valid.networkmod_jar_args is not None:
                raise ValueError("Must pass network modification jar if "
                                 "passing network modification jar "
                                 "arguements.")


        if valid.networkmod_jar is not None and valid.convert_events is False:
            raise ValueError("Must have events to convert if passing "
                             "network modification JAR currently (future "
                             "patch will remove this)")



