package InMemMatsim.Model.Specification.Core;

import org.w3c.dom.Element;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class MetaParser {
    public static void parseDescendant(Parameters param, Element element){
        String[] fieldNames = Parameters.getPrimitiveFieldNames(param.getClass().getDeclaredFields());
        for (Field field : param.getClass().getDeclaredFields())
            if (!Arrays.asList(fieldNames).contains(field.getName()))
                if (param.subclasses.contains(field.getType())) {
                    try {
                        field.set(param, field.getType().getDeclaredMethod("parse", Element.class).invoke(null, element));
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
    }
}
