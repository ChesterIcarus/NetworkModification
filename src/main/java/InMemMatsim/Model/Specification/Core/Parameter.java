package InMemMatsim.Model.Specification.Core;

import org.matsim.core.config.Config;
import org.w3c.dom.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class Parameter<Subclass> {
    private static final List<Class> PRIMITIVE_TYPES = Arrays.asList(
            String.class, Boolean.class,
            Integer.class, Long.class,
            Float.class, Double.class);

    private static final List<Class> LIST_TYPES = Arrays.asList(
            List.class, ArrayList.class, Collection.class);
    protected Class DESCENDANT;

    public Parameter() {
        this.DESCENDANT = null;
    }

    public abstract void toMatsim(Config config, Subclass subclass);


    public static void populate(Object object, Map<String, String> params) {
        validateInputParams(object, params);
        setFields(object, params);
    }

    public static String[] getPrimitiveFieldNames(Field[] fields) {
        return getFieldNamesByClass(fields, PRIMITIVE_TYPES);
    }

    // TODO: Evaluate using this vs subclasses
    public static String[] getListFieldNames(Field[] fields) {
        return getFieldNamesByClass(fields, LIST_TYPES);
    }


    public static String[] getFieldNamesByClass(Field[] fields, List<Class> classes) {
        List<String> names = new ArrayList<>();
        for (Field field : fields)
            if (classes.contains(field.getType()))
                names.add(field.getName());
        return names.toArray(new String[0]);
    }

    public static Field[] getPrimitiveFields(Field[] fields) {
        return getFieldsByClass(fields, PRIMITIVE_TYPES);
    }

    public static Field[] getListFields(Field[] fields) {
        return getFieldsByClass(fields, PRIMITIVE_TYPES);
    }

    public static Field[] getFieldsByClass(Field[] fields, List<Class> classes){
        List<Field> fieldList = new ArrayList<>();
        for (Field field : fields)
            if (classes.contains(field.getType()))
                fieldList.add(field);
        return fieldList.toArray(new Field[0]);
    }

    private static void setFields(Object object, Map<String, String> params) {
        for (String key : params.keySet()) {
            if (params.get(key).equals("") || params.get(key).equals("null") || params.get(key) == null)
                continue;
            try {
                Field field = object.getClass().getDeclaredField(key);
                if (PRIMITIVE_TYPES.contains(field.getType())){
                    field.set(object, field.getType().getDeclaredMethod(
                            "valueOf", String.class).invoke(null, params.get(key)));
                }
            } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
                // TODO: Change this error to exception, and bubble up
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void setMatsimParams(Object matsimParams, Object icarusParams) {
        HashMap<String, Method> setters = new HashMap<>();
        for (Method method : matsimParams.getClass().getMethods())
            if (method.getName().startsWith("set"))
                setters.put(method.getName().toLowerCase(), method);

        for (Field field : getPrimitiveFields(icarusParams.getClass().getDeclaredFields())) {
            try {
                Method setter = setters.getOrDefault(("set" + field.getName()).toLowerCase(), null);
                if (setter != null)
                    setter.invoke(matsimParams, field.get(icarusParams));
            } catch (IllegalAccessException | InvocationTargetException e) {
                // TODO: Change this error to exception, and bubble up
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private static void validateInputParams(Object object, Map<String, ?> params) {
        List<String> fieldNames = Arrays.asList(getPrimitiveFieldNames(
                object.getClass().getDeclaredFields()));

        if (!fieldNames.containsAll(params.keySet()))
            throw new InstantiationError(
                    "Specified key in input parameters doesn't exist in object given.");
    }
}
