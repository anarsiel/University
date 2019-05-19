package expression;

import expression.exceptions.*;

public strictfp class CheckedAdd extends AbstractBinaryOperator {
    public CheckedAdd(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    protected double apply(double x, double y) {
        return x + y;
    }

    protected int apply(int x, int y) throws OverflowException {
        check(x, y);
        return x + y;
    }

    private boolean checkSubIsOverfloated(int a, int b) {
        return (a > Integer.MAX_VALUE + b);
    }

    private boolean checkSumIsOverfloated(int a, int b) {
        return (!checkSubIsOverfloated(Integer.MAX_VALUE, b) && a > Integer.MAX_VALUE - b);
    }

    private void check(int a, int b) throws OverflowException {
        if (checkSumIsOverfloated(a, b) || checkSumIsOverfloated(-a, -b)) {
            throw new OverflowException();
        }
    }
}