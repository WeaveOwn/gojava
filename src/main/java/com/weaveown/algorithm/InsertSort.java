package com.weaveown.algorithm;

/**
 * @author wangwei
 * @date 2020/8/29
 */
public class InsertSort {
    public void sort(int[] nums) {
       int length = nums.length;
        for (int i = 1; i < length; i++) {
            int value = nums[i];
            int j = i - 1;
            for (; j >=0; j--) {
                if (nums[j] > value) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }

            }
            nums[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {4,5, 3,2,1};
        new InsertSort().sort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
 }
