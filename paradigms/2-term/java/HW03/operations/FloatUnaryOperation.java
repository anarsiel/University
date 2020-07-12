package operations;

public class FloatUnaryOperation implements UnaryOperation<Float> {
    @Override
    public Float negate(Float first) {
        return -first;
    }

    @Override
    public Float abs(Float first) {
        return abs(first);
    }

    @Override
    public Float square(Float first) {
        return (float) (Math.pow(first, 2));
    }
}
