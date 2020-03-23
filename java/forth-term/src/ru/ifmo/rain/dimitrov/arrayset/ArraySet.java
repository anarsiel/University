package ru.ifmo.rain.dimitrov.arrayset;

import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ArraySet<E> extends AbstractSet<E> implements NavigableSet<E> {
    private final ReversibleList<E> array;
    private final Comparator<? super E> comparator;

    private Comparator<? super E> validateComparator(Comparator<? super E> comparator) {
        if (Comparator.naturalOrder().equals(comparator)) {
            return null;
        } else {
            return comparator;
        }
    }

    public ArraySet() {
        this(Collections.emptyList(), null);
    }

    public ArraySet(Comparator<? super E> comparator) {
        this(Collections.emptyList(), comparator);
    }

    public ArraySet(Collection<? extends E> collection) {
        this(collection, null);
    }

    public ArraySet(Collection<? extends E> collection, Comparator<? super E> comparator) {
        NavigableSet<E> tmp = new TreeSet<>(comparator);
        tmp.addAll(collection);
        array = new ReversibleList<>(tmp);
        this.comparator = validateComparator(comparator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        return Collections.binarySearch(array, (E) Objects.requireNonNull(o), comparator) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }

    @Override
    public int size() {
        return array.size();
    }

    private int index(E e, boolean inclusive, boolean lower) {
        int index = Collections.binarySearch(array, Objects.requireNonNull(e), comparator);
        if (index < 0) {
            return lower ? (-index - 1 - 1) : (-index - 1);
        } else {
            return inclusive ? index : (lower ? (index - 1) : (index + 1));
        }
    }

    private E nullableGet(int index) {
        return (0 <= index && index < size()) ? array.get(index) : null;
    }

    @Override
    public E lower(E e) {
        return nullableGet(index(e, false, true));
    }

    @Override
    public E floor(E e) {
        return nullableGet(index(e, true, true));
    }

    @Override
    public E higher(E e) {
        return nullableGet(index(e, false, false));
    }

    @Override
    public E ceiling(E e) {
        return nullableGet(index(e, true, false));
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    private static class ReversibleList<T> extends AbstractList<T> implements RandomAccess {
        private final List<T> array;
        private boolean flip;

        private ReversibleList(List<T> list, boolean flip) {
            this.array = Collections.unmodifiableList(list);
            this.flip = flip;
        }

        ReversibleList(Collection<T> collection) {
            this.array = List.copyOf(collection);
            this.flip = false;
        }

        ReversibleList(ReversibleList<T> reverseArray, boolean flip) {
            this.array = reverseArray.array;
            this.flip = reverseArray.flip ^ flip;
        }

        private int index(int index) {
            return flip ? size() - 1 - index : index;
        }

        @Override
        public ReversibleList<T> subList(int fromIndex, int toIndex) {
            if (flip) {
                return new ReversibleList<>(array.subList(index(toIndex - 1), index(fromIndex) + 1), flip);
            } else {
                return new ReversibleList<>(array.subList(index(fromIndex), index(toIndex)), flip);
            }
        }

        public void flip() {
            flip ^= true;
        }

        @Override
        public T get(int index) {
            return array.get(index(index));
        }

        @Override
        public int size() {
            return array.size();
        }
    }

    private ArraySet(ReversibleList<E> reversibleArray, Comparator<? super E> comparator) {
        this.array = reversibleArray;
        this.comparator = comparator;
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return new ArraySet<E>(new ReversibleList<E>(array, true), Collections.reverseOrder(comparator));
    }

    @Override
    public Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    private NavigableSet<E> subSetImpl(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        Objects.requireNonNull(fromElement);
        Objects.requireNonNull(toElement);

        int fromIndex = index(fromElement, fromInclusive, false);
        int toIndex = index(toElement, toInclusive, true);

        if (fromIndex > toIndex) {
            return new ArraySet<>(comparator);
        }
        return new ArraySet<E>(array.subList(fromIndex, toIndex + 1), comparator);
    }

    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        return (comparator == null) ? ((Comparable<E>) e1).compareTo(e2) : comparator.compare(e1, e2);
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        if (compare(fromElement, toElement) > 0) {
            throw new IllegalArgumentException();
        }
        return subSetImpl(fromElement, fromInclusive, toElement, toInclusive);
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        if (isEmpty()) {
            return this;
        }
        return subSetImpl(first(), true, toElement, inclusive);
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        if (isEmpty()) {
            return this;
        }
        return subSetImpl(fromElement, inclusive, last(), true);
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return headSet(toElement, false);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return tailSet(fromElement, true);
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return array.get(0);
        }
    }

    @Override
    public E last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return array.get(size() - 1);
        }
    }
}