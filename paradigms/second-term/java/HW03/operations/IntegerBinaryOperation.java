package operations;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class IntegerBinaryOperation implements BinaryOperation<Integer>{

    @Override
    public Integer add(Integer first, Integer second) throws OverflowException {
        if (first > 0 && second > Integer.MAX_VALUE - first) {
            throw new OverflowException();
        }
        if (first < 0 && second < Integer.MIN_VALUE - first) {
            throw new OverflowException();
        }

        return first + second;
    }

    @Override
    public Integer sub(Integer first, Integer second) throws OverflowException {
        if (second > 0 && Integer.MIN_VALUE + second > first) {
            throw new OverflowException();
        }
        if (second < 0 && Integer.MAX_VALUE + second < first) {
            throw new OverflowException();
        }

        return first - second;
    }

    @Override
    public Integer mul(Integer first, Integer second) throws OverflowException {
        if (first > 0 && second > 0 && Integer.MAX_VALUE / second < first) {
            throw new OverflowException();
        }
        if (first < 0 && second < 0 && Integer.MAX_VALUE / second > first) {
            throw new OverflowException();
        }
        if (first > 0 && second < 0 && Integer.MIN_VALUE / first > second) {
            throw new OverflowException();
        }
        if (first < 0 && second > 0 && Integer.MIN_VALUE / second > first) {
            throw new OverflowException();
        }
        return first * second;
    }

    @Override
    public Integer div(Integer first, Integer second) throws OverflowException, DivisionByZeroException {
        if (second == 0) {
            throw new DivisionByZeroException();
        }

        if (second == -1 && first == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return first / second;
    }

    @Override
    public Integer mod(Integer first, Integer second) throws OverflowException, DivisionByZeroException {
        if (second == 0) {
            throw new DivisionByZeroException();
        }

        return first % second;
    }

    @Override
    public Integer parseValue(String string) throws NumberFormatException, OverflowException {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new OverflowException();
        }
    }
}
