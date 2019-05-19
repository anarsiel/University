package expression.exceptions;

public class UnknownSymbolException extends ParsingException {
    public UnknownSymbolException(final String s, final int ind, final int newind) {
        super("Unknown symbol '" + s.charAt(ind) + "' at position " + ind + "\n" + getPlace(newind, newind, s));
    }
}