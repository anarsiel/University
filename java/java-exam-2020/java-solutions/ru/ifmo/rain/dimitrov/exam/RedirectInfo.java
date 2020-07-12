package ru.ifmo.rain.dimitrov.exam;

public class RedirectInfo {
    int localPort;
    String remoteHost;
    int remotePort;

    public RedirectInfo(int localPort, String remoteHost, int remotePort) {
        this.localPort = localPort;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    public int getLocalPort() {
        return localPort;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public String getRemoteHost() {
        return remoteHost;
    }
}