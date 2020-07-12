package ru.ifmo.rain.dimitrov.hello;

public class ChannelProviderClient {
    private final int threadIdx;
    private int requests;

    ChannelProviderClient(int threadIdx) {
        this.requests = 0;
        this.threadIdx = threadIdx;
    }

    int getThreadIdx() {
        return threadIdx;
    }

    synchronized int getRequests() {
        return requests;
    }

    synchronized void incRequests() {
        requests++;
    }
}
