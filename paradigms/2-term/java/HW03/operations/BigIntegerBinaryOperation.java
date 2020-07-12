package operations;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

import java.math.BigInteger;

public class BigIntegerBinaryOperation implements BinaryOperation<BigInteger> {
    @Override
    public BigInteger add(BigInteger first, BigInteger second) {
        return first.add(second);
    }

    @Override
    public BigInteger sub(BigInteger first, BigInteger second) {
        return first.add(second.negate());
    }

    @Override
    public BigInteger mul(BigInteger first, BigInteger second) {
        return first.multiply(second);
    }

    @Override
    public BigInteger div(BigInteger first, BigInteger second) throws DivisionByZeroException {
        if (second.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException();
        }

        return first.divide(second);
    }

    @Override
    public BigInteger mod(BigInteger first, BigInteger second) throws OverflowException, DivisionByZeroException {
        if (second.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException();
        }

        return first.mod(second);
    }

    @Override
    public BigInteger parseValue(String string) {
        return new BigInteger(string);
    }
}
