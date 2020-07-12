package operations;

public class ByteUnaryOperation implements UnaryOperation<Byte> {
    @Override
    public Byte negate(Byte first) {
        return (byte) (-first);
    }

    @Override
    public Byte abs(Byte first) {
        return abs(first);
    }

    @Override
    public Byte square(Byte first) {
        return (byte)(Math.pow(first, 2));
    }
}
