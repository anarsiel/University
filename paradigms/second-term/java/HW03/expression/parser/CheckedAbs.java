package expression.parser;

import expression.TripleExpression;
import expression.exceptions.OverflowException;
import expression.operations.Operation;

public class CheckedAbs<T> extends AbstractUnaryOperator<T> {

    protected CheckedAbs(TripleExpression<T> x, Operation<T> op) {
        super(x, op);
    }

    @Override
    protected T apply(T x) throws OverflowException {
        return operation.abs(x);
    }
}
