package operations;

import expression.exceptions.DivisionByZeroException;

public class ByteBinaryOperation implements BinaryOperation<Byte> {
    @Override
    public Byte add(Byte first, Byte second) {
        return (byte) (first + second);
    }

    @Override
    public Byte sub(Byte first, Byte second) {
        return (byte)(first - second);
    }

    @Override
    public Byte mul(Byte first, Byte second) {
        return (byte) (first * second);
    }

    @Override
    public Byte div(Byte first, Byte second) throws DivisionByZeroException {
        if (second == 0) {
            throw new DivisionByZeroException();
        }
        return (byte) (first / second);
    }

    @Override
    public Byte mod(Byte first, Byte second) throws DivisionByZeroException {
        if (second == 0) {
            throw new DivisionByZeroException();
        }

        return (byte) (first % second);
    }

    @Override
    public Byte parseValue(String string) {
        return (byte) Integer.parseInt(string);
    }
}
