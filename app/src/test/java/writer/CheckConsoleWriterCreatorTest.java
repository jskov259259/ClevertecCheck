package writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckConsoleWriterCreatorTest {

    private CheckConsoleWriterCreator writerCreator;

    @BeforeEach
    public void setUp() {
        writerCreator = new CheckConsoleWriterCreator();
    }

    @Test
    public void checkCreateConsoleWriter() {

        CheckWriter consoleWriter = writerCreator.createWriter();
        assertEquals(consoleWriter.getClass(), CheckConsoleWriter.class);
    }


}