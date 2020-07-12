package expression;

import expression.exceptions.OverflowException;

public strictfp class CheckedNegate extends AbstractUnaryOperator {
    public CheckedNegate(TripleExpression x) {
        super(x);
    }

    private boolean checkUnaryMinusIsOverfloated(int x) {
        return x == Integer.MIN_VALUE;
    }

    private void check(int x) throws OverflowException {
        if (checkUnaryMinusIsOverfloated(x)) {
            throw new OverflowException();
        }
    }

    @Override
    protected int apply(int x) throws OverflowException {
        check(x);
        return -x;
    }
}