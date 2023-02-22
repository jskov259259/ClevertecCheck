package utils.cache;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DaoCacheLFUTest {

    private DaoCacheLFU daoCacheLFU;

    @BeforeEach
    void setUp() {
        daoCacheLFU = new DaoCacheLFU(3);
    }

    @Test
    void checkLFU() {
        User user1 = new User(1, "Ann");
        User user2 = new User(2, "Bob");
        User user3 = new User(3, "Don");
        User user4 = new User(4, "Tom");
        User user5 = new User(5, "Dok");

        daoCacheLFU.put(user1);
        daoCacheLFU.put(user2);
        daoCacheLFU.put(user3);
        daoCacheLFU.display();
        daoCacheLFU.put(user4);
        daoCacheLFU.put(user5);
        daoCacheLFU.display();
    }
}