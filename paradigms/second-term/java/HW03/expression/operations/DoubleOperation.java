package expression.operations;


import expression.exceptions.OverflowException;

public class DoubleOperation implements Operation<Double> {

    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double div(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double mul(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double sub(Double x, Double y) {
        return x - y;
    }

    public Double neg(Double x) {
        return -x;
    }

    @Override
    public Double abs(Double x) throws OverflowException {
        if (x < 0) return -x;
        return x;
    }

    @Override
    public Double square(Double x) throws OverflowException {
        return x * x;
    }

    @Override
    public Double mod(Double x, Double y) throws OverflowException {
        return x % y;
    }

    public Double parseValue(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
