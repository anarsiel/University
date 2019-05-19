package expression.parser;

import expression.TripleExpression;
import expression.exceptions.OverflowException;

public class Const<T> implements TripleExpression<T> {
    private final T value;

    public Const(T x) {
        value = x;
    }



    @Override
    public T evaluate(T x, T y, T z) throws OverflowException {
        return value;
    }
}
