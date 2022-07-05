package expression;

public strictfp class Variable implements TripleExpression {
	private final String type;

	public Variable(String str) {
		this.type = str;
	}

	public int evaluate(int x, int y, int z) {
		if (type.equals("x"))
			return x;
		if (type.equals("y"))
			return y;
		return z;
	}
}