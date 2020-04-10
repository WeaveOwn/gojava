package com.weaveown;

import com.google.common.io.Files;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Base64;

/**
 * @author WeaveOwn
 */
public class DailyPractice {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("/Users/wangwei/Desktop/nio.txt");
        FileChannel channel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        CharBuffer charBuffer = CharBuffer.allocate(3);
        //Java.nio.charset.Charset处理了字符转换问题。它通过构造CharsetEncoder和CharsetDecoder将字符序列转换成字节和逆转换。
        Charset charset = Charset.forName("UTF-8");

        CharsetDecoder decoder = charset.newDecoder();
        int n;
        while (true) {
            n = channel.read(byteBuffer);
            if (n == -1) {
                break;
            }
            byteBuffer.flip();
            decoder.decode(byteBuffer, charBuffer, false);
            charBuffer.flip();
            while (charBuffer.hasRemaining()) {
                System.out.print(charBuffer.get());
            }
            byteBuffer.clear();
            charBuffer.clear();
        }
        inputStream.close();
    }
}
