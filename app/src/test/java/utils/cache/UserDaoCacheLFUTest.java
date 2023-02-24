package utils.cache;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoCacheLFUTest {

    private UserDaoCacheLFU daoCacheLFU;

    @BeforeEach
    void setUp() {
        daoCacheLFU = new UserDaoCacheLFU(3);
    }

    @Test
    void checkSaveAndGetUserById() {

        User expectedUser = new User(1, "Don");
        daoCacheLFU.saveUser(expectedUser);
        User actualUser = daoCacheLFU.getUserById(expectedUser.getId());
        assertEquals(expectedUser.getName(), actualUser.getName());
    }

    @Test
    void checkGetUserByIdShouldReturnNull() {

        User actualUser = daoCacheLFU.getUserById(2);
        assertNull(actualUser);
    }

    @Test
    void checkUpdateUser() {

        User user = new User(1, "Molly");
        daoCacheLFU.saveUser(user);
        assertEquals(1, daoCacheLFU.size());
        user.setName("Vinni");
        assertEquals(1, daoCacheLFU.size());
        assertEquals("Vinni", daoCacheLFU.getUserById(1).getName());
    }

    @Test
    void checkDeleteUser() {

        User user1 = new User(1, "Curt");
        User user2 = new User(2, "Mike");
        daoCacheLFU.saveUser(user1);
        daoCacheLFU.saveUser(user2);
        assertEquals(2, daoCacheLFU.size());
        daoCacheLFU.deleteUser(user2);
        assertEquals(1, daoCacheLFU.size());
    }

    @Test
    void checkCacheShouldReplaceUserWithLowestFrequently() {

        User user1 = new User(1, "Bob");
        User user2 = new User(2, "Kent");
        User user3 = new User(3, "Sam");
        daoCacheLFU.saveUser(user1);
        daoCacheLFU.saveUser(user2);
        daoCacheLFU.saveUser(user3);
        daoCacheLFU.getUserById(1);
        daoCacheLFU.getUserById(2);

        User user4 = new User(4, "Jack");
        daoCacheLFU.saveUser(user4);
        assertEquals(3, daoCacheLFU.size());
        assertEquals(user1, daoCacheLFU.getUserById(1));
        assertEquals(user2, daoCacheLFU.getUserById(2));
        assertEquals(user4, daoCacheLFU.getUserById(4));
        assertNull(daoCacheLFU.getUserById(3));

        User user5 = new User(5, "Petr");
        daoCacheLFU.saveUser(user5);
        assertEquals(3, daoCacheLFU.size());
        assertEquals(user1, daoCacheLFU.getUserById(1));
        assertEquals(user2, daoCacheLFU.getUserById(2));
        assertEquals(user5, daoCacheLFU.getUserById(5));
        assertNull(daoCacheLFU.getUserById(4));
    }
}