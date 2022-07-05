package expression;

import expression.exceptions.*;

public strictfp class CheckedNegate extends AbstractUnaryOperator {
	
	public CheckedNegate(TripleExpression x) {
		super(x);
	}	

	protected int makeOperation(int x) throws OverflowException{
		if (x == Integer.MIN_VALUE) {
			throw new OverflowException();
		}
		return (-x);
	}

}