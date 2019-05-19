package expression.operations;

import expression.exceptions.OverflowException;

public class IntegerOperation implements Operation<Integer> {

    boolean isUnsigned;

    public IntegerOperation(boolean isUnsigned) {
        this.isUnsigned = isUnsigned;
    }

    @Override
    public Integer add(Integer x, Integer y) throws OverflowException {
        if (!isUnsigned) checkAdd(x, y);
        return x + y;
    }

    void checkAdd(int x, int y) throws OverflowException {
        if (x > 0 && Integer.MAX_VALUE - x < y) {
            throw new OverflowException("Overflow: sum of 2 positive arguments");
        }
        if (x < 0 && Integer.MIN_VALUE - x > y) {
            throw new OverflowException("Overflow: sum of 2 negative arguments");
        }
    }

    @Override
    public Integer div(Integer x, Integer y) throws OverflowException {
        if (!isUnsigned) checkDiv(x, y);
        return x / y;
    }

    void checkDiv(int x, int y) throws OverflowException {
        checkZero(y);
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("Overflow");
        }
    }

    @Override
    public Integer mul(Integer x, Integer y) throws OverflowException {
        if (!isUnsigned) checkMul(x, y);
        return x * y;
    }

    void checkMul(int x, int y) throws OverflowException {
        if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
            throw new OverflowException("Overflow: Multiply by 2 positive arguments");
        }
        if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
            throw new OverflowException("Overflow: Multiply by first positive and second negative arguments");
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new OverflowException("Overflow: Multiply by first negative and second positive argumts");
        }
        if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
            throw new OverflowException("Overflow: Multiply by 2 negative arguments");
        }
    }

    @Override
    public Integer sub(Integer x, Integer y) throws OverflowException {
        if (!isUnsigned) checkSub(x, y);
        return x - y;
    }

    void checkSub(final int x, final int y) throws OverflowException {
        if (x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) {
            throw new OverflowException("Overflow: substracting from fist positive and second negative arguments");
        }
        if (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y) {
            throw new OverflowException("Overflow: substracting from fist negative and second positive arguments");
        }
    }

    public Integer neg(Integer x) throws OverflowException {
        if (!isUnsigned) checkNeg(x);
        return -x;
    }

    @Override
    public Integer abs(Integer x) throws OverflowException {
        if (x < 0) {
            if (!isUnsigned) checkNeg(x);
            return -x;
        }
        return x;
    }

    @Override
    public Integer square(Integer x) throws OverflowException {
        if (!isUnsigned) checkMul(x, x);
        return x * x;
    }

    void checkZero(Integer y) throws OverflowException {
        if (y == 0) {
            throw new OverflowException("Mod by zero");
        }
    }

    @Override
    public Integer mod(Integer x, Integer y) throws OverflowException {
        checkZero(y);
        return x % y;
    }

    private void checkNeg(int x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow: unary minus from Integer.MIN_VALUE");
        }
    }

    public Integer parseValue(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
