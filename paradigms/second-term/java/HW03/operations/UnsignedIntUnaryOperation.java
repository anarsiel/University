package operations;

public class UnsignedIntUnaryOperation implements UnaryOperation<Integer>{
    @Override
    public Integer negate(Integer first) {
        return -first;
    }

    @Override
    public Integer abs(Integer first) {
        return abs(first);
    }

    @Override
    public Integer square(Integer first) {
        return square(first);
    }
}
