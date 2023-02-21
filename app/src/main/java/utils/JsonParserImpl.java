package utils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class JsonParserImpl implements JsonParser {

    @Override
    public String serialize(Object object) {

        Class objectClass = object.getClass();
        Field[] allFields = objectClass.getDeclaredFields();
        Arrays.stream(allFields).forEach(field -> field.setAccessible(true));
        return createJsonString(object, allFields).toString();
    }

    private StringBuilder createJsonString(Object object, Field[] allFields) {

        StringBuilder builder = new StringBuilder("[ {");

        Arrays.stream(allFields).forEach(field -> {
            try {
                builder.append("\"" + field.getName() + "\":\"" + field.get(object) + "\",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        builder.deleteCharAt(builder.length() - 1);
        builder.append("} ]");
        return builder;
    }

}
