package expression;

import expression.exceptions.*;

public strictfp class CheckedSubtract extends AbstractBinaryOperator {

	public CheckedSubtract(TripleExpression x, TripleExpression y) {
		super(x, y);
	}

	protected int makeOperation(int x, int y) throws OverflowException{
		if (y > 0 && Integer.MIN_VALUE + y > x) {
			throw new OverflowException();
		}
		if (y < 0 && Integer.MAX_VALUE + y < x) {
			throw new OverflowException();
		}
		return x - y;
	}

}