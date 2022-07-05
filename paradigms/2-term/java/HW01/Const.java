package expression;

public strictfp class Const implements TripleExpression {
	private final Number curNumber;

	public Const(Number x) {
		curNumber = x;
	}

	public int evaluate(int x) {
		int res = curNumber.intValue();
		return res;
	}

	public double evaluate(double x) {
		double res = curNumber.doubleValue();
		return res;
	}

	public int evaluate(int x, int y, int z) {
		int res = curNumber.intValue();
		return res;
	}
}