package expression;

import expression.exceptions.*;

public strictfp abstract class AbstractUnaryOperator implements TripleExpression {
	private final TripleExpression op;

	public AbstractUnaryOperator(TripleExpression a) {
		this.op = a;
	}

	protected abstract int makeOperation(int x) throws AllExceptions;

	public int evaluate(int x, int y, int z) throws AllExceptions{
		return makeOperation(op.evaluate(x, y, z));
	}
}