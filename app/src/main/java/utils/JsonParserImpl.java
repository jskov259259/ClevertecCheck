package utils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class JsonParserImpl implements JsonParser {

    private StringBuilder builder;

    public JsonParserImpl() {
        builder = new StringBuilder("[ {\n");
    }

    @Override
    public String serialize(Object object) {

        Class objectClass = object.getClass();
        Field[] allFields = objectClass.getDeclaredFields();
        Arrays.stream(allFields).forEach(field -> field.setAccessible(true));
        createJsonString(object).toString();
        builder.deleteCharAt(builder.length() - 1);
        builder.append("\n} ]");
        return builder.toString();
    }

    private StringBuilder createJsonString(Object object) {

        Field[] allFields = object.getClass().getDeclaredFields();
        Arrays.stream(allFields).forEach(field -> field.setAccessible(true));

        Arrays.stream(allFields).forEach(field -> {
            try {
                if (isFieldCustomObject(field)) {
                    builder.append(createJsonObjectString(field.getName(), field.get(object)));
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

        StringBuilder objectBuilder = new StringBuilder("\"" + name + "\": {");
        Arrays.stream(allFields).forEach(field -> {
            try {
                if (isFieldCustomObject(field)) {
                    objectBuilder.append(createJsonObjectString(field.getName(), field));
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

    private boolean isFieldCustomObject(Field field) {

        String packageName = field.getType().getPackageName();
        if (packageName.startsWith("java") || packageName.startsWith("jdk")) return false;
        return true;
    }

}


