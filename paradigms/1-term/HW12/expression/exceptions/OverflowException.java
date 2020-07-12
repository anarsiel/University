package expression.exceptions;

public class OverflowException extends EvaluationException{
    public OverflowException() {
        super("Overflow");
    }
}
