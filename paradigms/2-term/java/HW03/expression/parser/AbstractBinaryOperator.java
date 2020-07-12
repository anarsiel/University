package expression.parser;

import expression.TripleExpression;
import expression.exceptions.OverflowException;
import expression.operations.Operation;

public abstract class AbstractBinaryOperator<T> implements TripleExpression<T> {
    private final TripleExpression<T> firstOperand;
    private final TripleExpression<T> secondOperand;
    protected final Operation<T> operation;

    public AbstractBinaryOperator(TripleExpression<T> x, TripleExpression<T> y, Operation<T> op) {
        firstOperand = x;
        secondOperand = y;
        operation = op;
    }


    protected abstract T apply(T x, T y) throws OverflowException;


    public T evaluate(T x, T y, T z) throws OverflowException {
        return apply(firstOperand.evaluate(x, y, z), secondOperand.evaluate(x, y, z));
    }
}