package com.weaveown.io.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author WeaveOwn
 */
public class ClientSocketNioDemo {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello world".getBytes());
        outputStream.write("WeaveOwn".getBytes());
        outputStream.close();
    }
}
