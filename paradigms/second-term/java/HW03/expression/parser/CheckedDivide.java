package expression.parser;

import expression.TripleExpression;
import expression.exceptions.OverflowException;
import expression.operations.Operation;

public class CheckedDivide<T> extends AbstractBinaryOperator<T> {
    public CheckedDivide(TripleExpression<T> x, TripleExpression <T> y, Operation<T> op) {
        super(x, y, op);
    }

    @Override
    protected T apply(T x, T y) throws OverflowException {
        return operation.div(x,y);
    }



    protected int apply(int x, int y) throws OverflowException {
        return x / y;
    }
}