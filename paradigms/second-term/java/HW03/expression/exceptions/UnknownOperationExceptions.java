package expression.exceptions;

public class UnknownOperationExceptions extends ParsingException {
    public UnknownOperationExceptions(String s, int ind, int begin, int end) {
        super("Unknown operation: starting from position " + ind + "\n" + getPlace(begin,begin+end, s));
    }
}
