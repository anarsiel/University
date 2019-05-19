package operations;

import java.util.function.UnaryOperator;

public class DoubleUnaryOperation implements UnaryOperation<Double> {
    @Override
    public Double negate(Double first) {
        return -first;
    }

    @Override
    public Double abs(Double first) {
        return abs(first);
    }

    @Override
    public Double square(Double first) {
        return Math.pow(first, 2);
    }
}
