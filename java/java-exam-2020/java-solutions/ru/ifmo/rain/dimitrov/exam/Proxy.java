package ru.ifmo.rain.dimitrov.exam;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Proxy {
    private static final int TIMEOUT = 300;
    private static final int THREAD_COUNT = 10;
    private static final int BUFFER_SIZE = 1024;
    private final List<Thread> threads;
    private boolean started = false;


    public Proxy(final List<RedirectInfo> redirectInfo) {
        threads = newThreads(redirectInfo);
    }

    public synchronized void start() {
        if (started) {
            throw new IllegalStateException("Proxy already started");
        }
        started = true;
        // :NOTE: - Управление потоками "вручную"
        for (final Thread thread : threads) {

            thread.start();
        }
    }

    public synchronized void close() {
        for (final Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
                // ignored
            }
        }
    }


    private List<Thread> newThreads(final List<RedirectInfo> redirectInfo) {
        final int socketsPerThread = redirectInfo.size() / THREAD_COUNT;
        final int socketsRemainder = redirectInfo.size() % THREAD_COUNT;

        final List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int left = (i > socketsRemainder ? socketsPerThread * i + socketsRemainder : socketsPerThread * i + i);
            final int right = left + socketsPerThread + ((socketsRemainder - i) > 0 ? 1 : 0);
            threads.add(newThread(left, right, redirectInfo));
        }
        return threads;
    }

    private Thread newThread(final int left, final int right, final List<RedirectInfo> redirectInfo) {
        return new Thread(() -> {
            final List<ServerSocket> localSockets = new ArrayList<>();
            for (int i = left; i < right; i++) {
                final RedirectInfo info = redirectInfo.get(i);
                ServerSocket localSocket;
                try {
                    localSocket = new ServerSocket(info.getLocalPort());
                    localSocket.setSoTimeout(TIMEOUT);
                    localSockets.add(localSocket);
                } catch (IOException ignored) {
                } finally {
                    // :NOTE: - Открыли и тут же закрыли
                    closeLocalSockets(localSockets);
                }
            }
            while (!Thread.currentThread().isInterrupted()) {
                // :NOTE: # Порты слушаюся по-очереди, нет возможности открыть несколько соединений на один порт
                for (int i = 0; i < localSockets.size(); i++) {
                    processLocalSocket(localSockets.get(i), redirectInfo.get(i));
                }
            }
            closeLocalSockets(localSockets);
        }
        );
    }

    private void closeLocalSockets(List<ServerSocket> localSockets) {
        for (final ServerSocket localSocket : localSockets) {
            try {
                localSocket.close();
            } catch (IOException ignored) {
                //
            }
        }
    }

    // :NOTE: # Весь код блокирующий
    // :NOTE: # Передача данных осуществляется по-очереди, это не работает для интерактивных протоколов
    private void processLocalSocket(final ServerSocket localSocket, RedirectInfo info) {
        Socket client;
        try {

            client = localSocket.accept();
        } catch (IOException ignored) {
            return;
        }
        try (final Socket socket = new Socket(info.getRemoteHost(), info.getRemotePort());
             final InputStream fromClient = client.getInputStream();
             final InputStream fromServer = socket.getInputStream();
             final OutputStream toServer = socket.getOutputStream();
             final OutputStream toClient = client.getOutputStream()) {
            final byte[] buffer = new byte[BUFFER_SIZE];
            int byteCount;
            while ((byteCount = fromClient.read(buffer)) != -1) {
                toServer.write(buffer, 0, byteCount);
            }
            while ((byteCount = fromServer.read(buffer)) != -1) {
                toClient.write(buffer, 0, byteCount);
            }
        } catch (IOException ignored) {
        }
    }
}
