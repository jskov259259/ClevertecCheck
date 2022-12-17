package writer;

public class CheckFileWriterCreator extends CheckWriterCreator {

    @Override
    public CheckWriter createWriter() {
        return new CheckFileWriter();
    }
}
