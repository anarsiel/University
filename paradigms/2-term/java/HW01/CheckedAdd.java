package expression;

import expression.exceptions.*;

public class CheckedAdd extends AbstractBinaryOperator {

	public CheckedAdd(TripleExpression x, TripleExpression y) {
		super(x, y);
	}

	protected int makeOperation(int x, int y) throws OverflowException{
		if (x > 0 && y > Integer.MAX_VALUE - x) {
			throw new OverflowException();
		}
		if (x < 0 && y < Integer.MIN_VALUE - x) {
			throw new OverflowException();
		}
		return x + y;
	}

}