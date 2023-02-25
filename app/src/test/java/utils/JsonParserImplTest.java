package utils;

import model.Card;
import model.Person;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void checkDeserialize() throws Exception {

        String json = "[{id:1,name:Bob,email:bob@mail.ru,age:30}]";
        Person person = (Person) jsonParser.deserialize(json, Person.class);
        System.out.println(person);
        assertEquals(1, person.getId());
        assertEquals("Bob", person.getName());
        assertEquals("bob@mail.ru", person.getEmail());
        assertEquals(30, person.getAge());
    }

    private Person getTestPerson() {

        Person person = new Person();
        person.setId(1);
        person.setName("Bob");
        person.setEmail("bob@mail.ru");
        person.setAge(30);

        Card card = new Card();
        card.setId(1);
        card.setDescription("card-1");
        person.setCard(card);
        return person;
    }
}