package exceptions;

public class IncorrectValuesNumber extends RuntimeException {

    private String message;

    public IncorrectValuesNumber() {
        message = "Incorrect Variables Number in File";
    }

    public IncorrectValuesNumber(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
