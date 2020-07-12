package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public strictfp interface TripleExpression {
    public int evaluate(int x, int y, int z) throws OverflowException, DivisionByZeroException;
}