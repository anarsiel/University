package expression.exceptions;

public class DivisionByZeroException extends EvaluationException {
    public DivisionByZeroException() {
        super("Division by Zero!");
    }
}
