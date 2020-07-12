package expression.exceptions;

public class WrongSymbolException extends ParsingException {
    public WrongSymbolException() {
        super("Incorrect Symbol!");
    }
}
