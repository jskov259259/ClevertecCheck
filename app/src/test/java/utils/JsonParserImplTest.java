package utils;

import com.google.gson.Gson;
import model.Card;
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
        Gson gson = new Gson();
        System.out.println(gson.toJson(person));
        assertEquals(gson.toJson(person), jsonParser.serialize(person) );
    }

    @Test
    void checkDeserialize() throws Exception {

        String json = "{\"id\":\"1\",\"name\":\"Bob\",\"email\":\"bob@mail.ru\",\"age\":\"30\"}";
        Person person = (Person) jsonParser.deserialize(json, Person.class);
        assertEquals(1, person.getId());
        assertEquals("Bob", person.getName());
        assertEquals("bob@mail.ru", person.getEmail());
        assertEquals(30, person.getAge());

        Person gsonPerson = new Gson().fromJson(json, Person.class);
        assertEquals(gsonPerson, person);
    }

    private Person getTestPerson() {

        Person person = new Person();
        person.setId(1);
        person.setName("Bob");
        person.setEmail("bob@mail.ru");
        person.setAge(30);

        String[] animals = {"cat", "dog", "fish", "pig"};
        person.setAnimals(animals);

        Card card = new Card();
        card.setId(1);
        card.setDescription("card-1");
        card.setDiscount(10);
        person.setCard(card);
        return person;
    }
}