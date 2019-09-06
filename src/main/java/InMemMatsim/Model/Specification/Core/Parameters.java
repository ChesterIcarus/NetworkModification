package InMemMatsim.Model.Specification.Core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Parameters {
    public List<Class> subclasses;
    public Parameters() {
        this.subclasses = new ArrayList<>();
    }


    public static void createParams(Object object, Map<String, ?> params) {
        validateInputParams(object, params);
        setFields(object, params);
    }


    public static String[] getPrimitiveFieldNames(Field[] fields) {
        List<String> names = new ArrayList<>();
        for (Field field : fields) {
            if (field.getType() == String.class || field.getType() == boolean.class ||
                    field.getType() == int.class || field.getType() == double.class) {
                names.add(field.getName());
            }
        }
        return names.toArray(new String[0]);
    }

    public static String[] getListFieldNames(Field[] fields) {
        List<String> names = new ArrayList<>();
        for (Field field : fields) {
            if (field.getType() == List.class || field.getType() == ArrayList.class){
                names.add(field.getName());
            }
        }
        return names.toArray(new String[0]);
    }

    public static Field[] getPrimitiveFields(Field[] fields) {
        List<Class> classes = Arrays.asList(String.class,
                boolean.class, int.class, float.class, double.class, long.class);
        return getFieldsByClass(fields, classes);
    }

    public static Field[] getListFields(Field[] fields) {
        List<Class> classes = Arrays.asList(List.class, ArrayList.class);
        return getFieldsByClass(fields, classes);
    }

    public static Field[] getFieldsByClass(Field[] fields, List<Class> classes){
        List<Field> fieldList = new ArrayList<>();
        for (Field field : fields)
            if (classes.contains(field.getType()))
                fieldList.add(field);
        return fieldList.toArray(new Field[0]);
    }

    private static void setFields(Object object, Map<String, ?> params) {
        for (String key : params.keySet()) {
            try {
                Field field = object.getClass().getDeclaredField(key);
                if (params.get(key) != "" && params.get(key) != "null" && params.get(key) != null)
                    if (field.getType() == String.class)
                        field.set(object, params.get(key));
                    else if (field.getType() == int.class)
                        field.set(object, Integer.valueOf((String) params.get(key)));
                    else if (field.getType() == double.class)
                        field.set(object, Double.valueOf((String) params.get(key)));
                    else if (field.getType() == boolean.class)
                        field.set(object, Boolean.valueOf((String) params.get(key)));
                    else
                        field.set(object, params.get(key));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                throw new InstantiationError();
            }
        }
    }

    public static void setMatsimParams(Object matsimParams, Object icarusParams) {
        Method[] methods = matsimParams.getClass().getMethods();
        HashMap<String, Method> setters = new HashMap<>();
        for (Method method : methods) {
            if (method.getName().startsWith("set"))
                setters.put(method.getName().toLowerCase(), method);
        }
        for (Field field : getPrimitiveFields(icarusParams.getClass().getDeclaredFields())) {
            try {
                Method setter = setters.getOrDefault(("set" + field.getName()).toLowerCase(), null);
                if (setter != null)
                    setter.invoke(matsimParams, field.get(icarusParams));
            } catch (IllegalAccessException | InvocationTargetException e) {
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
