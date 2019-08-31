package InMemMatsim.Model.Specification.Setup;

import InMemMatsim.Model.Specification.Core.Parser;

public class SetupParser extends Parser {
    public static Setup getSetup(){
        return new Setup();
    }
}
