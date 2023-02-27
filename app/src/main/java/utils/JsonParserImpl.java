package utils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.IntStream;

public class JsonParserImpl implements JsonParser {

    @Override
    public String serialize(Object object) {

        Class objectClass = object.getClass();
        Field[] allFields = objectClass.getDeclaredFields();
        Arrays.stream(allFields).forEach(field -> field.setAccessible(true));
        StringBuilder builder = new StringBuilder();
        builder = createJsonString(object, builder);
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");
        return builder.toString();
    }

    @Override
    public Object deserialize(String json, Class objectClass) throws Exception {

        Constructor[] constructors = objectClass.getConstructors();
        Object obj = constructors[0].newInstance();
        Arrays.stream(objectClass.getDeclaredFields()).forEach(field -> field.setAccessible(true));

        String[] pairs = json.substring(1, json.length() - 1).split(",");
        Arrays.stream(pairs).forEach(pair -> {
            try {
            String fieldName = pair.split(":")[0].substring(1, pair.split(":")[0].length() - 1);;
            String value = pair.split(":")[1].substring(1, pair.split(":")[1].length() - 1);
            Field field = objectClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            if (isNumeric(value)) {
                field.set(obj, Integer.parseInt(value));
            } else
                field.set(obj, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return obj;
    }

    private StringBuilder createJsonString(Object object, StringBuilder builder) {

        builder.append("{");
        Field[] allFields = object.getClass().getDeclaredFields();
        Arrays.stream(allFields).forEach(field -> field.setAccessible(true));

        Arrays.stream(allFields).forEach(field -> {
            try {
                if (isFieldCustomObject(field)) {
                    builder.append(createJsonObjectString(field.getName(), field.get(object)));
                } else
                    if (isFieldArray(field)) {
                         builder.append(appendArrayElements(object, field) + ",");
                    } else
                        if (isNumeric(field.get(object).toString())) {
                            builder.append("\"" + field.getName() + "\":" + field.get(object) + ",");
                        } else
                builder.append("\"" + field.getName() + "\":\"" + field.get(object) + "\",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return builder;
    }

    private StringBuilder createJsonObjectString(String name, Object object) {

        Field[] allFields = object.getClass().getDeclaredFields();
        Arrays.stream(allFields).forEach(field -> field.setAccessible(true));

        StringBuilder objectBuilder = new StringBuilder("\"" + name + "\":{");
        Arrays.stream(allFields).forEach(field -> {
            try {
                if (isFieldCustomObject(field)) {
                    objectBuilder.append(createJsonObjectString(field.getName(), field));
                } else
                if (isFieldArray(field)) {
                    objectBuilder.append(appendArrayElements(object, field) + ",");
                } else
                if (isNumeric(field.get(object).toString())) {
                    objectBuilder.append("\"" + field.getName() + "\":" + field.get(object) + ",");
                } else
                    objectBuilder.append("\"" + field.getName() + "\":\"" + field.get(object) + "\",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        objectBuilder.deleteCharAt(objectBuilder.length() - 1);
        objectBuilder.append("} ");
        return objectBuilder;
    }

    private boolean isNumeric(String strNum) {

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("-?\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    private boolean isFieldCustomObject(Field field) {

        String packageName = field.getType().getPackageName();
        if (packageName.startsWith("java") || packageName.startsWith("jdk")) return false;
        return true;
    }

    private boolean isFieldArray(Field field) {
        return field.getType().isArray();
    }

    private StringBuilder appendArrayElements(Object object, Field field) throws IllegalAccessException {

        StringBuilder arrayBuilder = new StringBuilder("\"" + field.getName() + "\":[");
        IntStream.rangeClosed(0,  Array.getLength(field.get(object)) - 1)
                .forEach(index -> {
                    try {
                        arrayBuilder.append("\"" + Array.get(field.get(object), index)
                                + "\",");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        arrayBuilder.deleteCharAt(arrayBuilder.length() - 1);
        arrayBuilder.append("]");
        return arrayBuilder;
    }

}


