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
            System.out.println(ints[i]);
        }
    }

    public static void sort(int[] numbers, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        sort(numbers, left, mid);
        sort(numbers, mid + 1, right);
        merge(numbers, left, mid, right);
    }

    private static void merge(int[] numbers, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int t = 0;
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (numbers[i] < numbers[j]) {
                temp[t++] = numbers[i++];
            } else {
                temp[t++] = numbers[j++];
            }
        }

        int start = i;
        int end = mid;
        if (j <= right) {
            start = j;
            end = right;
        }

        while (start <= end) {
            temp[t++] = numbers[start++];
        }

        for (int k = 0; k <= right - left; k++) {
            numbers[left + k] = temp[k];
        }
    }


}