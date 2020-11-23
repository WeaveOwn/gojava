package com.weaveown.algorithm;

/**
 * @author wangwei
 * @date 2020/10/10
 */
public class CountSort {

    public static void main(String[] args) {
        int[] a = new int[]{5, 4, 3, 2, 1};
        int[] b = new int[]{0,1, 2, 3, 4, 5, 5};
        int[] c = new int[5];
        for (int i = 5 - 1; i >= 0; i--) {
            int index = b[a[i]] - 1;
            c[index] = a[i];
            b[a[i]]--;
        }
        for (int i = 0; i < 5; i++) {
            a[i] = c[i];
        }
        for (int i : a) {
            System.out.printf("%d ", i);
        }
    }
}
