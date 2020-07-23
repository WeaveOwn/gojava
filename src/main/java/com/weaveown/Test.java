package com.weaveown;

import lombok.Data;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * @author wangwei
 * @date 2020/5/28
 */
public class Test {


    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        if (addresses != null) {
                            while (addresses.hasMoreElements()) {
                                try {
                                    InetAddress address = addresses.nextElement();
                                    if (isValidAddress(address)) {
                                        System.out.println(address);
                                    }
                                } catch (Throwable e) {
                                }
                            }
                        }
                    } catch (Throwable e) {
                    }
                }
            }
        } catch (Throwable e) {
        }
        System.out.println("");
    }
    public static final String LOCALHOST = "127.0.0.1";

    public static final String ANYHOST = "0.0.0.0";

    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress())
            return false;
        String name = address.getHostAddress();
        return (name != null
                && ! ANYHOST.equals(name)
                && ! LOCALHOST.equals(name)
                && IP_PATTERN.matcher(name).matches());
    }
    transient private Node head;
    transient private Node tail;

    public void add(int value) {
        if (head == null) {
            head = new Node(value);
            tail = head;
        } else {
            Node next = new Node(value);
            tail.next = next;
            tail = next;
        }
    }

    public void add(int index, int value) {
        if (head == null) {
            head = new Node(value);
            tail = head;
        } else {
            Node next = new Node(value);
            tail.next = next;
            tail = next;
        }
    }

    public void remove(int value){
        Node tmp = head;
        Node pre = head;
        while (tmp != null) {
            if (tmp.value == value) {
                pre.next = tmp.next;
                tmp = null;
                break;
            }

            pre = tmp;
            tmp = tmp.next;
        }
    }

    public void print() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }

    public void addAll(int[] values) {
        for (int value : values) {
            add(value);
        }
    }

    static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
