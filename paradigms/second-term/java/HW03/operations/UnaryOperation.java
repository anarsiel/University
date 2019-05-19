package operations;

public interface UnaryOperation<T> {
    T negate(T first);
    T abs(T first);
    T square(T first);
}