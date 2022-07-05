package expression.exceptions;

public class InvalidBracketsException extends AllExceptions {
	
	public InvalidBracketsException(int index, int type) {
		super("there is wrong amount of " + (type != 0 ? "closing" : "opening") + " brackets on position " + index);
	}
}