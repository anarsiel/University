package expression.exceptions;

public class MissingOperatorException extends AllExceptions {

	public MissingOperatorException(int index) {
		super("operator not found on position " + index);
	}
}