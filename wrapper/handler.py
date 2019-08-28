from sys import argv
from arguments import Arguments
from metascenario import MetaScenario

if __name__ == '__main__':
    cli_args = Arguments.parse(argv[1::], silent=False)
    meta = MetaScenario()
    meta.build_meta(**cli_args)
    meta.run_scenarios()
