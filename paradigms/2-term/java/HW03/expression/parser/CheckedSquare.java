package expression.parser;

import expression.TripleExpression;
import expression.exceptions.OverflowException;
import expression.operations.Operation;

public class CheckedSquare<T> extends AbstractUnaryOperator<T> {

    protected CheckedSquare(TripleExpression<T> x, Operation<T> op) {
        super(x, op);
    }

    @Override
    protected T apply(T x) throws OverflowException {
        return operation.square(x);
    }
}
