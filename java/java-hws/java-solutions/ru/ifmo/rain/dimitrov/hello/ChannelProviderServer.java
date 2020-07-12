package ru.ifmo.rain.dimitrov.hello;

import java.net.SocketAddress;

public class ChannelProviderServer {
    private SocketAddress socketAddress;
    private String message;

    synchronized void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }


    synchronized SocketAddress getSocketAddress() {
        return socketAddress;
    }

    synchronized void setMessage(String message) {
        this.message = message;
    }

    synchronized String getMessage() {
        return message;
    }
}
