//package ru.ifmo.rain.dimitrov.crawler;
//
//import info.kgeorgiy.java.advanced.crawler.*;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class WebCrawler implements Crawler {
//    private static final int DEFAULT_ARG_VALUE = 1;
//
//    private final Downloader downloader;
//    private final ExecutorService extractorsPool;
//    private final ExecutorService downloadersPool;
//    private final ConcurrentMap<String, WebCrawler.HostDownloader> hostMapper;
//    private final int perHost;
//
//    public WebCrawler(Downloader downloader, final int downloaders, final int extractors, final int perHost) {
//        this.downloader = downloader;
//        extractorsPool = Executors.newFixedThreadPool(extractors);
//        downloadersPool = Executors.newFixedThreadPool(downloaders);
//        hostMapper = new ConcurrentHashMap<>();
//        this.perHost = perHost;
//    }
//
//    @Override
//    public Result download(final String url, final int depth) {
//        return new WebCrawler.RecurrentDownloader(url, depth).getResult();
//    }
//
//    @Override
//    public void close() {
//        extractorsPool.shutdown();
//        downloadersPool.shutdown();
//    }
//
//    private static int[] updateArgs(String[] args) {
//        int[] updatedArgs = new int[4];
//        for (int i = 1; i <= 4; ++i) {
//            updatedArgs[i - 1] = (i >= args.length ? DEFAULT_ARG_VALUE : Integer.parseInt(args[i]));
//        }
//        return updatedArgs;
//    }
//
//    private class RecurrentDownloader {
//
//        private final Set<String> success = ConcurrentHashMap.newKeySet();
//        private final ConcurrentMap<String, IOException> failed = new ConcurrentHashMap<>();
//        private final Set<String> extracted = ConcurrentHashMap.newKeySet();
//
//        private final ConcurrentLinkedQueue<String> awaits = new ConcurrentLinkedQueue<>();
//
//        RecurrentDownloader(String url, int depth) {
//            awaits.add(url);
//
//            final Phaser phaser = new Phaser(1);
//            for (; depth > 0; --depth) {
//                List<String> processing = new ArrayList<>(awaits);
//                awaits.clear();
//
//                int finalDepth = depth;
//                boolean lastDepth = finalDepth > 1;
//                processing.stream()
//                        .filter(extracted::add)
//                        .forEach(currentUrl -> downloadQueue(currentUrl, lastDepth, phaser));
//
//                phaser.arriveAndAwaitAdvance();
//            }
//        }
//
//        private void extractQueue(final Document page, final Phaser phaser) {
//            phaser.register();
//
//            extractorsPool.submit(() -> {
//                List<String> links = null;
//
//                try {
//                    links = page.extractLinks();
//                } catch (IOException e) {
//                    // ignore
//                } finally {
//                    awaits.addAll(links);
//                    phaser.arriveAndDeregister();
//                }
//            });
//        }
//
//        private void downloadQueue(final String link, final boolean isLastDepth, final Phaser phaser) {
//            String host;
//            try {
//                host = URLUtils.getHost(link);
//            } catch (MalformedURLException e) {
//                failed.put(link, e);
//                return;
//            }
//
//            WebCrawler.HostDownloader hostDownloader = hostMapper.computeIfAbsent(host, s -> new WebCrawler.HostDownloader());
//
//            phaser.register();
//            hostDownloader.addTask(() -> {
//                try {
//                    Document page = downloader.download(link);
//
//                    success.add(link);
//                    if (isLastDepth) {
//                        extractQueue(page, phaser);
//                    }
//                } catch (IOException e) {
//                    failed.put(link, e);
//                } finally {
//                    phaser.arriveAndDeregister();
//                }
//            });
//        }
//
//        Result getResult() {
//            return new Result(new ArrayList<>(success), failed);
//        }
//    }
//
//    private class HostDownloader {
//        private final Queue<Runnable> awaitQueue;
//        private final AtomicInteger runTaskCount;
//
//        HostDownloader() {
//            awaitQueue = new ArrayDeque<>();
//            runTaskCount = new AtomicInteger(0);
//        }
//
//        synchronized void addTask(Runnable task) {
//            awaitQueue.add(task);
//            runSafely(false);
//        }
//
//        synchronized private void runSafely(boolean finished) {
//            if (finished) {
//                runTaskCount.decrementAndGet();
//            }
//
//            if (runTaskCount.get() < perHost) {
//                runNextTask();
//            }
//        }
//
//        synchronized private void runNextTask() {
//            Runnable task = awaitQueue.poll();
//
//            if (task != null) {
//                runTaskCount.incrementAndGet();
//
//                downloadersPool.submit(() -> {
//                    task.run();
//                    runSafely(true);
//                });
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        if (args.length == 0) {
//            System.err.println("Zero arguments are not allowed.\n" +
//                    "Usage: WebCrawler url [depth [downloaders [extractors [perHost]]]]");
//        } else {
//            int[] updatedArgs = updateArgs(args);
//            int depth = updatedArgs[0];
//            int downloaders = updatedArgs[1];
//            int extractors = updatedArgs[2];
//            int streamHostCount = updatedArgs[3];
//
//            try (Crawler crawler = new WebCrawler(new CachingDownloader(), downloaders, extractors, streamHostCount)) {
//                crawler.download(args[0], depth);
//            } catch (IOException e) {
//                System.err.println("Error in creating new Downloader.\n" + e.getMessage());
//            }
//        }
//    }
//}
