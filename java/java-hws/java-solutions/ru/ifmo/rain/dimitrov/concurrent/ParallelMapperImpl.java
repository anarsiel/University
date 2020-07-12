//package ru.ifmo.rain.dimitrov.concurrent;
//
//import info.kgeorgiy.java.advanced.mapper.ParallelMapper;
//
//import java.util.*;
//import java.util.function.Function;
//
///**
// * Implementation class for {@link ParallelMapper} interface
// */
//public class ParallelMapperImpl implements ParallelMapper {
//    private final LinkedList<Runnable> tasks;
//    private final List<Thread> workers;
//
//    /**
//     * Thread-count constructor.
//     * Creates a ParallelMapperImpl instance operating with maximum of {@code threads}
//     * threads of type {@link Thread}.
//     *
//     * @param threads maximum count of operable threads
//     */
//    public ParallelMapperImpl(int threads) {
//        workers = new ArrayList<>();
//        tasks = new LinkedList<>();
//
//        for (int i = 0; i < threads; ++i) {
//            workers.add(new Thread(new Worker()));
//        }
//
//        workers.forEach(Thread::start);
//    }
//
//    /**
//     * Maps parallely function {@code f} over specified {@code args}.
//     *
//     * @param function function to map over
//     * @param args     arguments which will be mapped
//     * @param <T>      type of arguments
//     * @param <R>      type of returning values
//     * @return {@link List} of mapped arguments
//     * @throws InterruptedException in case if some threads were be interrupted
//     */
//    @Override
//    public <T, R> List<R> map(Function<? super T, ? extends R> function, List<? extends T> args) throws InterruptedException {
//        ResultProvider<R> collector = new ResultProvider<>(args.size());
//
//        for (int i = 0; i < args.size(); ++i) {
//            final int index = i;
//
//            addTask(() -> collector.addData(
//                    index,
//                    function.apply(args.get(index))
//            ));
//        }
//
//        return collector.getResult();
//    }
//
//    /**
//     * Closes all threads.
//     */
//    @Override
//    public void close() {
//        workers.forEach(Thread::interrupt);
//
//        workers.forEach(thread -> {
//            try {
//                thread.join();
//            } catch (InterruptedException ignored) {
//                // ignored
//            }
//        });
//    }
//
//    private void addTask(Runnable task) {
//
//        synchronized (tasks) {
//            tasks.add(task);
//            tasks.notifyAll();
//        }
//    }
//
//    private class Worker implements Runnable {
//        @Override
//        public void run() {
//            try {
//                while (!Thread.interrupted()) {
//                    Runnable task;
//
//                    synchronized (tasks) {
//                        while (tasks.isEmpty()) {
//                            tasks.wait();
//                        }
//
//                        task = tasks.poll();
//                        tasks.notifyAll();
//                    }
//
//                    task.run();
//                }
//            } catch (InterruptedException ignored) {
//                // ignore
//            } finally {
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//
//    private static class ResultProvider<R> {
//        private int count;
//        private final List<R> result;
//
//        private ResultProvider(final int size) {
//            result = new ArrayList<>(Collections.nCopies(size, null));
//            count = 0;
//        }
//
//        private void addData(final int pos, R data) {
//            result.set(pos, data);
//            synchronized (this) {
//                ++count;
//                if (count == result.size()) {
//                    notify();
//                }
//            }
//        }
//
//        synchronized private List<R> getResult() throws InterruptedException {
//            while (count < result.size()) {
//                wait();
//            }
//
//            return result;
//        }
//    }
//}