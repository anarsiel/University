package expression;

public strictfp class CheckedHigh extends AbstractUnaryOperator {
	
	public CheckedHigh(TripleExpression x) {
		super(x);
	}	

	protected int makeOperation(int x) {
		return Integer.highestOneBit(x);
	}

}