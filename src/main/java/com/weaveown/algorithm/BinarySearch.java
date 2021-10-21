package com.weaveown.algorithm;

/**
 * @ClassName : BinarySearch
 * @Description : 二分查找
 * @Author : WillVi
 * @Date : 2019/3/26 11:52
 * @Version : 1.0
 */
public class BinarySearch {

    public int search(int[] items, int item) {
        int low = 0;
        int high = items.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (items[mid] == item) {
                return mid;
            } else if (items[mid] > item) {
                high = mid - 1;
            } else {
                // search the first on the left
                // if( mid == 0 || (mid - 1) != item) return mid;
                // search the end on the right
                // if( mid == items.length -1 || (mid + 1) != item) return mid;
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int item = 0;
        int[] items = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int search = new BinarySearch().search(items, item);
        int search1 = new BinarySearch().search(items, 0, items.length - 1, item);
        System.out.println(search);
        System.out.println(search1);
    }

    public int search(int[] items, int low, int high, int item) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;
        if (items[mid] == item) {
            return mid;
        } else if ((items[mid] > item)) {
            return search(items, low, mid - 1, item);
        } else {
            return search(items, mid + 1, high, item);
        }
    }

}
