package operations;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class FloatBinaryOperation implements BinaryOperation<Float> {
    @Override
    public Float add(Float first, Float second) {
        return first + second;
    }

    @Override
    public Float sub(Float first, Float second) {
        return first - second;
    }

    @Override
    public Float mul(Float first, Float second) {
        return first * second;
    }

    @Override
    public Float div(Float first, Float second) throws DivisionByZeroException {
        return first / second;
    }

    @Override
    public Float mod(Float first, Float second) throws DivisionByZeroException {
        return first % second;
    }

    @Override
    public Float parseValue(String string) {
        return Float.parseFloat(string);
    }
}
