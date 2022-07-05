package expression.exceptions;

public class IncorrectUsingOfOperatorException extends AllExceptions {

	public IncorrectUsingOfOperatorException(int index, String type) {
		super(type + " is used incorrectly on position " + index);
	}
}