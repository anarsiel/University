package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public strictfp class CheckedDivide extends AbstractBinaryOperator {
    public CheckedDivide(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    private boolean checkDivideIsOverfloated(int a, int b) {
        return (a == Integer.MIN_VALUE && b == -1);
    }

    private void check(int a, int b) throws OverflowException, DivisionByZeroException {
        if (b == 0) {
            throw new DivisionByZeroException();
        }

        if (checkDivideIsOverfloated(a, b)) {
            throw new OverflowException();
        }
    }

    protected int apply(int x, int y) throws OverflowException, DivisionByZeroException {
        check(x, y);
        return x / y;
    }
}