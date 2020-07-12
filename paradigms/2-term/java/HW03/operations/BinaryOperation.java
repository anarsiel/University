package operations;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public interface BinaryOperation <T>{
    T add(T first, T second) throws OverflowException;
    T sub(T first, T second) throws OverflowException;
    T mul(T first, T second) throws OverflowException;
    T div(T first, T second) throws OverflowException, DivisionByZeroException;
    T mod(T first, T second) throws OverflowException, DivisionByZeroException;

    T parseValue(String string) throws OverflowException;
}
