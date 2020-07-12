package expression.operations;

import expression.exceptions.IncorrectConstException;
import expression.exceptions.OverflowException;

public class ByteOperation implements Operation<Byte> {

    @Override
    public Byte add(Byte x, Byte y) throws OverflowException {
        return (byte) (x + y);
    }

    @Override
    public Byte div(Byte x, Byte y) throws OverflowException {
        return (byte) (x / y);
    }

    @Override
    public Byte mul(Byte x, Byte y) throws OverflowException {
        return (byte) (x * y);
    }

    @Override
    public Byte sub(Byte x, Byte y) throws OverflowException {
        return (byte) (x - y);
    }

    @Override
    public Byte neg(Byte x) throws OverflowException {
        return (byte) (-x);
    }

    @Override
    public Byte abs(Byte x) throws OverflowException {
        if (x < 0) {
            return (byte) (-x);
        }
        return x;
    }

    @Override
    public Byte square(Byte x) throws OverflowException {
        return (byte) (x * x);
    }

    @Override
    public Byte mod(Byte x, Byte y) throws OverflowException {
        return (byte) (x % y);
    }

    @Override
    public Byte parseValue(String x) {
        return (byte) Integer.parseInt(x);
    }
}