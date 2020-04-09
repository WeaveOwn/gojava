package com.weaveown.io;

import org.apache.log4j.net.SocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author WeaveOwn
 */
public class ServerSocketNioDemo {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket socket = socketChannel.socket();
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8888);
        socket.bind(socketAddress);
        while (true) {
            System.out.println("selector.select() start");
            selector.select();
            System.out.println("selector.select() end");
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            if (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                    System.out.println(" channel.register(selector, SelectionKey.OP_READ);");
                } else if (selectionKey.isReadable()) {
                    SocketChannel sChannel = (SocketChannel) selectionKey.channel();
                    System.out.println(readDataFromSocketChannel(sChannel));
                    sChannel.close();
                }
                iterator.remove();
            }
        }
    }

    private static String readDataFromSocketChannel(SocketChannel sChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int n = sChannel.read(byteBuffer);
            if (n == -1) {
                break;
            }
            byteBuffer.flip();
            int limit = byteBuffer.limit();
            char[] chars = new char[limit];
            for (int i = 0; i < limit; i++) {
                chars[i] = (char) byteBuffer.get(i);
            }
            stringBuilder.append(chars);
            byteBuffer.clear();
        }
        return stringBuilder.toString();
    }
}
