package expression.exceptions;


public class MissingOperandException extends ParsingException {
    public MissingOperandException(final String s, final int ind, final int newind) {
        super("Missing operand before index: " + ind + "\n" + getPlace(newind, newind, s));
    }
}