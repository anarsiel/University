package operations;

import expression.exceptions.DivisionByZeroException;

public class UnsignedIntBinaryOperation implements BinaryOperation<Integer>{

    @Override
    public Integer add(Integer first, Integer second) {
        return first + second;
    }

    @Override
    public Integer sub(Integer first, Integer second) {
        return first - second;
    }

    @Override
    public Integer mul(Integer first, Integer second) {
        return first * second;
    }

    @Override
    public Integer div(Integer first, Integer second) throws DivisionByZeroException {
        if (second == 0) {
            throw new DivisionByZeroException();
        }
        return first / second;
    }

    @Override
    public Integer mod(Integer first, Integer second) throws DivisionByZeroException {
        if (second == 0) {
            throw new DivisionByZeroException();
        }
        return first % second;
    }

    @Override
    public Integer parseValue(String string) {
        return Integer.parseInt(string);
    }
}
