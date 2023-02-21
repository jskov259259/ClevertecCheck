package writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CheckConsoleWriterTest {

    private CheckConsoleWriter consoleWriter;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {

        consoleWriter = new CheckConsoleWriter();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void checkWriteInConsole() {

        String check = "Some check";
        consoleWriter.write(check);
        assertEquals(check, outputStreamCaptor.toString()
                .trim());
    }
}