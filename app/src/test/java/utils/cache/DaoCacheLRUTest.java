package utils.cache;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaoCacheLRUTest {

    private DaoCacheLRU daoCacheLRU;

    @BeforeEach
    void setUp() {
        daoCacheLRU = new DaoCacheLRU(3);
    }

    @Test
    void checkCache() {

    }
}