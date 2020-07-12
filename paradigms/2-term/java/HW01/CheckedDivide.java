package expression;

import expression.exceptions.*;

public strictfp class CheckedDivide extends AbstractBinaryOperator {

	public CheckedDivide(TripleExpression x, TripleExpression y) {
		super(x, y);
	}

	protected int makeOperation(int x, int y) throws AllExceptions{
		if (y == 0) {
			throw new DivisionByZeroException();
		}
		if (y == -1 && x == Integer.MIN_VALUE) {
			throw new OverflowException();
		}
		return x / y;
	}

}