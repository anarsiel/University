package expression;

import expression.exceptions.*;

public strictfp abstract class AbstractBinaryOperator implements TripleExpression {
	private final TripleExpression op1;
	private final TripleExpression op2;

	public AbstractBinaryOperator(TripleExpression a, TripleExpression b) {
		this.op1 = a;
		this.op2 = b;
	}

	protected abstract int makeOperation(int x, int y) throws AllExceptions;

	public int evaluate(int x, int y, int z) throws AllExceptions{
		return makeOperation(op1.evaluate(x, y, z), op2.evaluate(x, y, z));
	}
}