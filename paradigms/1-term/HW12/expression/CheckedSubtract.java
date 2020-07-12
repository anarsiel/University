package expression;

import expression.exceptions.OverflowException;

public strictfp class CheckedSubtract extends AbstractBinaryOperator {
    public CheckedSubtract(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    public double apply(double x, double y) {
        return x - y;
    }

    public int apply(int x, int y) {
        return x - y;
    }

    private boolean checkSumIsOverfloated(int a, int b) {
        return (a > Integer.MAX_VALUE - b);
    }

    private boolean checkSubIsOverfloated(int a, int b) {
        return (!(checkSumIsOverfloated(Integer.MAX_VALUE, b)) && a > Integer.MAX_VALUE + b);
    }

    private void check(int a, int b) throws OverflowException {
        if (checkSubIsOverfloated(a, -b) || checkSubIsOverfloated(-a, b)) {
            throw new OverflowException();
        }
    }
}