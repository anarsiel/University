package expression.exceptions;


public class MissingClosingBracketException extends ParsingException {
    public MissingClosingBracketException(final String s, final int ind, final int newind) {
        super("Missing closing parenthesis for opening one at position: " + ind + "\n" + getPlace(newind, newind, s));
    }
}