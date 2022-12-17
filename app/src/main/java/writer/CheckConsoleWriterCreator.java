package writer;

public class CheckConsoleWriterCreator extends CheckWriterCreator {

    @Override
    public CheckWriter createWriter() {
        return new CheckConsoleWriter();
    }
}
