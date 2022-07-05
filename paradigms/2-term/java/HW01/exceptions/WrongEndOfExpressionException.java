package expression.exceptions;

public class WrongEndOfExpressionException extends AllExceptions {

	public WrongEndOfExpressionException() {
		super("expression can't end with such symbol");
	}
}