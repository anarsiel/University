package ru.ifmo.rain.dimitrov.arrayset;

import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ArraySet<E> extends AbstractSet<E> implements NavigableSet<E> {
    private final ReversibleList<E> elements;
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

        elements = new ReversibleList<>(tmp);
        this.comparator = validateComparator(comparator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        return Collections.binarySearch(elements, (E) Objects.requireNonNull(o), comparator) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    private int index(E e, boolean inclusive, boolean lower) {
        int index = Collections.binarySearch(elements, Objects.requireNonNull(e), comparator);
        if (index < 0) {
            if (lower)
                return (-index - 1 - 1);
            else
                return (-index - 1);
        } else {
            if (inclusive)
                return index;

            return lower ? (index - 1) : (index + 1);
        }
    }

    private E nullableGet(int index) {
        return (0 <= index && index < size()) ? elements.get(index) : null;
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
        private final List<T> elements;
        private boolean swap;

        private ReversibleList(List<T> list, boolean swap) {
            this.elements = Collections.unmodifiableList(list);
            this.swap = swap;
        }

        ReversibleList(Collection<T> collection) {
            this.elements = List.copyOf(collection);
            this.swap = false;
        }

        ReversibleList(ReversibleList<T> reverseArray, boolean swap) {
            this.elements = reverseArray.elements;
            this.swap = reverseArray.swap ^ swap;
        }

        private int index(int index) {
            return swap ? size() - 1 - index : index;
        }

        @Override
        public ReversibleList<T> subList(int fromIndex, int toIndex) {
            if (swap) {
                return new ReversibleList<>(elements.subList(index(toIndex - 1), index(fromIndex) + 1), swap);
            } else {
                return new ReversibleList<>(elements.subList(index(fromIndex), index(toIndex)), swap);
            }
        }

        public void swap() {
            swap ^= true;
        }

        @Override
        public T get(int index) {
            return elements.get(index(index));
        }

        @Override
        public int size() {
            return elements.size();
        }
    }

    private ArraySet(ReversibleList<E> reversibleArray, Comparator<? super E> comparator) {
        this.elements = reversibleArray;
        this.comparator = comparator;
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return new ArraySet<E>(new ReversibleList<E>(elements, true), Collections.reverseOrder(comparator));
    }

    @Override
    public Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    private NavigableSet<E> getSubSet(E from, boolean inclusiveFrom, E to, boolean toInclusiveTo) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        int fromIndex = index(from, inclusiveFrom, false);
        int toIndex = index(to, toInclusiveTo, true);

        if (fromIndex > toIndex) {
            return new ArraySet<>(comparator);
        }
        return new ArraySet<E>(elements.subList(fromIndex, toIndex + 1), comparator);
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
        return getSubSet(fromElement, fromInclusive, toElement, toInclusive);
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        if (isEmpty()) {
            return this;
        }
        return getSubSet(first(), true, toElement, inclusive);
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        if (isEmpty()) {
            return this;
        }

        return getSubSet(fromElement, inclusive, last(), true);
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
            return elements.get(0);
        }
    }

    @Override
    public E last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return elements.get(size() - 1);
        }
    }
}