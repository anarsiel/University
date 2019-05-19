package expression.parser;

import expression.TripleExpression;
import expression.exceptions.OverflowException;
import expression.operations.Operation;

public class CheckedMod<T> extends AbstractBinaryOperator<T> {

    public CheckedMod(TripleExpression<T> x, TripleExpression<T> y, Operation<T> op) {
        super(x, y, op);
    }

    @Override
    protected T apply(T x, T y) throws OverflowException {
        return operation.mod(x, y);
    }
}
