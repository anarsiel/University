package expression.exceptions;

public class WrongOperandException extends ParsingException {
    public WrongOperandException() {
        super("Wrong operand");
    }
}
