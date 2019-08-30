package InMemoryMatsim.Model.Specification;


import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

public class Parameters {
    public Parameters(){};

    public static void createParams(Object object, Map<String, ?> params){
        validateInputParams(object, params);
        setFields(object, params);
    }

    public static Field[] getFieldsOfType(Field[] fields, Type type){
        List<Field> typeFields = new ArrayList<>();
        for (Field field : fields)
            if (field.getType() == type)
                typeFields.add(field);
        return typeFields.toArray(new Field[0]);
    }


    public static String[] getPrimitiveFieldNames(Field[] fields){
        List<String> names = new ArrayList<>();
        for (Field field : fields){
            if (field.getType() == String.class || field.getType() == boolean.class ||
                field.getType() == int.class || field.getType() == float.class){
                names.add(field.getName());
            }
        }
        return names.toArray(new String[0]);
    }

    private static void setFields(Object object, Map<String, ?> params){
        for (String key : params.keySet()){
            try {
                Field field = object.getClass().getDeclaredField(key);
                if (params.get(key) == "" || params.get(key) == "null" || params.get(key) == null)
                    continue;
                else if (field.getType() == String.class)
                    field.set(object, params.get(key));
                else if (field.getType() == int.class)
                    field.set(object, Integer.valueOf((String) params.get(key)));
                else if (field.getType() == float.class)
                    field.set(object, Float.valueOf((String) params.get(key)));
                else if (field.getType() == boolean.class)
                    field.set(object, Boolean.valueOf((String) params.get(key)));
                else
                    field.set(object, params.get(key));
            }
            catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                throw new InstantiationError();
            }
        }
    }

    private static void validateInputParams(Object object, Map<String, ?> params){
        List<String> fieldNames = Arrays.asList(getPrimitiveFieldNames(
                object.getClass().getDeclaredFields()));

        if (!fieldNames.containsAll(params.keySet()))
            throw new InstantiationError(
                    "Specified key in input parameters doesn't exist in object given.");
    }
}
