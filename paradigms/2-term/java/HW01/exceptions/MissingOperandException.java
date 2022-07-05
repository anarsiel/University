package expression.exceptions;

public class MissingOperandException extends AllExceptions {

	public MissingOperandException(int index) {
		super("operator on position " + index + " is needed");
	}
}