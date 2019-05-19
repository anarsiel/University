package expression.exceptions;


public class MissingOperationException extends ParsingException {
    public MissingOperationException(final String s, final int ind, final int newind) {
        super("Missing operation before index: " + ind + "\n" + getPlace(newind, newind, s));
    }
}