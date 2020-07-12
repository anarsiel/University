package ru.ifmo.rain.dimitrov.hello;

import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;

class MessageProvider {

    public static String createMessage(final String prefix, final int threadId, final int requestId) {
        return prefix + threadId + "_" + requestId;
    }

    public static void setMessage(final DatagramPacket packet, final String message) {
        packet.setData(message.getBytes(StandardCharsets.UTF_8));
    }

    public static String getMessage(final DatagramPacket packet) {
        return new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
    }
}
