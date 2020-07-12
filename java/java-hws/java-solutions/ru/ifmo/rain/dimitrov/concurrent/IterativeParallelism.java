//package ru.ifmo.rain.dimitrov.concurrent;
//
//import info.kgeorgiy.java.advanced.concurrent.AdvancedIP;
//import info.kgeorgiy.java.advanced.concurrent.ListIP;
//import info.kgeorgiy.java.advanced.mapper.ParallelMapper;
//
//import java.util.*;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class IterativeParallelism implements ListIP, AdvancedIP {
//    private final ParallelMapper parallelMapper;
//
//    public IterativeParallelism(ParallelMapper parallelMapper) {
//        this.parallelMapper = parallelMapper;
//    }
//
//    public IterativeParallelism() {
//        parallelMapper = null;
//    }
//
//    @Override
//    public String join(int threads, List<?> values) throws InterruptedException {
//        return runFunctionByThreads(threads, values,
//                s -> s.map(Object::toString).collect(Collectors.joining()),
//                s -> s.collect(Collectors.joining()));
//    }
//
//    @Override
//    public <T> List<T> filter(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
//        return runFunctionByThreads(threads, values, s -> s.filter(predicate).collect(Collectors.toList()),
//                s -> s.flatMap(List::stream).collect(Collectors.toList()));
//    }
//
//    @Override
//    public <T, U> List<U> map(int threads, List<? extends T> values, Function<? super T, ? extends U> f) throws InterruptedException {
//        return runFunctionByThreads(threads, values, s -> s.map(f).collect(Collectors.toList()),
//                s -> s.flatMap(List::stream).collect(Collectors.toList()));
//    }
//
//    @Override
//    public <T> T maximum(int threads, List<? extends T> values, Comparator<? super T> comparator) throws InterruptedException {
//        Function<Stream<? extends T>, ? extends T> maxCmp = s -> s.max(comparator).orElseThrow();
//        return runFunctionByThreads(threads, values,
//                s -> s.max(comparator).orElse(null),
//                s -> s.max(comparator).orElse(null));
//    }
//
//    @Override
//    public <T> T minimum(int threads, List<? extends T> values, Comparator<? super T> comparator) throws InterruptedException {
//        return maximum(threads, values, comparator.reversed());
//    }
//
//    @Override
//    public <T> boolean all(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
//        return runFunctionByThreads(threads, values,
//                s -> s.allMatch(predicate),
//                s -> s.allMatch(Boolean::booleanValue));
//    }
//
//    @Override
//    public <T> boolean any(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
//        return runFunctionByThreads(threads, values,
//                s -> s.anyMatch(predicate),
//                s -> s.anyMatch(Boolean::booleanValue));
//    }
//
//    private void joinAllWorkers(List<Thread> workers) throws InterruptedException {
//        InterruptedException workerException = null;
//        for (Thread thread : workers) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                if (workerException == null) {
//                    workerException = e;
//                } else {
//                    workerException.addSuppressed(e);
//                }
//
//            }
//        }
//
//        if (workerException != null) {  // if collected any the exceptions - throw
//            throw workerException;
//        }
//    }
//
//    private <T, R> R runFunctionByThreads(int threads,
//                                          List<? extends T> values,
//                                          Function<Stream<? extends T>, R> function,
//                                          Function<Stream<R>, R> collector) throws InterruptedException {
//        int oneThreadCount = values.size() / threads; // minimum amount of values per thread
//        int rest = values.size() % threads;
//
//        List<List<? extends T>> elements = new ArrayList<>();
//        for (int i = 0, startElementIndex = 0; i < threads; i++) {
//            int elementCount = oneThreadCount;
//            if (i < rest) {
//                elementCount++;
//            }
//
//            if (elementCount == 0) {
//                break;
//            }
//            elements.add(values.subList(startElementIndex, startElementIndex + elementCount));
//            startElementIndex += elementCount;
//        }
//
//        if (parallelMapper != null) {
//            return collector.apply(parallelMapper.map(function, elements.stream().map(Collection::stream).collect(Collectors.toList())).stream());
//        }
//
//        List<R> threadResults = new ArrayList<>(Collections.nCopies(elements.size(), null));
//        List<Thread> workerThreads = new ArrayList<>();
//
//        for (int threadIndex = 0; threadIndex < elements.size(); threadIndex++) {
//            int finalThreadIdx = threadIndex;
//
//            Thread worker = new Thread(
//                    () -> threadResults
//                            .set(finalThreadIdx, function.apply(elements
//                                    .get(finalThreadIdx)
//                                    .stream()))
//            );
//            workerThreads.add(worker);
//            worker.start();
//        }
//
//        joinAllWorkers(workerThreads);
//
//        return collector.apply(threadResults.stream());
//    }
//
//    @Override
//    public <T> T reduce(int threads, List<T> values, Monoid<T> monoid) throws InterruptedException {
//        return mapReduce(threads, values, Function.identity(), monoid);
//    }
//
//    @Override
//    public <T, R> R mapReduce(int threads, List<T> values, Function<T, R> lift, Monoid<R> monoid) throws InterruptedException {
//        return runFunctionByThreads(threads, values,
//                s -> s.map(lift).reduce(monoid.getIdentity(), monoid.getOperator()),
//                s -> s.reduce(monoid.getIdentity(), monoid.getOperator()));
//    }
//}