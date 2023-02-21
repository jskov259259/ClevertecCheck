package utils;

import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class JsonParserImplTest {

    private JsonParserImpl jsonParser;

    @BeforeEach
    void setUp() {
        jsonParser = new JsonParserImpl();
    }

    @Test
    void checkSerialize() {
        Person person = getTestPerson();
        System.out.println(jsonParser.serialize(person));
    }

    private Person getTestPerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("Bob");
        person.setEmail("bob@mail.ru");
        person.setAge(30);
        return person;
    }
}