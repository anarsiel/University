package expression.operations;


import expression.exceptions.OverflowException;

import java.math.BigInteger;

public class BigIntegerOperation implements Operation<BigInteger> {
    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger div(BigInteger x, BigInteger y) {
        return x.divide(y);
    }

    @Override
    public BigInteger mul(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger sub(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    public BigInteger neg(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger abs(BigInteger x) throws OverflowException {
        return x.abs();
    }

    @Override
    public BigInteger square(BigInteger x) throws OverflowException {
        return x.multiply(x);
    }

    @Override
    public BigInteger mod(BigInteger x, BigInteger y) throws OverflowException {
        return x.mod(y);
    }

    public BigInteger parseValue(String number) {
        try {
            return new BigInteger(number);
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
