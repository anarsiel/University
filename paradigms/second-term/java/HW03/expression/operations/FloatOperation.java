package expression.operations;

import expression.exceptions.OverflowException;

public class FloatOperation implements Operation<Float> {
    @Override
    public Float add(Float x, Float y) throws OverflowException {
        return x + y;
    }

    @Override
    public Float div(Float x, Float y) throws OverflowException {
        return x / y;
    }

    @Override
    public Float mul(Float x, Float y) throws OverflowException {
        return x * y;
    }

    @Override
    public Float sub(Float x, Float y) throws OverflowException {
        return x - y;
    }

    @Override
    public Float neg(Float x) throws OverflowException {
        return -x;
    }

    @Override
    public Float abs(Float x) throws OverflowException {
        if (x < 0) return -x;
        return x;
    }

    @Override
    public Float square(Float x) throws OverflowException {
        return x * x;
    }

    @Override
    public Float mod(Float x, Float y) throws OverflowException {
        return x % y;
    }

    @Override
    public Float parseValue(String x) {
        return Float.parseFloat(x);
    }
}
