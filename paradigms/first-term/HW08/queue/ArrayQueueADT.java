package queue;

// elements - array of elements
// size - count of elements in queue
// Invariant: size >= 0 && 0 <= beginning, ending <= elements.size
public class ArrayQueueADT {
    // Constructor's Invariant
    private static Object[] elements = new Object[10];
    private static int beginning = 0, ending = 1, size = 0;

    // Pre: Invariant
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    } // Post: result = queue.size

    // Pre: Invariant
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    } // Post: result = (queue.size == 0)

    // Pre: Invariant && x ∈ R && mod ∈ R
    private static int MOD(int x, int mod) {
        return (x % mod + mod) % mod;
    } // Post: result = mathematical module of x by mod

    // Pre: Invariant && x ∈ R
    private static int MOD(ArrayQueueADT queue, int x) {
        int mod = queue.elements.length;
        return (x % mod + mod) % mod;
    } // Post: result = mathematical module of x by queue.elements.length

    // Pre: Invariant
    private static void ensureCapacity(ArrayQueueADT queue) {
        if (queue.size == 0) {
            queue.elements = new Object[10];
            queue.beginning = 0;
            queue.ending = 1;
        } else if (queue.elements.length == queue.size) {
            Object[] buffer = new Object[2 * queue.size + 1];

            for (int i = 0; i < queue.size; i++) {
                buffer[i] = queue.elements[MOD(queue,queue.ending + i)];
            }

            queue.elements = buffer;
            queue.beginning = queue.size - 1;
            queue.ending = 0;
        } else if (queue.elements.length > 4 * queue.size) {
            Object[] buffer = new Object[queue.size * 2 + 1];

            for (int i = 0; i < queue.size; i++) {
                buffer[i] = queue.elements[MOD(queue.ending + i, queue.elements.length)];
            }

            queue.elements = buffer;
            queue.beginning = queue.size - 1;
            queue.ending = 0;
        }
    } // Post: (queue.size - 1 <= queue.elements.length <= queue.size * 4 + 1) && (queue.size' == queue.size) && (a'[i] == a[i])
    //  && queue pushed --> to the queue.beginning of queue.elements

    // Pre: Invariant
    public static void enqueue(ArrayQueueADT queue, Object element) {
        ensureCapacity(queue);
        queue.ending = MOD(queue,queue.ending - 1);
        queue.elements[queue.ending] = element;
        queue.size++;
    } // Post: queue.ending' = queue.ending - 1 && queue.elements[queue.ending'] = element && queue.size' = queue.size + 1;

    // Pre: Invariant
    public static Object element(ArrayQueueADT queue) {
        return queue.elements[queue.beginning];
    } // Post: result = top queue's element

    // Pre: Invariant
    public static Object dequeue(ArrayQueueADT queue) {
        Object first = element(queue);
        queue.beginning = MOD(queue,queue.beginning - 1);
        queue.size--;
        ensureCapacity(queue);
        return first;
    } // Post: queue.beginning' = queue.beginning - 1 && queue.size' = queue.size - 1;

    // Pre: Invariant
    public static void push(ArrayQueueADT queue, Object element) {
        ensureCapacity(queue);
//        System.out.println(beginning + "fuck");
        queue.beginning = MOD(queue, queue.beginning + 1);
//        System.out.println(beginning + "fuck");
        queue.elements[queue.beginning] = element;
        queue.size++;
    } // Post: beginning' = beginning + 1 && elements[beginning'] = element && size' = size + 1;

    // Pre: Invariant
    public static Object peek(ArrayQueueADT queue) {
        return queue.elements[queue.ending];
    } // Post: result = peek queue's element

    // Pre: Invariant
    public static Object remove(ArrayQueueADT queue) {
        Object last = peek(queue);
        queue.ending = MOD(queue, queue.ending + 1);
        queue.size--;
        ensureCapacity(queue);
        return last;
    } // Post: ending' = ending + 1 && size' = size - 1;

    // Pre: Invariant
    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[10];
        queue.beginning = 0;
        queue.ending = 1;
        queue.size = 0;
    } // Post: Constructor's Invariant
}
