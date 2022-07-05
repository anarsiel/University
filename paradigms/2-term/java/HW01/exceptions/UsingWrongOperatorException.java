package expression.exceptions;

public class UsingWrongOperatorException extends AllExceptions {

	public UsingWrongOperatorException(int index) {
		super("There is undeclared operator on position " + index);
	}
}