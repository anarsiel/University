package expression;

public strictfp class Const implements TripleExpression {
    private final Number value;

    public Const(Number x) {
        value = x;
    }

    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }
}