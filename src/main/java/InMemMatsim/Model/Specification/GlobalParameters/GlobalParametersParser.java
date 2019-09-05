package InMemMatsim.Model.Specification.GlobalParameters;

import InMemMatsim.Model.Specification.Core.MetaParser;
import InMemMatsim.Model.Specification.Core.Parameters;
import InMemMatsim.Model.Specification.Core.Parser;
import InMemMatsim.Model.Specification.GlobalParameters.Threads.Threads;
import InMemMatsim.Model.Specification.PlanParameters.Activities.Activities;
import InMemMatsim.Model.Specification.PlanParameters.Modes.Modes;
import InMemMatsim.Model.Specification.PlanParameters.PlanParameters;
import org.w3c.dom.Element;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GlobalParametersParser extends Parser {
    private static final Class parseClass = GlobalParameters.class;
//    private static final List<Class> parseSubclasses = Arrays.asList(
//            Threads.class);
//    private static final String[] fieldNames = Parameters.getPrimitiveFieldNames(
//            parseClass.getDeclaredFields());

    public static GlobalParameters getGlobalParameters(Element element){
        HashMap<String, String> params = new HashMap<>();
        for (String field : Parameters.getPrimitiveFieldNames(parseClass.getDeclaredFields()))
            params.put(field, getChild(element, field).getAttribute("value"));;
        GlobalParameters globalParams = new GlobalParameters(params);
        MetaParser.parseDescendant(globalParams, element);
        return globalParams;
//        for (String field : fieldNames)
//            params.put(field, getChild(element, field).getAttribute("value"));;
//
//        for (Field field : globalParams.getClass().getDeclaredFields())
//            if (!Arrays.asList(fieldNames).contains(field.getName()))
//                if (parseSubclasses.contains(field.getType())) try {
//                    field.set(globalParams, field.getType().getDeclaredMethod(
//                            "parse", Element.class).invoke(null, element));
//                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                    e.printStackTrace();
//                    throw new InstantiationError(); }
//        return globalParams;
    }


}
