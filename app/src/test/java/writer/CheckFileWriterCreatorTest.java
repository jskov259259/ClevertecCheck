package writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckFileWriterCreatorTest {

    private CheckFileWriterCreator writerCreator;

    @BeforeEach
    public void setUp() {
        writerCreator = new CheckFileWriterCreator();
    }

    @Test
    public void checkCreateFileWriter() {

        CheckWriter fileWriter = writerCreator.createWriter();
        assertEquals(fileWriter.getClass(), CheckFileWriter.class);
    }
}