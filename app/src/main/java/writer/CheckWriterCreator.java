package writer;

public abstract class CheckWriterCreator {

    public void writeCheck(String check) {

        CheckWriter writer = createWriter();
        writer.write(check);
    }

    public abstract CheckWriter createWriter();
}
