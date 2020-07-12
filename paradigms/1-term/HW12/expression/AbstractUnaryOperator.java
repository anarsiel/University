
package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public abstract class AbstractUnaryOperator implements TripleExpression {
    protected final TripleExpression operand;

    protected AbstractUnaryOperator(final TripleExpression x) {
        operand = x;
    }

    protected abstract int apply(final int x) throws OverflowException;

    public int evaluate(final int x, final int y, final int z) throws OverflowException, DivisionByZeroException {
        return apply(operand.evaluate(x, y, z));
    }
}