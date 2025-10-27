package com.weaveown.algorithm;

/**
 * @author wangmmmnnb b>pwei
 * @date 2020/8/29
 */
public class MergeSort {

    public static void main(String[] args) {
        final int[] ints = {1, 5, 2, 6, 8100, 23, 6, 2, 7, 23, 2, 6, 57, 123, 61, 253, 6, 3, 13, 46, 4, 6, 1, 3, 4, 4, 1, 3, 54, 6, 1, 5, 3, 4534, 5, 31, 3, 4, 5};
        sort(ints, 0, ints.length - 1);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
            System.out.print(" ");
        }
    }

    private static void sort(int[] numbers, int left, int right) {
        if (right <= left) {
            return;

        }

        int mid = (right - left) / 2 + left;
        sort(numbers, left, mid);
        sort(numbers, mid + 1, right);
        merge(numbers, left, right, mid);
    }

    private static void merge(int[] numbers, int left, int right, int mid) {
        int[] temps = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (numbers[i] <= numbers[j]) {
                temps[k++] = numbers[i++];
            } else {
                temps[k++] = numbers[j++];
            }
        }

        int start = i;
        int end = mid;
        if (j <= right) {
            start = j;
            end = right;
        }

        while (start <= end) {
            temps[k++] = numbers[start++];
        }

        for (int t = 0; t < temps.length; t++) {
            numbers[left + t] = temps[t];
        }
    }


}