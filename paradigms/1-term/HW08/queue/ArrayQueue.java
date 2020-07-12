package queue;

// elements - array of elements
// size - count of elements in queue
// Invariant: size >= 0 && 0 <= beginning, ending <= elements.size
public class ArrayQueue {
    // Constructor's Invariant
    private  Object[] elements = new Object[10];
    private  int beginning = 0, ending = 1, size = 0;

    // Pre: Invariant
    public int size() {
        return size;
    } // Post: result = size

    // Pre: Invariant
    public  boolean isEmpty() {
        return size == 0;
    } // Post: result = (size == 0)

    // Pre: Invariant && x ∈ R && mod ∈ R
    private  int MOD(int x, int mod) {
        return (x % mod + mod) % mod;
    } // Post: result = mathematical module of x by mod

    // Pre: Invariant && x ∈ R
    private  int MOD(int x) {
        int mod = elements.length;
        return (x % mod + mod) % mod;
    } // Post: result = mathematical module of x by elements.length

    // Pre: Invariant
    private  void ensureCapacity() {
        if (size == 0) {
            elements = new Object[10];
            beginning = 0;
            ending = 1;
        } else if (elements.length == size) {
            Object[] buffer = new Object[2 * size() + 1];

            for (int i = 0; i < size; i++) {
                buffer[i] = elements[MOD(ending + i)];
            }

            elements = buffer;
            beginning = size - 1;
            ending = 0;
        } else if (elements.length > 4 * size) {
            Object[] buffer = new Object[size * 2 + 1];

            for (int i = 0; i < size; i++) {
                buffer[i] = elements[MOD(ending + i, elements.length)];
            }

            elements = buffer;
            beginning = size - 1;
            ending = 0;
        }
    } // Post: (size - 1 <= elements.length <= size * 4 + 1) && (size' == size) && (a'[i] == a[i])
    //  && queue pushed --> to the beginning of elements

    // Pre: Invariant
    public  void enqueue(Object element) {
        ensureCapacity();
        ending = MOD(ending - 1);
        elements[ending] = element;
        size++;
    } // Post: ending' = ending - 1 && elements[ending'] = element && size' = size + 1;

    // Pre: Invariant
    public  Object element() {
        return elements[beginning];
    } // Post: result = top queue's element

    // Pre: Invariant
    public  Object dequeue() {
        Object first = element();
        beginning = MOD(beginning - 1);
        size--;
        ensureCapacity();
        return first;
    } // Post: beginning' = beginning - 1 && size' = size - 1;

    // Pre: Invariant
    public void push(Object element) {
        ensureCapacity();
//        System.out.println(beginning + "fuck");
        beginning = MOD(beginning + 1);
//        System.out.println(beginning + "fuck");
        elements[beginning] = element;
        size++;
    } // Post: beginning' = beginning + 1 && elements[beginning'] = element && size' = size + 1;

    // Pre: Invariant
    public Object peek() {
        return elements[ending];
    } // Post: result = peek queue's element

    // Pre: Invariant
    public Object remove() {
        Object last = peek();
        ending = MOD(ending + 1);
        size--;
        ensureCapacity();
        return last;
    } // Post: ending' = ending + 1 && size' = size - 1;

    // Pre: Invariant
    public void clear() {
        elements = new Object[10];
        beginning = 0;
        ending = 1;
        size = 0;
    } // Post: Constructor's Invariant
}
