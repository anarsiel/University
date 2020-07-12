package expression.exceptions;

public class IncorrectConstException extends ParsingException {
    public IncorrectConstException(int ind) {
        super("Overflow const before position: " + ind);
    }
}