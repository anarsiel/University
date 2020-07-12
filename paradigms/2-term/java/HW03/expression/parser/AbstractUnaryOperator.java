package expression.parser;


import expression.TripleExpression;
import expression.exceptions.OverflowException;
import expression.operations.Operation;

public abstract class AbstractUnaryOperator<T> implements TripleExpression<T> {
    protected final TripleExpression<T> operand;
    protected final Operation<T> operation;

    protected AbstractUnaryOperator(TripleExpression<T> x, Operation<T> op) {
        operand = x;
        operation = op;
    }

    protected abstract T apply(T x) throws OverflowException;

    public T evaluate(T x, T y, T z) throws OverflowException {
        return apply(operand.evaluate(x, y, z));
    }
}