package expression.exceptions;

public class EmptyBracketsException extends AllExceptions {

	public EmptyBracketsException(int index) {
		super("brackets content nothing on position " + index);
	}
}