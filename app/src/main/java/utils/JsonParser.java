package utils;

public interface JsonParser {

    String serialize(Object object);

    Object deserialize(String json, Class objectClass) throws Exception;
}
