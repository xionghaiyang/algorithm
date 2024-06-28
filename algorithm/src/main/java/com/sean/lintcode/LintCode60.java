package com.sean.lintcode;

public class LintCode60 {

    public static int searchInsert(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

}
