package expression;

public strictfp class Count extends AbstractUnaryOperator {
    public Count(TripleExpression x) {
        super(x);
    }

    @Override
    protected int apply(int x) {
        return Integer.bitCount(x);
    }
}