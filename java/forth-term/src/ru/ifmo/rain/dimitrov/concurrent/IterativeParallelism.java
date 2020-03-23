package ru.ifmo.rain.dimitrov.concurrent;

import info.kgeorgiy.java.advanced.concurrent.AdvancedIP;
import info.kgeorgiy.java.advanced.concurrent.ListIP;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IterativeParallelism implements ListIP, AdvancedIP {
    @Override
    public String join(int threads, List<?> values) throws InterruptedException {
        return runFunctionByThreads(threads, values, s -> s.map(Object::toString).collect(Collectors.joining())).collect(Collectors.joining());
    }

    @Override
    public <T> List<T> filter(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        return uniteManyListsToOne(runFunctionByThreads(threads, values, s -> s.filter(predicate)));
    }

    private <T> List<T> uniteManyListsToOne(Stream<? extends Stream<? extends T>> lists) {
        return lists.flatMap(Function.identity()).collect(Collectors.toList());
    }

    @Override
    public <T, U> List<U> map(int threads, List<? extends T> values, Function<? super T, ? extends U> f) throws InterruptedException {
        return uniteManyListsToOne(runFunctionByThreads(threads, values, s -> s.map(f)));
    }

    @Override
    public <T> T maximum(int threads, List<? extends T> values, Comparator<? super T> comparator) throws InterruptedException {
        Function<Stream<? extends T>, ? extends T> maxCmp = s -> s.max(comparator).orElseThrow();
        return maxCmp.apply(runFunctionByThreads(threads, values, maxCmp));
    }

    @Override
    public <T> T minimum(int threads, List<? extends T> values, Comparator<? super T> comparator) throws InterruptedException {
        return maximum(threads, values, comparator.reversed());
    }

    @Override
    public <T> boolean all(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        return runFunctionByThreads(threads, values, s -> s.allMatch(predicate)).allMatch(Boolean::booleanValue);
    }

    @Override
    public <T> boolean any(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        return runFunctionByThreads(threads, values, s -> s.anyMatch(predicate)).anyMatch(Boolean::booleanValue);
    }

    private void joinAllWorkers(List<Thread> workers) throws InterruptedException {
        InterruptedException collectedExceptions = null;
        for (Thread thread : workers) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                if (collectedExceptions == null) {
                    collectedExceptions = new InterruptedException("Some worker joined incorrectly");
                }
                collectedExceptions.addSuppressed(e);
            }
        }

        if (collectedExceptions != null) {
            throw collectedExceptions;
        }
    }

    private <T, R> Stream<R> runFunctionByThreads(int threads, List<? extends T> values, Function<Stream<? extends T>, R> function) throws InterruptedException {
        int everyThreadCount = values.size() / threads;
        int remainder = values.size() % threads;
        List<List<? extends T>> threadElements = new ArrayList<>();
        for (int i = 0, startElement = 0; i < threads; i++) {
            int currentThreadCount = everyThreadCount;
            if (i < remainder) {
                currentThreadCount++;
            }
            if (currentThreadCount == 0) {
                break;
            }
            threadElements.add(values.subList(startElement, startElement + currentThreadCount));
            startElement += currentThreadCount;
        }
        List<R> threadResults = new ArrayList<>(Collections.nCopies(threadElements.size(), null));
        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < threadElements.size(); i++) {
            int currentThread = i;
            Thread worker = new Thread(() -> threadResults.set(currentThread, function.apply(threadElements.get(currentThread).stream())));
            workers.add(worker);
            worker.start();
        }
        joinAllWorkers(workers);
        return threadResults.stream();
    }

    @Override
    public <T> T reduce(int threads, List<T> values, Monoid<T> monoid) throws InterruptedException {
        return mapReduce(threads, values, Function.identity(), monoid);
    }

    @Override
    public <T, R> R mapReduce(int threads, List<T> values, Function<T, R> lift, Monoid<R> monoid) throws InterruptedException {
        return runFunctionByThreads(threads, values, s -> s.map(lift).reduce(monoid.getIdentity(), monoid.getOperator())).reduce(monoid.getIdentity(), monoid.getOperator());
    }
}