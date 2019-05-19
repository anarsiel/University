package operations;

import java.math.BigInteger;

public class BigIntegerUnaryOperation implements UnaryOperation<BigInteger> {
    @Override
    public BigInteger negate(BigInteger first) {
        return negate(first);
    }

    @Override
    public BigInteger abs(BigInteger first) {
        return abs(first);
    }

    @Override
    public BigInteger square(BigInteger first) {
        return first.pow(2);
    }
}
