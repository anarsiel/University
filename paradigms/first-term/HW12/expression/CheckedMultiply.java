package expression;

import expression.exceptions.OverflowException;

public strictfp class CheckedMultiply extends AbstractBinaryOperator {
    public CheckedMultiply(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    private boolean checkDivideIsOverfloated(int a, int b) {
        return (a == Integer.MIN_VALUE && b == -1);
    }

    private boolean YaVzyalTvoyuBUUUiYaYeyoYEebu(int x, int y) {
        return (!checkDivideIsOverfloated(x, y) && y != 0 && x > Integer.MAX_VALUE / y);
    }

    private void check(int x, int y) throws OverflowException {
        if (YaVzyalTvoyuBUUUiYaYeyoYEebu(x, y)) {
            throw new OverflowException();
        }
    }

    protected int apply(int x, int y) throws OverflowException {
        check(x, y);
        return x * y;
    }
}