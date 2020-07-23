package com.weaveown.algorithm;

/**
 * @author wangwei
 * @date 2020/7/16
 */
public class Find {

    public int find(String[] strings, String key) {
        int length = strings.length;
        int i = 0;
        while (i < length) {
            if (strings[i].equals(key)) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public int findFast(String[] strings, String key) {
        int length = strings.length;

        if (strings[length - 1].equals(key)) {
            return length - 1;
        }
        String tmp = strings[length - 1];
        strings[length - 1] = key;
        int i = 0;
        while (!strings[i].equals(key)) {
            ++i;
        }

        if (i == length - 1) {
            return -1;
        }

        return i;
    }

    public static void main(String[] args) {

    }
}
