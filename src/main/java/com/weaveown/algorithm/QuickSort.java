package com.weaveown.algorithm;

/**
 * @author wangwei
 * @date 2020/7/2
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 7, 53, 12, 6, 23, 61, 832, 2, 51, 2};
        sort(numbers, 0, numbers.length - 1);
        for (int number : numbers) {
            System.out.print(number);
            System.out.print(" ");
        }
    }

    public static void sort(int[] numbers, int left, int right) {
        if (right <= left) {
            return;
        }

        int point = partition(numbers, left, right);
        sort(numbers, left, point - 1);
        sort(numbers, point + 1, right);
    }

    private static int partition(int[] numbers, int left, int right) {
        int pivot = numbers[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (numbers[j] < pivot) {
                swap(numbers, i, j);
                i++;
            }
        }
        swap(numbers, right, i);

        return i;
    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
