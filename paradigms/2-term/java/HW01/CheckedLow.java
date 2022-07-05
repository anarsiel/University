package expression;

public strictfp class CheckedLow extends AbstractUnaryOperator {
	
	public CheckedLow(TripleExpression x) {
		super(x);
	}	

	protected int makeOperation(int x) {
		return Integer.lowestOneBit(x);
	}

}