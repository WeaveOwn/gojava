package com.weaveown.algorithm;

/**
 * @author wangwei
 * @date 2020/7/2
 */
public class QuickSort {
    public static void main(String[] args) {
        final int[] ints = {2, 5, 1, 5, 6, 7, 2, 8, 3};
        sort(ints, 0, ints.length - 1);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    public static void sort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int point = partition(nums, left, right);
        sort(nums, left, point - 1);
        sort(nums, point + 1, right);

    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        for (int j = left; j <= right - 1; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }

        swap(nums, right, i);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
