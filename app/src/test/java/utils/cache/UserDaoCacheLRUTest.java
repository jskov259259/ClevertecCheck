package utils.cache;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDaoCacheLRUTest {

    private UserDaoCacheLRU daoCacheLRU;

    @BeforeEach
    void setUp() {
        daoCacheLRU = new UserDaoCacheLRU(3);
    }

    @Test
    void checkSaveAndGetUserById() {

        User expectedUser = new User(1, "Don");
        daoCacheLRU.saveUser(expectedUser);
        User actualUser = daoCacheLRU.getUserById(expectedUser.getId());
        assertEquals(expectedUser.getName(), actualUser.getName());
    }

    @Test
    void checkGetUserByIdShouldReturnNull() {

        User actualUser = daoCacheLRU.getUserById(2);
        assertNull(actualUser);
    }

    @Test
    void checkUpdateUser() {

        User user = new User(1, "Molly");
        daoCacheLRU.saveUser(user);
        assertEquals(1, daoCacheLRU.size());
        user.setName("Vinni");
        assertEquals(1, daoCacheLRU.size());
        assertEquals("Vinni", daoCacheLRU.getUserById(1).getName());
    }

    @Test
    void checkDeleteUser() {

        User user1 = new User(1, "Curt");
        User user2 = new User(2, "Mike");
        daoCacheLRU.saveUser(user1);
        daoCacheLRU.saveUser(user2);
        assertEquals(2, daoCacheLRU.size());
        daoCacheLRU.deleteUser(user2);
        assertEquals(1, daoCacheLRU.size());
    }

    @Test
    void checkCacheShouldReplaceUserWithLongUsed() {

        User user1 = new User(1, "Bob");
        User user2 = new User(2, "Kent");
        User user3 = new User(3, "Sam");
        daoCacheLRU.saveUser(user1);
        daoCacheLRU.saveUser(user2);
        daoCacheLRU.saveUser(user3);
        daoCacheLRU.getUserById(1);
        daoCacheLRU.getUserById(2);

        User user4 = new User(4, "Jack");
        daoCacheLRU.saveUser(user4);
        assertEquals(3, daoCacheLRU.size());
        assertEquals(user1, daoCacheLRU.getUserById(1));
        assertEquals(user2, daoCacheLRU.getUserById(2));
        assertEquals(user4, daoCacheLRU.getUserById(4));
        assertNull(daoCacheLRU.getUserById(3));

        User user5 = new User(5, "Petr");
        daoCacheLRU.saveUser(user5);
        assertEquals(3, daoCacheLRU.size());
        assertEquals(user2, daoCacheLRU.getUserById(2));
        assertEquals(user4, daoCacheLRU.getUserById(4));
        assertEquals(user5, daoCacheLRU.getUserById(5));
        assertNull(daoCacheLRU.getUserById(1));
    }
}