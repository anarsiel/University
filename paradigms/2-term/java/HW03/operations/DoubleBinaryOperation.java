package operations;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class DoubleBinaryOperation implements BinaryOperation<Double> {

    @Override
    public Double add(Double first, Double second) {
        return first + second;
    }

    @Override
    public Double sub(Double first, Double second) {
        return first - second;
    }

    @Override
    public Double mul(Double first, Double second) {
        return first * second;
    }

    @Override
    public Double div(Double first, Double second) throws DivisionByZeroException {
        return first / second;
    }

    @Override
    public Double mod(Double first, Double second) throws DivisionByZeroException {
        if (second == 0) {
            throw new DivisionByZeroException();
        }

        return first % second;
    }

    @Override
    public Double parseValue(String string) {
        return Double.parseDouble(string);
    }
}
