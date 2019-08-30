package InMemoryMatsim.Model.Specification;

import org.w3c.dom.Element;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlanParametersParser extends Parser {
    private static final Class parseClass = PlanParameters.class;
    private static final List<Class> parseSubclasses = Arrays.asList(
            Modes.class, Activities.class);
    private static final String[] fieldNames = Parameters.getPrimitiveFieldNames(
            parseClass.getDeclaredFields());

    public static PlanParameters getPlanParameters(Element element){
        HashMap<String, String> params = new HashMap<>();
        for (String field : fieldNames)
            params.put(field, getChild(element, field).getAttribute("value"));;
        PlanParameters planParameters = new PlanParameters(params);
        for (Field field : planParameters.getClass().getDeclaredFields()){
            if ((!field.getType().equals(String.class)) &&
                    (parseSubclasses.contains(field.getType()))){
                try {
                    field.set(planParameters, field.getType().getDeclaredMethod(
                            "parse", Element.class).invoke(null, element));
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                    throw new InstantiationError();
                }
            }
        }
        return planParameters;
    }
}
