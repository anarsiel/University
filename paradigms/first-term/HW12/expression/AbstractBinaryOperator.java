package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public strictfp abstract class AbstractBinaryOperator implements TripleExpression {
    private final TripleExpression firstOperand;
    private final TripleExpression secondOperand;

    public AbstractBinaryOperator(TripleExpression x, TripleExpression y) {
        firstOperand = x;
        secondOperand = y;
    }

    protected abstract int apply(int x, int y) throws OverflowException, DivisionByZeroException;

    public int evaluate(int x, int y, int z) throws OverflowException, DivisionByZeroException {
        return apply(firstOperand.evaluate(x, y, z), secondOperand.evaluate(x, y, z));
    }
}