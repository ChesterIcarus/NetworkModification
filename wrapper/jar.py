from pprint import pprint

import subprocess

class Jar:
    java = None
    shell = None

    def __init__(self, path, args):
        '''
        path: Path to the jar file
        args: Arguments used every invocation of jar (eg. ["-Xmx12g", "-jar"])
        '''
        self.path = path
        if args[-1] != '-jar':
            raise ValueError("Last arguement passed to args for any "
                             "`Jar` must be equal to \'-jar\'.\n"
                             f"The passed args: {args} violates this.")
        self.args = args


class SimulationJar(Jar):
    def __init__(self, path, args):
        super().__init__(path, args)

    @staticmethod
    def create_args(name=None, config=None, output_dir=None):
        params = locals()
        for var in params:
            if not isinstance(params[var], str):
                raise ValueError(f"{var} must be an instance of `str`.\n"
                                 f"Currently {var} = {params[var]}")

        sh_str = [
            '-config', config,
            '-outputdir', output_dir,
            '-runid', name
        ]
        return tuple(sh_str)

    def simulate(self, args):
        cmd = [Jar.java, *self.args, self.path, *args]
        pprint(cmd)
        if Jar.shell:
            cmd = subprocess.list2cmdline(cmd)

        # We run with check=True to catch errors
        completed = subprocess.run(cmd,
                                   shell=Jar.shell,
                                   check=True)
                                   # stdout=subprocess.DEVNULL,
                                   # stdin=subprocess.DEVNULL)
        completed.check_returncode()

class NetworkModJar(Jar):
    def __init__(self, path, args):
        super().__init__(path, args)

    @staticmethod
    def create_args(config_input=None, config_output=None,
                    xml_event_output=None, csv_event_input=None):
        ''' Currently not handling writing to dynamic config_out/xml_events
            though there will be support in the future.  '''
        params = locals()
        for var in params:
            if not isinstance(params[var], str):
                raise ValueError(f"{var} must be an instance of `str`.\n"
                                 f"Currently {var} = {params[var]}")

        sh_str = [
            '--config-input', config_input,
            '--csv-event-input', csv_event_input,
            '--config-output', config_output,
            '--xml-event-output', xml_event_output
        ]
        return tuple(sh_str)

    def create_dynamic_net(self, args):
        cmd = [Jar.java, *self.args, self.path, *args]
        pprint(cmd)
        if Jar.shell:
            cmd = subprocess.list2cmdline(cmd)

        # We run with check=True to catch errors
        completed = subprocess.run(cmd,
                                   shell=Jar.shell,
                                   check=True)
                                   # stdout=subprocess.DEVNULL,
                                   # stdin=subprocess.DEVNULL)
        completed.check_returncode()
