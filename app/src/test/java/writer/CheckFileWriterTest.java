package writer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckFileWriterTest {

    private CheckFileWriter fileWriter;

    @BeforeEach
    public void setUp() {
        fileWriter = new CheckFileWriter();
    }

    @Test
    void checkIsFileCreating() {

        String check = "Some check";
        fileWriter.write(check);
        File checkFile = new File("Check.txt");
        assertTrue(checkFile.exists());
    }

    @Test
    void checkWriteInFile() throws IOException {

        String check = "Some check";
        fileWriter.write(check);
        File file = new File("Check.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        assertEquals(check, line);
        reader.close();
    }

    @AfterEach
    public void tearDown() {

        File checkFile = new File("Check.txt");
        checkFile.delete();
    }

}