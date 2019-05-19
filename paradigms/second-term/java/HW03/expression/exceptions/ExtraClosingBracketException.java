package expression.exceptions;

public class ExtraClosingBracketException extends ParsingException {

    public ExtraClosingBracketException(final String s, final int ind, final int newind) {
        super("Extra closing bracket at position: " + ind + "\n" + getPlace(newind, newind, s));
    }
}
