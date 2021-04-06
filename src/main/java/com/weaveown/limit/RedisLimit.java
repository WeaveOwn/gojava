package com.weaveown.limit;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author wangwei
 * @date 2021/4/1
 */
public class RedisLimit {

    public static void main(String[] args) throws IOException {
        String lua = Files.asCharSource(new File("limit.lua"), Charset.defaultCharset()).read();
        System.out.println(lua);
    }
}
