package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class JsonParserImpl implements JsonParser {

    private StringBuilder builder;

    public JsonParserImpl() {
        builder = new StringBuilder("[{");
    }

    @Override
    public String serialize(Object object) {

        Class objectClass = object.getClass();
        Field[] allFields = objectClass.getDeclaredFields();
        Arrays.stream(allFields).forEach(field -> field.setAccessible(true));
        createJsonString(object).toString();
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}]");
        return builder.toString();
    }

    @Override
    public Object deserialize(String json, Class objectClass) throws Exception {

        Constructor[] constructors = objectClass.getConstructors();
        Object obj = constructors[0].newInstance();
        Arrays.stream(objectClass.getDeclaredFields()).forEach(field -> field.setAccessible(true));

        String[] pairs = json.substring(2, json.length() - 2).split(",");
        Arrays.stream(pairs).forEach(pair -> {
            try {
            String fieldName = pair.split(":")[0];
            String value = pair.split(":")[1];
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

}


