package expression;

import expression.exceptions.*;

public strictfp class CheckedMultiply extends AbstractBinaryOperator {

	public CheckedMultiply(TripleExpression x, TripleExpression y) {
		super(x, y);
	}

	protected int makeOperation(int x, int y) throws OverflowException{
		if (x > 0 && y > 0 && Integer.MAX_VALUE / y < x) {
			throw new OverflowException();
		}
		if (x < 0 && y < 0 && Integer.MAX_VALUE / y > x) {
			throw new OverflowException();
		}
		if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
			throw new OverflowException();
		}
		if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
			throw new OverflowException();
		}
		return x * y;
	}

}