package com.example.netty.nio.udp;

import com.example.netty.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/9
 */
public class UdpServer {

    public static void main(String[] args) {
        try (DatagramChannel channel = DatagramChannel.open()) {
            channel.socket().bind(new InetSocketAddress(9090));
            System.out.println("waiting...");
            ByteBuffer buffer = ByteBuffer.allocate(16);
            channel.receive(buffer);
            buffer.flip();
            ByteBufferUtil.debugAll(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
